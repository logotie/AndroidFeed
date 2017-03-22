package com.example.admin.androidfeed.main.base;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.androidfeed.R;
import com.example.admin.androidfeed.main.tasks.newsfeed.NewsFeedFragment;
import com.example.admin.androidfeed.main.tasks.newsfeedwebview.NewsWebViewFragment;

/**
 * Starting container activity for the fragments.
 *
 * Created by Admin on 21/03/2017.
 */

public class BaseNewsFeedActivity extends AppCompatActivity
        implements NewsFeedFragment.OnNewsArticleSelected{

    private final static String TAG = BaseNewsFeedActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        // Begin the transaction, sets the default fragment 'NewsFeedFragment'
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_placeholder, new NewsFeedFragment());
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //I am returning false for these options as they will be handled in NewsWebViewFragment
        switch(id)
        {
            case R.id.action_business_newsfeed:
                Log.d(TAG, "Business dropdown selected");
                return false;
            case R.id.action_frontpage_newsfeed:
                Log.d(TAG, "Front Page dropdown selected");
                return false;
            case R.id.action_sports_newsfeed:
                Log.d(TAG, "Sports dropdown selected");
                return false;
            case R.id.action_world_newsfeed:
                Log.d(TAG, "World dropdown selected");
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Interface method permitting fragment to activity communication.
     * NewsFeedActivity will send the url of the news article to the NewsWebView fragment.
     *
     * Source: https://developer.android.com/training/basics/fragments/communicating.html#DefineInterface
     *
     * @param newsArticleUrl String representing url of news article.
     */
    @Override
    public void newsArticleSelected(String newsArticleUrl) {

        Log.d(TAG, "News article url received is: "+newsArticleUrl);

        // Create fragment and give it an argument for the selected article
        NewsWebViewFragment newsWebViewFragment = new NewsWebViewFragment();

        //Create bundle to store selected article url
        Bundle args = new Bundle();
        args.putString(Constants.NewsWebViewFragmentConstants.ARTICLEURL.getConstant(), newsArticleUrl);

        //Store bundle on fragment
        newsWebViewFragment.setArguments(args);

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_placeholder, newsWebViewFragment);
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
    }
}
