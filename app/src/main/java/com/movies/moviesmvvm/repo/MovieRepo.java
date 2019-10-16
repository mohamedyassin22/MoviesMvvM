package com.movies.moviesmvvm.repo;

import com.movies.moviesmvvm.model.Movie;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public interface MovieRepo {
    LiveData<PagedList<Movie>> loadPopular();

    LiveData<PagedList<Movie>> loadUpcoming();

    LiveData<PagedList<Movie>> loadNowPlaying();

    LiveData<PagedList<Movie>> loadTopRated();

}
