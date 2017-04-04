package com.buildit.puneet.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dell laptop on 4/4/2017.
 */

public class City {
    @SerializedName("id")
    long ID;
    String name;
    String country;


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
