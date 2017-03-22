package com.example.admin.androidfeed.main.base.dagger.module;

import android.util.Log;

import com.example.admin.androidfeed.main.tasks.newsfeed.NewsFeedFragment;
import com.example.admin.androidfeed.main.tasks.newsfeed.NewsFeedModel;
import com.example.admin.androidfeed.main.tasks.newsfeed.NewsFeedPresenter;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger 2 NewsFeedMvp module.
 *
 * Created by Admin on 19/03/2017.
 */

@Module
public class NewsFeedMvpModule {

    private final WeakReference<NewsFeedFragment> viewWeakReference;

    private final static String TAG = NewsFeedMvpModule.class.getSimpleName();

    public NewsFeedMvpModule(WeakReference<NewsFeedFragment> viewWeakReference)
    {
        this.viewWeakReference = viewWeakReference;
    }

    @Provides
    NewsFeedPresenter providesNewsFeedPresenter()
    {
        if(viewWeakReference!=null)
        {
            Log.d(TAG, "View weak reference variable has been initialized");

            if(viewWeakReference.get()!=null)
            {
                Log.d(TAG, "Activity reference does not point to null");

                NewsFeedPresenter presenter =  NewsFeedPresenter.instanceOf(viewWeakReference);

                presenter.setModel(NewsFeedModel.instanceOf(presenter));

                return presenter;
            }

            throw new NullPointerException("Reference with weak reference points to null");

        }

        throw new NullPointerException("View weak reference points to null");
    }




}
