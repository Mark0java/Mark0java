package com.example.android.workoutgenerator.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.workoutgenerator.data.WodButton.WorkoutEntry;


public class WodDbHelper extends SQLiteOpenHelper{

    public final static String LOG_TAG = WodDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "savedworkouts.db";

    private static final int DATABASE_VERSION = 1;

    public WodDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_WODS_TABLE =  "CREATE TABLE " + WorkoutEntry.TABLE_NAME + " ("
                + WorkoutEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WorkoutEntry.COLUMN_WO_DESCRIPTION + " TEXT NOT NULL, "
                + WorkoutEntry.COLUMN_WO_RX + " TEXT DEFAULT NULL, "
                + WorkoutEntry.COLUMN_WO_TYPE + " TEXT, "
                + WorkoutEntry.COLUMN_WO_SAVE + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_WODS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
