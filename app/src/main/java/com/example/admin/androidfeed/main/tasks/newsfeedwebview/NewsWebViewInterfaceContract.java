package com.example.admin.androidfeed.main.tasks.newsfeedwebview;

/**
 * Created by Admin on 22/03/2017.
 */

public class NewsWebViewInterfaceContract
{
    interface View extends ViewOperationsForPresenter
    {

    }

    interface ViewOperationsForPresenter
    {
        void setWebViewUrlOnView(String webViewUrl);
    }

    interface Presenter extends PresenterOperationsForModel, PresenterOperationsForView
    {

    }

    interface PresenterOperationsForView
    {
        void handleNewsArticleUrlReceived(String url);
    }

    interface PresenterOperationsForModel
    {

    }

    interface Model extends ModelOperationsForPresenter
    {

    }

    interface ModelOperationsForPresenter
    {

    }
}
