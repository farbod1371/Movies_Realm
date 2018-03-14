package com.example.elessar1992.movies_realm.activities.activities.Activity;

import android.content.Context;
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


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
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
            //popular = (TextView) view.findViewById(R.id.popular);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            moviesLayout = (LinearLayout) view.findViewById(R.id.movies_Layout);
            save.setOnClickListener(this);
            favorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            if(v.getId() == favorite.getId())
            {
                Toast.makeText(v.getContext(), "favorite PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            if(v.getId() == save.getId())
            {
                Toast.makeText(v.getContext(), "save PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            listenerRef.get().onPositionClicked(getAdapterPosition());

        }

        @Override
        public boolean onLongClick(View v)
        {
            if(v.getId() == favorite.getId())
            {
                Toast.makeText(v.getContext(), "favorite long PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            if(v.getId() == save.getId())
            {
                Toast.makeText(v.getContext(), "save long PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            listenerRef.get().onLongClicked(getAdapterPosition());
            return true;
        }

        /*public void saveData()
        {
            mRealm.executeTransactionAsync(new Realm.Transaction()
            {
                @Override
                public void execute(Realm bgrealm) {
                    User user = bgrealm.createObject(User.class);
                    //user.setFirstname(user.getFirstname());
                    user.setEventTitle(title.getText().toString().trim());
                    user.setEventScore(score.getText().toString().trim());
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    Log.d(Tag, "onSuccess");
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    Log.d(Tag, "onError");
                }
            });
        }*/
    }



    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false),listener);
        //return new MyViewHolder(view);
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
