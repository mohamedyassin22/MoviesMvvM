package com.movies.moviesmvvm.view_model;

import com.movies.moviesmvvm.repo.MovieRepo;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private MovieRepo movieRepo;
    private String movieType;

    public MainViewModelFactory(MovieRepo movieRepo, String movieType) {
        this.movieRepo = movieRepo;
        this.movieType = movieType;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(movieRepo, movieType);
    }
}
