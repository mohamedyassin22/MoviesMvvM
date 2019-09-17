package com.movies.moviesmvvm.model;

import java.util.List;

public class ExpandableList {
    private String header;
    List<Movie> movies;
    public ExpandableList(String header, List<Movie> movies) {
        this.header = header;
        this.movies = movies;
    }


    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
