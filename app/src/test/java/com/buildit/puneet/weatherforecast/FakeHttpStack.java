package com.buildit.puneet.weatherforecast;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.NoCache;
import com.android.volley.toolbox.StringRequest;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/**
 * Created by Puneet on 5/17/2017.
 */

public class FakeHttpStack implements HttpStack {
    private static final String DEFAULT_STRING_RESPONSE = "Hello";
    private static final String DEFAULT_JSON_RESPONSE = "{\\'cod\\':\\'200\\',\\'message\\':0.0396,\\'cnt\\':38,\\'list\\':[{\\'dt\\':1494914400,\\'main\\':{\\'temp\\':15.85,\\'temp_min\\':15.16,\\'temp_max\\':15.85,\\'pressure\\':1029.83,\\'sea_level\\':1037.39,\\'grnd_level\\':1029.83,\\'humidity\\':83,\\'temp_kf\\':0.68},\\'weather\\':[{\\'id\\':802,\\'main\\':\\'Clouds\\',\\'description\\':\\'scattered clouds\\',\\'icon\\':\\'03d\\'}],\\'clouds\\':{\\'all\\':48},\\'wind\\':{\\'speed\\':3.48,\\'deg\\':202.009},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-16 06:00:00\\'},{\\'dt\\':1494925200,\\'main\\':{\\'temp\\':18.82,\\'temp_min\\':18.3,\\'temp_max\\':18.82,\\'pressure\\':1029.9,\\'sea_level\\':1037.4,\\'grnd_level\\':1029.9,\\'humidity\\':74,\\'temp_kf\\':0.51},\\'weather\\':[{\\'id\\':803,\\'main\\':\\'Clouds\\',\\'description\\':\\'broken clouds\\',\\'icon\\':\\'04d\\'}],\\'clouds\\':{\\'all\\':56},\\'wind\\':{\\'speed\\':4.31,\\'deg\\':205.5},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-16 09:00:00\\'},{\\'dt\\':1494936000,\\'main\\':{\\'temp\\':20.64,\\'temp_min\\':20.3,\\'temp_max\\':20.64,\\'pressure\\':1029.64,\\'sea_level\\':1037.16,\\'grnd_level\\':1029.64,\\'humidity\\':71,\\'temp_kf\\':0.34},\\'weather\\':[{\\'id\\':804,\\'main\\':\\'Clouds\\',\\'description\\':\\'overcast clouds\\',\\'icon\\':\\'04d\\'}],\\'clouds\\':{\\'all\\':88},\\'wind\\':{\\'speed\\':4.76,\\'deg\\':206.503},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-16 12:00:00\\'},{\\'dt\\':1494946800,\\'main\\':{\\'temp\\':20.01,\\'temp_min\\':19.84,\\'temp_max\\':20.01,\\'pressure\\':1029.58,\\'sea_level\\':1037.12,\\'grnd_level\\':1029.58,\\'humidity\\':66,\\'temp_kf\\':0.17},\\'weather\\':[{\\'id\\':804,\\'main\\':\\'Clouds\\',\\'description\\':\\'overcast clouds\\',\\'icon\\':\\'04d\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':4.87,\\'deg\\':213.002},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-16 15:00:00\\'},{\\'dt\\':1494957600,\\'main\\':{\\'temp\\':18.61,\\'temp_min\\':18.61,\\'temp_max\\':18.61,\\'pressure\\':1029.4,\\'sea_level\\':1036.86,\\'grnd_level\\':1029.4,\\'humidity\\':66,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':2.58,\\'deg\\':205.001},\\'rain\\':{\\'3h\\':0.005},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-16 18:00:00\\'},{\\'dt\\':1494968400,\\'main\\':{\\'temp\\':16.66,\\'temp_min\\':16.66,\\'temp_max\\':16.66,\\'pressure\\':1028.86,\\'sea_level\\':1036.36,\\'grnd_level\\':1028.86,\\'humidity\\':71,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':804,\\'main\\':\\'Clouds\\',\\'description\\':\\'overcast clouds\\',\\'icon\\':\\'04n\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':1.28,\\'deg\\':157.001},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'n\\'},\\'dt_txt\\':\\'2017-05-16 21:00:00\\'},{\\'dt\\':1494979200,\\'main\\':{\\'temp\\':15.34,\\'temp_min\\':15.34,\\'temp_max\\':15.34,\\'pressure\\':1028.28,\\'sea_level\\':1035.77,\\'grnd_level\\':1028.28,\\'humidity\\':78,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':804,\\'main\\':\\'Clouds\\',\\'description\\':\\'overcast clouds\\',\\'icon\\':\\'04n\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':1.23,\\'deg\\':223.003},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'n\\'},\\'dt_txt\\':\\'2017-05-17 00:00:00\\'},{\\'dt\\':1494990000,\\'main\\':{\\'temp\\':14.89,\\'temp_min\\':14.89,\\'temp_max\\':14.89,\\'pressure\\':1027.2,\\'sea_level\\':1034.65,\\'grnd_level\\':1027.2,\\'humidity\\':83,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':804,\\'main\\':\\'Clouds\\',\\'description\\':\\'overcast clouds\\',\\'icon\\':\\'04n\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':1.34,\\'deg\\':320.001},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'n\\'},\\'dt_txt\\':\\'2017-05-17 03:00:00\\'},{\\'dt\\':1495000800,\\'main\\':{\\'temp\\':15.24,\\'temp_min\\':15.24,\\'temp_max\\':15.24,\\'pressure\\':1025.65,\\'sea_level\\':1033.16,\\'grnd_level\\':1025.65,\\'humidity\\':84,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':1.96,\\'deg\\':37.5016},\\'rain\\':{\\'3h\\':0.01},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-17 06:00:00\\'},{\\'dt\\':1495011600,\\'main\\':{\\'temp\\':16.44,\\'temp_min\\':16.44,\\'temp_max\\':16.44,\\'pressure\\':1024.17,\\'sea_level\\':1031.57,\\'grnd_level\\':1024.17,\\'humidity\\':80,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':100},\\'wind\\':{\\'speed\\':2.13,\\'deg\\':358.003},\\'rain\\':{\\'3h\\':0.045},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-17 09:00:00\\'},{\\'dt\\':1495022400,\\'main\\':{\\'temp\\':17.8,\\'temp_min\\':17.8,\\'temp_max\\':17.8,\\'pressure\\':1021.67,\\'sea_level\\':1029.14,\\'grnd_level\\':1021.67,\\'humidity\\':79,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':2.47,\\'deg\\':15.0028},\\'rain\\':{\\'3h\\':0.015},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-17 12:00:00\\'},{\\'dt\\':1495033200,\\'main\\':{\\'temp\\':17.3,\\'temp_min\\':17.3,\\'temp_max\\':17.3,\\'pressure\\':1019.73,\\'sea_level\\':1027.2,\\'grnd_level\\':1019.73,\\'humidity\\':86,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':1.68,\\'deg\\':304.014},\\'rain\\':{\\'3h\\':0.52},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-17 15:00:00\\'},{\\'dt\\':1495044000,\\'main\\':{\\'temp\\':12.36,\\'temp_min\\':12.36,\\'temp_max\\':12.36,\\'pressure\\':1019.54,\\'sea_level\\':1026.93,\\'grnd_level\\':1019.54,\\'humidity\\':100,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':5.97,\\'deg\\':333.502},\\'rain\\':{\\'3h\\':2.675},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-17 18:00:00\\'},{\\'dt\\':1495054800,\\'main\\':{\\'temp\\':10.96,\\'temp_min\\':10.96,\\'temp_max\\':10.96,\\'pressure\\':1019.61,\\'sea_level\\':1027.13,\\'grnd_level\\':1019.61,\\'humidity\\':100,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10n\\'}],\\'clouds\\':{\\'all\\':100},\\'wind\\':{\\'speed\\':3.41,\\'deg\\':324},\\'rain\\':{\\'3h\\':2.57},\\'sys\\':{\\'pod\\':\\'n\\'},\\'dt_txt\\':\\'2017-05-17 21:00:00\\'},{\\'dt\\':1495065600,\\'main\\':{\\'temp\\':10.42,\\'temp_min\\':10.42,\\'temp_max\\':10.42,\\'pressure\\':1019.36,\\'sea_level\\':1026.97,\\'grnd_level\\':1019.36,\\'humidity\\':100,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10n\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':2.85,\\'deg\\':297},\\'rain\\':{\\'3h\\':0.7},\\'sys\\':{\\'pod\\':\\'n\\'},\\'dt_txt\\':\\'2017-05-18 00:00:00\\'},{\\'dt\\':1495076400,\\'main\\':{\\'temp\\':10.16,\\'temp_min\\':10.16,\\'temp_max\\':10.16,\\'pressure\\':1018.95,\\'sea_level\\':1026.54,\\'grnd_level\\':1018.95,\\'humidity\\':99,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10n\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':2.52,\\'deg\\':277.501},\\'rain\\':{\\'3h\\':0.66},\\'sys\\':{\\'pod\\':\\'n\\'},\\'dt_txt\\':\\'2017-05-18 03:00:00\\'},{\\'dt\\':1495087200,\\'main\\':{\\'temp\\':10.26,\\'temp_min\\':10.26,\\'temp_max\\':10.26,\\'pressure\\':1019.02,\\'sea_level\\':1026.59,\\'grnd_level\\':1019.02,\\'humidity\\':100,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':88},\\'wind\\':{\\'speed\\':2.86,\\'deg\\':288.502},\\'rain\\':{\\'3h\\':0.15},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-18 06:00:00\\'},{\\'dt\\':1495098000,\\'main\\':{\\'temp\\':11.13,\\'temp_min\\':11.13,\\'temp_max\\':11.13,\\'pressure\\':1019.5,\\'sea_level\\':1027.01,\\'grnd_level\\':1019.5,\\'humidity\\':100,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':68},\\'wind\\':{\\'speed\\':2.72,\\'deg\\':291.504},\\'rain\\':{\\'3h\\':0.09},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-18 09:00:00\\'},{\\'dt\\':1495108800,\\'main\\':{\\'temp\\':13.95,\\'temp_min\\':13.95,\\'temp_max\\':13.95,\\'pressure\\':1019.63,\\'sea_level\\':1027.08,\\'grnd_level\\':1019.63,\\'humidity\\':97,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':801,\\'main\\':\\'Clouds\\',\\'description\\':\\'few clouds\\',\\'icon\\':\\'02d\\'}],\\'clouds\\':{\\'all\\':24},\\'wind\\':{\\'speed\\':2.07,\\'deg\\':268.002},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-18 12:00:00\\'},{\\'dt\\':1495119600,\\'main\\':{\\'temp\\':14.94,\\'temp_min\\':14.94,\\'temp_max\\':14.94,\\'pressure\\':1019.36,\\'sea_level\\':1026.89,\\'grnd_level\\':1019.36,\\'humidity\\':79,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':802,\\'main\\':\\'Clouds\\',\\'description\\':\\'scattered clouds\\',\\'icon\\':\\'03d\\'}],\\'clouds\\':{\\'all\\':44},\\'wind\\':{\\'speed\\':2.78,\\'deg\\':256.502},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-18 15:00:00\\'},{\\'dt\\':1495130400,\\'main\\':{\\'temp\\':14.14,\\'temp_min\\':14.14,\\'temp_max\\':14.14,\\'pressure\\':1019.53,\\'sea_level\\':1027.14,\\'grnd_level\\':1019.53,\\'humidity\\':72,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':56},\\'wind\\':{\\'speed\\':3.16,\\'deg\\':252.005},\\'rain\\':{\\'3h\\':0.0099999999999998},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-18 18:00:00\\'},{\\'dt\\':1495141200,\\'main\\':{\\'temp\\':11.5,\\'temp_min\\':11.5,\\'temp_max\\':11.5,\\'pressure\\':1020.16,\\'sea_level\\':1027.73,\\'grnd_level\\':1020.16,\\'humidity\\':72,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':802,\\'main\\':\\'Clouds\\',\\'description\\':\\'scattered clouds\\',\\'icon\\':\\'03n\\'}],\\'clouds\\':{\\'all\\':36},\\'wind\\':{\\'speed\\':2.72,\\'deg\\':246},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'n\\'},\\'dt_txt\\':\\'2017-05-18 21:00:00\\'},{\\'dt\\':1495152000,\\'main\\':{\\'temp\\':8.97,\\'temp_min\\':8.97,\\'temp_max\\':8.97,\\'pressure\\':1019.96,\\'sea_level\\':1027.61,\\'grnd_level\\':1019.96,\\'humidity\\':85,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':802,\\'main\\':\\'Clouds\\',\\'description\\':\\'scattered clouds\\',\\'icon\\':\\'03n\\'}],\\'clouds\\':{\\'all\\':32},\\'wind\\':{\\'speed\\':2.33,\\'deg\\':248.506},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'n\\'},\\'dt_txt\\':\\'2017-05-19 00:00:00\\'},{\\'dt\\':1495162800,\\'main\\':{\\'temp\\':6.41,\\'temp_min\\':6.41,\\'temp_max\\':6.41,\\'pressure\\':1019.3,\\'sea_level\\':1027.06,\\'grnd_level\\':1019.3,\\'humidity\\':100,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':801,\\'main\\':\\'Clouds\\',\\'description\\':\\'few clouds\\',\\'icon\\':\\'02n\\'}],\\'clouds\\':{\\'all\\':12},\\'wind\\':{\\'speed\\':1.52,\\'deg\\':231.502},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'n\\'},\\'dt_txt\\':\\'2017-05-19 03:00:00\\'},{\\'dt\\':1495173600,\\'main\\':{\\'temp\\':8.02,\\'temp_min\\':8.02,\\'temp_max\\':8.02,\\'pressure\\':1019.09,\\'sea_level\\':1026.72,\\'grnd_level\\':1019.09,\\'humidity\\':94,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':802,\\'main\\':\\'Clouds\\',\\'description\\':\\'scattered clouds\\',\\'icon\\':\\'03d\\'}],\\'clouds\\':{\\'all\\':48},\\'wind\\':{\\'speed\\':1.36,\\'deg\\':230.5},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-19 06:00:00\\'},{\\'dt\\':1495184400,\\'main\\':{\\'temp\\':12.6,\\'temp_min\\':12.6,\\'temp_max\\':12.6,\\'pressure\\':1018.24,\\'sea_level\\':1025.79,\\'grnd_level\\':1018.24,\\'humidity\\':84,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':803,\\'main\\':\\'Clouds\\',\\'description\\':\\'broken clouds\\',\\'icon\\':\\'04d\\'}],\\'clouds\\':{\\'all\\':56},\\'wind\\':{\\'speed\\':1.8,\\'deg\\':276.503},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-19 09:00:00\\'},{\\'dt\\':1495195200,\\'main\\':{\\'temp\\':14.07,\\'temp_min\\':14.07,\\'temp_max\\':14.07,\\'pressure\\':1017.18,\\'sea_level\\':1024.6,\\'grnd_level\\':1017.18,\\'humidity\\':79,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':803,\\'main\\':\\'Clouds\\',\\'description\\':\\'broken clouds\\',\\'icon\\':\\'04d\\'}],\\'clouds\\':{\\'all\\':76},\\'wind\\':{\\'speed\\':1.77,\\'deg\\':323.002},\\'rain\\':{},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-19 12:00:00\\'},{\\'dt\\':1495206000,\\'main\\':{\\'temp\\':14.56,\\'temp_min\\':14.56,\\'temp_max\\':14.56,\\'pressure\\':1016.47,\\'sea_level\\':1023.98,\\'grnd_level\\':1016.47,\\'humidity\\':71,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':88},\\'wind\\':{\\'speed\\':1.7,\\'deg\\':266.003},\\'rain\\':{\\'3h\\':0.059999999999999},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-19 15:00:00\\'},{\\'dt\\':1495216800,\\'main\\':{\\'temp\\':13.86,\\'temp_min\\':13.86,\\'temp_max\\':13.86,\\'pressure\\':1015.77,\\'sea_level\\':1023.43,\\'grnd_level\\':1015.77,\\'humidity\\':68,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':88},\\'wind\\':{\\'speed\\':1.88,\\'deg\\':266},\\'rain\\':{\\'3h\\':0.030000000000001},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-19 18:00:00\\'},{\\'dt\\':1495227600,\\'main\\':{\\'temp\\':11.95,\\'temp_min\\':11.95,\\'temp_max\\':11.95,\\'pressure\\':1016.45,\\'sea_level\\':1024.02,\\'grnd_level\\':1016.45,\\'humidity\\':72,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10n\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':1.91,\\'deg\\':295.001},\\'rain\\':{\\'3h\\':0.02},\\'sys\\':{\\'pod\\':\\'n\\'},\\'dt_txt\\':\\'2017-05-19 21:00:00\\'},{\\'dt\\':1495238400,\\'main\\':{\\'temp\\':9.82,\\'temp_min\\':9.82,\\'temp_max\\':9.82,\\'pressure\\':1016.51,\\'sea_level\\':1024.12,\\'grnd_level\\':1016.51,\\'humidity\\':91,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10n\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':2.5,\\'deg\\':65.5001},\\'rain\\':{\\'3h\\':1.51},\\'sys\\':{\\'pod\\':\\'n\\'},\\'dt_txt\\':\\'2017-05-20 00:00:00\\'},{\\'dt\\':1495249200,\\'main\\':{\\'temp\\':8.71,\\'temp_min\\':8.71,\\'temp_max\\':8.71,\\'pressure\\':1016.46,\\'sea_level\\':1024.08,\\'grnd_level\\':1016.46,\\'humidity\\':99,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10n\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':3.42,\\'deg\\':48.5006},\\'rain\\':{\\'3h\\':2.01},\\'sys\\':{\\'pod\\':\\'n\\'},\\'dt_txt\\':\\'2017-05-20 03:00:00\\'},{\\'dt\\':1495260000,\\'main\\':{\\'temp\\':7.79,\\'temp_min\\':7.79,\\'temp_max\\':7.79,\\'pressure\\':1016.96,\\'sea_level\\':1024.57,\\'grnd_level\\':1016.96,\\'humidity\\':99,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':88},\\'wind\\':{\\'speed\\':3.85,\\'deg\\':27.5049},\\'rain\\':{\\'3h\\':0.89},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-20 06:00:00\\'},{\\'dt\\':1495270800,\\'main\\':{\\'temp\\':8.31,\\'temp_min\\':8.31,\\'temp_max\\':8.31,\\'pressure\\':1017.08,\\'sea_level\\':1024.64,\\'grnd_level\\':1017.08,\\'humidity\\':100,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':3.4,\\'deg\\':335.002},\\'rain\\':{\\'3h\\':0.54},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-20 09:00:00\\'},{\\'dt\\':1495281600,\\'main\\':{\\'temp\\':9.07,\\'temp_min\\':9.07,\\'temp_max\\':9.07,\\'pressure\\':1016.98,\\'sea_level\\':1024.56,\\'grnd_level\\':1016.98,\\'humidity\\':98,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':4.55,\\'deg\\':355.5},\\'rain\\':{\\'3h\\':1.61},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-20 12:00:00\\'},{\\'dt\\':1495292400,\\'main\\':{\\'temp\\':8.49,\\'temp_min\\':8.49,\\'temp_max\\':8.49,\\'pressure\\':1016.82,\\'sea_level\\':1024.39,\\'grnd_level\\':1016.82,\\'humidity\\':100,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':4.26,\\'deg\\':330},\\'rain\\':{\\'3h\\':0.69},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-20 15:00:00\\'},{\\'dt\\':1495303200,\\'main\\':{\\'temp\\':7.78,\\'temp_min\\':7.78,\\'temp_max\\':7.78,\\'pressure\\':1016.37,\\'sea_level\\':1024.01,\\'grnd_level\\':1016.37,\\'humidity\\':99,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10d\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':4.35,\\'deg\\':316.001},\\'rain\\':{\\'3h\\':1},\\'sys\\':{\\'pod\\':\\'d\\'},\\'dt_txt\\':\\'2017-05-20 18:00:00\\'},{\\'dt\\':1495314000,\\'main\\':{\\'temp\\':7.46,\\'temp_min\\':7.46,\\'temp_max\\':7.46,\\'pressure\\':1016.63,\\'sea_level\\':1024.32,\\'grnd_level\\':1016.63,\\'humidity\\':99,\\'temp_kf\\':0},\\'weather\\':[{\\'id\\':500,\\'main\\':\\'Rain\\',\\'description\\':\\'light rain\\',\\'icon\\':\\'10n\\'}],\\'clouds\\':{\\'all\\':92},\\'wind\\':{\\'speed\\':3.86,\\'deg\\':313},\\'rain\\':{\\'3h\\':0.37},\\'sys\\':{\\'pod\\':\\'n\\'},\\'dt_txt\\':\\'2017-05-20 21:00:00\\'}],\\'city\\':{\\'id\\':2643743,\\'name\\':\\'London\\',\\'coord\\':{\\'lat\\':51.5085,\\'lon\\':-0.1258},\\'country\\':\\'GB\\'}}";
    private static final String URL_PREFIX = "http://api.openweathermap.org";
    private static final String LOGGER_TAG = "WeatherActivity";

    private static final int SIMULATED_DELAY_MS = 500;
    private final Context context;

    FakeHttpStack(Context context) {
        this.context = context;
    }

    @Override
    public HttpResponse performRequest(Request<?> request, Map<String, String> stringStringMap)
            throws IOException, AuthFailureError {
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }
        HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, 200, "OK"));
        List<Header> headers = defaultHeaders();
        response.setHeaders(headers.toArray(new Header[0]));
        response.setLocale(Locale.US);
        response.setEntity(createEntity(request));
        return response;
    }

    private List<Header> defaultHeaders() {
        DateFormat dateFormat = new SimpleDateFormat("EEE, dd mmm yyyy HH:mm:ss zzz");
        return Lists.<Header>newArrayList(
                new BasicHeader("Date", dateFormat.format(new Date())),
                new BasicHeader("Server",
                        /* Data below is header info of my server */
                        "Apache/1.3.42 (Unix) mod_ssl/2.8.31 OpenSSL/0.9.8e")
        );
    }

    /**
     * returns the fake content using resource file in res/raw. fake_res_foo.txt is used for
     * request to http://example.com/foo
     */
    private HttpEntity createEntity(Request request) throws UnsupportedEncodingException {
        String resourceName = constructFakeResponseFileName(request);
        int resourceId = context.getResources().getIdentifier(
                resourceName, "raw", context.getApplicationContext().getPackageName());
        if (resourceId == 0) {
            Log.w(LOGGER_TAG, "No fake file named " + resourceName
                    + " found. default fake response should be used.");
        } else {
            InputStream stream = context.getResources().openRawResource(resourceId);
            try {
                String string = CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
                return new StringEntity(string);
            } catch (IOException e) {
                Log.e(LOGGER_TAG, "error reading " + resourceName, e);
            }
        }

        // Return default value since no fake file exists for given URL.
        if (request instanceof StringRequest) {
            return new StringEntity(DEFAULT_STRING_RESPONSE);
        }
        return new StringEntity(DEFAULT_JSON_RESPONSE);
    }

    /**
     * Map request URL to fake file name
     */
    private String constructFakeResponseFileName(Request request) {
        String reqUrl = request.getUrl();
        String apiName = reqUrl.substring(URL_PREFIX.length());
        return "fake_res_" + apiName;
    }
}