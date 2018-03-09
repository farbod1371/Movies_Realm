package com.example.elessar1992.movies_realm.activities.activities.api;

import com.example.elessar1992.movies_realm.activities.activities.Model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by elessar1992 on 3/8/18.
 */

public interface myService
{
    @GET("search/movie")
    Call<MoviesResponse> searchMovies(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcoming(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlaying(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id , @Query("api_key") String apiKey);
}
