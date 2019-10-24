package com.movies.moviesmvvm.rest;


import com.movies.moviesmvvm.local.MoviesDao;
import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.model.MoviesResponse;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.movies.moviesmvvm.utils.Util.NOW_PLAYING;
import static com.movies.moviesmvvm.utils.Util.POPULAR;
import static com.movies.moviesmvvm.utils.Util.TOP_RATED;
import static com.movies.moviesmvvm.utils.Util.UPCOMING;


public class MoviesBoundaryCallback extends PagedList.BoundaryCallback<Movie> {

    private int lastRequestedPage = 1;
    private boolean isRequestInProgress = false;

    private ApiInterface service;
    private MoviesDao cache;
    private String apiKey;
    private String movieType;
    private Executor executor = Executors.newSingleThreadExecutor();

    public MoviesBoundaryCallback(ApiInterface service, MoviesDao cache, String apiKey, String movieType) {
        this.service = service;
        this.cache = cache;
        this.apiKey = apiKey;
        this.movieType = movieType;
    }

    private void requestAndSaveData() {
        switch (movieType) {
            case POPULAR:
                if (isRequestInProgress) return;

                isRequestInProgress = true;
                service.getPopularMovies(apiKey, lastRequestedPage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MoviesResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(MoviesResponse moviesResponse) {
                                isRequestInProgress = false;
                                if (moviesResponse.getResults() != null) {
                                    lastRequestedPage++;

                                    executor.execute(() -> {
                                        if (lastRequestedPage == 1)
                                            cache.deletePopular();

                                        cache.insert(moviesResponse.getResults());
                                    });

                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                isRequestInProgress = false;
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case UPCOMING:
                if (isRequestInProgress) return;

                isRequestInProgress = true;
                service.getUpcomingMovies(apiKey, lastRequestedPage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MoviesResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(MoviesResponse moviesResponse) {
                                isRequestInProgress = false;
                                if (moviesResponse.getResults() != null) {
                                    lastRequestedPage++;

                                    executor.execute(() -> {
                                        if (lastRequestedPage == 1)
                                            cache.deleteUpcoming();

                                        cache.insert(moviesResponse.getResults());
                                    });

                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                isRequestInProgress = false;
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case NOW_PLAYING:
                if (isRequestInProgress) return;

                isRequestInProgress = true;
                service.getNowPlayingMovies(apiKey, lastRequestedPage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MoviesResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(MoviesResponse moviesResponse) {
                                isRequestInProgress = false;
                                if (moviesResponse.getResults() != null) {
                                    lastRequestedPage++;

                                    executor.execute(() -> {
                                        if (lastRequestedPage == 1)
                                            cache.deleteNowPlaying();

                                        cache.insert(moviesResponse.getResults());
                                    });

                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                isRequestInProgress = false;
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case TOP_RATED:
                if (isRequestInProgress) return;

                isRequestInProgress = true;
                service.getTopRatedMovies(apiKey, lastRequestedPage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MoviesResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(MoviesResponse moviesResponse) {
                                isRequestInProgress = false;
                                if (moviesResponse.getResults() != null) {
                                    lastRequestedPage++;

                                    executor.execute(() -> {
                                        if (lastRequestedPage == 1)
                                            cache.deleteTopRated();

                                        cache.insert(moviesResponse.getResults());
                                    });

                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                isRequestInProgress = false;
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            default:
                break;
        }


    }


    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    @Override
    public void onZeroItemsLoaded() {
        requestAndSaveData();
    }

    /**
     * When all items in the database were loaded, we need to query the backend for more items.
     */
    @Override
    public void onItemAtEndLoaded(@NonNull Movie itemAtEnd) {
        requestAndSaveData();
    }


}
