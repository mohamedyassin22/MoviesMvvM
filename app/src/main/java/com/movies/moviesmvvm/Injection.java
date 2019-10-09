package com.movies.moviesmvvm;

import android.content.Context;

import com.movies.moviesmvvm.repo.MovieRepo;
import com.movies.moviesmvvm.repo.MovieRepoImp;
import com.movies.moviesmvvm.rest.ApiClient;
import com.movies.moviesmvvm.rest.ApiInterface;

public class Injection {

    public static MovieRepo provideMovieRepository(Context context) {
        return new MovieRepoImp(provideAPIService(), provideAPIKey(context));
    }

    private static ApiInterface provideAPIService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

    private static String provideAPIKey(Context context) {
        return context.getString(R.string.api_key);
    }
}
