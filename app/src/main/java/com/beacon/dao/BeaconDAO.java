package com.beacon.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.beacon.util.SQLLiteHelper;

/**
 * Created by saravanan on 13-Oct-15.
 */
public class BeaconDAO {
    // Database fields
    private SQLiteDatabase database;
    private SQLLiteHelper dbHelper;

    public BeaconDAO(Context context) {
        dbHelper = new SQLLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createUser(String accountNumber) {
        ContentValues values = new ContentValues();
        values.put(SQLLiteHelper.COLUMN_ID, 1);
        values.put(SQLLiteHelper.COLUMN_ACCOUNT_NUMBER, accountNumber);
        database.insert(SQLLiteHelper.TABLE_USER, null,
                values);
    }

    public String getUser() {
        String retrievedAccountNumber = null;
        Cursor cursor = database.query(SQLLiteHelper.TABLE_USER,
                SQLLiteHelper.allColumns, SQLLiteHelper.COLUMN_ID + " = 1 " , null,
                null, null, null);

        cursor.moveToFirst();
        if(!cursor.isAfterLast()) {
            retrievedAccountNumber = cursor.getString(1);
        }
        cursor.close();
        return retrievedAccountNumber;
    }

    public void deleteUser() {
        database.delete(SQLLiteHelper.TABLE_USER, SQLLiteHelper.COLUMN_ID + " = 1 ", null);
    }
}
