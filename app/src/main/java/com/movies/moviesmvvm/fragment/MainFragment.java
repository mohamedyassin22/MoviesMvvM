package com.movies.moviesmvvm.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.movies.moviesmvvm.Injection;
import com.movies.moviesmvvm.R;
import com.movies.moviesmvvm.adapter.MovieAdapter;
import com.movies.moviesmvvm.databinding.FragmentMainBinding;
import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.view_model.MainViewModel;
import com.movies.moviesmvvm.view_model.MainViewModelFactory;

import java.util.ArrayList;
import java.util.List;

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
        return fragmentMainBinding.getRoot();
    }

}
