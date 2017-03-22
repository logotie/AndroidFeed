package com.example.admin.androidfeed.main.base.web;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 *  Volley enum singleton for Json web requests.
 *
 * Created by Admin on 19/03/2017.
 */

public enum WebJsonRequest
{
    INSTANCE;

    //CallBack interface variable, to send the response asynchronously back to the caller class.
    private static AsyncWebResponseCallback callback;
    private static String TAG = WebJsonRequest.class.getSimpleName();

    public void getRequest(AsyncWebResponseCallback callback, String url, Context instanceContext)
    {
        this.callback = callback;

        getRequest(url, instanceContext);
    }

    private String getRequest(String url, Context appInstanceContext) {
        RequestQueue requestQueue = Volley.newRequestQueue(appInstanceContext);

        try {
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(callback!=null)
                            {
                                callback.feedResponseReceived(response);
                            }
                            else
                            {
                                Log.d(TAG, "Callback variable points to null");
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.d(TAG, error.getMessage());

                    if(callback!=null)
                    {
                        callback.feedResponseReceived(null);
                    }
                    else
                    {
                        Log.d(TAG, "Callback variable points to null");
                    }
                }
            });
            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
