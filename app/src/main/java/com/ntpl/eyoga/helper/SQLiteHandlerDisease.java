package com.ntpl.eyoga.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHandlerDisease extends SQLiteOpenHelper {

    public static final String TAG = SQLiteHandlerDisease.class.getSimpleName();
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "e_yoga_disease";
    public static final String TABLE_DISEASE = "disease";

    public static final String _ID = "d_id";
    public static final String KEY_DISEASE_NAME = "disease_name";
    public static final String KEY_ASANA_NAME = "asana_name";
    public static final String KEY_ASANA_VIDEO = "asana_video";
    public static final String KEY_DIET = "diet";
    public static final String KEY_RULES = "rules";
    public static final String KEY_BENEFITS = "benefits";
    public static final String KEY_SIDE_EFFECTS = "side_effects";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME_SPENT = "time_spent";
    public static final String KEY_YOGA_COUNT = "count";

    public SQLiteHandlerDisease(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EXAM_TABLE = "CREATE TABLE " + TABLE_DISEASE + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DISEASE_NAME + " TEXT," + KEY_ASANA_NAME + " TEXT,"
                + KEY_ASANA_VIDEO + " TEXT," + KEY_DIET + " TEXT,"
                + KEY_RULES + " TEXT," + KEY_BENEFITS + " TEXT,"
                + KEY_SIDE_EFFECTS + " TEXT," + KEY_DATE + " TEXT,"
                + KEY_TIME_SPENT + " TEXT," + KEY_YOGA_COUNT + " TEXT" + ")";

        db.execSQL(CREATE_EXAM_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISEASE);

        // Create tables again
        onCreate(db);
    }

    public void insertDisease(String diseaseName, String asanaName, String asanaVideo, String diet, String rules, String benefits, String sideEffects, String date, String timeSpent, String yogaCount){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DISEASE_NAME, diseaseName);
        values.put(KEY_ASANA_NAME, asanaName);
        values.put(KEY_ASANA_VIDEO, asanaVideo);
        values.put(KEY_DIET, diet);
        values.put(KEY_RULES, rules);
        values.put(KEY_BENEFITS, benefits);
        values.put(KEY_SIDE_EFFECTS, sideEffects);
        values.put(KEY_DATE, date);
        values.put(KEY_TIME_SPENT, timeSpent);
        values.put(KEY_YOGA_COUNT, yogaCount);

        // Inserting Row
        long id = db.insert(TABLE_DISEASE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);

    }

    public Cursor getSingleRow(String diseaseName) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor mCursor = db.query(true, TABLE_DISEASE, new String[]{KEY_ASANA_NAME, KEY_ASANA_VIDEO,
                        KEY_DIET, KEY_RULES, KEY_BENEFITS, KEY_SIDE_EFFECTS}, KEY_DISEASE_NAME + " = '" + diseaseName +"'" , null,
                null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        db.close();
        return mCursor;
    }

    public void deleteData() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_DISEASE, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }


}
