package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App5 {
    private static final int DAY = 5;
    private static final String FILE_PATH = "C:\\Users\\fpernias\\OneDrive - Capgemini\\"+
    "Documents\\AoC2025\\maven-java21\\src\\resources\\input"+ DAY +".txt";

    public static List<String> readFileLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        Path path = Paths.get(filePath);
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }


    public static void main(String[] args) throws IOException {
        List<String> lines = readFileLines(FILE_PATH);
        exo1(lines);
        exo2(lines);
    }

    private static void exo2(List<String> lines) {
        long zeros = 0;
        System.out.println(zeros);
    }

     private static void exo1(List<String> lines) {
        long zeros = 0;
        System.out.println(zeros);
    }


}
