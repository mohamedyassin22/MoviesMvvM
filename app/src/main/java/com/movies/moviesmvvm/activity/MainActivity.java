package com.movies.moviesmvvm.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.movies.moviesmvvm.R;
import com.movies.moviesmvvm.adapter.movieExpandableListAdapter;
import com.movies.moviesmvvm.databinding.ActivityMainBinding;
import com.movies.moviesmvvm.model.ExpandableList;
import com.movies.moviesmvvm.view_model.MainViewModel;
import com.movies.moviesmvvm.view_model.MainViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String API_KEY;
    private Context mContext;
    List<ExpandableList> expandableLists = new ArrayList<>();

    movieExpandableListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        API_KEY = getString(R.string.api_key);
        mContext = this;
        listAdapter = new movieExpandableListAdapter(mContext, expandableLists);
        activityMainBinding.lvExp.setVisibility(View.GONE);
        activityMainBinding.pbLoading.setVisibility(View.VISIBLE);
        activityMainBinding.setAdapter(listAdapter);
        final MainViewModel mainViewModel = ViewModelProviders.of(this, new MainViewModelFactory(API_KEY)).get(MainViewModel.class);


        mainViewModel.getMovies().observe(this, new Observer<List<ExpandableList>>() {
            @Override
            public void onChanged(@Nullable List<ExpandableList> expandableLists) {
                if (expandableLists != null) {
                    listAdapter.addItem(expandableLists);
                    activityMainBinding.lvExp.setVisibility(View.VISIBLE);
                    activityMainBinding.pbLoading.setVisibility(View.GONE);
                } else {
                    activityMainBinding.pbLoading.setVisibility(View.GONE);
                    Toast.makeText(mContext, R.string.error, Toast.LENGTH_LONG).show();
                }

            }
        });


    }


}

