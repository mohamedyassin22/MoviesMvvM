package com.movies.moviesmvvm.view_model;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.movies.moviesmvvm.model.ExpandableList;
import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.model.MoviesResponse;
import com.movies.moviesmvvm.rest.ApiClient;
import com.movies.moviesmvvm.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();

    List<ExpandableList> expandableLists = new ArrayList<>();
    private String apiKey;

    public MainViewModel(String apiKey) {
        this.apiKey = apiKey;
    }

    public void loadMovies(final OnDataLoadListener onDataLoadListener) {
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
                            ExpandableList popularMovies = new ExpandableList("PopularMovies", movies);
                            expandableLists.add(popularMovies);
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
                                                ExpandableList upcomingMovies = new ExpandableList("Upcoming", movies);
                                                expandableLists.add(upcomingMovies);
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
                                                                    ExpandableList nowPlayingMovies = new ExpandableList("Now Playing", movies);
                                                                    expandableLists.add(nowPlayingMovies);
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
                                                                                        expandableLists.add(topRatedMovies);
                                                                                        onDataLoadListener.onSuccess(expandableLists);
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onError(Throwable e) {
                                                                                    onDataLoadListener.onFailure();

                                                                                }

                                                                                @Override
                                                                                public void onComplete() {

                                                                                }
                                                                            });

                                                                }
                                                            }

                                                            @Override
                                                            public void onError(Throwable e) {
                                                                onDataLoadListener.onFailure();

                                                            }

                                                            @Override
                                                            public void onComplete() {

                                                            }
                                                        });

                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            onDataLoadListener.onFailure();

                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        onDataLoadListener.onFailure();

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public interface OnDataLoadListener {

        void onSuccess(List<ExpandableList> expandableLists);

        void onFailure();
    }

}
