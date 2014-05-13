package com.afomina.datamining;

import com.afomina.datamining.model.Actor;
import com.afomina.datamining.parser.Parser;

import java.io.IOException;
import java.util.List;

/**
 * Created by alexandra on 13.05.14.
 */
public class App {
    public static void main(String[] args) throws IOException {
        String path = "/home/alexandra/actresses.list";
        List<Actor> actors = Parser.actressesParse(path);
        for (Actor actor : actors) {
            System.out.println(actor);
        }
    }
}