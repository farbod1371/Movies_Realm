package com.example.elessar1992.movies_realm.activities.activities.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.elessar1992.movies_realm.R;
import com.example.elessar1992.movies_realm.activities.activities.Model.Movie;
import com.example.elessar1992.movies_realm.activities.activities.Model.User;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.example.elessar1992.movies_realm.activities.activities.Activity.MainActivity.Tag;

/**
 * Created by elessar1992 on 3/8/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder>
{
    private Context context;
    private List<Movie> movies;
    private int rowLayout;
    private RealmResults<User> myResults;
    private Realm mRealm;
    private final ClickListener listener;
    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context, ClickListener listener)
    {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        this.listener = listener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder //implements View.OnClickListener
    {
        public TextView title;
        public TextView releaseDate;
        public TextView overview;
        public TextView score;
        public TextView popular;
        public Button favorite;
        public Button save;
        public ImageView thumbnail;
        LinearLayout moviesLayout;
        private WeakReference<ClickListener> listenerRef;


        public MyViewHolder(View view, ClickListener listener)
        {
            super(view);
            listenerRef = new WeakReference<>(listener);
            favorite = (Button) view.findViewById(R.id.favorite);
            save = (Button) view.findViewById(R.id.save);
            title = (TextView) view.findViewById(R.id.title);
            releaseDate = (TextView) view.findViewById(R.id.releaseDate);
            overview = (TextView) view.findViewById(R.id.overview);
            score = (TextView) view.findViewById(R.id.score);
            //context = view.getContext();
            //popular = (TextView) view.findViewById(R.id.popular);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            moviesLayout = (LinearLayout) view.findViewById(R.id.movies_Layout);
            //save.setOnClickListener(this);
            //favorite.setOnClickListener(this);
        }


        /*@Override
        public void onClick(View v)
        {
            if(v.getId() == favorite.getId())
            {
                Intent accountsIntent = new Intent(context, RegistrationActivity.class);
                context.startActivity(accountsIntent);
                //saveFavorite();
                //Toast.makeText(v.getContext(), "favorite PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            if(v.getId() == save.getId())
            {
                Toast.makeText(v.getContext(), "save PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            listenerRef.get().onPositionClicked(getAdapterPosition());

        }*/


    }



    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false),listener);
        //return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder,final int i)
    {
        viewHolder.title.setText(movies.get(i).getTitle());
        viewHolder.releaseDate.setText(movies.get(i).getRelease_date());
        viewHolder.overview.setText(movies.get(i).getOverview());
        viewHolder.overview.setMovementMethod(new ScrollingMovementMethod());
        viewHolder.score.setText(movies.get(i).getVote_average().toString());
        viewHolder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent moreinfo = new Intent(context, MoreInfoActivity.class);
                moreinfo.putExtra("TITLE",movies.get(i).getTitle());
                moreinfo.putExtra("REALEASEDATE",movies.get(i).getRelease_date());
                moreinfo.putExtra("OVERVIEW",movies.get(i).getOverview());
                moreinfo.putExtra("SCORE",movies.get(i).getVote_average().toString());
                moreinfo.putExtra("IMAGE",movies.get(i).getPoster_path());
                context.startActivity(moreinfo);

            }
        });

        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/original/" + movies.get(i).getPoster_path())
                //.resize(100,100)
                //.placeholder(R.drawable.placeholder2)
                .into(viewHolder.thumbnail);


    }



    @Override
    public int getItemCount()
    {
        return movies.size();
    }

}
