package com.movies.moviesmvvm.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.repo.MovieRepo;

import java.util.List;

import static com.movies.moviesmvvm.utils.Util.NOW_PLAYING;
import static com.movies.moviesmvvm.utils.Util.POPULAR;
import static com.movies.moviesmvvm.utils.Util.TOP_RATED;
import static com.movies.moviesmvvm.utils.Util.UPCOMING;

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
                break;
        }


    }


    public LiveData<List<Movie>> getMovies() {
        return movieLists;
    }


}
