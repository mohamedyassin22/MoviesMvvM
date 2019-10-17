package com.movies.moviesmvvm.local;

import android.content.Context;

import com.movies.moviesmvvm.model.Movie;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {
    private static volatile MoviesDatabase INSTANCE;

    public static MoviesDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MoviesDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesDatabase.class, "movies_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MoviesDao moviesDao();
}
