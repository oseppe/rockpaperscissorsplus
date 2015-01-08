package module;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

/**
 * Created by j on 1/6/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rpsplusDb.db";
    private static final String RECORD_TABLE = "record";

    // rows
    private static final String COL_ID = "_id";
    private static final String COL_OPPONENT = "Opponent";
    private static final String COL_RESULT = "Result";
    private static final String COL_DATE = "Date";

    // database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + RECORD_TABLE
            + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_OPPONENT
            + " TEXT, " + COL_RESULT + " INTEGER, " + COL_DATE + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getAllRecords() {
        SQLiteDatabase db=this.getReadableDatabase();

        String[] projection = { COL_ID, COL_OPPONENT, COL_RESULT, COL_DATE };

        Cursor cur = db.query(
            DATABASE_NAME, projection, null, null, null, null, null
        );

        return cur;
    }

    public void saveMatch(String mode, int result) {
        String opponent = "HAL 9000";

        if (mode.equals("player")) {
            opponent = "Player";
        }

        SQLiteDatabase db = this.getWritableDatabase();

        // create map of values to insert in db
        ContentValues entry = new ContentValues();
        entry.put(COL_OPPONENT, opponent);
        entry.put(COL_RESULT, result);

        // returns an id
        db.insert(RECORD_TABLE, "Date", entry);
    }
}
