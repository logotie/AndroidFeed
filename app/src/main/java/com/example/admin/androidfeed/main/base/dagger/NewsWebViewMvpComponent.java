package com.example.admin.androidfeed.main.base.dagger;

import com.example.admin.androidfeed.main.base.dagger.module.NewsWebViewMvpModule;
import com.example.admin.androidfeed.main.tasks.newsfeedwebview.NewsWebViewPresenter;

import dagger.Component;

/**
 * Dagger2 component bridge used to provide the NewsWebViewPresenter from the Dagger2 dependency injection.
 *
 * Created by Admin on 19/03/2017.
 */
@Component(modules = {NewsWebViewMvpModule.class})
public interface NewsWebViewMvpComponent {

    NewsWebViewPresenter providesNewsWebViewPresenter();

}