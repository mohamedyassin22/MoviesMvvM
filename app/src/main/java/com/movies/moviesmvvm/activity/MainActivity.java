package com.movies.moviesmvvm.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.movies.moviesmvvm.R;
import com.movies.moviesmvvm.adapter.movieExpandableListAdapter;
import com.movies.moviesmvvm.databinding.ActivityMainBinding;
import com.movies.moviesmvvm.model.ExpandableList;
import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.model.MoviesResponse;
import com.movies.moviesmvvm.rest.ApiClient;
import com.movies.moviesmvvm.rest.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static String API_KEY;
    private Context mContext;
    List<ExpandableList> expandableLists = new ArrayList<>();


    movieExpandableListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        API_KEY = getString(R.string.api_key);
        mContext = this;
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = service.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                List<Movie> movies = null;
                if (response.body() != null) {
                    movies = response.body().getResults();
                    ExpandableList popularMovies = new ExpandableList("PopularMovies", movies);
                    expandableLists.add(popularMovies);
                    listAdapter = new movieExpandableListAdapter(mContext,
                            expandableLists);
                    activityMainBinding.setAdapter(listAdapter);

                    // Listview Group expanded listener
//                    expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//                        int previousGroup = -1;
//                        @Override
//                        public void onGroupExpand(int groupPosition) {
//                            if ((previousGroup != -1) && (groupPosition != previousGroup)) {
//                                expListView.collapseGroup(previousGroup);
//                            }
//                            previousGroup = groupPosition;
//                        }
//                    });
//
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                Log.e("error", t.getMessage());
            }
        });
    }
}

