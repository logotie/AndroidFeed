package com.example.admin.androidfeed.main.tasks.newsfeedwebview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.admin.androidfeed.R;
import com.example.admin.androidfeed.main.base.Constants;
import com.example.admin.androidfeed.main.base.dagger.DaggerNewsWebViewMvpComponent;
import com.example.admin.androidfeed.main.base.dagger.NewsWebViewMvpComponent;
import com.example.admin.androidfeed.main.base.dagger.module.NewsWebViewMvpModule;

import java.lang.ref.WeakReference;

/**
 * I used this as a guide: https://github.com/codepath/android_guides/wiki/Creating-and-Using-Fragments
 *
 * Display a news article clicked from the News Feed.
 * Uses a webview to display the article.
 * Dagger is used to handle loading loading of presenter.
 *
 * Uses the MVP framework.
 * Activity acts as the view.  Presenter and Model are created/initializes as seperate classes.
 * Created by Admin on 21/03/2017.
 */

public class NewsWebViewFragment extends Fragment implements NewsWebViewInterfaceContract.View
{

    private WebView newsArticleWebView;
    private WebSettings webViewSettings;

    private NewsWebViewPresenter newsWebViewPresenter;

    private final static String TAG = NewsWebViewFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        Log.d(TAG, "On create view called");

        setHasOptionsMenu(false);

        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.layout_newsfeed_webview, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        Log.d(TAG, "On view created called");

        //Retrieve arguments in bundle
        Bundle fragmentBundle = getArguments();
        String selectedArticleUrl = fragmentBundle.getString(Constants.NewsWebViewFragmentConstants.ARTICLEURL.getConstant());

        if(selectedArticleUrl==null)
        {
            throw new NullPointerException("Selected Article Url points to null");
        }

        Log.d(TAG, "Url of article received is: "+selectedArticleUrl);

        //Retrieve web view
        newsArticleWebView = (WebView) getActivity().findViewById(R.id.newsArticle_webview);

        //Enable javascript on webview
        webViewSettings = newsArticleWebView.getSettings();
        webViewSettings.setJavaScriptEnabled(true);

        setUpMvp();

        newsWebViewPresenter.handleNewsArticleUrlReceived(selectedArticleUrl);
    }

    /**
     * Uses Dagger 2 dependency injection to resolve the presenter object for the view
     */
    private void setUpMvp()
    {
        Log.d(TAG, "Set up Mvp called");

        //Creates a weak reference for the dagger component builder
        WeakReference<NewsWebViewFragment> newsWebViewActivityWeakReference = new WeakReference<NewsWebViewFragment>(this);
        NewsWebViewMvpComponent component = DaggerNewsWebViewMvpComponent.builder().newsWebViewMvpModule(new NewsWebViewMvpModule(newsWebViewActivityWeakReference)).build();
        newsWebViewPresenter = component.providesNewsWebViewPresenter();
    }

    /**
     * Receives string denoting url and loads on web view
     *
     * @param webViewUrl String representing url
     */
    @Override
    public void setWebViewUrlOnView(String webViewUrl)
    {
        Log.d(TAG, "Received request to load url for: "+webViewUrl);
        newsArticleWebView.loadUrl(webViewUrl);
    }
}
