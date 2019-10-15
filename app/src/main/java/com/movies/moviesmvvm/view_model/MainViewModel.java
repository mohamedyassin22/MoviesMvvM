package com.movies.moviesmvvm.view_model;

import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.repo.MovieRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import static com.movies.moviesmvvm.Util.NOW_PLAYING;
import static com.movies.moviesmvvm.Util.POPULAR;
import static com.movies.moviesmvvm.Util.TOP_RATED;
import static com.movies.moviesmvvm.Util.UPCOMING;

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();


    private LiveData<List<Movie>> movieLists;


    public MainViewModel(MovieRepo movieRepo, String movieType) {

        switch (movieType) {
            case POPULAR:
                movieLists = movieRepo.loadPopular();
                break;
            case UPCOMING:
                movieLists = movieRepo.loadUpcoming();
                break;
            case NOW_PLAYING:
                movieLists = movieRepo.loadNowPlaying();
                break;
            case TOP_RATED:
                movieLists = movieRepo.loadTopRated();
                break;
            default:
                movieLists = movieRepo.loadPopular();

        }


    }


    public LiveData<List<Movie>> getMovies() {
        return movieLists;
    }


}
