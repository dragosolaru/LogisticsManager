package com.example.android.logisticsmanager;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dragos Andrei Olaru on 01.02.2017.
 */

public class EditAuto {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public EditAuto(Context context) {
        dbHelper=new DBHelper(context);
    }

    public void insertAuto(Auto aut) {
        db=dbHelper.getReadableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.NR_INM, aut.getNr_inm());
        contentValue.put(DBHelper.MARCA, aut.getMarca());
        contentValue.put(DBHelper.TIP, aut.getTip());
        contentValue.put(DBHelper.DATA, aut.getData());
        contentValue.put(DBHelper.SOFER, aut.getSofer());
        db.insert(DBHelper.TABLE, null, contentValue);
    }

    public void delete(int _id) {
        db=dbHelper.getWritableDatabase();
        db.delete(DBHelper.TABLE, DBHelper._ID + "=" + _id, null);
    }


    public int updateAutoById(long _id, Auto aut) {
        db=dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.NR_INM, aut.getNr_inm());
        values.put(DBHelper.MARCA, aut.getMarca());
        values.put(DBHelper.TIP, aut.getTip());
        values.put(DBHelper.DATA, aut.getData());
        values.put(DBHelper.SOFER, aut.getSofer());

        int i = db.update(DBHelper.TABLE, values, DBHelper._ID + " = " + _id, null);
        return i;

    }
    public ArrayList<HashMap<String, String>> getCarsList() {
        //Open connection to read only
         db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                DBHelper._ID + "," +
                DBHelper.NR_INM + "," +
                DBHelper.MARCA + "," +
                DBHelper.TIP + "," +
                DBHelper.DATA + "," +
                DBHelper.SOFER +
                " FROM " + DBHelper.TABLE;

       // Auto auto = new Auto();
        ArrayList<HashMap<String, String>> carsList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> car = new HashMap<String, String>();
                car.put("id", cursor.getString(cursor.getColumnIndex(DBHelper._ID)));
                car.put("numar", cursor.getString(cursor.getColumnIndex(DBHelper.NR_INM)));
                car.put("marca", cursor.getString(cursor.getColumnIndex(DBHelper.MARCA)));
                car.put("tip", cursor.getString(cursor.getColumnIndex(DBHelper.TIP)));
                car.put("data", cursor.getString(cursor.getColumnIndex(DBHelper.DATA)));
                car.put("sofer", cursor.getString(cursor.getColumnIndex(DBHelper.SOFER)));

                carsList.add(car);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return carsList;
    }
    public Auto getAutoById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                DBHelper._ID + "," +
                DBHelper.NR_INM + "," +
                DBHelper.MARCA + "," +
                DBHelper.TIP + "," +
                DBHelper.DATA + "," +
                DBHelper.SOFER +
                " FROM " + DBHelper.TABLE
                + " WHERE " +
                DBHelper._ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Auto auto = new Auto();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                auto.setAuto_Id(cursor.getInt(cursor.getColumnIndex(DBHelper._ID)));
                auto.setNr_inm(cursor.getString(cursor.getColumnIndex(DBHelper.NR_INM)));
                auto.setMarca(cursor.getString(cursor.getColumnIndex(DBHelper.MARCA)));
                auto.setTip(cursor.getString(cursor.getColumnIndex(DBHelper.TIP)));
                auto.setData(cursor.getString(cursor.getColumnIndex(DBHelper.DATA)));
                auto.setSofer(cursor.getString(cursor.getColumnIndex(DBHelper.SOFER)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return auto;
    }


}
//    public int insert(Auto auto) {
//        //Open connection to write data
//        db = dbHelper.getWritableDatabase();
//        ContentValues contentValue = new ContentValues();
//        contentValue.put(DBHelper.NR_INM, auto.getNr_inm());
//        contentValue.put(DBHelper.MARCA, auto.getMarca());
//        contentValue.put(DBHelper.TIP, auto.getTip());
//        contentValue.put(DBHelper.DATA, auto.getData());
//        contentValue.put(DBHelper.SOFER, auto.getSofer());
//        // Inserting Row
//        long auto_Id = db.insert(DBHelper.TABLE, null, contentValue);
//        db.close(); // Closing database connection
//        return (int) auto_Id; //cast to int
//    }
//    public void delete(int cars_Id) {
//
//        db = dbHelper.getWritableDatabase();
//        // It's a good practice to use parameter ?, instead of concatenate string
//        db.delete(DBHelper.TABLE, DBHelper._ID + "= ?", new String[] { String.valueOf(cars_Id) });
//        db.close(); // Closing database connection
//    }


//    public void update(Auto auto) {
//
//        db = dbHelper.getWritableDatabase();
//        ContentValues contentValue = new ContentValues();
//
//        contentValue.put(DBHelper.NR_INM, auto.getNr_inm());
//        contentValue.put(DBHelper.MARCA, auto.getMarca());
//        contentValue.put(DBHelper.TIP, auto.getTip());
//        contentValue.put(DBHelper.DATA, auto.getData());
//        contentValue.put(DBHelper.SOFER, auto.getSofer());
//
//        // It's a good practice to use parameter ?, instead of concatenate string
//        db.update(DBHelper.TABLE, contentValue, DBHelper._ID + "= ?", new String[] { String.valueOf(auto.getAuto_Id()) });
//        db.close(); // Closing database connection
//    }