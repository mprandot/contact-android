package persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by marcio on 04/06/17.
 */
public class ConnectionSqlLite extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "contacts_db";
    private static int VERSION = 1;

    private static String CREATE_CONTACTS_TABLE = "create table contacts (" +
            "id integer primary key autoincrement, " +
            "name text, " +
            "address text, " +
            "city text, " +
            "phone1 text, " +
            "phone2 text) ";

    public ConnectionSqlLite(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.d(getClass().getSimpleName(), "Database created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE contacts;");
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
}
