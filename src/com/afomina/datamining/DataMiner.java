package com.afomina.datamining;

import com.afomina.datamining.model.Actor;
import com.afomina.datamining.model.Base;
import com.afomina.datamining.model.Movie;

import java.util.List;
import java.util.Map;

/**
 * Created by alexandra
 * Date: 24.05.14
 * Time: 13:00
 */
public class DataMiner {

    public static Actor findTheMostPopularActor(Map<Base, List<Base>> graph) {
        int maxMovies = 0;
        Actor maxMoviesActor = null;
        for (Object actor: graph.keySet()) {
            if (actor instanceof Actor) {
                if (graph.get(actor).size() > maxMovies) {
                    maxMovies = graph.get(actor).size();
                    maxMoviesActor = (Actor) actor;
                }
            }
        }
        return maxMoviesActor;
    }

    public static Movie findTheMostPopularMovie(Map<Base, List<Base>> graph) {
        int max = 0;
        Movie resultMovie = null;
        for (Object movie: graph.keySet()) {
            if (movie instanceof Movie) {
                if (graph.get(movie).size() > max) {
                    max = graph.get(movie).size();
                    resultMovie = (Movie) movie;
                }
            }
        }
        return resultMovie;
    }

}
