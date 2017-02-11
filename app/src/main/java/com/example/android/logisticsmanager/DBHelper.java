package com.example.android.logisticsmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
    public static final String LICENTA = "licenta";
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "crud.db";

    private static final String CREATE_TABLE_CARS = "CREATE TABLE " + TABLE + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NR_INM + " TEXT NOT NULL, "
            + MARCA + " TEXT NOT NULL, "
            + TIP + " TEXT NOT NULL, "
            + DATA + " TEXT NOT NULL, "
            + SOFER + " TEXT NOT NULL, "
            + LICENTA + " TEXT);";

    private static final String DATABASE_ALTER_CARS_1 = "ALTER TABLE "
            + TABLE + " ADD COLUMN " + LICENTA + " string;";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(CREATE_TABLE_CARS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        // Create tables again
        onCreate(db);

    }

    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"mesage"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }


    }

}
