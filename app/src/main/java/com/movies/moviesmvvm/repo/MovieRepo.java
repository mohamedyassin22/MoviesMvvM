package com.movies.moviesmvvm.repo;

import androidx.lifecycle.LiveData;

import com.movies.moviesmvvm.model.Movie;

import java.util.List;

public interface MovieRepo {
    LiveData<List<Movie>> loadPopular();

    LiveData<List<Movie>> loadUpcoming();

    LiveData<List<Movie>> loadNowPlaying();

    LiveData<List<Movie>> loadTopRated();

}
