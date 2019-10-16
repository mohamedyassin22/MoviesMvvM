package com.movies.moviesmvvm.model;


import com.movies.moviesmvvm.rest.ApiInterface;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.movies.moviesmvvm.Util.NOW_PLAYING;
import static com.movies.moviesmvvm.Util.POPULAR;
import static com.movies.moviesmvvm.Util.TOP_RATED;
import static com.movies.moviesmvvm.Util.UPCOMING;

public class MoviesDataSource extends PageKeyedDataSource<Integer, Movie> {

    private ApiInterface service;
    private String apiKey;
    private String movieType;

    MoviesDataSource(ApiInterface service, String apiKey, String movieType) {
        this.service = service;
        this.apiKey = apiKey;
        this.movieType = movieType;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Integer, Movie> callback) {

        switch (movieType) {
            case POPULAR:
                Observable<MoviesResponse> popularCall = service.getPopularMovies(apiKey, 1);
                popularCall.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MoviesResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(MoviesResponse moviesResponse) {

                                if (moviesResponse.getResults() != null) {


                                    int currentPageNum = moviesResponse.getPage();
                                    int totalNumOfPages = moviesResponse.getTotalPages();

                                    if (totalNumOfPages > currentPageNum)
                                        callback.onResult(moviesResponse.getResults(), null, currentPageNum + 1);

                                    else
                                        callback.onResult(moviesResponse.getResults(), null, null);

                                } else {
                                    callback.onResult(new ArrayList<Movie>(), null, null);
                                }
                            }
//


                            @Override
                            public void onError(Throwable e) {
                                callback.onResult(new ArrayList<Movie>(), null, null);

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case UPCOMING:
                Observable<MoviesResponse> upcomingCall = service.getUpcomingMovies(apiKey, 1);
                upcomingCall.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MoviesResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(MoviesResponse moviesResponse) {

                                if (moviesResponse.getResults() != null) {


                                    int currentPageNum = moviesResponse.getPage();
                                    int totalNumOfPages = moviesResponse.getTotalPages();

                                    if (totalNumOfPages > currentPageNum)
                                        callback.onResult(moviesResponse.getResults(), null, currentPageNum + 1);

                                    else
                                        callback.onResult(moviesResponse.getResults(), null, null);

                                } else {
                                    callback.onResult(new ArrayList<Movie>(), null, null);
                                }
                            }
//


                            @Override
                            public void onError(Throwable e) {
                                callback.onResult(new ArrayList<Movie>(), null, null);

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case NOW_PLAYING:
                Observable<MoviesResponse> nowPlayingCall = service.getNowPlayingMovies(apiKey, 1);
                nowPlayingCall.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MoviesResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(MoviesResponse moviesResponse) {

                                if (moviesResponse.getResults() != null) {


                                    int currentPageNum = moviesResponse.getPage();
                                    int totalNumOfPages = moviesResponse.getTotalPages();

                                    if (totalNumOfPages > currentPageNum)
                                        callback.onResult(moviesResponse.getResults(), null, currentPageNum + 1);

                                    else
                                        callback.onResult(moviesResponse.getResults(), null, null);

                                } else {
                                    callback.onResult(new ArrayList<Movie>(), null, null);
                                }
                            }
//


                            @Override
                            public void onError(Throwable e) {
                                callback.onResult(new ArrayList<Movie>(), null, null);

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case TOP_RATED:
                Observable<MoviesResponse> topRatedCall = service.getTopRatedMovies(apiKey, 1);
                topRatedCall.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MoviesResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(MoviesResponse moviesResponse) {

                                if (moviesResponse.getResults() != null) {


                                    int currentPageNum = moviesResponse.getPage();
                                    int totalNumOfPages = moviesResponse.getTotalPages();

                                    if (totalNumOfPages > currentPageNum)
                                        callback.onResult(moviesResponse.getResults(), null, currentPageNum + 1);

                                    else
                                        callback.onResult(moviesResponse.getResults(), null, null);

                                } else {
                                    callback.onResult(new ArrayList<Movie>(), null, null);
                                }
                            }
//


                            @Override
                            public void onError(Throwable e) {
                                callback.onResult(new ArrayList<Movie>(), null, null);

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

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Integer, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull LoadCallback<Integer, Movie> callback) {

        switch (movieType) {
            case POPULAR:
                Observable<MoviesResponse> popularCall = service.getPopularMovies(apiKey, params.key);
                popularCall.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MoviesResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(MoviesResponse moviesResponse) {

                                if (moviesResponse.getResults() != null) {


                                    int currentPageNum = moviesResponse.getPage();
                                    int totalNumOfPages = moviesResponse.getTotalPages();

                                    if (totalNumOfPages > currentPageNum)
                                        callback.onResult(moviesResponse.getResults(), currentPageNum + 1);

                                    else
                                        callback.onResult(moviesResponse.getResults(), null);

                                } else {
                                    callback.onResult(new ArrayList<Movie>(), null);
                                }
                            }
//


                            @Override
                            public void onError(Throwable e) {
                                callback.onResult(new ArrayList<Movie>(), null);

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case UPCOMING:
                Observable<MoviesResponse> upcomingCall = service.getUpcomingMovies(apiKey, params.key);
                upcomingCall.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MoviesResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(MoviesResponse moviesResponse) {

                                if (moviesResponse.getResults() != null) {


                                    int currentPageNum = moviesResponse.getPage();
                                    int totalNumOfPages = moviesResponse.getTotalPages();

                                    if (totalNumOfPages > currentPageNum)
                                        callback.onResult(moviesResponse.getResults(), currentPageNum + 1);

                                    else
                                        callback.onResult(moviesResponse.getResults(), null);

                                } else {
                                    callback.onResult(new ArrayList<Movie>(), null);
                                }
                            }


                            @Override
                            public void onError(Throwable e) {
                                callback.onResult(new ArrayList<Movie>(), null);

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case NOW_PLAYING:
                Observable<MoviesResponse> nowPlayingCall = service.getNowPlayingMovies(apiKey, params.key);
                nowPlayingCall.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MoviesResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(MoviesResponse moviesResponse) {

                                if (moviesResponse.getResults() != null) {


                                    int currentPageNum = moviesResponse.getPage();
                                    int totalNumOfPages = moviesResponse.getTotalPages();

                                    if (totalNumOfPages > currentPageNum)
                                        callback.onResult(moviesResponse.getResults(), currentPageNum + 1);

                                    else
                                        callback.onResult(moviesResponse.getResults(), null);

                                } else {
                                    callback.onResult(new ArrayList<Movie>(), null);
                                }
                            }
//


                            @Override
                            public void onError(Throwable e) {
                                callback.onResult(new ArrayList<Movie>(), null);

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case TOP_RATED:
                Observable<MoviesResponse> topRatedCall = service.getTopRatedMovies(apiKey, params.key);
                topRatedCall.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MoviesResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(MoviesResponse moviesResponse) {

                                if (moviesResponse.getResults() != null) {


                                    int currentPageNum = moviesResponse.getPage();
                                    int totalNumOfPages = moviesResponse.getTotalPages();

                                    if (totalNumOfPages > currentPageNum)
                                        callback.onResult(moviesResponse.getResults(), currentPageNum + 1);

                                    else
                                        callback.onResult(moviesResponse.getResults(), null);

                                } else {
                                    callback.onResult(new ArrayList<Movie>(), null);
                                }
                            }
//


                            @Override
                            public void onError(Throwable e) {
                                callback.onResult(new ArrayList<Movie>(), null);

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
}
