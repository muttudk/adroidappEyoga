package com.ntpl.eyoga.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHandler extends SQLiteOpenHelper {

    public static final String TAG = SQLiteHandler.class.getSimpleName();
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "e_yoga";
    public static final String TABLE_USERS = "users";

    public static final String _ID = "u_id";
    public static final String KEY_FULL_NAME = "full_name";
    public static final String KEY_EMAIL_ID = "email_id";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_AGE = "age";
    public static final String KEY_GENDER = "gender";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EXAM_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FULL_NAME + " TEXT," + KEY_EMAIL_ID + " TEXT,"
                + KEY_PASSWORD + " TEXT," + KEY_AGE + " TEXT," + KEY_GENDER + " TEXT" + ")";

        db.execSQL(CREATE_EXAM_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    public void insertUser(String fullName, String emailID, String password, String age, String gender){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FULL_NAME, fullName);
        values.put(KEY_EMAIL_ID, emailID);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_AGE, age);
        values.put(KEY_GENDER, gender);

        // Inserting Row
        long id = db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);

    }

    public boolean checkUser(String fullName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String count = "SELECT " + "* "+"from " + TABLE_USERS + " where "+KEY_FULL_NAME  + " = '" + fullName +"'" + " AND "+ KEY_PASSWORD + " = '" + password +"'" ;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        boolean check = false;
        if (icount > 0) {
            System.out.println("HAve Data********" + icount);
            check = true;
        }
//leave
        else {
            System.out.println("No Data**********" + icount);
            check = false;
        }
        db.close();
//populate table
        return check;
    }

    public boolean checkUserExist(String username, String password){
        String[] columns = {"full_name"};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = "full_name=? and password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUserExist(String username){
        String[] columns = {"full_name"};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = "full_name=?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkEmailExist(String emailID){
        String[] columns = {"email_id"};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = "email_id=?";
        String[] selectionArgs = {emailID};

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
    }

    public long updatePassword(String emailID, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PASSWORD, password);

        SQLiteDatabase db = this.getWritableDatabase();
        long val = db.update( TABLE_USERS, contentValues,
                KEY_EMAIL_ID + " = '" + emailID +"'", null);
        db.close();
        return val;
    }

    public String getPassword(String fullName){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur = db.rawQuery("Select * from " + TABLE_USERS + " where " + KEY_FULL_NAME + " = '" + fullName + "'", null);
        String password = null;
        while (cur.moveToNext()) {
            password = cur.getString(cur.getColumnIndex(KEY_PASSWORD));
        }

        cur.close();
        db.close();
        return password;
    }

    public String getEmailId(String emailID){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur = db.rawQuery("Select * from " + TABLE_USERS + " where " + KEY_EMAIL_ID + " = '" + emailID + "'", null);
        String password = null;
        while (cur.moveToNext()) {
            password = cur.getString(cur.getColumnIndex(KEY_EMAIL_ID));
        }

        cur.close();
        db.close();
        return password;
    }
}
