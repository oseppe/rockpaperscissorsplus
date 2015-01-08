package module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by j on 1/8/15.
 */
public class Record {

    private static final String DATABASE_NAME = "rpsplusDb.db";
    private static final String RECORD_TABLE = "record";

    // rows
    private static final String COL_ID = "_id";
    private static final String COL_OPPONENT = "Opponent";
    private static final String COL_RESULT = "Result";
    private static final String COL_DATE = "Date";

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
        String opponent = "HAL 9000";
        String resultText = "";

        if (mode.equals("player")) {
            opponent = "Player";
        }

        switch(result) {
            case 0:
                resultText = "Draw";
                break;

            case 1:
                resultText = "Won";
                break;

            case 2:
                resultText = "Lost";
                break;
        }

        // create map of values to insert in db
        ContentValues entry = new ContentValues();
        entry.put(DatabaseHelper.COL_OPPONENT, opponent);
        entry.put(DatabaseHelper.COL_RESULT, result);
        entry.put(DatabaseHelper.COL_RESULT_TEXT, resultText);

        // returns an id
        database.insert(DatabaseHelper.RECORD_TABLE, "Date", entry);
    }
}
