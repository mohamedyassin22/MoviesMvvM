package com.movies.moviesmvvm.view_model;

import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.repo.MovieRepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import static com.movies.moviesmvvm.Util.NOW_PLAYING;
import static com.movies.moviesmvvm.Util.POPULAR;
import static com.movies.moviesmvvm.Util.TOP_RATED;
import static com.movies.moviesmvvm.Util.UPCOMING;

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();


    private LiveData<PagedList<Movie>> movieLists;


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


    public LiveData<PagedList<Movie>> getMovies() {
        return movieLists;
    }


}
