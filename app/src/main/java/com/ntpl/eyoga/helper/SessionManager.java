package com.ntpl.eyoga.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "E-Yoga";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_FULL_NAME = "full_name";
    public static final String KEY_EMAIL_ID = "email_id";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_AGE = "age";
    public static final String KEY_GENDER = "gender";



    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */

    public void createLoginSession(String fullName, String emailID){

        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_FULL_NAME, fullName);
        editor.putString(KEY_EMAIL_ID, emailID);
        // commit changes
        editor.commit();
    }

    public void updateSession(String fullName, String emailID, String password, String age, String gender){

        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_FULL_NAME, fullName);
        editor.putString(KEY_EMAIL_ID, emailID);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_AGE, age);
        editor.putString(KEY_GENDER, gender);

        // commit changes
        editor.commit();

    }


    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
//            // user is not logged in redirect him to Login Activity
//            Intent i = new Intent(_context, LoginActivity.class);
//
//            // Closing all the Activities
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            // Add new Flag to start new Activity
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            // Staring Login Activity
//            _context.startActivity(i);
        }

    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_FULL_NAME, pref.getString(KEY_FULL_NAME, null));
        user.put(KEY_EMAIL_ID, pref.getString(KEY_EMAIL_ID, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        user.put(KEY_AGE, pref.getString(KEY_AGE, null));
        user.put(KEY_GENDER, pref.getString(KEY_GENDER, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }


}

