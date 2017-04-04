package com.buildit.puneet.weatherforecast.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.buildit.puneet.weatherforecast.R;
import com.buildit.puneet.weatherforecast.model.ParseResponseData;
import com.buildit.puneet.weatherforecast.model.WeatherList;
import com.buildit.puneet.weatherforecast.utils.DateUtils;

import java.util.ArrayList;

/**
 * Created by dell laptop on 4/4/2017.
 * This adapter is used to show data in recycler view.
 */

public class WeatherRecyclerViewAdapter extends RecyclerView
        .Adapter<WeatherRecyclerViewAdapter
        .DataObjectHolder> {
private static String LOG_TAG = "WeatherRecyclerViewAdapter";
private ParseResponseData mParseResponseData;
    private ArrayList<WeatherList> mWeatherList = new ArrayList<WeatherList>();
private static ItemClickListener itemClickListener;

    /**
     * DataObjectHolder is data holder for Recycler view, holds all UI elements and click listener used in Recycler view .
     */
    public static class DataObjectHolder extends RecyclerView.ViewHolder
        implements View
        .OnClickListener {
     TextView cityField;
     TextView updatedField;
     TextView detailsField;
     TextView currentTemperatureField;
     DataObjectHolder(View itemView) {
        super(itemView);
         cityField = (TextView) itemView.findViewById(R.id.city_field);
         updatedField = (TextView) itemView.findViewById(R.id.updated_field);
         detailsField = (TextView) itemView.findViewById(R.id.details_field);
         currentTemperatureField = (TextView) itemView.findViewById(R.id.current_temperature_field);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onItemClick(getPosition(), v);
    }
}

    public void setOnItemClickListener(ItemClickListener myClickListener) {
        this.itemClickListener = myClickListener;
    }
    public WeatherRecyclerViewAdapter(ParseResponseData parseResponseData) {
        this.mParseResponseData = parseResponseData;
        String tempDate = "";
        ArrayList<WeatherList> tempWeatherList = parseResponseData.getWeatherList();
        for (WeatherList mWeatherList: tempWeatherList) {
            String str = mWeatherList.getDate().substring(0, 10);
            if(!str.equals(tempDate)){
               this.mWeatherList.add(mWeatherList) ;
                tempDate=str;
            }
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.cityField.setText(mParseResponseData.getCity().getName()+", "+mParseResponseData.getCity().getCountry());
        holder.currentTemperatureField.setText("Min: "+mWeatherList.get(position).getTemprature().getTemp_min()
                +"\nMax: "+mWeatherList.get(position).getTemprature().getTemp_max()
                +"\nCurrent Temp: "+mWeatherList.get(position).getTemprature().getTemp());
        holder.detailsField.setText(mWeatherList.get(position).getWeather().get(0).getDescription()
                +"\nHumidity: "+mWeatherList.get(position).getTemprature().getHumidity()+"%"
                +"\nPressure: "+ mWeatherList.get(position).getPressure());
        holder.updatedField.setText(DateUtils.formatDate(mWeatherList.get(position).getDate()));



    }

    public void addItem(WeatherList weatherList, int index) {
        mWeatherList.add(weatherList);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mWeatherList.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }

public interface ItemClickListener {
    public void onItemClick(int position, View v);
}
}
