package com.afomina.datamining.model;

import java.util.ArrayList;
import java.util.List;

public class Actor {

    private String name;
    private List<Movie> movies;

    public Actor() {}

    public Actor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        if (getMovies() == null) {
            setMovies(new ArrayList<Movie>());
        }
        getMovies().add(movie);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }
}
