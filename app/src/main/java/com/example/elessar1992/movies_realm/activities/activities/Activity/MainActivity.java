package com.example.elessar1992.movies_realm.activities.activities.Activity;

import android.app.SearchManager;
import android.app.Service;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.elessar1992.movies_realm.R;
import com.example.elessar1992.movies_realm.activities.activities.Model.Movie;
import com.example.elessar1992.movies_realm.activities.activities.Model.MoviesResponse;
import com.example.elessar1992.movies_realm.activities.activities.api.Client;
import com.example.elessar1992.movies_realm.activities.activities.api.myService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    public static final String Tag = MoviesAdapter.class.getName();
    private final static String API_KEY = "5bf838270fac55799c2a21e65505ae5b";

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.navigator_action);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (API_KEY.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please obtain API Key", Toast.LENGTH_LONG).show();
            return;
        }

        NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {
                int id = menuItem.getItemId();
                switch (id)
                {
                    case R.id.mostPopular:
                        getMostPopularResponse();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.upcoming:
                        upcomingResponse();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.topRated:
                        getTopRatedResponse();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nowPlaying:
                        nowPlayingResponse();
                        drawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search_action).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                searchResponse(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.editProfile:
                //Intent editIntent = new Intent(getApplicationContext(), EditProfile.class);
                //startActivity(editIntent);
                return true;
            case R.id.signOut:
                Intent signoutIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(signoutIntent);
                return true;
            case R.id.favorite:
                upcomingResponse();
                return true;
            case R.id.itemSales:
                upcomingResponse();
                return true;



        }
        // Handle item selection
        return super.onOptionsItemSelected(item);
    }


    private void searchResponse(String query)
    {
        //it should be here, otherwise, not working
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        myService service = Client.getClient().create(myService.class);
        Call<MoviesResponse> call = service.searchMovies(API_KEY, query);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                Log.d(Tag, "my responce is .. " + response.body().getResults().get(0).getBackdrop_path());
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(Tag, t.toString());
            }
        });
    }

    private void getMostPopularResponse()
    {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        myService service = Client.getClient().create(myService.class);
        Call<MoviesResponse> call = service.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                Log.d(Tag, "my responce is .. " + response.body().getResults().get(0).getBackdrop_path());
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(Tag, t.toString());
            }
        });
    }

    private void getTopRatedResponse()
    {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        myService service = Client.getClient().create(myService.class);
        Call<MoviesResponse> call = service.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                Log.d(Tag, "my responce is .. " + response.body().getResults().get(0).getBackdrop_path());
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(Tag, t.toString());
            }
        });
    }
    private void upcomingResponse()
    {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        myService service = Client.getClient().create(myService.class);
        Call<MoviesResponse> call = service.getUpcoming(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                Log.d(Tag, "my responce is .. " + response.body().getResults().get(0).getBackdrop_path());
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(Tag, t.toString());
            }
        });
    }

    private void nowPlayingResponse()
    {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        myService service = Client.getClient().create(myService.class);
        Call<MoviesResponse> call = service.getNowPlaying(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                Log.d(Tag, "my responce is .. " + response.body().getResults().get(0).getBackdrop_path());
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(Tag, t.toString());
            }
        });
    }
}
