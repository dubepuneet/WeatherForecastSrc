package com.buildit.puneet.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by dell laptop on 4/4/2017.
 */

public class WeatherList {
    @SerializedName("main")
    Temprature temprature;
    double pressure;
    ArrayList<Weather> weather;
    @SerializedName("dt_txt")
    String date;

    public Temprature getTemprature() {
        return temprature;
    }

    public void setTemprature(Temprature temprature) {
        this.temprature = temprature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
