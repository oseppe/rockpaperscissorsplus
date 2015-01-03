package com.example.j.rockpaperscissorsplus;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import module.GameEngine;
import module.Weapon;


public class PickActivity extends ActionBarActivity {

    protected String mode;
    protected String playerOneWeapon;
    protected String playerTwoWeapon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        Spinner spinner = (Spinner) findViewById(R.id.weapon_spinner);
        // Create an ArrayAdapter and populate it with items from weapons_array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.weapons_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        this.mode = getIntent().getExtras().getString("com.example.j.rockpaperscissorsplus.EXTRA_MODE");
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

    public void armPlayer(View view) {
        Spinner spinner = (Spinner)findViewById(R.id.weapon_spinner);
        String weapon = spinner.getSelectedItem().toString();


        if (mode.equals("player")) {
            playerOneWeapon = weapon;
            mode = "player2";

            // change text view to indicate player 2's turn
            TextView t=new TextView(this);

            t=(TextView)findViewById(R.id.pick_title);
            t.setText(getString(R.string.pick_p2));

            return;
        }

        if (mode.equals("player2")) {
            playerTwoWeapon = weapon;
        } else {
            playerOneWeapon = weapon;
            playerTwoWeapon = Weapon.random();
        }

//        String text = "Mode: " + mode + "PlayerOne: " + playerOneWeapon + " Player two: " + playerTwoWeapon;
//
//        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
//        toast.show();

        fight(playerOneWeapon, playerTwoWeapon);
    }

    public void fight(String playerOne, String playerTwo) {
        GameEngine engine = new GameEngine();

        int winner = engine.fight(playerOne, playerTwo);

        String text = "Player One: " + playerOne + " Player Two: " + playerTwo + " Winner: " + winner;

        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();

//        goToResult(mode,winner, playerOne, playerTwo );
    }

//    public void goToResult(String mode, int winner, String playerOne, String playerTwo) {
//        Intent intent = new Intent(this, PickActivity.class);
//        intent.putExtra("com.example.j.rockpaperscissorsplus.EXTRA_WINNER", winner);
//        intent.putExtra("com.example.j.rockpaperscissorsplus.EXTRA_MODE", mode);
//        intent.putExtra("com.example.j.rockpaperscissorsplus.EXTRA_PLYRONE", playerOne);
//        intent.putExtra("com.example.j.rockpaperscissorsplus.EXTRA_PLYRTWO", playerTwo);
//        startActivity(intent);
//    }
}
