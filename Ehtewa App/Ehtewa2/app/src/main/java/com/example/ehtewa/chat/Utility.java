package com.example.ehtewa.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
    private static Utility ourInstance = new Utility();

    private Utility() {
    }

    public static Utility getInstance() {
        return ourInstance;
    }




    public boolean checkemail(String email) {
        Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting());

    }



    public void setDataBykeyValue(Context context, String key, String value)
    {
        try
        {
            SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
        catch (NullPointerException e)
        {
            Log.e("",String.valueOf(e.getMessage()));
        }

    }

    public void setArrayDataBykeyValue(Context context, String key, Set<String> value) {
        SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(value);
        editor.putStringSet(key, set);
        editor.commit();

    }
    public Set<String> getArrayDataByKey(Context context, String key) {

        SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        Set<String> set = preferences.getStringSet(key, new HashSet<String>());
        return set;
    }
    public String getDataByKey(Context context, String key, String defaultValue) {

        try
        {
            SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
            String name = preferences.getString(key, defaultValue);
            return name;
        }
        catch (NullPointerException e)
        {
            Log.e("",String.valueOf(e.getMessage()));
            return defaultValue;
        }

    }

}
