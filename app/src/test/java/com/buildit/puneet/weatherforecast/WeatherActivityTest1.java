package com.buildit.puneet.weatherforecast;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.buildit.puneet.weatherforecast.model.ParseResponseData;
import com.buildit.puneet.weatherforecast.model.WeatherList;
import com.buildit.puneet.weatherforecast.request.WeatherJSONObjectRequest;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;


/**
 * Created by Puneet on 5/17/2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class WeatherActivityTest1 {
    private static FakeRequestQueue fakeRequestQueue;
    private WeatherActivity weatherActivity;
    private String response;
    @Before
    public void setup() {
        weatherActivity = Mockito.mock(WeatherActivity.class);
        System.out.println("setup ");
        if (fakeRequestQueue == null) {
            fakeRequestQueue = new FakeRequestQueue(Mockito.mock(Context.class));
        }
    }
    @Test
    public void getWeatherUpdate(){
        String url ="http://api.openweathermap.org/data/2.5/forecast?q=London,GB&units=metric&appid=37d05fc8b810612ade5253e73aad2985";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String res) {
                        response = res;
                        System.out.print("Response: "+res);
                        ParseResponseData parseResponseData = new GsonBuilder().create().fromJson(response.toString(), ParseResponseData.class);
                        ArrayList<WeatherList> tempWeatherList = parseResponseData.getWeatherList();
                        assertNotNull(tempWeatherList);
                        assertNotSame(-1, tempWeatherList.size());
                        assertEquals("London", parseResponseData.getCity().getName());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print("Response: Error");
            }
        });
        fakeRequestQueue.add(stringRequest);
}
}
