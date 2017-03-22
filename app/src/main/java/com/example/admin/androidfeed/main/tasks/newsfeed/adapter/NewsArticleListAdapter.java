package com.example.admin.androidfeed.main.tasks.newsfeed.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.androidfeed.R;
import com.example.admin.androidfeed.main.tasks.newsfeed.model.NewsArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * I used this source as a guide: http://www.coderefer.com/android-recyclerview-cardview-tutorial/
 *
 * Created by Admin on 20/03/2017.
 */

public class NewsArticleListAdapter extends RecyclerView.Adapter<NewsArticleListAdapter.ViewHolder>
{
    private static List<NewsArticle> newsArticleList = new ArrayList<>();
    private Context adapterContext;

    //Reference to the individual views
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView newsArticleDescription, newsArticleTitle;
        public ImageView newsArticleMainImage;

        public ViewHolder(View v)
        {
            super(v);
            newsArticleDescription = (TextView) v.findViewById(R.id.newsArticle_summary);
            newsArticleTitle = (TextView) v.findViewById(R.id.newsArticle_headline);
            newsArticleMainImage = (ImageView) v.findViewById(R.id.newsArticle_main_image);
        }

    }

    public NewsArticleListAdapter(Context context)
    {
        adapterContext = context;
    }


    @Override
    public NewsArticleListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        //New view
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.newsfeed_item,viewGroup,false);

        //set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Replaces view contents
    @Override
    public void onBindViewHolder(NewsArticleListAdapter.ViewHolder viewHolder, int position)
    {
        //Retrieve element from arraylist and  replace appropriate fields
        NewsArticle newsArticle = newsArticleList.get(position);
        viewHolder.newsArticleTitle.setText(String.valueOf(newsArticle.getHeadlineOverride()));
        viewHolder.newsArticleDescription.setText(newsArticle.getShortHeadline());

        if(!newsArticle.getImage().getUrl().equals(""))
        {
            Glide.with(adapterContext)
                    .load(newsArticle.getImage().getUrl()).centerCrop().crossFade()
                    .into(viewHolder.newsArticleMainImage);
        }
    }

    public void setArticleList(List<NewsArticle> articleList)
    {
        clear();
        this.newsArticleList = articleList;
        notifyDataSetChanged();
    }

    public void clear()
    {
        newsArticleList.clear();
    }

    @Override
    public int getItemCount()
    {
        return newsArticleList.size();
    }

    /**
     * Retrieve news article object, stored in the list.
     *
     * @param position int specifying the position of the object
     * @return NewsArticle object specified at the position
     */
    public NewsArticle getNewsArticle(int position)
    {
        return newsArticleList.get(position);
    }

}



