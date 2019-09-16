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
import com.movies.moviesmvvm.model.HeaderList;
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
    List<HeaderList>  listDataHeader = new ArrayList<>();
    HashMap<String, List<Movie>> listDataChild  = new HashMap<>();
    HeaderList headerList;

    movieExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expListView = findViewById(R.id.lvExp);
//        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        API_KEY = getString(R.string.api_key);
        mContext = this;
        headerList=new HeaderList("PopularMovies",0);

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = service.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {

                List<Movie> movies = null;
                if (response.body() != null) {
                    listDataHeader.add(headerList);
                    movies = response.body().getResults();
                    listDataChild.put(listDataHeader.get(0).getHeader(),movies);
                    //ToDo 21: set data binding adapter with new instance of Movie Adapter
                    listAdapter=new movieExpandableListAdapter(mContext,
                            listDataHeader,listDataChild,movies);
                    expListView.setAdapter(listAdapter);
                    // Listview Group click listener
                    expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                        @Override
                        public boolean onGroupClick(ExpandableListView parent, View v,
                                                    int groupPosition, long id) {
                            // Toast.makeText(getApplicationContext(),
                            // "Group Clicked " + listDataHeader.get(groupPosition),
                            // Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    });

                    // Listview Group expanded listener
                    expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                        @Override
                        public void onGroupExpand(int groupPosition) {
                            Toast.makeText(getApplicationContext(),
                                    listDataHeader.get(groupPosition) + " Expanded",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
//                    activityMainBinding.setAdapter(new movieExpandableListAdapter(mContext,
//                            listDataHeader,listDataChild,movies));
                    //recyclerView.setAdapter(new MoviesAdapter(mContext, movies));
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                Log.e("error",t.getMessage());
            }
        });
    }
    }

