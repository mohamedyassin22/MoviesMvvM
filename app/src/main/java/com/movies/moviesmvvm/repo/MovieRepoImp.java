package com.movies.moviesmvvm.repo;

import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.model.MoviesDataSourceFactory;
import com.movies.moviesmvvm.rest.ApiInterface;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class MovieRepoImp implements MovieRepo {
    private static final int PAGE_SIZE = 20;
    private ApiInterface apiServiece;
    private String apiKey;
    private String movieType;

    public MovieRepoImp(ApiInterface apiServiece, String apiKey, String movieType) {

        this.apiServiece = apiServiece;
        this.apiKey = apiKey;
        this.movieType = movieType;
    }

    @Override
    public LiveData<PagedList<Movie>> loadPopular() {
        MoviesDataSourceFactory dataSourceFactory = new MoviesDataSourceFactory(apiServiece, apiKey, movieType);
        return (LiveData<PagedList<Movie>>) new LivePagedListBuilder<>(dataSourceFactory, PAGE_SIZE).build();

    }

    @Override
    public LiveData<PagedList<Movie>> loadUpcoming() {
        MoviesDataSourceFactory dataSourceFactory = new MoviesDataSourceFactory(apiServiece, apiKey, movieType);
        return (LiveData<PagedList<Movie>>) new LivePagedListBuilder<>(dataSourceFactory, PAGE_SIZE).build();
    }

    @Override
    public LiveData<PagedList<Movie>> loadNowPlaying() {
        MoviesDataSourceFactory dataSourceFactory = new MoviesDataSourceFactory(apiServiece, apiKey, movieType);
        return (LiveData<PagedList<Movie>>) new LivePagedListBuilder<>(dataSourceFactory, PAGE_SIZE).build();
    }

    @Override
    public LiveData<PagedList<Movie>> loadTopRated() {
        MoviesDataSourceFactory dataSourceFactory = new MoviesDataSourceFactory(apiServiece, apiKey, movieType);
        return (LiveData<PagedList<Movie>>) new LivePagedListBuilder<>(dataSourceFactory, PAGE_SIZE).build();
    }
}
