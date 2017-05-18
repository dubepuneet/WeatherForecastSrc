package com.buildit.puneet.weatherforecast.utils;


import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by dell laptop on 4/4/2017.
 */
public class CityPreferences {

    public SharedPreferences prefs;
    /**
     * CityPreference constructor
     * @param Activity activity
     * Activity based and mode private
     */
    public CityPreferences(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    /**
     * getCity is used to get city name saved in preferences
     * @return City name
     */
    public String getCity(){
        return prefs.getString("city", "London, GB");
    }

    /**
     * setCity is used to save city name into prefences
     * @param city
     */
    public void setCity(String city){
        prefs.edit().putString("city", city).commit();
    }

}