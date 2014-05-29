package com.afomina.datamining.parser;

import com.afomina.datamining.model.Actor;
import com.afomina.datamining.model.Movie;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexandra on 12.05.14.
 */
public class Parser {

    public static final int ACTRESSES_OFFSET = 241;
    public static final int ACTORS_OFFSET = 239;

    public static Map<Object, List<? extends Object>> actressesParse(String filePath, int movieYearBegin, int movieYearEnd) throws IOException {
       return actressesParse(filePath, movieYearBegin, movieYearEnd, ACTRESSES_OFFSET);
    }

    public static Map<Object, List<? extends Object>> actorsParse(String filePath, int movieYearBegin, int movieYearEnd)throws IOException {
        return actressesParse(filePath, movieYearBegin, movieYearEnd, ACTORS_OFFSET);
    }

    public static Map<Object, List<? extends Object>> actressesParse(String filePath, int movieYearBegin, int movieYearEnd, int offset) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));

        //skip offset
        for (int i = 0; i < offset; i++) {
            reader.readLine();
        }

        //name film year {...} (...) [...] <...>
        Pattern actorPattern = Pattern.compile("(\\S+)\t+(.*) \\(([0-9]{4})\\).*");
        Pattern filmPattern = Pattern.compile("\t+(.*) \\(([0-9]{4})\\) ?(\\{.*\\})? *(\\(.*\\))? *(\\[.*\\])? *(<.*>)?");

        Map<Object, List<? extends Object>> movieGraph = new HashMap<>();
        String line = reader.readLine();
        while (line != null) {
            Matcher matcher = actorPattern.matcher(line);
            if (matcher.matches()) {
                String actorName = matcher.group(1).replaceAll("\t", "");
                String filmName = matcher.group(2);
                Integer filmYear = Integer.parseInt(matcher.group(3));
                Actor actor = new Actor(actorName);
                List<Movie> movies = new LinkedList<>();

                if (filmYear >= movieYearBegin && filmYear <= movieYearEnd) {
                    movies.add(new Movie(filmName, filmYear));
                }

                line = reader.readLine();
                matcher = filmPattern.matcher(line);
                while (line != null && matcher.matches()) {
                    int year = Integer.parseInt(matcher.group(2));
                    if (year >= movieYearBegin && year <= movieYearEnd) {
                        movies.add(new Movie(matcher.group(1), year));
                    }
                    line = reader.readLine();
                    matcher = filmPattern.matcher(line);
                }

                if (!movies.isEmpty()) {
                    if (!movieGraph.containsKey(actor)) {
                        movieGraph.put(actor, movies);
                    }
                }
            }

            if (line != null) {
                line = reader.readLine();
            }
        }
        reader.close();

        return movieGraph;
    }

}
