package com.movies.moviesmvvm.local;

import com.movies.moviesmvvm.model.Movie;

import java.util.List;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Movie> Movies);

    @Query("select * from MOVIE_TABLE order by popularity desc")
    DataSource.Factory<Integer, Movie> getPopularMovies();

    @Query("select * from MOVIE_TABLE order by popularity desc")
    DataSource.Factory<Integer, Movie> getUpcomingMovies();

    @Query("select * from MOVIE_TABLE order by popularity desc")
    DataSource.Factory<Integer, Movie> getNowPlayingMovies();

    @Query("select * from MOVIE_TABLE order by popularity desc")
    DataSource.Factory<Integer, Movie> getTopRatedMovies();


    @Query("DELETE FROM MOVIE_TABLE")
    void deletePopular();

    @Query("DELETE FROM MOVIE_TABLE")
    void deleteUpcoming();

    @Query("DELETE FROM MOVIE_TABLE")
    void deleteNowPlaying();

    @Query("DELETE FROM MOVIE_TABLE")
    void deleteTopRated();
}
