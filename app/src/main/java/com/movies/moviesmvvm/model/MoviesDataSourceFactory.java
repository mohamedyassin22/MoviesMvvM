package com.movies.moviesmvvm.model;


import com.movies.moviesmvvm.rest.ApiInterface;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class MoviesDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<MoviesDataSource> sourceLiveData = new MutableLiveData<>();

    private ApiInterface service;
    private String apiKey;
    private String movieType;

    public MoviesDataSourceFactory(ApiInterface service, String apiKey, String movieType) {
        this.service = service;
        this.apiKey = apiKey;
        this.movieType = movieType;
    }

    @Override
    public DataSource create() {
        MoviesDataSource moviesSource = new MoviesDataSource(service, apiKey, movieType);
        sourceLiveData.postValue(moviesSource);
        return moviesSource;
    }
}
