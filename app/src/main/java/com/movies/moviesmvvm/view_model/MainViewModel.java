package com.movies.moviesmvvm.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.movies.moviesmvvm.model.ExpandableList;
import com.movies.moviesmvvm.repo.MovieRepo;

import java.util.List;

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<ExpandableList>> expandableLists;


    public MainViewModel(MovieRepo movieRepo) {

        expandableLists = movieRepo.loadMovies();
    }


    public LiveData<List<ExpandableList>> getMovies() {
        return expandableLists;
    }


}
