package com.example.admin.androidfeed.main.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

/**
 * Custom application class, created to provide a static application context when required.
 *
 * Created by Admin on 19/03/2017.
 */

public class MyApplication extends Application
{
    private final static String TAG = MyApplication.class.getSimpleName();

    private static Context appContext;

    public void onCreate() {

        Log.d(TAG, "Application initialized");

        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            return;
        }
        LeakCanary.install(this);

        appContext = this;
    }

    /**
     * Retrieves a static application context.
     *
     * @return Context object.
     */
    public static Context getContext() {

        Log.d(TAG, "Get context called");

        return appContext;
    }

}
