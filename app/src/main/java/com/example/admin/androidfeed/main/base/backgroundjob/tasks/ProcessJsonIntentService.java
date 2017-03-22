package com.example.admin.androidfeed.main.base.backgroundjob.tasks;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.admin.androidfeed.main.base.MyApplication;
import com.example.admin.androidfeed.main.base.web.helper.JsonHelper;
import com.example.admin.androidfeed.main.tasks.newsfeed.model.NewsFeed;

import static com.example.admin.androidfeed.main.base.Constants.BackgrounJobIntentConstants.BACKGROUND_JOB_IDENT;
import static com.example.admin.androidfeed.main.base.Constants.BackgrounJobIntentConstants.JOBSTRING;
import static com.example.admin.androidfeed.main.base.Constants.BackgrounJobIntentConstants.RESULT;
import static com.example.admin.androidfeed.main.base.Constants.BackgrounJobIntentConstants.UNIQUEJOBID;


/**
 * Background intent service for processing Json text off the main thread.
 *
 * Created by Admin on 19/03/2017.
 */

public class ProcessJsonIntentService extends IntentService
{
    private final static String TAG = ProcessJsonIntentService.class.getSimpleName();
    private static String jobUniqueId;

    public ProcessJsonIntentService() {
        super(ProcessJsonIntentService.class.getSimpleName());
    }
    /**
     * Receives intent and stores job unique id as class variable, once the intensive process of generating the models from the Json has completed
     * we will send the newsfeed back to Background job manager.
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent)
    {
        Log.d(TAG, "Received request to process background job in intent service");
        jobUniqueId = intent.getStringExtra(UNIQUEJOBID.getConstantValue());
        String jsonStringToProcess = intent.getStringExtra(JOBSTRING.getConstantValue());
        Log.d(TAG, "Job Unique Id: "+jobUniqueId);
        Log.d(TAG, "Json String to process: "+jsonStringToProcess);
        NewsFeed newsFeed = processNewsFeedJson(jsonStringToProcess);
        sendIntentToBackgroundJobManager(newsFeed);
    }

    /**
     * Converts the json string into a model object
     *
     * @param newsFeedJsonString Json string representing news feed object
     * @return NewsFeed model object.
     */
    private NewsFeed processNewsFeedJson(String newsFeedJsonString)
    {
        Log.d(TAG, "Received request to process adverts json");
        final NewsFeed newsFeed = JsonHelper.generateNewsFeedObj(newsFeedJsonString);
        return newsFeed;
    }

    /**
     * Sends the parcelable NewsFeed model object back to the Background job manager,
     * with the uniquejobId stored in the iuntent.
     *
     * @param newsFeed NewsFeed model object.
     */
    private void sendIntentToBackgroundJobManager(NewsFeed newsFeed)
    {
        Log.d(TAG, "Received request to send intent to background job manager");
        Intent processedAdvertsIntent = new Intent();
        processedAdvertsIntent.setAction(BACKGROUND_JOB_IDENT.getConstantValue());
        processedAdvertsIntent.putExtra(UNIQUEJOBID.getConstantValue(), jobUniqueId);
        processedAdvertsIntent.putExtra(RESULT.getConstantValue(), newsFeed);
        LocalBroadcastManager.getInstance(MyApplication.getContext()).sendBroadcast(processedAdvertsIntent);
    }
}
