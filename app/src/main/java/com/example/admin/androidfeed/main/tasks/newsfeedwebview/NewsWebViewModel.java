package com.example.admin.androidfeed.main.tasks.newsfeedwebview;

import android.util.Log;

/**
 * Created by Admin on 22/03/2017.
 */

public class NewsWebViewModel
{
    private static NewsWebViewPresenter newsWebViewPresenter;
    private final static String TAG = NewsWebViewModel.class.getSimpleName();

    public static NewsWebViewModel instanceOf(NewsWebViewPresenter presenter)
    {
        Log.d(TAG, "Received request to create a new instance of NewsFeedModel");
        return new NewsWebViewModel(presenter);
    }

    private NewsWebViewModel(NewsWebViewPresenter presenter)
    {
        Log.d(TAG, "News feed model initialized");
        newsWebViewPresenter = presenter;
    }
}
