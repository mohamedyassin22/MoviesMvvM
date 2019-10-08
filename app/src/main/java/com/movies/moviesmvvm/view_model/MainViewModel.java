package com.movies.moviesmvvm.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.movies.moviesmvvm.model.ExpandableList;
import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.model.MoviesResponse;
import com.movies.moviesmvvm.rest.ApiClient;
import com.movies.moviesmvvm.rest.ApiInterface;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();


    private MutableLiveData<List<ExpandableList>> expandableLists = new MutableLiveData<>();
    private String apiKey;

    public MainViewModel(String apiKey) {
        this.apiKey = apiKey;
        loadMovies();
    }

    public void loadMovies() {
        // we can copy it from the MainActivity

        Log.d(TAG, "Load Movies Called");

        final ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        Observable<MoviesResponse> popularcall = service.getPopularMovies(apiKey);
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
                            Observable<MoviesResponse> upcomingCall = service.getUpcomingMovies(apiKey);
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
                                                Observable<MoviesResponse> nowPlayingCall = service.getNowPlayingMovies(apiKey);
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
                                                                    Observable<MoviesResponse> topRatedCall = service.getTopRatedMovies(apiKey);
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


    }

    public LiveData<List<ExpandableList>> getMovies() {
        return expandableLists;
    }


}
