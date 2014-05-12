package com.afomina.datamining.parser;

import java.io.*;

/**
 * Created by alexandra on 12.05.14.
 */
public class Parser {

    private static final int ACTRESSES_OFFSET = 241;

    private String filePath;

    // actresses.list
    // RegExp: /(.*?)\t+((.*? \(\S{4,}\)) ?(\(\S+\))? ?(?!\{\{SUSPENDED\}\})(\{(.*?) ?(\(\S+?\))?\})? ?(\{\{SUSPENDED\}\})?)\s*(\(.*?\))?\s*(\(.*\))?\s*(\[.*\])?\s*(<.*>)?$/gm
    //pattern: (.*?)\t+((.*? \(\S{4,}\)) ?(\(\S+\))? ?(?!\{\{SUSPENDED\}\})(\{(.*?) ?(\(\S+?\))?\})? ?(\{\{SUSPENDED\}\})?)\s*(\(.*?\))?\s*(\(.*\))?\s*(\[.*\])?\s*(<.*>)?$
    public void actressesParse() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
        for (int i = 0; i < ACTRESSES_OFFSET; i++) {
            reader.readLine();
        }


    }

}
