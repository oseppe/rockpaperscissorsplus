package com.example.j.rockpaperscissorsplus;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import module.GameEngine;
import module.ShakeListener;
import module.Weapon;


public class PickActivity extends ActionBarActivity {

    protected String mode;
    protected String playerOneWeapon;
    protected String playerTwoWeapon;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeListener mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        Spinner spinner = (Spinner) findViewById(R.id.weaponSpinner);
        // Create an ArrayAdapter and populate it with items from weapons_array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.weapons_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        this.mode = getIntent().getExtras().getString("com.example.j.rockpaperscissorsplus.EXTRA_MODE");

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeListener();
        mShakeDetector.setOnShakeListener(new ShakeListener.OnShakeListener() {

            @Override
            public void onShake(int count) {
                /*
                 * The following method, "handleShakeEvent(count):" is a stub //
                 * method you would use to setup whatever you want done once the
                 * device has been shook.
                 */
                armPlayer();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,    SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    public void armPlayer() {
        Spinner spinner = (Spinner)findViewById(R.id.weaponSpinner);
        String weapon = spinner.getSelectedItem().toString();


        if (mode.equals("player")) {
            playerOneWeapon = weapon;
            mode = "player2";

            // change text view to indicate player 2's turn
            TextView t= new TextView(this);

            t = (TextView)findViewById(R.id.pickText);
            t.setText(getString(R.string.pick_p2));

            // set spinner selection back to default value
            spinner.setSelection(0);

            return;
        }

        if (mode.equals("player2")) {
            playerTwoWeapon = weapon;

            // change mode back to "player" for use in next activity
            mode = "player";

        } else {
            playerOneWeapon = weapon;
            playerTwoWeapon = Weapon.random();
        }

        fight(playerOneWeapon, playerTwoWeapon);
    }

    public void fight(String playerOne, String playerTwo) {
        GameEngine engine = new GameEngine();

        int winner = engine.fight(playerOne, playerTwo);

        goToResult(mode,winner, playerOne, playerTwo );
    }

    public void goToResult(String mode, int winner, String playerOne, String playerTwo) {
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("com.example.j.rockpaperscissorsplus.EXTRA_WINNER", winner);
        intent.putExtra("com.example.j.rockpaperscissorsplus.EXTRA_MODE", mode);
        intent.putExtra("com.example.j.rockpaperscissorsplus.EXTRA_PLYRONE", playerOne);
        intent.putExtra("com.example.j.rockpaperscissorsplus.EXTRA_PLYRTWO", playerTwo);
        startActivity(intent);
    }
}
