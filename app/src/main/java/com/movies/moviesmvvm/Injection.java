package com.movies.moviesmvvm;

import android.content.Context;

import com.movies.moviesmvvm.local.MoviesDao;
import com.movies.moviesmvvm.local.MoviesDatabase;
import com.movies.moviesmvvm.local.MoviesNowPlayingDatabase;
import com.movies.moviesmvvm.local.MoviesTopRatedDatabase;
import com.movies.moviesmvvm.local.MoviesUpcomingDatabase;
import com.movies.moviesmvvm.repo.MovieRepo;
import com.movies.moviesmvvm.repo.MovieRepoImp;
import com.movies.moviesmvvm.rest.ApiClient;
import com.movies.moviesmvvm.rest.ApiInterface;

import static com.movies.moviesmvvm.utils.Util.NOW_PLAYING;
import static com.movies.moviesmvvm.utils.Util.POPULAR;
import static com.movies.moviesmvvm.utils.Util.TOP_RATED;
import static com.movies.moviesmvvm.utils.Util.UPCOMING;

public class Injection {

    public static MovieRepo provideMovieRepository(Context context, String movieType) {
        return new MovieRepoImp(provideAPIService(), provideAPIKey(context),
                provideMovieType(movieType), provideMovieDao(context, movieType));
    }

    private static ApiInterface provideAPIService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

    private static String provideAPIKey(Context context) {
        return context.getString(R.string.api_key);
    }

    private static String provideMovieType(String movieType) {
        return movieType;
    }

    //ToDo 7: Inject Movie Dao Creation
    private static MoviesDao provideMovieDao(Context context, String movieType) {
        switch (movieType) {
            case POPULAR:
                return MoviesDatabase.getInstance(context).moviesDao();
            case UPCOMING:
                return MoviesUpcomingDatabase.getInstance(context).moviesDao();
            case NOW_PLAYING:
                return MoviesNowPlayingDatabase.getInstance(context).moviesDao();
            case TOP_RATED:
                return MoviesTopRatedDatabase.getInstance(context).moviesDao();
            default:
                return MoviesDatabase.getInstance(context).moviesDao();

        }
    }

}
