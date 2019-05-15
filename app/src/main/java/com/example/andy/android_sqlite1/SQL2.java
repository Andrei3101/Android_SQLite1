package com.example.andy.android_sqlite1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQL2 extends SQLiteOpenHelper {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String AGE = "age";
    static final String NAME_OF_DATABASE = "db1";
    static final String NAME_OF_TABLE = "Clients";
    static final String CREATE_QUERY =
            "CREATE TABLE IF NOT EXISTS " + NAME_OF_TABLE + " (" + ID + " integer primary key autoincrement,"
                    + NAME + " text not null, " + SURNAME + " text not null," + AGE + " integer);";
    static final int VERSION = 1;

    SQL2(Context context) {
        super(context, NAME_OF_DATABASE, null, VERSION);
    }

    public void createTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL(CREATE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveNewRecord(String n, String s, int a) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL(CREATE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.execSQL("INSERT INTO Clients"
                + " (NAME, SURNAME, AGE)"
                + " VALUES ('" + n + "', '" + s + "', '" + a + "')"
        );
    }

    public void saveNewRecord2(String n, String s, int a) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, n);
        contentValues.put(SURNAME, s);
        contentValues.put(AGE, a);

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            long id = db.insert("Clients", null, contentValues);
            Log.i("ID", "" + String.valueOf(id));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Clients", new String[]{});
        return cursor;
    }

    public void deleteClient(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Clients WHERE id =" + id);
    }

    public void dropTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + NAME_OF_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}