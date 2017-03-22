package com.example.admin.androidfeed.main.base.backgroundjob;

import com.example.admin.androidfeed.main.tasks.newsfeed.model.NewsFeed;

/**
 * Interface class to store background job callback methods.
 *
 * Created by Admin on 19/03/2017.
 */

public interface BackgroundJobCallback {

    public void newsFeedJsonProcessingCompleted(NewsFeed newsFeed);

}
