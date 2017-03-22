package com.example.admin.androidfeed.main.tasks.newsfeed;

/**
 * I used this as a guide: https://github.com/codepath/android_guides/wiki/Creating-and-Using-Fragments
 *
 * Created by Admin on 21/03/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.admin.androidfeed.R;
import com.example.admin.androidfeed.main.base.BaseNewsFeedActivity;
import com.example.admin.androidfeed.main.base.Constants;
import com.example.admin.androidfeed.main.base.ItemClickSupport;

import com.example.admin.androidfeed.main.base.dagger.DaggerNewsFeedMvpComponent;
import com.example.admin.androidfeed.main.base.dagger.NewsFeedMvpComponent;
import com.example.admin.androidfeed.main.base.dagger.module.NewsFeedMvpModule;
import com.example.admin.androidfeed.main.tasks.newsfeed.adapter.NewsArticleListAdapter;
import com.example.admin.androidfeed.main.tasks.newsfeed.model.NewsArticle;

import java.lang.ref.WeakReference;
import java.util.List;


/**
 * Starting fragment for activity.
 * Displays the News articles loaded from the Web Api.
 * Dagger is used to handle loading loading of presenter.
 *
 * Uses the MVP framework.
 * Activity acts as the view.  Presenter and Model are created/initializes as seperate classes.
 */
public class NewsFeedFragment extends Fragment implements NewsFeedInterfaceContract.View{

    //String TAG used for logging purposes
    private final static String TAG = NewsFeedFragment.class.getSimpleName();

    private NewsFeedPresenter newsFeedPresenter;
    private RecyclerView newsFeedRecyclerView;
    private NewsArticleListAdapter newsArticleAdapter;

    //Store the user's currently selected section;
    private Constants.FeedTypes selectedFeedType;

    private OnNewsArticleSelected newsArticleSelectedCallback;

    private RelativeLayout newsFeedLayoutContents;
    private ProgressBar newsFeedProgressBar;

    // Container Activity must implement this interface, to allow fragment to Activity communication for article url.
    public interface OnNewsArticleSelected {
        public void newsArticleSelected(String newsArticleUrl);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment

        View newsFeedFragmentView =  inflater.inflate(R.layout.layout_news_feed, parent, false);

        setHasOptionsMenu(true);

        return newsFeedFragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        // Setup any handles to view objects here
        newsFeedProgressBar = (ProgressBar)getActivity().findViewById(R.id.progressBar_newsFeedActivity);
        newsFeedRecyclerView = (RecyclerView)getActivity().findViewById(R.id.recyclerView_NewsFeed);

        setUpMvp();

        if(newsFeedPresenter==null)
        {
            throw new NullPointerException("MVP Presenter points to null");
        }

        initRecyclerView();

        //Checks whether the user has previously selected a feed type
        if(selectedFeedType!=null)
        {
            //Request the feedtype the user has selected
            newsFeedPresenter.requestArticlesForFeed(selectedFeedType);
        }
        else
        {
            //User has not selected a feedtype and we load the default 'FrontPage' feedtype.
            //Requests the articles for the field specified in the enum Constant.
            newsFeedPresenter.requestArticlesForFeed(Constants.FeedTypes.FRONTPAGE);
        }
    }

    /**
     * Uses Dagger 2 dependency injection to resolve the presenter object for the view
     */
    private void setUpMvp()
    {
        //Creates a weak reference for the dagger component builder
        WeakReference<NewsFeedFragment> newsFeedActivityWeakReference = new WeakReference<NewsFeedFragment>(this);
        NewsFeedMvpComponent component = DaggerNewsFeedMvpComponent.builder().newsFeedMvpModule(new NewsFeedMvpModule(newsFeedActivityWeakReference)).build();
        newsFeedPresenter = component.providesNewsFeedPresenter();
    }

    /**
     * Initializes the recycler view & newsarticlelist adapter of the activity, with a linear layout.
     */
    private void initRecyclerView()
    {
        Log.d(TAG, "Received request to initialize recycler view of the news feed activity");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        newsFeedRecyclerView.setLayoutManager(layoutManager);

        newsArticleAdapter = new NewsArticleListAdapter(getActivity());

        newsFeedRecyclerView.setAdapter(newsArticleAdapter);

        ItemClickSupport.addTo(newsFeedRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Log.d(TAG, "Detected on click for position: "+position);

                handleOnClickOfRecyclerView(recyclerView, position);

            }
        });

    }


    /**
     * Sets the layout to a 'loading' state, based on the boolean received.
     *
     * @param action boolean specifying whether the view should be switched to a loading state.
     */
    @Override
    public void setActivityViewToLoading(boolean action) {

        Log.d(TAG, "Received request to set view to loading for action: "+action);

        if(action)
        {
            newsFeedRecyclerView.setVisibility(View.GONE);
            newsFeedProgressBar.setVisibility(View.VISIBLE);
        }
        else
        {
            newsFeedProgressBar.setVisibility(View.GONE);
            newsFeedRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Receives list of newsArticle objects asynchronously from the presenter.
     * On receipt of list, sets the list on the attached adapter.
     *
     * @param newsArticleList List representing news Articles from the requested newsfeed.
     */
    @Override
    public void newsArticlesReceived(List<NewsArticle> newsArticleList)
    {
        Log.d(TAG, "News articles have been received of size: "+newsArticleList.size());

        if(newsArticleAdapter==null)
        {
            throw new NullPointerException("News Article adapter points to null");
        }

        newsArticleAdapter.setArticleList(newsArticleList);
    }

    /**
     * Receives url of clicked article.
     * Url will be sent to container activity
     *
     * @param articleUrl String representing the article url of the clicked news article.
     */
    @Override
    public void clickedArticleUrlReceived(String articleUrl)
    {
        Log.d(TAG, "Clicked article url received is: "+articleUrl);

        if(newsArticleSelectedCallback==null)
        {
            throw new NullPointerException("news article selected callback points to null");
        }

        newsArticleSelectedCallback.newsArticleSelected(articleUrl);
    }

    /**
     * Sets the action bar title, to the title denoted in the constant
     *
     * @param feedType Constant representing feedType selected by the user
     */
    @Override
    public void setActionBarTitle(Constants.FeedTypes feedType) {
        Log.d(TAG, "Received request to set action bar title to: "+feedType.getFeedName());

        ((BaseNewsFeedActivity)getActivity()).getSupportActionBar().setTitle(feedType.getFeedName());
    }

    /**
     * Adds an onclick listener to the recycler view returned from the presenter.
     */
    private void handleOnClickOfRecyclerView(RecyclerView recyclerView, int position)
    {
        Log.d(TAG, "Received on click event for recycler view for position: "+position);

        NewsArticleListAdapter currentAdapter = (NewsArticleListAdapter) recyclerView.getAdapter();

        NewsArticle newsArticle = currentAdapter.getNewsArticle(position);

        Log.d(TAG, "Retrieved news article with headline: "+newsArticle.getHeadline());

        newsFeedPresenter.handleRecyclerViewClick(newsArticle);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;

        if (context instanceof Activity){
            activity=(Activity) context;

            // This makes sure that the container activity has implemented
            // the callback interface. If not, it throws an exception

            try {
                newsArticleSelectedCallback = (OnNewsArticleSelected) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnHeadlineSelectedListener");
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        menu.clear();
        inflater.inflate(R.menu.menu_news_feed, menu);

        super.onCreateOptionsMenu(menu, inflater);

        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(newsFeedPresenter==null)
        {
            throw new NullPointerException("MVP Presenter points to null");
        }

        //Based on what the user has selected, we load the appropriate feedtype and store the selection as an instance variable
        switch (item.getItemId()) {
            case R.id.action_business_newsfeed:
                selectedFeedType = Constants.FeedTypes.BUSINESS;
                newsFeedPresenter.requestArticlesForFeed(Constants.FeedTypes.BUSINESS);
                return true;

            case R.id.action_frontpage_newsfeed:
                selectedFeedType = Constants.FeedTypes.FRONTPAGE;
                newsFeedPresenter.requestArticlesForFeed(Constants.FeedTypes.FRONTPAGE);
                return true;

            case R.id.action_sports_newsfeed:
                selectedFeedType = Constants.FeedTypes.SPORTS;
                newsFeedPresenter.requestArticlesForFeed(Constants.FeedTypes.SPORTS);
                return true;

            case R.id.action_world_newsfeed:
                selectedFeedType = Constants.FeedTypes.WORLD;
                newsFeedPresenter.requestArticlesForFeed(Constants.FeedTypes.WORLD);
                return true;

            default:
                break;
        }

        return false;
    }





}
