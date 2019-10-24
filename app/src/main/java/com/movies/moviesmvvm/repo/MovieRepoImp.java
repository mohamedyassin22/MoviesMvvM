package com.movies.moviesmvvm.repo;

import com.movies.moviesmvvm.local.MoviesDao;
import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.rest.ApiInterface;
import com.movies.moviesmvvm.rest.MoviesBoundaryCallback;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class MovieRepoImp implements MovieRepo {
    private static final int PAGE_SIZE = 20;
    private ApiInterface apiServiece;
    private String apiKey;
    private String movieType;
    private MoviesDao cache;

    public MovieRepoImp(ApiInterface apiServiece, String apiKey, String movieType, MoviesDao cache) {

        this.apiServiece = apiServiece;
        this.apiKey = apiKey;
        this.movieType = movieType;
        this.cache = cache;
    }

    @Override
    public LiveData<PagedList<Movie>> loadPopular() {
        DataSource.Factory<Integer, Movie> movieFactory = cache.getPopularMovies();
        MoviesBoundaryCallback popularBoundaryCallback = new MoviesBoundaryCallback(apiServiece, cache, apiKey, movieType);
        LiveData<PagedList<Movie>> popularMovies = new LivePagedListBuilder(movieFactory, PAGE_SIZE)
                .setBoundaryCallback(popularBoundaryCallback)
                .build();

        return popularMovies;

    }

    @Override
    public LiveData<PagedList<Movie>> loadUpcoming() {
        DataSource.Factory<Integer, Movie> movieFactory = cache.getUpcomingMovies();
        MoviesBoundaryCallback upcomingBoundaryCallback = new MoviesBoundaryCallback(apiServiece, cache, apiKey, movieType);
        LiveData<PagedList<Movie>> upcomingMovies = new LivePagedListBuilder(movieFactory, PAGE_SIZE)
                .setBoundaryCallback(upcomingBoundaryCallback)
                .build();
        return upcomingMovies;

    }

    @Override
    public LiveData<PagedList<Movie>> loadNowPlaying() {
        DataSource.Factory<Integer, Movie> movieFactory = cache.getNowPlayingMovies();
        MoviesBoundaryCallback playingBoundaryCallback = new MoviesBoundaryCallback(apiServiece, cache, apiKey, movieType);
        LiveData<PagedList<Movie>> nowPlayingMovies = new LivePagedListBuilder(movieFactory, PAGE_SIZE)
                .setBoundaryCallback(playingBoundaryCallback)
                .build();

        return nowPlayingMovies;

    }

    @Override
    public LiveData<PagedList<Movie>> loadTopRated() {
        DataSource.Factory<Integer, Movie> movieFactory = cache.getTopRatedMovies();
        MoviesBoundaryCallback topBoundaryCallback = new MoviesBoundaryCallback(apiServiece, cache, apiKey, movieType);
        LiveData<PagedList<Movie>> topRatedMovies = new LivePagedListBuilder(movieFactory, PAGE_SIZE)
                .setBoundaryCallback(topBoundaryCallback)
                .build();

        return topRatedMovies;
    }
}
