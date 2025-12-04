package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        List<String> lines = readFileLines("C:\\Users\\fpernias\\OneDrive - Capgemini\\Documents\\AoC2025\\maven-java21\\src\\resources\\input1.txt");
        exo1(lines);
        exo2(lines);
    }

    private static void exo2(List<String> lines) {
        int zeros = 0;
        int position = 50; 
        
        for (String string : lines) {
            int step = 1;
            int num = Integer.parseInt(string.substring(1));
            if (string.startsWith("L"))
                step= -1;
            for (int i = 0; i < num; i++) {
                position += step;
                position = (position + 100) %100;
                if (position == 0) {
                    zeros++;
                }   
             }
        }
        System.out.println(zeros);
    }

    private static void exo1(List<String> lines) {
        int zeros = 0;
        int position = 50; 
        for (String string : lines) {
            int num = Integer.parseInt(string.substring(1));
            if (string.startsWith("L"))
                num*= -1;
            position = (position + num + 100) %100;
            if (position == 0) {
                zeros++;
            }
        }
        System.out.println(zeros);
    }

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
}
