package com.movies.moviesmvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.movies.moviesmvvm.R;
import com.movies.moviesmvvm.databinding.MoviesListItemBinding;
import com.movies.moviesmvvm.model.Movie;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends ListAdapter<Movie, MovieAdapter.MovieViewHolder> {
    private Context context;


    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie movie, @NonNull Movie t1) {
            return movie.getId().equals(t1.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie movie, @NonNull Movie t1) {
            return movie.getId().equals(t1.getId());
        }
    };

    public MovieAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        MoviesListItemBinding listItemsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.movies_list_item, parent, false);

        return new MovieViewHolder(listItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        if (movie != null) {
            holder.moviesListItemBinding.setMovie(movie);
        }
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
private MoviesListItemBinding moviesListItemBinding;
        MovieViewHolder(MoviesListItemBinding moviesListItemBinding) {
            super(moviesListItemBinding.getRoot());
            this.moviesListItemBinding = moviesListItemBinding;
        }
    }

}
