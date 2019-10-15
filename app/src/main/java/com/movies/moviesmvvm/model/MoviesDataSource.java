//package com.movies.moviesmvvm.model;
//
//import android.arch.paging.PageKeyedDataSource;
//import android.support.annotation.NonNull;
//
//import com.movies.moviesmvvm.rest.ApiInterface;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import io.reactivex.Observable;
//import io.reactivex.Observer;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;
//
//public class MoviesDataSource extends PageKeyedDataSource<Integer, ExpandableList> {
//
//    private ApiInterface service;
//    private String apiKey;
//    private List<ExpandableList> expandableLists = new ArrayList<>();
//    public MoviesDataSource(ApiInterface service, String apiKey) {
//        this.service = service;
//        this.apiKey = apiKey;
//    }
//
//    @Override
//    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
//                            @NonNull final LoadInitialCallback<Integer, ExpandableList> callback) {
//        Observable<MoviesResponse> popularcall = service.getPopularMovies(apiKey, 1);
//        popularcall.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<MoviesResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(MoviesResponse moviesResponse) {
//                        List<Movie> movies = null;
//
//                        if (moviesResponse.getResults() != null) {
//                            int currentPageNum = moviesResponse.getPage();
//                            int totalNumOfPages = moviesResponse.getTotalPages();
//                            movies = moviesResponse.getResults();
//                            final ExpandableList popularMovies = new ExpandableList("PopularMovies", movies);
//                            expandableLists.add(popularMovies);
//                            if (totalNumOfPages > currentPageNum) {
//                                callback.onResult(expandableLists, null, currentPageNum + 1);
//                            } else {
//                                callback.onResult(expandableLists, null, null);
//                            }
//
//                        } else {
//                            callback.onResult(new ArrayList<ExpandableList>(), null, null);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callback.onResult(new ArrayList<ExpandableList>(), null, null);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
////        Observable<MoviesResponse> upcomingCall = service.getUpcomingMovies(apiKey, 1);
////        upcomingCall.subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Observer<MoviesResponse>() {
////                    @Override
////                    public void onSubscribe(Disposable d) {
////
////                    }
////
////                    @Override
////                    public void onNext(MoviesResponse moviesResponse) {
////                        if (moviesResponse.getResults() != null) {
////                            int currentPageNum = moviesResponse.getPage();
////                            int totalNumOfPages = moviesResponse.getTotalPages();
////
////                            if (totalNumOfPages > currentPageNum) {
////                                callback.onResult(moviesResponse.getResults(), null, currentPageNum + 1);
////                            } else {
////                                callback.onResult(moviesResponse.getResults(), null, null);
////                            }
////
////                        } else {
////                            callback.onResult(new ArrayList<Movie>(), null, null);
////                        }
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////                        callback.onResult(new ArrayList<Movie>(), null, null);
////                    }
////
////                    @Override
////                    public void onComplete() {
////
////                    }
////                });
////
////        Observable<MoviesResponse> nowPlayingCall = service.getNowPlayingMovies(apiKey, 1);
////        nowPlayingCall.subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Observer<MoviesResponse>() {
////                    @Override
////                    public void onSubscribe(Disposable d) {
////
////                    }
////
////                    @Override
////                    public void onNext(MoviesResponse moviesResponse) {
////                        if (moviesResponse.getResults() != null) {
////                            int currentPageNum = moviesResponse.getPage();
////                            int totalNumOfPages = moviesResponse.getTotalPages();
////
////                            if (totalNumOfPages > currentPageNum) {
////                                callback.onResult(moviesResponse.getResults(), null, currentPageNum + 1);
////                            } else {
////                                callback.onResult(moviesResponse.getResults(), null, null);
////                            }
////
////                        } else {
////                            callback.onResult(new ArrayList<Movie>(), null, null);
////                        }
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////                        callback.onResult(new ArrayList<Movie>(), null, null);
////                    }
////
////                    @Override
////                    public void onComplete() {
////
////                    }
////                });
////
////        Observable<MoviesResponse> topRatedCall = service.getTopRatedMovies(apiKey, 1);
////        topRatedCall.subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Observer<MoviesResponse>() {
////                    @Override
////                    public void onSubscribe(Disposable d) {
////
////                    }
////
////                    @Override
////                    public void onNext(MoviesResponse moviesResponse) {
////                        if (moviesResponse.getResults() != null) {
////                            int currentPageNum = moviesResponse.getPage();
////                            int totalNumOfPages = moviesResponse.getTotalPages();
////
////                            if (totalNumOfPages > currentPageNum) {
////                                callback.onResult(moviesResponse.getResults(), null, currentPageNum + 1);
////                            } else {
////                                callback.onResult(moviesResponse.getResults(), null, null);
////                            }
////                        } else {
////                            callback.onResult(new ArrayList<Movie>(), null, null);
////                        }
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////                        callback.onResult(new ArrayList<Movie>(), null, null);
////                    }
////
////                    @Override
////                    public void onComplete() {
////
////                    }
////                });
//
//    }
//
//    @Override
//    public void loadBefore(@NonNull LoadParams<Integer> params,
//                           @NonNull LoadCallback<Integer, ExpandableList> callback) {
//
//    }
//
//    @Override
//    public void loadAfter(@NonNull final LoadParams<Integer> params,
//                          @NonNull final LoadCallback<Integer, ExpandableList> callback) {
//        Observable<MoviesResponse> popularcall = service.getPopularMovies(apiKey, params.key);
//        popularcall.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<MoviesResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(MoviesResponse moviesResponse) {
//                        List<Movie> movies = null;
//                        if (moviesResponse.getResults() != null) {
//                            int currentPageNum = moviesResponse.getPage();
//                            int totalNumOfPages = moviesResponse.getTotalPages();
//                            movies = moviesResponse.getResults();
//                            final ExpandableList popularMovies = new ExpandableList("PopularMovies", movies);
//                            expandableLists.add(popularMovies);
//                            if (totalNumOfPages > currentPageNum) {
//                                callback.onResult(expandableLists, currentPageNum + 1);
//                            } else {
//                                callback.onResult(expandableLists, null);
//                            }
//
//                        } else {
//                            callback.onResult(new ArrayList<ExpandableList>(), null);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callback.onResult(new ArrayList<ExpandableList>(), null);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
////        Observable<MoviesResponse> upcomingCall = service.getUpcomingMovies(apiKey, params.key);
////        upcomingCall.subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Observer<MoviesResponse>() {
////                    @Override
////                    public void onSubscribe(Disposable d) {
////
////                    }
////
////                    @Override
////                    public void onNext(MoviesResponse moviesResponse) {
////                        if (moviesResponse.getResults() != null) {
////                            int currentPageNum = moviesResponse.getPage();
////                            int totalNumOfPages = moviesResponse.getTotalPages();
////
////                            if (totalNumOfPages > currentPageNum) {
////                                callback.onResult(moviesResponse.getResults(),  currentPageNum + 1);
////                            } else {
////                                callback.onResult(moviesResponse.getResults(),  null);
////                            }
////
////                        } else {
////                            callback.onResult(new ArrayList<Movie>(),  null);
////                        }
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////                        callback.onResult(new ArrayList<Movie>(),  null);
////                    }
////
////                    @Override
////                    public void onComplete() {
////
////                    }
////                });
////
////        Observable<MoviesResponse> nowPlayingCall = service.getNowPlayingMovies(apiKey, params.key);
////        nowPlayingCall.subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Observer<MoviesResponse>() {
////                    @Override
////                    public void onSubscribe(Disposable d) {
////
////                    }
////
////                    @Override
////                    public void onNext(MoviesResponse moviesResponse) {
////                        if (moviesResponse.getResults() != null) {
////                            int currentPageNum = moviesResponse.getPage();
////                            int totalNumOfPages = moviesResponse.getTotalPages();
////
////                            if (totalNumOfPages > currentPageNum) {
////                                callback.onResult(moviesResponse.getResults(),  currentPageNum + 1);
////                            } else {
////                                callback.onResult(moviesResponse.getResults(),  null);
////                            }
////
////                        } else {
////                            callback.onResult(new ArrayList<Movie>(),  null);
////                        }
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////                        callback.onResult(new ArrayList<Movie>(),  null);
////                    }
////
////                    @Override
////                    public void onComplete() {
////
////                    }
////                });
////
////        Observable<MoviesResponse> topRatedCall = service.getTopRatedMovies(apiKey, params.key);
////        topRatedCall.subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Observer<MoviesResponse>() {
////                    @Override
////                    public void onSubscribe(Disposable d) {
////
////                    }
////
////                    @Override
////                    public void onNext(MoviesResponse moviesResponse) {
////                        if (moviesResponse.getResults() != null) {
////                            int currentPageNum = moviesResponse.getPage();
////                            int totalNumOfPages = moviesResponse.getTotalPages();
////
////                            if (totalNumOfPages > currentPageNum) {
////                                callback.onResult(moviesResponse.getResults(),  currentPageNum + 1);
////                            } else {
////                                callback.onResult(moviesResponse.getResults(),  null);
////                            }
////                        } else {
////                            callback.onResult(new ArrayList<Movie>(),  null);
////                        }
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////                        callback.onResult(new ArrayList<Movie>(),  null);
////                    }
////
////                    @Override
////                    public void onComplete() {
////
////                    }
////                });
//
//    }
//}
