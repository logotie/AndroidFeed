package com.example.admin.androidfeed.main.base.dagger;

import com.example.admin.androidfeed.main.base.dagger.module.NewsFeedMvpModule;
import com.example.admin.androidfeed.main.tasks.newsfeed.NewsFeedPresenter;

import dagger.Component;

/**
 * Dagger2 component bridge used to provide the NewsFeedPresenter from the Dagger2 dependency injection.
 *
 * Created by Admin on 19/03/2017.
 */
@Component(modules = {NewsFeedMvpModule.class})
public interface NewsFeedMvpComponent {

    NewsFeedPresenter providesNewsFeedPresenter();

}
