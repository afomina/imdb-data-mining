package com.afomina.datamining.parser;

import com.afomina.datamining.model.Actor;
import com.afomina.datamining.model.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexandra on 12.05.14.
 */
public class Parser {

    private static final int ACTRESSES_OFFSET = 241;

    public static List<Actor> actressesParse(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));

        //skip offset
        for (int i = 0; i < ACTRESSES_OFFSET; i++) {
            reader.readLine();
        }

        List<Actor> actors = new ArrayList<Actor>();

        //name film year {...} (...) [...] <...>
        Pattern actorPattern = Pattern.compile("(.*)\t+(.*) \\((.{4})\\).*");
        Pattern filmPattern = Pattern.compile("\t+(.*) \\((.{4})\\) ?(\\{.*\\})? *(\\(.*\\))? *(\\[.*\\])? *(<.*>)?");

        String line = reader.readLine();
        while (line != null) {
            Matcher matcher = actorPattern.matcher(line);
            if (matcher.matches()) {
                String actorName = matcher.group(1);
                String filmName = matcher.group(2);
                Integer filmYear = Integer.parseInt(matcher.group(3));

                Actor actor = new Actor(actorName);
                actor.addMovie(new Movie(filmName, filmYear));

                line = reader.readLine();
                matcher = filmPattern.matcher(line);
                while (line != null && matcher.matches()) {
                    actor.addMovie(new Movie(matcher.group(1), Integer.parseInt(matcher.group(2))));
                    line = reader.readLine();
                    matcher = filmPattern.matcher(line);
                }

                actors.add(actor);
            }

            if (line != null) {
                line = reader.readLine();
            }
        }
        reader.close();

        return actors;
    }

}
