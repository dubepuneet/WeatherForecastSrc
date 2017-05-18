package com.buildit.puneet.weatherforecast;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.ExecutorDelivery;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ResponseDelivery;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.buildit.puneet.weatherforecast.model.ParseResponseData;
import com.buildit.puneet.weatherforecast.model.WeatherList;
import com.buildit.puneet.weatherforecast.utils.CityPreferences;
import com.google.gson.GsonBuilder;

import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static okhttp3.Protocol.HTTP_2;

/**
 * Created by Puneet on 5/16/2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class WeatherHTTPRequestTest  {
    private WeatherActivity weatherActivity;
    protected Context mContext;
    private String response;

    @Mock
    CityPreferences cityPreferences;
    @Mock
    ParseResponseData parseResponseData;
    @Before
    public void setUp() throws Exception {
        VolleyLog.DEBUG = true;
        MockitoAnnotations.initMocks(this);
        weatherActivity = Mockito.mock(WeatherActivity.class);
        cityPreferences = Mockito.mock(new CityPreferences(weatherActivity).getClass());
        this.cityPreferences = Mockito.mock(CityPreferences.class);
        this.mContext = Mockito.mock(Context.class);
        weatherActivity = Robolectric.buildActivity( WeatherActivity.class )
                .create()
                .resume()
                .get();
        response =null;

        }



    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( weatherActivity );
    }

    @Test
    public void doUITest() throws Exception
    {
        assertNotNull( weatherActivity.findViewById(R.id.my_recycler_view));
        assertNotNull( weatherActivity.findViewById(R.id.toolbar));

    }

@Test
    public void getWeatherUpdateTest()throws Exception{
//    Robolectric.getFakeHttpLayer().interceptHttpRequests(false);
        RequestQueue queue = Volley.newRequestQueue(this.mContext);
        String url ="http://api.openweathermap.org/data/2.5/forecast?q=London,GB&units=metric&appid=37d05fc8b810612ade5253e73aad2985";
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String res) {
                    response = res;
                    ParseResponseData parseResponseData = new GsonBuilder().create().fromJson(response.toString(), ParseResponseData.class);
                    ArrayList<WeatherList> tempWeatherList = parseResponseData.getWeatherList();
                    assertNotNull(tempWeatherList);
                    assertNotSame(0, tempWeatherList.size());
                    assertEquals("London", parseResponseData.getCity().getName());
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });
    queue.add(stringRequest);
    }

//    @After
//    public void tearDown() throws Exception {
//        queue.cancelAll(new RequestQueue.RequestFilter() {
//            @Override
//            public boolean apply(Request<?> request) {
//                return true;
//            }
//        });
//        response = null;
//    }
}