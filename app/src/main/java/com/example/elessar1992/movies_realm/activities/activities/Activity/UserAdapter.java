package com.example.elessar1992.movies_realm.activities.activities.Activity;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elessar1992.movies_realm.R;
import com.example.elessar1992.movies_realm.activities.activities.Model.User;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by elessar1992 on 3/9/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>
{
    private LayoutInflater mInflater;
    private Realm mRealm;
    private RealmResults<User> mResults;

    public UserAdapter(Context context, Realm realm, RealmResults<User> results)
    {
        mRealm = realm;
        mInflater = LayoutInflater.from(context);
        setResults(results);
    }

    public User getItem(int position) {
        return mResults.get(position);
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_all_users, parent, false);
        UserHolder userHolder = new UserHolder(view);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position)
    {
        User user = mResults.get(position);
        //holder.textViewId.setText(user.getId());
        holder.textViewEmail.setText(user.getEmail());
        holder.textViewName.setText(user.getUsername());
        holder.textViewPassword.setText(user.getPassword());
    }

    @Override
    public int getItemCount()
    {
        return mResults.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder
    {
        //public AppCompatTextView textViewId;
        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;

        public UserHolder(View view)
        {
            super(view);
            //textViewId = (AppCompatTextView) view.findViewById(R.id.textViewID) ;
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewPassword);
        }
    }

    public void setResults(RealmResults<User> results)
    {
        mResults = results;
        notifyDataSetChanged();
    }

    /*public void add()
    {
        User user = new User();
        user.setFirstname(user.getFirstname());
        user.setLastname(user.getLastname());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setEmail(user.getEmail());

        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(user);
        mRealm.commitTransaction();
        notifyDataSetChanged();
    }

    public void remove(int position)
    {

        //Start a transaction
        mRealm.beginTransaction();

        //Remove the item from the desired position
        mResults.deleteFromRealm(position);

        //Commit the transaction
        mRealm.commitTransaction();

        //Tell the Adapter to update what it shows
        notifyItemRemoved(position);
    }*/
}
