package com.example.user.pugmatic;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by user on 10/07/2017.
 */

public class SharedPreferencesGameState {

    private static final String PREF_USERMONEY = "userMoney";
    private static final String PREF_SYSTEMCREDIT = "machineCredit";
    private static final String PREF_USERWINNINGS = "userWinnings";


    public static void setStoredInt(Context context, String key, int value) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getStoredInt(Context context, String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(key, 0);
    }



}
