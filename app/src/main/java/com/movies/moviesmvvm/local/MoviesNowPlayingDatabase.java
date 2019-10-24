package com.movies.moviesmvvm.local;

import android.content.Context;

import com.movies.moviesmvvm.model.Movie;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 2)
public abstract class MoviesNowPlayingDatabase extends RoomDatabase {
    private static volatile MoviesNowPlayingDatabase INSTANCE;

    public static MoviesNowPlayingDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MoviesNowPlayingDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesNowPlayingDatabase.class, "movies_playing_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MoviesDao moviesDao();
}
