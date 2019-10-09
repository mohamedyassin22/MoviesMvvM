package com.movies.moviesmvvm.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.movies.moviesmvvm.model.ExpandableList;
import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.model.MoviesResponse;
import com.movies.moviesmvvm.rest.ApiInterface;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieRepoImp implements MovieRepo {

    private ApiInterface apiServiece;
    private String apiKey;

    public MovieRepoImp(ApiInterface apiServiece, String apiKey) {

        this.apiServiece = apiServiece;
        this.apiKey = apiKey;
    }

    @Override
    public LiveData<List<ExpandableList>> loadMovies() {
        final MutableLiveData<List<ExpandableList>> expandableLists = new MutableLiveData<>();
        Observable<MoviesResponse> popularcall = apiServiece.getPopularMovies(apiKey);
        popularcall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoviesResponse moviesResponse) {
                        List<Movie> movies = null;
                        if (moviesResponse.getResults() != null) {
                            movies = moviesResponse.getResults();
                            final ExpandableList popularMovies = new ExpandableList("PopularMovies", movies);
                            Observable<MoviesResponse> upcomingCall = apiServiece.getUpcomingMovies(apiKey);
                            upcomingCall.subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<MoviesResponse>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(MoviesResponse moviesResponse) {
                                            List<Movie> movies = null;
                                            if (moviesResponse.getResults() != null) {
                                                movies = moviesResponse.getResults();
                                                final ExpandableList upcomingMovies = new ExpandableList("Upcoming", movies);
                                                Observable<MoviesResponse> nowPlayingCall = apiServiece.getNowPlayingMovies(apiKey);
                                                nowPlayingCall.subscribeOn(Schedulers.io())
                                                        .observeOn(AndroidSchedulers.mainThread())
                                                        .subscribe(new Observer<MoviesResponse>() {
                                                            @Override
                                                            public void onSubscribe(Disposable d) {

                                                            }

                                                            @Override
                                                            public void onNext(MoviesResponse moviesResponse) {
                                                                List<Movie> movies = null;
                                                                if (moviesResponse.getResults() != null) {
                                                                    movies = moviesResponse.getResults();
                                                                    final ExpandableList nowPlayingMovies = new ExpandableList("Now Playing", movies);
                                                                    Observable<MoviesResponse> topRatedCall = apiServiece.getTopRatedMovies(apiKey);
                                                                    topRatedCall.subscribeOn(Schedulers.io())
                                                                            .observeOn(AndroidSchedulers.mainThread())
                                                                            .subscribe(new Observer<MoviesResponse>() {
                                                                                @Override
                                                                                public void onSubscribe(Disposable d) {

                                                                                }

                                                                                @Override
                                                                                public void onNext(MoviesResponse moviesResponse) {
                                                                                    List<Movie> movies = null;
                                                                                    if (moviesResponse.getResults() != null) {
                                                                                        movies = moviesResponse.getResults();
                                                                                        ExpandableList topRatedMovies = new ExpandableList("Top Rated", movies);
                                                                                        expandableLists.setValue(Collections.singletonList(popularMovies));
                                                                                        expandableLists.setValue(Collections.singletonList(upcomingMovies));
                                                                                        expandableLists.setValue(Collections.singletonList(nowPlayingMovies));
                                                                                        expandableLists.setValue(Collections.singletonList(topRatedMovies));
                                                                                    } else {
                                                                                        expandableLists.setValue(null);
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onError(Throwable e) {
                                                                                    expandableLists.setValue(null);

                                                                                }

                                                                                @Override
                                                                                public void onComplete() {

                                                                                }
                                                                            });

                                                                } else {
                                                                    expandableLists.setValue(null);
                                                                }
                                                            }

                                                            @Override
                                                            public void onError(Throwable e) {
                                                                expandableLists.setValue(null);

                                                            }

                                                            @Override
                                                            public void onComplete() {

                                                            }
                                                        });

                                            } else {
                                                expandableLists.setValue(null);
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            expandableLists.setValue(null);

                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                        } else {
                            expandableLists.setValue(null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        expandableLists.setValue(null);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return expandableLists;
    }
}
