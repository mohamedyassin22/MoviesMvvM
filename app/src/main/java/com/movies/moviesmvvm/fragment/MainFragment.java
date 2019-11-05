package com.movies.moviesmvvm.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.movies.moviesmvvm.Injection;
import com.movies.moviesmvvm.R;
import com.movies.moviesmvvm.adapter.MovieAdapter;
import com.movies.moviesmvvm.databinding.FragmentMainBinding;
import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.view_model.MainViewModel;
import com.movies.moviesmvvm.view_model.MainViewModelFactory;
import com.movies.moviesmvvm.worker.MoviesWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.movies.moviesmvvm.utils.Util.WORK_TYPE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public static final String MOVIE_TYPE = "movieType";
    MovieAdapter moviesAdapter;
    MainViewModel mainViewModel;
    private List<Movie> mMovies = new ArrayList<>();
    private String movieType;

    public static MainFragment newInstance(String movieType) {

        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MOVIE_TYPE, movieType);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        moviesAdapter = new MovieAdapter();
        FragmentMainBinding fragmentMainBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        fragmentMainBinding.moviesRecyclerView.setVisibility(View.GONE);
        fragmentMainBinding.pbLoading.setVisibility(View.VISIBLE);
        fragmentMainBinding.setAdapter(moviesAdapter);
        if (getArguments() != null) {
            movieType = getArguments().getString(MOVIE_TYPE);
            mainViewModel = ViewModelProviders.of(this,
                    new MainViewModelFactory(Injection.provideMovieRepository(getActivity(), movieType), movieType))
                    .get(MainViewModel.class);
        }

        mainViewModel.getMovies().observe(this, movies -> {
            if (movies != null) {
                moviesAdapter.submitList(movies);
                fragmentMainBinding.moviesRecyclerView.setVisibility(View.VISIBLE);
                fragmentMainBinding.pbLoading.setVisibility(View.GONE);
            } else {
                fragmentMainBinding.pbLoading.setVisibility(View.GONE);
                Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_LONG).show();
            }

        });

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                //.setRequiresCharging(true)
                // .setRequiresBatteryNotLow(true)
                //.setRequiresStorageNotLow(true)
                .build();


        //If you need to send data to worker
        Data source = new Data.Builder()
                .putString(WORK_TYPE, movieType)
                .build();
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MoviesWorker.class, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .setInputData(source)
                .build();
        WorkManager.getInstance().enqueue(periodicWorkRequest);
        return fragmentMainBinding.getRoot();
    }

}
