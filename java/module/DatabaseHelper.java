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

    public static final String RECORD_TABLE = "record";

    // rows
    public static final String COL_ID = "_id";
    public static final String COL_OPPONENT = "Opponent";
    public static final String COL_RESULT = "Result";
    public static final String COL_DATE = "Date";
    public static final String COL_RESULT_TEXT = "ResultText";

    // database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + RECORD_TABLE
            + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_OPPONENT
            + " TEXT, " + COL_RESULT + " INTEGER, " + COL_RESULT_TEXT + " TEXT, "
            + COL_DATE + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
