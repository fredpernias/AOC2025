package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App2 {
    public static void main(String[] args) throws IOException {
        List<String> lines = readFileLines("C:\\Users\\fpernias\\OneDrive - Capgemini\\Documents\\AoC2025\\maven-java21\\src\\resources\\input2.txt");
        exo1(lines);
        exo2(lines);
    }

    private static void exo2(List<String> lines) {
        long zeros = 0;
        for (String string : lines.get(0).split(",")) {
            int dash = string.indexOf("-");
            long start= Long.parseLong(string.substring(0,dash));
            long end= Long.parseLong(string.substring(dash+1));
            for (long i = start; i <= end; i++) {
                 if (isInvalid(i)) {
                    zeros += i;
                }
            }
            
        }
        System.out.println(zeros);
    }

    private static void exo1(List<String> lines) {
        long zeros = 0;
        for (String string : lines.get(0).split(",")) {
            int dash = string.indexOf("-");
            long start= Long.parseLong(string.substring(0,dash));
            long end= Long.parseLong(string.substring(dash+1));
            for (long i = start; i <= end; i++) {
                String number = Long.toString(i);
                if (number.length() % 2 == 0 ) {
                    if ( number.substring(0, number.length()/2).equals(number.substring(number.length()/2)) ) {
                        zeros+=i;
                    }
                }
            }
        }
        System.out.println(zeros);
    }

    
    private static boolean isInvalid(long number) {
        String string = Long.toString(number);
        int len = string.length();
        for (int patternLen = 1; patternLen <= len / 2; patternLen++) {
            if (len % patternLen != 0) {
                continue; 
            }
            int repeatCount = len / patternLen;
            if (repeatCount < 2) {
                continue;
            }
            boolean ok = true;
            for (int i = patternLen; i < len; i++) {
                if (string.charAt(i) != string.charAt(i % patternLen)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return true;
            }
        }
        return false;
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
