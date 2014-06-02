package com.afomina.datamining.parser;

import com.afomina.datamining.model.Actor;
import com.afomina.datamining.model.Base;
import com.afomina.datamining.model.Movie;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexandra on 12.05.14.
 */
public class Parser {

    public static final int ACTRESSES_OFFSET = 241;
    public static final int ACTORS_OFFSET = 239;

    public static Map<Base, List<Base>> actressesParse(String filePath, int movieYearBegin, int movieYearEnd) throws IOException {
        return actressesParse(filePath, movieYearBegin, movieYearEnd, ACTRESSES_OFFSET);
    }

    public static Map<Base, List<Base>> actorsParse(String filePath, int movieYearBegin, int movieYearEnd) throws IOException {
        return actressesParse(filePath, movieYearBegin, movieYearEnd, ACTORS_OFFSET);
    }

    public static Map<Base, List<Base>> actressesParse(String filePath, int movieYearBegin, int movieYearEnd, int offset) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));

        //skip offset
        for (int i = 0; i < offset; i++) {
            reader.readLine();
        }

        //name film year {...} (...) [...] <...>
        Pattern actorPattern = Pattern.compile("(\\S.*)\t+(.*) \\(([0-9]{4})\\).*");
        Pattern filmPattern = Pattern.compile("\t+(.*) \\(([0-9]{4})\\) ?(\\{.*\\})? *(\\(.*\\))? *(\\[.*\\])? *(<.*>)?");

        Map<Base, List<Base>> movieGraph = new HashMap<>();     //TODO REWRITE ALL THIS SHIT!!!
        String line = reader.readLine();
        while (line != null) {
            Matcher matcher = actorPattern.matcher(line);
            if (matcher.matches()) {
                String actorName = matcher.group(1).replaceAll("\t", "");
                String filmName = matcher.group(2);
                Integer filmYear = Integer.parseInt(matcher.group(3));
                Actor actor = new Actor(actorName);
                List<Base> movies = new LinkedList<>();

                if (filmYear >= movieYearBegin && filmYear <= movieYearEnd) {
                    Movie movie = new Movie(filmName, filmYear);
                    movies.add(movie);
                    if (!movieGraph.containsKey(movie)) {
                        movieGraph.put(movie, new ArrayList<>());
                    }
                    movieGraph.get(movie).add(actor);
                }

                line = reader.readLine();
                matcher = filmPattern.matcher(line);
                while (line != null && matcher.matches()) {
                    int year = Integer.parseInt(matcher.group(2));
                    if (year >= movieYearBegin && year <= movieYearEnd) {
                        Movie movie = new Movie(matcher.group(1), year);
                        movies.add(movie);
                        if (!movieGraph.containsKey(movie)) {
                            movieGraph.put(movie, new ArrayList<>());
                        }
                        movieGraph.get(movie).add(actor);
                    }
                    line = reader.readLine();
                    matcher = filmPattern.matcher(line);
                }

                movieGraph.put(actor, movies);
            }

            if (line != null) {
                line = reader.readLine();
            }
        }
        reader.close();

        return movieGraph;
    }

}
