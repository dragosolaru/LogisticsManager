package com.example.android.logisticsmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dragos Andrei Olaru on 01.02.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE = "CARS";
    public static final String _ID = "_id";
    public static final String NR_INM = "nr_inmatriculare";
    public static final String MARCA = "marca";
    public static final String TIP = "tip_auto";
    public static final String DATA = "data_inmatriculare";
    public static final String SOFER = "sofer";
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "crud.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        String CREATE_TABLE_CARS = "CREATE TABLE " + TABLE + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NR_INM + " TEXT NOT NULL, "
                + MARCA + " TEXT NOT NULL, "
                + TIP + " TEXT NOT NULL, "
                + DATA + " TEXT NOT NULL, "
                + SOFER + " TEXT);";
        db.execSQL(CREATE_TABLE_CARS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        // Create tables again
        onCreate(db);

    }

}
