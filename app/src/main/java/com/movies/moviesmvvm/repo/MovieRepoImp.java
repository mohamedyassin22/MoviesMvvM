package com.movies.moviesmvvm.repo;

import com.movies.moviesmvvm.model.Movie;
import com.movies.moviesmvvm.model.MoviesResponse;
import com.movies.moviesmvvm.rest.ApiInterface;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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
    public LiveData<List<Movie>> loadPopular() {
        final MutableLiveData<List<Movie>> popularMovies = new MutableLiveData<>();
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
                            popularMovies.setValue(moviesResponse.getResults());

                        } else {
                            popularMovies.setValue(null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        popularMovies.setValue(null);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return popularMovies;
    }

    @Override
    public LiveData<List<Movie>> loadUpcoming() {
        final MutableLiveData<List<Movie>> upcomingMovies = new MutableLiveData<>();
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
                            upcomingMovies.setValue(moviesResponse.getResults());

                        } else {
                            upcomingMovies.setValue(null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        upcomingMovies.setValue(null);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return upcomingMovies;
    }

    @Override
    public LiveData<List<Movie>> loadNowPlaying() {
        final MutableLiveData<List<Movie>> nowPlayingMovies = new MutableLiveData<>();
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
                            nowPlayingMovies.setValue(moviesResponse.getResults());

                        } else {
                            nowPlayingMovies.setValue(null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        nowPlayingMovies.setValue(null);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return nowPlayingMovies;
    }

    @Override
    public LiveData<List<Movie>> loadTopRated() {
        final MutableLiveData<List<Movie>> topRatedMovies = new MutableLiveData<>();
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
                            topRatedMovies.setValue(moviesResponse.getResults());
                        } else {
                            topRatedMovies.setValue(null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        topRatedMovies.setValue(null);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return topRatedMovies;
    }
}
