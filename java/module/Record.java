package module;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.j.rockpaperscissorsplus.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by j on 1/8/15.
 */
public class Record {

    private static final String DRAW_TEXT = "Draw with";
    private static final String WIN_TEXT = "Won against";
    private static final String LOSE_TEXT = "Destroyed by";

    private static final String OPPONENT_COM = "Hal 9000";
    private static final String OPPONENT_PLAYER = "Player 2";

    // Database fields
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;


    public Record(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Cursor getAllRecords() {
        String[] projection = { DatabaseHelper.COL_ID, DatabaseHelper.COL_OPPONENT,
                DatabaseHelper.COL_RESULT, DatabaseHelper.COL_RESULT_TEXT, DatabaseHelper.COL_DATE };

        Cursor cur = database.query(
                DatabaseHelper.RECORD_TABLE, projection, null, null, null, null, null
        );

        return cur;
    }

    public void saveMatch(String mode, int result) {
        String opponent = OPPONENT_COM;
        String resultText = "";

        if (mode.equals("player")) {
            opponent = OPPONENT_PLAYER;
        }

        switch(result) {
            case 0:
                resultText = DRAW_TEXT;
                break;

            case 1:
                resultText = WIN_TEXT;
                break;

            case 2:
                resultText = LOSE_TEXT;
                break;
        }

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy h:m a");
        String date = df.format(c.getTime());

        // create map of values to insert in db
        ContentValues entry = new ContentValues();
        entry.put(DatabaseHelper.COL_OPPONENT, opponent);
        entry.put(DatabaseHelper.COL_RESULT, result);
        entry.put(DatabaseHelper.COL_DATE, date);
        entry.put(DatabaseHelper.COL_RESULT_TEXT, resultText);

        // returns an id
        database.insert(DatabaseHelper.RECORD_TABLE, "Date", entry);
    }
}
