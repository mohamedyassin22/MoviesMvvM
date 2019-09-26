package com.movies.moviesmvvm.view_model;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private String mApiKey;

    public MainViewModelFactory(String mApiKey) {
        this.mApiKey = mApiKey;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(mApiKey);
    }
}
