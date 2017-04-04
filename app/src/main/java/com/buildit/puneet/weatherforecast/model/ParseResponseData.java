package com.buildit.puneet.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import static android.R.id.list;

/**
 * Created by dell laptop on 4/4/2017.
 */

public class ParseResponseData implements Serializable {
    City city;
    @SerializedName("list")
    ArrayList<WeatherList> weatherList;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ArrayList<WeatherList> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(ArrayList<WeatherList> weatherList) {
        this.weatherList = weatherList;
    }
}
