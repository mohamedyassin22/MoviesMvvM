package com.movies.moviesmvvm.view_model;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.movies.moviesmvvm.repo.MovieRepo;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private MovieRepo movieRepo;

    public MainViewModelFactory(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(movieRepo);
    }
}
