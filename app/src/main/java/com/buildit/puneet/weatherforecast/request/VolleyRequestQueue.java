package com.buildit.puneet.weatherforecast.request;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

/**
 * VolleyRequestQueue is responsible to handle all qued request in volley.
 * Created by dell laptop on 4/3/2017.
 */

public class VolleyRequestQueue {
    private static VolleyRequestQueue mInstance;
        private static Context mCtx;
        private RequestQueue mRequestQueue;

        private VolleyRequestQueue(Context context) {
            mCtx = context;
            mRequestQueue = getRequestQueue();
        }

    /**
     *
     * @param context
     * @return VolleyRequestQueue object
     */
        public static synchronized VolleyRequestQueue getInstance(Context context) {
            if (mInstance == null) {
                mInstance = new VolleyRequestQueue(context);
            }
            return mInstance;
        }

    /**
     *
     * @return RequestQueue
     */
        public RequestQueue getRequestQueue() {
            if (mRequestQueue == null) {
                Cache cache = new DiskBasedCache(mCtx.getCacheDir(), 10 * 1024 * 1024);
                Network network = new BasicNetwork(new HurlStack());
                mRequestQueue = new RequestQueue(cache, network);
                mRequestQueue.start();
            }
            return mRequestQueue;
        }
}
