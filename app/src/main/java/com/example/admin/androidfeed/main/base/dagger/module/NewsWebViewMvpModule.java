package com.example.admin.androidfeed.main.base.dagger.module;

import android.util.Log;

import com.example.admin.androidfeed.main.tasks.newsfeedwebview.NewsWebViewFragment;
import com.example.admin.androidfeed.main.tasks.newsfeedwebview.NewsWebViewModel;
import com.example.admin.androidfeed.main.tasks.newsfeedwebview.NewsWebViewPresenter;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger 2 NewsWebView Module
 *
 * Created by Admin on 22/03/2017.
 */

@Module
public class NewsWebViewMvpModule
{
    private final WeakReference<NewsWebViewFragment> viewWeakReference;

    private final static String TAG = NewsWebViewMvpModule.class.getSimpleName();

    public NewsWebViewMvpModule(WeakReference<NewsWebViewFragment> viewWeakReference)
    {
        this.viewWeakReference = viewWeakReference;
    }

    @Provides
    NewsWebViewPresenter providesNewsWebViewPresenter()
    {
        if(viewWeakReference!=null)
        {
            Log.d(TAG, "View weak reference variable has been initialized");

            if(viewWeakReference.get()!=null)
            {
                Log.d(TAG, "Activity reference does not point to null");

                NewsWebViewPresenter presenter =  NewsWebViewPresenter.instanceOf(viewWeakReference);

                presenter.setModel(NewsWebViewModel.instanceOf(presenter));

                return presenter;
            }

            throw new NullPointerException("Reference with weak reference points to null");

        }

        throw new NullPointerException("View weak reference points to null");
    }
}
