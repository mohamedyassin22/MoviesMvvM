package com.movies.moviesmvvm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.movies.moviesmvvm.R;
import com.movies.moviesmvvm.databinding.MoviesListItemBinding;
import com.movies.moviesmvvm.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private Context context;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
    }
    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MoviesListItemBinding listItemsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.movies_list_item, parent, false);

        return new MovieViewHolder(listItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.moviesListItemBinding.setMovie(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
private MoviesListItemBinding moviesListItemBinding;
        MovieViewHolder(MoviesListItemBinding moviesListItemBinding) {
            super(moviesListItemBinding.getRoot());
            this.moviesListItemBinding = moviesListItemBinding;
        }
    }
}
