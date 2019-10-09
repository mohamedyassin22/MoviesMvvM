package com.movies.moviesmvvm.repo;

import android.arch.lifecycle.LiveData;

import com.movies.moviesmvvm.model.ExpandableList;

import java.util.List;

public interface MovieRepo {
    LiveData<List<ExpandableList>> loadMovies();
}
