package com.example.admin.androidfeed.main.tasks.newsfeed;


import android.util.Log;

import com.example.admin.androidfeed.main.base.Constants;
import com.example.admin.androidfeed.main.tasks.newsfeed.model.NewsArticle;


import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Presenter for NewsFeedActivity, receives a weak reference of the activity during initilization.
 *
 * Created by Admin on 19/03/2017.
 */

public class NewsFeedPresenter implements NewsFeedInterfaceContract.Presenter
{
    private final static String TAG = NewsFeedPresenter.class.getSimpleName();
    private WeakReference<NewsFeedFragment> newsFeedActivityWeakReference;
    private NewsFeedModel model;

    public static NewsFeedPresenter instanceOf(WeakReference<NewsFeedFragment> activityWeakReference)
    {
        Log.d(TAG, "Received request to create a new instance of NewsFeedPresenter");
        return new NewsFeedPresenter(activityWeakReference);
    }

    private NewsFeedPresenter(WeakReference<NewsFeedFragment> activityWeakReference)
    {
        Log.d(TAG, "News feed presenter initialized");
        newsFeedActivityWeakReference = activityWeakReference;
    }

    public void setModel(NewsFeedModel model)
    {
        Log.d(TAG, "Model set on presenter");
        this.model = model;
    }

    /**
     * Receives a list of news article objects asynchronously from the Model.
     * Then proceeds to call a method on the view, to handle the list of news articles.
     *
     * @param newsArticleList List Of NewsArticles generated.
     */
    @Override
    public void newsFeedArticlesGenerated(List<NewsArticle> newsArticleList) {

        Log.d(TAG, "Received generated news feed articles of size: "+newsArticleList.size());

        if(newsFeedActivityWeakReference.get()!=null)
        {
            newsFeedActivityWeakReference.get().newsArticlesReceived(newsArticleList);
        }
    }

    /**
     * Calls the appropriate method on the view, to switch to the loading view based on the parameter
     * passed
     *
     * @param action Boolean representing whether the view should be switched to 'loading' state or not.
     */
    @Override
    public void setActivityViewToLoading(boolean action) {

        Log.d(TAG, "Received request to set view to loading of action: "+action);

        if(newsFeedActivityWeakReference.get()!=null)
        {
            newsFeedActivityWeakReference.get().setActivityViewToLoading(action);
        }

    }

    /**
     * Receives clicked news article url from model, sends to presenter
     *
     * @param url String url of news article.
     */
    @Override
    public void articleUrlReceived(String url) {

        Log.d(TAG, "Url received from model: "+url);

        if(newsFeedActivityWeakReference.get()!=null)
        {
            Log.d(TAG, "Activity weak reference does not resolve to null");

            newsFeedActivityWeakReference.get().clickedArticleUrlReceived(url);
        }
    }

    /**
     * Sets the appropriate action bar title on the view, based on the feedtype selected
     *
     * @param feedType
     */
    @Override
    public void setActionBarTitle(Constants.FeedTypes feedType) {

        Log.d(TAG, "Received a request to set the title to: "+feedType.getFeedName());

        if(newsFeedActivityWeakReference.get()!=null)
        {
            Log.d(TAG, "Activity weak reference does not resolve to null");

            newsFeedActivityWeakReference.get().setActionBarTitle(feedType);
        }
    }

    /**
     * A request to load the feed from the web api
     *
     * @param feedType Enum representing the different feed types.
     */
    @Override
    public void requestArticlesForFeed(Constants.FeedTypes feedType) {
        Log.d(TAG, "Received request to handle first load for page type: "+feedType.name());

        model.requestArticlesForFeed(feedType);
    }

    /**
     * Receives Newsarticle that was clicked on the view and calls appropriate method on model
     *
     * @param newsArticle NewsArticle model object representing clicked article
     */
    @Override
    public void handleRecyclerViewClick(NewsArticle newsArticle) {

        Log.d(TAG, "Handle recycler view clicked for: "+newsArticle.getHeadline());

        model.handleRecyclerViewClick(newsArticle);
    }
}
