package com.example.admin.androidfeed.main.tasks.newsfeed.model;

/**
 * Model class created using: http://www.jsonschema2pojo.org/
 *
 * Created by Admin on 19/03/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.admin.androidfeed.main.tasks.newsfeed.model.childmodel.Feeds;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "feeds",
        "articles",
        "section",
        "url"
})
public class NewsFeed implements Parcelable {

    @JsonProperty("feeds")
    private Feeds feeds;
    @JsonProperty("articles")
    private List<NewsArticle> articles = new ArrayList<>();
    @JsonProperty("section")
    private String section;
    @JsonProperty("url")
    private String url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("feeds")
    public Feeds getFeeds() {
        return feeds;
    }

    @JsonProperty("feeds")
    public void setFeeds(Feeds feeds) {
        this.feeds = feeds;
    }

    @JsonProperty("articles")
    public List<NewsArticle> getArticles() {
        return articles;
    }

    @JsonProperty("articles")
    public void setArticles(List<NewsArticle> articles) {
        this.articles = articles;
    }

    @JsonProperty("section")
    public String getSection() {
        return section;
    }

    @JsonProperty("section")
    public void setSection(String section) {
        this.section = section;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.feeds, flags);
        dest.writeList(this.articles);
        dest.writeString(this.section);
        dest.writeString(this.url);
//        dest.writeInt(this.additionalProperties.size());
//        for (Map.Entry<String, Object> entry : this.additionalProperties.entrySet()) {
//            dest.writeString(entry.getKey());
//            dest.writeParcelable(entry.getValue(), flags);
//        }
    }

    public NewsFeed() {
    }

    protected NewsFeed(Parcel in) {
        this.feeds = in.readParcelable(Feeds.class.getClassLoader());
        this.articles = new ArrayList<NewsArticle>();
        in.readList(this.articles, NewsArticle.class.getClassLoader());
        this.section = in.readString();
        this.url = in.readString();
//        int additionalPropertiesSize = in.readInt();
//        this.additionalProperties = new HashMap<String, Object>(additionalPropertiesSize);
//        for (int i = 0; i < additionalPropertiesSize; i++) {
//            String key = in.readString();
//            Object value = in.readParcelable(Object.class.getClassLoader());
//            this.additionalProperties.put(key, value);
//        }
    }

    public static final Parcelable.Creator<NewsFeed> CREATOR = new Parcelable.Creator<NewsFeed>() {
        @Override
        public NewsFeed createFromParcel(Parcel source) {
            return new NewsFeed(source);
        }

        @Override
        public NewsFeed[] newArray(int size) {
            return new NewsFeed[size];
        }
    };
}
