package com.buildit.puneet.weatherforecast;

import android.net.Uri;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Puneet on 5/16/2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class WeatherUriTest {

    @Test
    public void shouldParseUris() throws Exception {
        Uri testUri = Uri.parse("http://api.openweathermap.org/data/2.5/forecast?q=London,GB&units=metric&appid=37d05fc8b810612ade5253e73aad2985");

        assertThat(testUri.getQuery()).isEqualTo("q=London,GB&units=metric&appid=37d05fc8b810612ade5253e73aad2985");
        assertThat(testUri.getAuthority()).isEqualTo("api.openweathermap.org");
        assertThat(testUri.getHost()).isEqualTo("api.openweathermap.org");
        assertThat(testUri.getPath()).isEqualTo("data/2.5/forecast");
        assertThat(testUri.getScheme()).isEqualTo("http");
    }

    @Test public void getQueryParameter_shouldWork() throws Exception {
        Uri testUri = Uri.parse("http://api.openweathermap.org/data/2.5/forecast?q=London,GB&units=metric&appid=37d05fc8b810612ade5253e73aad2985");
        assertThat(testUri.getQueryParameter("q")).isEqualTo("London,GB");
    }
}
