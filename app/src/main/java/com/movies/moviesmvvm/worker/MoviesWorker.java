package com.movies.moviesmvvm.worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.movies.moviesmvvm.Injection;
import com.movies.moviesmvvm.R;
import com.movies.moviesmvvm.local.MoviesDao;
import com.movies.moviesmvvm.model.MoviesResponse;
import com.movies.moviesmvvm.rest.ApiInterface;

import java.io.IOException;

import retrofit2.Response;

//ToDo 2 : Create our worker
// And Extend Worker and implement do work method and it's constructor

public class MoviesWorker extends Worker {

    private static final int NUM_OF_PAGES = 3;
    private int lastRequestedPage = 1;


    private ApiInterface service;
    private MoviesDao cache;
    private String apiKey;

    public MoviesWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);


        service = Injection.provideAPIService();
        cache = Injection.provideMovieDao(getApplicationContext());
        apiKey = Injection.provideAPIKey(getApplicationContext());

    }

    @NonNull
    @Override
    public ListenableWorker.Result doWork() {

        //Get input data if you need to send data to worker
        //Data data = getInputData();


        Response<MoviesResponse> response = null;
        try {
            response = service.getPopularMovies(apiKey, lastRequestedPage).execute();

            while (response != null && response.body() != null &&
                    response.isSuccessful() && lastRequestedPage < NUM_OF_PAGES) {

                if (lastRequestedPage == 1)
                    cache.deletePopular();

                cache.insert(response.body().getResults());

                lastRequestedPage++;

                if (lastRequestedPage <= NUM_OF_PAGES)
                    response = service.getPopularMovies(apiKey, lastRequestedPage).execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.retry();
        }


        if (lastRequestedPage == NUM_OF_PAGES) {
            showNotification("Work Manager", "Congratulation now first 3 pages download successfully");
            Log.d("WorkManager", "Congratulation now first 3 pages download successfully");
            return Result.success();
        } else if (response == null || lastRequestedPage == 1)
            return Result.failure();
        else
            return Result.retry();


    }

    //ToDo 2. we will send notification to make sure that work manager success to get first 3 pages of movies
    private void showNotification(String task, String desc) {

        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


        String channelId = "task_channel";
        String channelName = "task_name";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new
                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher);

        manager.notify(1, builder.build());

    }

}
