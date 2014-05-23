package com.afomina.datamining.ui;

import com.afomina.datamining.model.Actor;
import com.afomina.datamining.parser.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * Created by alexandra on 14.05.14.
 */
public class AppForm {
    private JTextField filePath;
    private JTextField yearBegin;
    private JTextField yearEnd;
    private JButton startButton;
    private JTextArea resultTextArea;
    private JPanel panel1;
    private JButton openButton;

    public AppForm() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    List<Actor> actors = Parser.actressesParse(filePath.getText(), Integer.parseInt(yearBegin.getText()), Integer.parseInt(yearEnd.getText()));
                    int maxMovies = 0;
                    Actor maxMoviesActor = null;
                    for (Actor actor: actors) {
                        if (actor.getMovies().size() > maxMovies) {
                            maxMovies = actor.getMovies().size();
                            maxMoviesActor = actor;
                        }
                    }
                    System.out.println(maxMoviesActor);
                    resultTextArea.setText(maxMoviesActor.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        panel1.setVisible(true);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int chooseState = fileChooser.showOpenDialog(panel1);
                if (chooseState == JFileChooser.APPROVE_OPTION) {
                    filePath.setText(fileChooser.getSelectedFile().getPath());
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AppForm");
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setContentPane(new AppForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
