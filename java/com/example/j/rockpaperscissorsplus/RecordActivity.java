package com.example.j.rockpaperscissorsplus;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import module.Record;


public class RecordActivity extends ActionBarActivity {

    private Record record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        record = new Record(this);
        record.open();

        Cursor cur = record.getAllRecords();

        CursorAdapter adapter = new ResultsCursorAdapter(this, cur);

        ListView listView = (ListView) findViewById(R.id.recordListView);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_record, menu);
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

    public class ResultsCursorAdapter extends CursorAdapter {
        public ResultsCursorAdapter(Context context, Cursor cursor) {
            super(context, cursor, 0);
        }

        // The newView method is used to inflate a new view and return it,
        // you don't bind any data to the view at this point.
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.record_entry, parent, false);
        }

        // The bindView method is used to bind all data to a given view
        // such as setting the text on a TextView.
        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            // Find fields to populate in inflated template
            TextView date = (TextView) view.findViewById(R.id.date_entry);
            TextView result = (TextView) view.findViewById(R.id.result_entry);
            // Extract properties from cursor
            String dateText = cursor.getString(cursor.getColumnIndexOrThrow("Date"));
            String opponentText = cursor.getString(cursor.getColumnIndexOrThrow("Opponent"));
            String resultText = cursor.getString(cursor.getColumnIndexOrThrow("ResultText"));

            String combinedText = resultText + " " + opponentText;

            // Populate fields with extracted properties
            date.setText(dateText);
            result.setText(combinedText);
        }
    }
}
