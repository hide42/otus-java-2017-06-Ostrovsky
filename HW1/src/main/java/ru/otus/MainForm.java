package ru.otus;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import au.com.bytecode.opencsv.CSVReader;

/**
 * Created by peter on 04.06.2017.
 */
public class MainForm {
    public static void main(String[] args) throws FileNotFoundException {
        CSVReader reader = new CSVReader(new FileReader("pom.xml"));
        JFrame frame = new JFrame("Homework1 "+reader.toString());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(new Dimension(500,300));
    }
}
