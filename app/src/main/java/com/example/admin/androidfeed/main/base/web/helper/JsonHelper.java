package com.example.admin.androidfeed.main.base.web.helper;

import android.util.Log;

import com.example.admin.androidfeed.main.tasks.newsfeed.model.NewsFeed;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class created for storing utility methods regarding JSON and Model creation.
 *
 * Created by Admin on 21/03/2017.
 */

public class JsonHelper {

    //TAG string for debug purposes.
    private final static String TAG = JsonHelper.class.getSimpleName();

    /**
     * Creates a news feed model object based on the json string received
     *
     * @param newsFeedJsonString Json string received from the server
     * @return NewsFeed model object created from the Json string.
     */
    public static NewsFeed generateNewsFeedObj(String newsFeedJsonString)
    {
        Log.d(TAG, "Received request to generate News Feed model object from json string: "+newsFeedJsonString);
        NewsFeed newsFeed = null;

        try
        {
            //Object mapper for creating the object from the json string.
            ObjectMapper objMapper = new ObjectMapper();

            newsFeed = objMapper.readValue(newsFeedJsonString, NewsFeed.class);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return newsFeed;
    }


}
