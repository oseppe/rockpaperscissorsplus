package com.example.j.rockpaperscissorsplus;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ResultsActivity extends ActionBarActivity {

    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // retrieve mode
        this.mode = getIntent().getExtras().getString("com.example.j.rockpaperscissorsplus.EXTRA_MODE");

        // retrieve winner
        int winner = getIntent().getExtras().getInt("com.example.j.rockpaperscissorsplus.EXTRA_WINNER");
        // set winner text
        TextView w = (TextView)findViewById(R.id.victoryText);
        w.setText(composeWinnerText(mode, winner));

        // retrieve weapons used
        String playerOne = getIntent().getExtras().getString("com.example.j.rockpaperscissorsplus.EXTRA_PLYRONE");
        String playerTwo = getIntent().getExtras().getString("com.example.j.rockpaperscissorsplus.EXTRA_PLYRTWO");
        // set fight flavour text
        TextView f = (TextView)findViewById(R.id.fightText);
        f.setText(composeFightText(winner, playerOne, playerTwo));

        // create buttons
        Button btnMenu = (Button) findViewById(R.id.btnMenu);
        Button btnRetry = (Button) findViewById(R.id.btnRepeat);

        // create listener
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnMenu:
                        goToMenu();
                        break;

                    case R.id.btnRepeat:
                        goToPick(mode);
                        break;
                }
            }
        };

        // register buttons
        btnMenu.setOnClickListener(listener);
        btnRetry.setOnClickListener(listener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
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

    public String composeFightText(int winner, String playerOne, String playerTwo) {
        String text = null;

        switch (winner) {
            case 0:
                text = playerOne + " and " + playerTwo + " just danced the Macarena";
                break;

            case 1:
                text = playerOne + " eviscerates " + playerTwo;
                break;

            case 2:
                text = playerTwo + " stabs " + playerOne;
                break;
        }

        return text;
    }

    public String composeWinnerText(String mode, int winner) {
        String text = null;

        if (winner == 1) {
            text = getString(R.string.player_one_win);
        } else if (winner == 0) {
            text = getString(R.string.draw);
        } else if (mode.equals("player") && winner == 2) {
            text = getString(R.string.player_two_win);
        } else if (mode.equals("com") && winner == 2) {
            text = getString(R.string.com_win);
        }

        return text;
    }

    public void goToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToPick(String mode) {
        Intent intent = new Intent(this, PickActivity.class);
        intent.putExtra("com.example.j.rockpaperscissorsplus.EXTRA_MODE", mode);
        startActivity(intent);
    }
}
