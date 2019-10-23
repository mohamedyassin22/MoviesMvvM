package com.movies.moviesmvvm.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.movies.moviesmvvm.model.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MoviesUpcomingDatabase extends RoomDatabase {
    private static volatile MoviesUpcomingDatabase INSTANCE;

    public static MoviesUpcomingDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MoviesUpcomingDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesUpcomingDatabase.class, "movies_upcoming_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MoviesDao moviesDao();
}
