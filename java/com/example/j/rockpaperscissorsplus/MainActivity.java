package com.example.j.rockpaperscissorsplus;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCom = (Button) findViewById(R.id.btnVerCom);
        Button btnPla = (Button) findViewById(R.id.btnVerPla);
        Button btnRec = (Button) findViewById(R.id.btnRecord);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnVerCom:
                        goToPick("com");
                        break;

                    case R.id.btnVerPla:
                        goToPick("player");
                        break;

                    case R.id.btnRecord:
                        goToRecord();
                        break;
                }
            }
        };

        btnCom.setOnClickListener(listener);
        btnPla.setOnClickListener(listener);
        btnRec.setOnClickListener(listener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void goToPick(String mode) {
        Intent intent = new Intent(this, PickActivity.class);
        intent.putExtra("com.example.j.rockpaperscissorsplus.EXTRA_MODE", mode);
        startActivity(intent);
    }

    public void goToRecord() {
        Intent intent = new Intent(this, RecordActivity.class);
        startActivity(intent);
    }
}
