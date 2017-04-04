package com.buildit.puneet.weatherforecast;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.buildit.puneet.weatherforecast.adapter.WeatherRecyclerViewAdapter;
import com.buildit.puneet.weatherforecast.model.ParseResponseData;
import com.buildit.puneet.weatherforecast.request.VolleyRequestQueue;
import com.buildit.puneet.weatherforecast.request.WeatherJSONObjectRequest;
import com.buildit.puneet.weatherforecast.utils.CityPreferences;
import com.buildit.puneet.weatherforecast.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.json.JSONObject;


public class WeatherActivity extends AppCompatActivity implements Response.Listener,
        Response.ErrorListener{
    public static final String REQUEST_TAG = "WeatherActivity";
    private TextView mTextView;
    private RequestQueue mQueue;
    private Gson gson;
    Spinner spinner = null;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "RecyclerViewActivity";
    private ParseResponseData parseResponseData = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        MenuItem item = menu.findItem(R.id.spinner);
        spinner = (Spinner) MenuItemCompat.getActionView(item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        String city = new CityPreferences(this).getCity();
        if(city.equals("London,GB")){
            spinner.setSelection(0);
        }else if(city.equals("Mumbai,IN")){
            spinner.setSelection(1);
        }else if(city.equals("Bangaluru,IN")){
            spinner.setSelection(2);
        }else if(city.equals("Chennai,IN")){
            spinner.setSelection(3);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                new CityPreferences(WeatherActivity.this).setCity(spinner.getSelectedItem().toString());
                getWeatherUpdate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return false;

    }

    @Override
    protected void onStart() {
        super.onStart();
        getWeatherUpdate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            parseResponseData = gson.fromJson(response, ParseResponseData.class);
            mAdapter = new WeatherRecyclerViewAdapter(parseResponseData);
            mRecyclerView.setAdapter(mAdapter);
            RecyclerView.ItemDecoration itemDecoration =
                    new DividerItemDecoration(WeatherActivity.this, LinearLayoutManager.VERTICAL);
            mRecyclerView.addItemDecoration(itemDecoration);
        }
    };

    private final Response.ErrorListener onPostsError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(WeatherActivity.class.getSimpleName(), error.toString());
        }
    };

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {
        ParseResponseData parseResponseData = gson.fromJson(response.toString(), ParseResponseData.class);
        mAdapter = new WeatherRecyclerViewAdapter(parseResponseData);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(WeatherActivity.this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
    }



    void getWeatherUpdate(){
        // Instantiate the RequestQueue.
        mQueue = VolleyRequestQueue.getInstance(WeatherActivity.this)
                .getRequestQueue();
        final WeatherJSONObjectRequest request = new WeatherJSONObjectRequest(Request.Method
                .GET, getURL(),
                new JSONObject(), this, this);
        request.setTag(REQUEST_TAG);
        mQueue.add(request);
        request.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(request);
    }

    String getURL(){
        String city = new CityPreferences(this).getCity();
        return Constants.BASE_URL + city+"%s&units=metric&appid="+getString(R.string.open_weather_maps_app_id);
    }
}
