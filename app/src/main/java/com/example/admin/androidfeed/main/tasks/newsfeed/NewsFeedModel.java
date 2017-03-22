package com.example.admin.androidfeed.main.tasks.newsfeed;


import android.util.Log;

import com.example.admin.androidfeed.main.base.Constants;
import com.example.admin.androidfeed.main.base.MyApplication;
import com.example.admin.androidfeed.main.base.backgroundjob.BackgroundJobCallback;
import com.example.admin.androidfeed.main.base.backgroundjob.manager.BackgroundJobManager;


import com.example.admin.androidfeed.main.base.web.AsyncWebResponseCallback;
import com.example.admin.androidfeed.main.base.web.WebJsonRequest;
import com.example.admin.androidfeed.main.tasks.newsfeed.model.NewsArticle;
import com.example.admin.androidfeed.main.tasks.newsfeed.model.NewsFeed;

import java.util.List;

/**
 * NewsFeedModel implements:
 * AsyncWebResponseCallback - to receive String web response from Volley singleton asynchronously.
 * BackgroundJobCallBack - receives an interface callback once json string processing has been completed on another thread.
 *
 * Created by Admin on 19/03/2017.
 */

public class NewsFeedModel implements NewsFeedInterfaceContract.Model, AsyncWebResponseCallback, BackgroundJobCallback
{
    private static NewsFeedPresenter newsFeedPresenter;
    private final static String TAG = NewsFeedModel.class.getSimpleName();

    public static NewsFeedModel instanceOf(NewsFeedPresenter presenter)
    {
        Log.d(TAG, "Received request to create a new instance of NewsFeedModel");
        return new NewsFeedModel(presenter);
    }

    private NewsFeedModel(NewsFeedPresenter presenter)
    {
        Log.d(TAG, "News feed model initialized");
        newsFeedPresenter = presenter;
    }

    /**
     * Request a feed load from the web api
     *
     * @param feedType Enum constant denoting specific web request.
     */
    @Override
    public void requestArticlesForFeed(Constants.FeedTypes feedType) {

        Log.d(TAG, "Received request to handle feed load for feedtype: "+feedType.name());

        //Proceed to perform a get request
        WebJsonRequest.INSTANCE.getRequest(this, feedType.getFeedUrl(), MyApplication.getContext());

        newsFeedPresenter.setActivityViewToLoading(true);

        newsFeedPresenter.setActionBarTitle(feedType);
    }

    /**
     * Receives clicked NewsArticle, retrieves Url and calls appropriate methods to trigger
     * webview fragment.
     *
     * @param newsArticle NewsArticle model object representing clicked news article.
     */
    @Override
    public void handleRecyclerViewClick(NewsArticle newsArticle)
    {
        Log.d(TAG, "Received news article that was clicked with title: "+newsArticle.getHeadline());

        String webViewUrl = newsArticle.getUrl();

        newsFeedPresenter.articleUrlReceived(webViewUrl);
    }

    /**
     * Interface callback method used when a web response has been received from the server.
     *
     * @param feedResponseJson Json String denoting web feed response json.
     */
    @Override
    public void feedResponseReceived(String feedResponseJson) {

        Log.d(TAG, "Received json response back: "+feedResponseJson);

        Log.d(TAG, "Add background job request to Background job manager");

        //Adds a background job request to process the Json string on another thread.
        BackgroundJobManager.addBackgroundJob(this, feedResponseJson);
    }

    /**
     * Interface callback method for 'BackgroundJobManager'.
     * Method is called once, the background processing of the json is complete.
     *
     * Calls the appropriate method on the presenter to pass the generated news articles.
     *
     * @param newsFeed NewsFeed object, which contains all articles contained in the news feed.
     */
    @Override
    public void newsFeedJsonProcessingCompleted(NewsFeed newsFeed)
    {
        Log.d(TAG, "Received news Feed from background job manager");

        List<NewsArticle> newsArticlesList = newsFeed.getArticles();

        newsFeedPresenter.newsFeedArticlesGenerated(newsArticlesList);

        //Switches the view away from the 'loading' state
        newsFeedPresenter.setActivityViewToLoading(false);
    }
}
