package com.movies.moviesmvvm.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.movies.moviesmvvm.R;
import com.movies.moviesmvvm.adapter.movieExpandableListAdapter;
import com.movies.moviesmvvm.databinding.ActivityMainBinding;
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

public class MainActivity extends AppCompatActivity {
    private static String API_KEY;
    private Context mContext;
    List<ExpandableList> expandableLists = new ArrayList<>();

    movieExpandableListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        API_KEY = getString(R.string.api_key);
        mContext = this;
        final ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        activityMainBinding.lvExp.setVisibility(View.GONE);
        activityMainBinding.pbLoading.setVisibility(View.VISIBLE);


        Observable<MoviesResponse> popularcall = service.getPopularMovies(API_KEY);
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
                            Observable<MoviesResponse> upcomingCall = service.getUpcomingMovies(API_KEY);
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
                                                Observable<MoviesResponse> nowPlayingCall = service.getNowPlayingMovies(API_KEY);
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
                                                                    Observable<MoviesResponse> topRatedCall = service.getTopRatedMovies(API_KEY);
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
                                                                                        listAdapter = new movieExpandableListAdapter(mContext,
                                                                                                expandableLists);
                                                                                        listAdapter.notifyDataSetChanged();
                                                                                        activityMainBinding.setAdapter(listAdapter);
                                                                                        activityMainBinding.lvExp.setVisibility(View.VISIBLE);
                                                                                        activityMainBinding.pbLoading.setVisibility(View.GONE);
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onError(Throwable e) {
                                                                                    Log.e("errorTopRated", e.getMessage());
                                                                                    activityMainBinding.pbLoading.setVisibility(View.GONE);
                                                                                }

                                                                                @Override
                                                                                public void onComplete() {

                                                                                }
                                                                            });

                                                                }
                                                            }

                                                            @Override
                                                            public void onError(Throwable e) {
                                                                Log.e("errorNowPlaying", e.getMessage());
                                                                activityMainBinding.pbLoading.setVisibility(View.GONE);
                                                            }

                                                            @Override
                                                            public void onComplete() {

                                                            }
                                                        });

                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Log.e("errorUpcoming", e.getMessage());
                                            activityMainBinding.pbLoading.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("errorPopular", e.getMessage());
                        activityMainBinding.pbLoading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}

