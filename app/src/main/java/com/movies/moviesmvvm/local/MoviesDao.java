package com.movies.moviesmvvm.local;

import com.movies.moviesmvvm.model.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Movie> Movies);

    @Query("select * from MOVIE_TABLE")
    LiveData<List<Movie>> getPopularMovies();

    @Query("select * from MOVIE_TABLE")
    LiveData<List<Movie>> getUpcomingMovies();

    @Query("select * from MOVIE_TABLE")
    LiveData<List<Movie>> getNowPlayingMovies();

    @Query("select * from MOVIE_TABLE")
    LiveData<List<Movie>> getTopRatedMovies();

    @Query("DELETE FROM MOVIE_TABLE")
    void deleteAll();
}
