package com.movies.moviesmvvm.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.movies.moviesmvvm.model.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MoviesTopRatedDatabase extends RoomDatabase {
    private static volatile MoviesTopRatedDatabase INSTANCE;

    public static MoviesTopRatedDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MoviesTopRatedDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesTopRatedDatabase.class, "movies_top_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MoviesDao moviesDao();
}
