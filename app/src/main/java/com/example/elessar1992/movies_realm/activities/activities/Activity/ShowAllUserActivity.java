package com.example.elessar1992.movies_realm.activities.activities.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.elessar1992.movies_realm.R;
import com.example.elessar1992.movies_realm.activities.activities.Model.User;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by elessar1992 on 3/9/18.
 */

public class ShowAllUserActivity extends AppCompatActivity
{
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showallusers);
        mRealm = Realm.getDefaultInstance();
        RealmResults<User> modules = mRealm.where(User.class).findAll();
        if (modules.size() == 0)
        {
            mRealm.beginTransaction();
            mRealm.createAllFromJson(User.class,
                    "[ { id:100, value:100 }, {id:200, value:200} ]");
            mRealm.commitTransaction();
            modules = mRealm.where(User.class).findAll();
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerViewUsers);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UserAdapter(this, mRealm, modules));
    }
}
