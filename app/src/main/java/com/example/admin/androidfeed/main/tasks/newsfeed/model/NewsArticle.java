package com.example.admin.androidfeed.main.tasks.newsfeed.model;

/**
 * NewsArticle model class created using: http://www.jsonschema2pojo.org/
 *
 * Created by Admin on 19/03/2017.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.admin.androidfeed.main.tasks.newsfeed.model.childmodel.article.Author;
import com.example.admin.androidfeed.main.tasks.newsfeed.model.childmodel.article.Image;
import com.example.admin.androidfeed.main.tasks.newsfeed.model.childmodel.article.ImageOverride;
import com.example.admin.androidfeed.main.tasks.newsfeed.model.childmodel.article.Video;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "guid",
        "state",
        "headline_override",
        "headline",
        "url",
        "link",
        "authors",
        "author_location",
        "updated_date",
        "editorial_priority",
        "short_headline",
        "sub_headline",
        "author",
        "local_caption",
        "comments_setting",
        "publish_date",
        "topics",
        "tags",
        "section",
        "section_url",
        "image",
        "image_override",
        "video",
        "body"
})
public class NewsArticle {

    @JsonProperty("guid")
    private String guid;
    @JsonProperty("state")
    private String state;
    @JsonProperty("headline_override")
    private String headlineOverride;
    @JsonProperty("headline")
    private String headline;
    @JsonProperty("url")
    private String url;
    @JsonProperty("link")
    private String link;
    @JsonProperty("authors")
    private List<Author> authors = null;
    @JsonProperty("author_location")
    private String authorLocation;
    @JsonProperty("updated_date")
    private String updatedDate;
    @JsonProperty("editorial_priority")
    private String editorialPriority;
    @JsonProperty("short_headline")
    private String shortHeadline;
    @JsonProperty("sub_headline")
    private String subHeadline;
    @JsonProperty("author")
    private String author;
    @JsonProperty("local_caption")
    private String localCaption;
    @JsonProperty("comments_setting")
    private String commentsSetting;
    @JsonProperty("publish_date")
    private String publishDate;
    @JsonProperty("topics")
    private List<String> topics = null;
    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonProperty("section")
    private String section;
    @JsonProperty("section_url")
    private String sectionUrl;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("image_override")
    private ImageOverride imageOverride;
    @JsonProperty("video")
    private Video video;
    @JsonProperty("body")
    private String body;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("guid")
    public String getGuid() {
        return guid;
    }

    @JsonProperty("guid")
    public void setGuid(String guid) {
        this.guid = guid;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("headline_override")
    public String getHeadlineOverride() {
        return headlineOverride;
    }

    @JsonProperty("headline_override")
    public void setHeadlineOverride(String headlineOverride) {
        this.headlineOverride = headlineOverride;
    }

    @JsonProperty("headline")
    public String getHeadline() {
        return headline;
    }

    @JsonProperty("headline")
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("authors")
    public List<Author> getAuthors() {
        return authors;
    }

    @JsonProperty("authors")
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @JsonProperty("author_location")
    public String getAuthorLocation() {
        return authorLocation;
    }

    @JsonProperty("author_location")
    public void setAuthorLocation(String authorLocation) {
        this.authorLocation = authorLocation;
    }

    @JsonProperty("updated_date")
    public String getUpdatedDate() {
        return updatedDate;
    }

    @JsonProperty("updated_date")
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    @JsonProperty("editorial_priority")
    public String getEditorialPriority() {
        return editorialPriority;
    }

    @JsonProperty("editorial_priority")
    public void setEditorialPriority(String editorialPriority) {
        this.editorialPriority = editorialPriority;
    }

    @JsonProperty("short_headline")
    public String getShortHeadline() {
        return shortHeadline;
    }

    @JsonProperty("short_headline")
    public void setShortHeadline(String shortHeadline) {
        this.shortHeadline = shortHeadline;
    }

    @JsonProperty("sub_headline")
    public String getSubHeadline() {
        return subHeadline;
    }

    @JsonProperty("sub_headline")
    public void setSubHeadline(String subHeadline) {
        this.subHeadline = subHeadline;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("local_caption")
    public String getLocalCaption() {
        return localCaption;
    }

    @JsonProperty("local_caption")
    public void setLocalCaption(String localCaption) {
        this.localCaption = localCaption;
    }

    @JsonProperty("comments_setting")
    public String getCommentsSetting() {
        return commentsSetting;
    }

    @JsonProperty("comments_setting")
    public void setCommentsSetting(String commentsSetting) {
        this.commentsSetting = commentsSetting;
    }

    @JsonProperty("publish_date")
    public String getPublishDate() {
        return publishDate;
    }

    @JsonProperty("publish_date")
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @JsonProperty("topics")
    public List<String> getTopics() {
        return topics;
    }

    @JsonProperty("topics")
    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("section")
    public String getSection() {
        return section;
    }

    @JsonProperty("section")
    public void setSection(String section) {
        this.section = section;
    }

    @JsonProperty("section_url")
    public String getSectionUrl() {
        return sectionUrl;
    }

    @JsonProperty("section_url")
    public void setSectionUrl(String sectionUrl) {
        this.sectionUrl = sectionUrl;
    }

    @JsonProperty("image")
    public Image getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(Image image) {
        this.image = image;
    }

    @JsonProperty("image_override")
    public ImageOverride getImageOverride() {
        return imageOverride;
    }

    @JsonProperty("image_override")
    public void setImageOverride(ImageOverride imageOverride) {
        this.imageOverride = imageOverride;
    }

    @JsonProperty("video")
    public Video getVideo() {
        return video;
    }

    @JsonProperty("video")
    public void setVideo(Video video) {
        this.video = video;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}