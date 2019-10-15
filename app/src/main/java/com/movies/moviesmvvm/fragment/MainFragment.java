package com.movies.moviesmvvm.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.movies.moviesmvvm.Injection;
import com.movies.moviesmvvm.R;
import com.movies.moviesmvvm.adapter.MovieAdapter;
import com.movies.moviesmvvm.databinding.FragmentMainBinding;
import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.view_model.MainViewModel;
import com.movies.moviesmvvm.view_model.MainViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import static com.movies.moviesmvvm.Util.NOW_PLAYING;
import static com.movies.moviesmvvm.Util.POPULAR;
import static com.movies.moviesmvvm.Util.TOP_RATED;
import static com.movies.moviesmvvm.Util.UPCOMING;

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

        moviesAdapter = new MovieAdapter(mMovies);
        FragmentMainBinding fragmentMainBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        fragmentMainBinding.moviesRecyclerView.setVisibility(View.GONE);
        fragmentMainBinding.pbLoading.setVisibility(View.VISIBLE);
        fragmentMainBinding.setAdapter(moviesAdapter);
        if (getArguments() != null) {
            movieType = getArguments().getString(MOVIE_TYPE);
            switch (movieType) {
                case POPULAR:
                    mainViewModel = ViewModelProviders.of(this,
                            new MainViewModelFactory(Injection.provideMovieRepository(getActivity()), POPULAR))
                            .get(MainViewModel.class);
                    break;
                case UPCOMING:
                    mainViewModel = ViewModelProviders.of(this,
                            new MainViewModelFactory(Injection.provideMovieRepository(getActivity()), UPCOMING))
                            .get(MainViewModel.class);
                    break;
                case NOW_PLAYING:
                    mainViewModel = ViewModelProviders.of(this,
                            new MainViewModelFactory(Injection.provideMovieRepository(getActivity()), NOW_PLAYING))
                            .get(MainViewModel.class);
                    break;
                case TOP_RATED:
                    mainViewModel = ViewModelProviders.of(this,
                            new MainViewModelFactory(Injection.provideMovieRepository(getActivity()), TOP_RATED))
                            .get(MainViewModel.class);
                    break;
                default:
                    mainViewModel = ViewModelProviders.of(this,
                            new MainViewModelFactory(Injection.provideMovieRepository(getActivity()), POPULAR))
                            .get(MainViewModel.class);


            }
        }

        mainViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies != null) {
                    moviesAdapter.addItem(movies);
                    fragmentMainBinding.moviesRecyclerView.setVisibility(View.VISIBLE);
                    fragmentMainBinding.pbLoading.setVisibility(View.GONE);
                } else {
                    fragmentMainBinding.pbLoading.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_LONG).show();
                }
            }
        });
        return fragmentMainBinding.getRoot();
    }

}
