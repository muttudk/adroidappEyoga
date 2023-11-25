package com.ntpl.eyoga.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class SQLiteHandlerReport extends SQLiteOpenHelper {
    public static final String TAG = SQLiteHandlerReport.class.getSimpleName();
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "e_yoga_report";
    public static final String TABLE_REPORT = "report";

    public static final String _ID = "r_id";
    public static final String KEY_ASANA_NAME = "asana_name";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME_SPENT = "time_spent";
//    public static final String KEY_YOGA_COUNT = "count";

    public SQLiteHandlerReport(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EXAM_TABLE = "CREATE TABLE " + TABLE_REPORT + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ASANA_NAME + " TEXT,"
                + KEY_DATE + " TEXT," + KEY_TIME_SPENT + " TEXT" + ")";

        db.execSQL(CREATE_EXAM_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORT);

        // Create tables again
        onCreate(db);
    }

    public void insertData(String asanaName, String date, String timeSpent){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ASANA_NAME, asanaName);
        values.put(KEY_DATE, date);
        values.put(KEY_TIME_SPENT, timeSpent);

        // Inserting Row
        long id = db.insert(TABLE_REPORT, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);

    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }

    public Cursor getTimeSpentSum() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT SUM(time_spent) FROM "+ TABLE_REPORT;

        Cursor cursor = db.rawQuery(query,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor getMonthTimeSpentSum(String fromDate, String toDate) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();

//        String query = "SELECT SUM(time_spent) FROM "+ TABLE_REPORT + " WHERE strftime('%m', date) = "+ "05"  ;
//        String query = "SELECT SUM(time_spent) FROM "+ TABLE_REPORT + " WHERE date BETWEEN '"+fromDate +"'" + " AND '"+ toDate +"'" ;
        String query = "SELECT SUM(time_spent) FROM "+ TABLE_REPORT + " WHERE date >= '"+fromDate +"'" + " AND date <='"+ toDate +"'" ;
        Log.d("Query", query);
        Cursor cursor = db.rawQuery(query,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

//    select * from mytable where `date` >= '2014-10-09' and `date` <= '2014-10-10'

    public Cursor getDistinctAsana() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT COUNT(DISTINCT asana_name) FROM "+ TABLE_REPORT;

        Cursor cursor = db.rawQuery(query,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor getMonthDistinctAsana(String fromDate, String toDate) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT COUNT(DISTINCT asana_name) FROM "+ TABLE_REPORT + " WHERE date >= '"+fromDate +"'" + " AND date <='"+ toDate +"'" ;
        Log.d("Query", query);
        Cursor cursor = db.rawQuery(query,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
