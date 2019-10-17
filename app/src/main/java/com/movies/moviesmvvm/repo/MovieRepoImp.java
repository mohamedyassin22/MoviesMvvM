package com.movies.moviesmvvm.repo;

import com.movies.moviesmvvm.local.MoviesDao;
import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.model.MoviesResponse;
import com.movies.moviesmvvm.rest.ApiInterface;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieRepoImp implements MovieRepo {
    private static final int PAGE_SIZE = 20;
    private ApiInterface apiServiece;
    private String apiKey;
    private String movieType;
    private MoviesDao cache;
    private Executor executor = Executors.newSingleThreadExecutor();

    public MovieRepoImp(ApiInterface apiServiece, String apiKey, String movieType, MoviesDao cache) {

        this.apiServiece = apiServiece;
        this.apiKey = apiKey;
        this.movieType = movieType;
        this.cache = cache;
    }

    @Override
    public LiveData<List<Movie>> loadPopular() {
        LiveData<List<Movie>> popularMovies = cache.getPopularMovies();
        Observable<MoviesResponse> popularcall = apiServiece.getPopularMovies(apiKey);
        popularcall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoviesResponse moviesResponse) {
                        if (moviesResponse.getResults() != null) {
                            List<Movie> popularMovies = moviesResponse.getResults();

                            executor.execute(() ->
                            {
                                //clear cache and save new data in cache
                                cache.deleteAll();
                                cache.insert(popularMovies);
                            });

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return popularMovies;

    }

    @Override
    public LiveData<List<Movie>> loadUpcoming() {
        LiveData<List<Movie>> upcomingMovies = cache.getUpcomingMovies();
        Observable<MoviesResponse> upcomingCall = apiServiece.getUpcomingMovies(apiKey);
        upcomingCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoviesResponse moviesResponse) {
                        if (moviesResponse.getResults() != null) {
                            List<Movie> movies = moviesResponse.getResults();

                            executor.execute(() ->
                            {
                                //clear cache and save new data in cache
                                cache.deleteAll();
                                cache.insert(movies);
                            });

                        }
                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return upcomingMovies;

    }

    @Override
    public LiveData<List<Movie>> loadNowPlaying() {
        LiveData<List<Movie>> nowPlayingMovies = cache.getNowPlayingMovies();
        Observable<MoviesResponse> nowPlayingCall = apiServiece.getNowPlayingMovies(apiKey);
        nowPlayingCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoviesResponse moviesResponse) {
                        if (moviesResponse.getResults() != null) {
                            List<Movie> movies = moviesResponse.getResults();

                            executor.execute(() ->
                            {
                                //clear cache and save new data in cache
                                cache.deleteAll();
                                cache.insert(movies);
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return nowPlayingMovies;

    }

    @Override
    public LiveData<List<Movie>> loadTopRated() {
        LiveData<List<Movie>> topRatedMovies = cache.getTopRatedMovies();
        Observable<MoviesResponse> topRatedCall = apiServiece.getTopRatedMovies(apiKey);
        topRatedCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoviesResponse moviesResponse) {

                        if (moviesResponse.getResults() != null) {
                            List<Movie> movies = moviesResponse.getResults();

                            executor.execute(() ->
                            {
                                //clear cache and save new data in cache
                                cache.deleteAll();
                                cache.insert(movies);
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return topRatedMovies;
    }
}
