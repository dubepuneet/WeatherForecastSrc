package com.buildit.puneet.weatherforecast;

import android.content.Context;
import android.test.InstrumentationTestCase;


import com.buildit.puneet.weatherforecast.model.ParseResponseData;
import com.buildit.puneet.weatherforecast.utils.CityPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class WeatherActivityUnitTest extends InstrumentationTestCase {
    protected Context mContext;
    @Mock
    WeatherActivity weatherActivity;
    @Mock
    CityPreferences cityPreferences;
    @Mock
    ParseResponseData parseResponseData;



    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
        weatherActivity = Mockito.mock(WeatherActivity.class);
        weatherActivity.getWeatherUpdate();
        cityPreferences = Mockito.mock(new CityPreferences(weatherActivity).getClass());
        this.cityPreferences = Mockito.mock(CityPreferences.class);
        this.mContext = Mockito.mock(Context.class);
        Mockito.when(mContext.getSharedPreferences(anyString(), anyInt())).thenReturn(cityPreferences.prefs);
    }
@Test
    public void getWeatherUpdateTest(){
    assertEquals(null, cityPreferences.getCity());
    doCallRealMethod().when(cityPreferences).setCity("London,GB");
    doCallRealMethod().when(weatherActivity).getWeatherUpdate();

}

@Test
public void getCity() throws Exception {
    Mockito.when(cityPreferences.getCity()).thenReturn("London,GB");
    assertEquals("London,GB", cityPreferences.getCity());
}

}