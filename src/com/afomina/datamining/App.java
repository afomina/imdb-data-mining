package com.afomina.datamining;

import com.afomina.datamining.model.Base;
import com.afomina.datamining.parser.Parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by alexandra on 13.05.14.
 */
public class App {
    public static void main(String[] args) throws IOException {
        String path = "/home/alexa/Документы/actresses.list";
        Map<Base, List<Base>> actors = Parser.actressesParse(path, 1990, 1991);
        System.out.println(DataMiner.findTheMostPopularActor(actors));
    }
}
