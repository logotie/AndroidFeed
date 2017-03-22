package com.example.admin.androidfeed.main.base;

/**
 * Created by Admin on 19/03/2017.
 */

public class Constants
{

    public enum BackgrounJobIntentConstants
    {
        UNIQUEJOBID("UniqueJobId"), RESULT("Result"), BACKGROUND_JOB_IDENT("BackgroundJobIdentifier"), JOBSTRING("JobString");

        private String constantValue;

        BackgrounJobIntentConstants(String constantValue) {
            this.constantValue = constantValue;
        }

        public String getConstantValue() {
            return constantValue;
        }
    }

    public enum FeedTypes
    {
        SPORTS("http://www.independent.co.uk/api/v1/12791/json", "Sports"), FRONTPAGE("http://www.independent.co.uk/api/v1/11831/json", "Front Page"),
        WORLD("http://www.independent.co.uk/api/v1/11916/json", "World"), BUSINESS("http://www.independent.co.uk/api/v1/11981/json", "Business");

        private String feedUrl, feedName;

        public String getFeedUrl() {
            return feedUrl;
        }

        public String getFeedName()
        {
            return feedName;
        }

        FeedTypes(String feedUrl, String feedName) {
            this.feedUrl = feedUrl;
            this.feedName = feedName;
        }

    }

    public enum NewsWebViewFragmentConstants
    {
        ARTICLEURL("ArticleUrl");

        private String constant;

        NewsWebViewFragmentConstants(String constant) {
            this.constant = constant;
        }

        public String getConstant() {
            return constant;
        }
    }

}

