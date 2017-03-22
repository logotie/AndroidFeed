package com.example.admin.androidfeed.main.tasks.newsfeedwebview;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by Admin on 22/03/2017.
 */

public class NewsWebViewPresenter implements NewsWebViewInterfaceContract.Presenter
{
    private final static String TAG = NewsWebViewPresenter.class.getSimpleName();
    private WeakReference<NewsWebViewFragment> newsWebViewFragmentWeakReference;
    private NewsWebViewModel model;

    public static NewsWebViewPresenter instanceOf(WeakReference<NewsWebViewFragment> activityWeakReference)
    {
        Log.d(TAG, "Received request to create a new instance of NewsFeedPresenter");
        return new NewsWebViewPresenter(activityWeakReference);
    }

    private NewsWebViewPresenter(WeakReference<NewsWebViewFragment> activityWeakReference)
    {
        Log.d(TAG, "News feed presenter initialized");
        newsWebViewFragmentWeakReference = activityWeakReference;
    }

    public void setModel(NewsWebViewModel model)
    {
        Log.d(TAG, "Model set on presenter");
        this.model = model;
    }

    @Override
    public void handleNewsArticleUrlReceived(String url) {

        Log.d(TAG, "Received url from view: "+url);

        if(newsWebViewFragmentWeakReference.get()!=null)
        {
            Log.d(TAG, "fragment weak reference does not resolve to null");
            newsWebViewFragmentWeakReference.get().setWebViewUrlOnView(url);
        }
    }
}
