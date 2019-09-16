package com.movies.moviesmvvm.model;

public class HeaderList {
    private String header;
    int position;
    public HeaderList(String header,int position) {
        this.header = header;
        this.position = position;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
