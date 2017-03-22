package com.example.admin.androidfeed.main.tasks.newsfeed;

import com.example.admin.androidfeed.main.base.Constants;
import com.example.admin.androidfeed.main.tasks.newsfeed.model.NewsArticle;

import java.util.List;

/**
 * MVP Interface contracts between presenter, view & model.
 *
 * Used to define the available public operations between different parts of the framework.
 *
 * I seperated the operations based on which class requires the operations -> PresenterOperationsForView,
 * PresenterOperationsForModel.
 * I created a base interface that extends the specific operation groups needed.
 *
 * Created by Admin on 19/03/2017.
 */

public interface NewsFeedInterfaceContract {

    interface View extends ViewOperationsForPresenter
    {

    }

    interface ViewOperationsForPresenter
    {
        void setActivityViewToLoading(boolean action);
        void newsArticlesReceived(List<NewsArticle> newsArticleList);
        void clickedArticleUrlReceived(String articleUrl);
        void setActionBarTitle(Constants.FeedTypes feedType);
    }

    interface Presenter extends PresenterOperationsForModel, PresenterOperationsForView
    {

    }

    interface PresenterOperationsForView
    {
        void requestArticlesForFeed(Constants.FeedTypes feedType);
        void handleRecyclerViewClick(NewsArticle newsArticle);
    }

    interface PresenterOperationsForModel
    {
        void newsFeedArticlesGenerated(List<NewsArticle> newsArticleList);
        void setActivityViewToLoading(boolean action);
        void articleUrlReceived(String url);
        void setActionBarTitle(Constants.FeedTypes feedType);
    }

    interface Model extends ModelOperationsForPresenter
    {

    }

    interface ModelOperationsForPresenter
    {
        void requestArticlesForFeed(Constants.FeedTypes feedType);
        void handleRecyclerViewClick(NewsArticle newsArticle);
    }





}
