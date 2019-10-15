package com.movies.moviesmvvm.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.movies.moviesmvvm.R;
import com.movies.moviesmvvm.fragment.MainFragment;

import androidx.appcompat.app.AppCompatActivity;

import static com.movies.moviesmvvm.Util.NOW_PLAYING;
import static com.movies.moviesmvvm.Util.POPULAR;
import static com.movies.moviesmvvm.Util.TOP_RATED;
import static com.movies.moviesmvvm.Util.UPCOMING;

public class MainActivity extends AppCompatActivity {
    AHBottomNavigationItem popular, upcoming, nowPlaying, topRated;
    private AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container,
                        MainFragment.newInstance(POPULAR))
                .commit();
        setupBottomNavigation();

    }

    private void setupBottomNavigation() {
        popular = new AHBottomNavigationItem(getString(R.string.popular), R.drawable.ic_popular);
        upcoming = new AHBottomNavigationItem(getString(R.string.upcome), R.drawable.ic_upcoming);
        nowPlaying = new AHBottomNavigationItem(getString(R.string.now_play), R.drawable.ic_now_playing);
        topRated = new AHBottomNavigationItem(getString(R.string.top_rated), R.drawable.ic_top_rated);

        bottomNavigation.addItem(popular);
        bottomNavigation.addItem(upcoming);
        bottomNavigation.addItem(nowPlaying);
        bottomNavigation.addItem(topRated);

        bottomNavigation.setAccentColor(getResources().getColor(R.color.colorWhite));
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.colorGrey));
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#050505"));
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            switch (position) {
                case 0:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container,
                                    MainFragment.newInstance(POPULAR))
                            .commit();
                    return true;
                case 1:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container,
                                    MainFragment.newInstance(UPCOMING))
                            .commit();

                    return true;

                case 2:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container,
                                    MainFragment.newInstance(NOW_PLAYING))
                            .commit();
                    return true;

                case 3:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container,
                                    MainFragment.newInstance(TOP_RATED))
                            .commit();
                    return true;


                default:
                    return false;
            }

        });


    }


}

