package com.afomina.datamining;

import com.afomina.datamining.model.Actor;

import java.util.List;

/**
 * Created by alexandra
 * Date: 24.05.14
 * Time: 13:00
 */
public class DataMiner {

    public static Actor findTheMostPopularActor(List<Actor> actors) {
        int maxMovies = 0;
        Actor maxMoviesActor = null;
        for (Actor actor: actors) {
            if (actor.getMovies().size() > maxMovies) {
                maxMovies = actor.getMovies().size();
                maxMoviesActor = actor;
            }
        }
        return maxMoviesActor;
    }

}
