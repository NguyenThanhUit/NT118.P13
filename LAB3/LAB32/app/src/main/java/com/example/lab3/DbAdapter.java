package com.example.lab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbAdapter {
    public static final String KEY_MSSV = "MSSV";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_NGAYSINH = "NGAYSINH";
    public static final String KEY_PHONENUMBER = "NUMBER";

    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static final String DATABASE_NAME = "Database_LAB3";
    private static final String DATABASE_TABLE = "Students";
    private static final int DATABASE_VERSION = 2;
    private final Context context;

    public DbAdapter(Context context) {
        this.context = context;
    }

    public DbAdapter open() {
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }
    public long createUser(String NAME, String MSSV, String NGAYSINH, String PHONENUMBER) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, NAME);
        initialValues.put(KEY_MSSV, MSSV);
        initialValues.put(KEY_PHONENUMBER, PHONENUMBER);
        initialValues.put(KEY_NGAYSINH, NGAYSINH);
        return sqLiteDatabase.insert(DATABASE_TABLE, null, initialValues);
    }
    public boolean deleteUser(String mssv) {
        return sqLiteDatabase.delete(DATABASE_TABLE, KEY_MSSV + "=" + mssv,
                null) > 0;
    }
    public boolean deleteAllUsers() {
        return sqLiteDatabase.delete(DATABASE_TABLE, null, null) > 0;
    }
    public Cursor getAllUsers(String mssv) {
        return sqLiteDatabase.query(DATABASE_TABLE, new String[]{KEY_MSSV,
                KEY_NAME, KEY_NGAYSINH, KEY_PHONENUMBER}, null, null, null, null, null);
    }
    public Cursor getUserByMSSV(String mssv) {
        return sqLiteDatabase.query(DATABASE_TABLE, new String[]{KEY_MSSV,
                KEY_NAME, KEY_NGAYSINH, KEY_PHONENUMBER}, KEY_MSSV + "=?", new String[]{mssv}, null, null, null);
    }

    public boolean updateUser(String mssv, String name, String phone, String dateOfBirth) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PHONENUMBER, phone);
        values.put(KEY_NGAYSINH, dateOfBirth);

        return sqLiteDatabase.update(DATABASE_TABLE, values, KEY_MSSV + "=?", new String[]{mssv}) > 0;
    }

}

