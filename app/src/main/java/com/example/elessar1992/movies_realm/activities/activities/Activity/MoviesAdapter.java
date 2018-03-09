package com.example.elessar1992.movies_realm.activities.activities.Activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.elessar1992.movies_realm.R;
import com.example.elessar1992.movies_realm.activities.activities.Model.Movie;

import java.util.List;

/**
 * Created by elessar1992 on 3/8/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder>
{
    private Context context;
    private List<Movie> movies;
    private int rowLayout;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title;
        public TextView releaseDate;
        public TextView overview;
        public TextView score;
        public TextView popular;
        public ImageView thumbnail;
        LinearLayout moviesLayout;

        public MyViewHolder(View view)
        {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            releaseDate = (TextView) view.findViewById(R.id.releaseDate);
            overview = (TextView) view.findViewById(R.id.overview);
            score = (TextView) view.findViewById(R.id.score);
            //popular = (TextView) view.findViewById(R.id.popular);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            moviesLayout = (LinearLayout) view.findViewById(R.id.movies_Layout);
        }
    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context)
    {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i)
    {
        viewHolder.title.setText(movies.get(i).getTitle());
        viewHolder.releaseDate.setText(movies.get(i).getRelease_date());
        viewHolder.overview.setText(movies.get(i).getOverview());
        viewHolder.overview.setMovementMethod(new ScrollingMovementMethod());
        viewHolder.score.setText(movies.get(i).getVote_average().toString());
        //viewHolder.popular.setText(movies.get(i).getPopularity().toString());
        //String poster = "https://image.tmdb.org/t/p/original" + movies.get(i).getPosterPath();
        //String image = movies.get(i).getId() + movies.get(i).getPosterPath();
        //String image = movies.get(i).getPosterPath();
        /*Glide.with(context)
                .load(image)
                .placeholder(R.drawable.load)
                .into(viewHolder.thumbnail);*/

        /*Picasso.with(context)
                .load(poster)
                .placeholder(R.drawable.load)
                .into(viewHolder.thumbnail);*/



    }

    @Override
    public int getItemCount()
    {
        return movies.size();
    }
}
