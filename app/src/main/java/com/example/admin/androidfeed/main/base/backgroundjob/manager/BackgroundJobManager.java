package com.example.admin.androidfeed.main.base.backgroundjob.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.admin.androidfeed.main.base.MyApplication;
import com.example.admin.androidfeed.main.base.backgroundjob.BackgroundJobCallback;
import com.example.admin.androidfeed.main.base.backgroundjob.tasks.ProcessJsonIntentService;
import com.example.admin.androidfeed.main.tasks.newsfeed.model.NewsFeed;

import java.util.HashMap;
import java.util.UUID;

import static com.example.admin.androidfeed.main.base.Constants.BackgrounJobIntentConstants.BACKGROUND_JOB_IDENT;
import static com.example.admin.androidfeed.main.base.Constants.BackgrounJobIntentConstants.JOBSTRING;
import static com.example.admin.androidfeed.main.base.Constants.BackgrounJobIntentConstants.RESULT;
import static com.example.admin.androidfeed.main.base.Constants.BackgrounJobIntentConstants.UNIQUEJOBID;


/**
 * Manages requests for jobs, that are to be run using an intent service.
 *
 * Created by Admin on 19/03/2017.
 */

public enum BackgroundJobManager
{
    INSTANCE;

    private static HashMap<String, BackgroundJobCallback> bckGroundJobMap = new HashMap<>();
    private static BroadcastReceiver backgroundJobBroadcastReceiver;
    private static IntentFilter backgroundJobIntentFilter;
    private static String TAG = BackgroundJobManager.class.getSimpleName();

    /**
     * Adds the callback instance with a randomly generated UUID.
     * Sends the UUID nad the json string to the intent service to be processed.
     *
     * @param callBackInstance Background job callback instance object of background job requester.
     * @param jsonFromServer Json string to be processed
     */
    public static void addBackgroundJob(BackgroundJobCallback callBackInstance, String jsonFromServer)
    {
        Log.d(TAG, "Received request to add background job to be processed");
        //Creates a unique job id, using a random UUID
        String uniqueJobId = UUID.randomUUID().toString();

        Log.d(TAG, "Unique Job Id: "+uniqueJobId);
        //Store callback instance with the unique job id.
        bckGroundJobMap.put(uniqueJobId, callBackInstance);

        //Generate intent, storing the UUID also.
        Intent backgroundJobIntent = generateJobIntent(uniqueJobId, jsonFromServer);

        sendLocalBroadcastForBackgroundJob(backgroundJobIntent);
    }

    BackgroundJobManager()
    {
        setUpBackgroundJobReceiver();
    }


    private static void setUpBackgroundJobReceiver()
    {
        Log.d(TAG, "Received request to set up background job receiver");

        //Receives Job unique Id with the completed Payload.
        backgroundJobBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                Log.d(TAG,"Received an intent result");

                String uniqueJobId = intent.getStringExtra(UNIQUEJOBID.getConstantValue());

                Log.d(TAG, "Received result for job Id: "+uniqueJobId);

                NewsFeed newsFeed = intent.getParcelableExtra(RESULT.getConstantValue());

                handleReceivedNewsFeed(uniqueJobId, newsFeed);
            }
        };

        backgroundJobIntentFilter = new IntentFilter();
        backgroundJobIntentFilter.addAction(BACKGROUND_JOB_IDENT.getConstantValue());
        LocalBroadcastManager.getInstance(MyApplication.getContext()).registerReceiver(backgroundJobBroadcastReceiver, backgroundJobIntentFilter);
    }

    /**
     * Creates an intent storing the unique job and json string to processed as intent 'extra' fields.
     *
     * @param uniqueJobId Unique Job Id associated to Json string
     * @param jsonFromServer Json string received from web api
     * @return Intent containing uniqueJobId and Json string to be processed
     */
    private static Intent generateJobIntent(String uniqueJobId, String jsonFromServer)
    {
        Log.d(TAG, "Received request to generate job intent for intent service");
        Intent backgroundJobRequestIntent = new Intent(MyApplication.getContext(), ProcessJsonIntentService.class);
        backgroundJobRequestIntent.putExtra(UNIQUEJOBID.getConstantValue(), uniqueJobId);
        backgroundJobRequestIntent.putExtra(JOBSTRING.getConstantValue(), jsonFromServer);

        return backgroundJobRequestIntent;
    }


    /**
     * Receives the created newsFeed model object and unique Job Id associated with model object.
     * Using the uniqueJob Id we can retrieve the call back instance and call the appropriate callback method
     *
     * @param uniqueJobId String representing the unique Job Id
     * @param newsFeed NewsFeed model object associated to the unique Job Id.
     */
    private static void handleReceivedNewsFeed(String uniqueJobId, NewsFeed newsFeed)
    {
        Log.d(TAG, "Received request to handle received news feed by calling call back method on object");
        if(bckGroundJobMap.containsKey(uniqueJobId)==false)
        {
            throw new NullPointerException("Key: "+uniqueJobId+" for background job is not stored in map");
        }
        Log.d(TAG, "Matching instance was found for key");
        BackgroundJobCallback receiverInstance = bckGroundJobMap.get(uniqueJobId);

        //Remove the instance, so as not to cause a mem leak.
        bckGroundJobMap.remove(uniqueJobId);

        Log.d(TAG, "Will now proceed to call callback method");
        receiverInstance.newsFeedJsonProcessingCompleted(newsFeed);
    }

    private static void sendLocalBroadcastForBackgroundJob(Intent backgroundJobIntent)
    {
        Log.d(TAG, "Received request to send local broadcast to intent service");
        MyApplication.getContext().startService(backgroundJobIntent);
    }
}
