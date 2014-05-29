package com.afomina.datamining;

import com.afomina.datamining.model.Actor;

import java.util.List;
import java.util.Map;

/**
 * Created by alexandra
 * Date: 24.05.14
 * Time: 13:00
 */
public class DataMiner {

    public static Actor findTheMostPopularActor(Map<Object, List<? extends Object>> graph) {
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

}
