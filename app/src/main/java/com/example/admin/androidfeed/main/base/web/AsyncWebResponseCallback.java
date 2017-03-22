package com.example.admin.androidfeed.main.base.web;

/**
 * Interface for the purpose of asynchronous web response callback methods.
 *
 * Created by Admin on 19/03/2017.
 */

public interface AsyncWebResponseCallback
{
    void feedResponseReceived(String feedResponseJson);
}
