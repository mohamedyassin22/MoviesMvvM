package com.movies.moviesmvvm.rest;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit retofit = null;

   public static Retrofit getClient(){
       if(retofit==null){
           retofit = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                   .build();
       }
       return retofit;
   }

}
