package com.movies.moviesmvvm.rest;


import com.movies.moviesmvvm.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlayingMovies(@Query("api_key") String apiKey, @Query("page") int page);



}
