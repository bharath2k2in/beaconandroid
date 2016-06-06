package com.beacon.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by saravanan on 13-Oct-15.
 */
public class SQLLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ACCOUNT_NUMBER = "accountnumber";

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    public static String[] allColumns = { SQLLiteHelper.COLUMN_ID,
            SQLLiteHelper.COLUMN_ACCOUNT_NUMBER };

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_USER + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_ACCOUNT_NUMBER
            + " text not null);";

    public SQLLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
