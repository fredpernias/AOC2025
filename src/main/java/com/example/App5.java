package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App5 {
    // Extrait dynamiquement le numéro du jour depuis le nom de la classe (ex: App5 -> 5)
    private static final int DAY =5;
    private static final String FILE_PATH = "C:\\Users\\fpernias\\OneDrive - Capgemini\\" +
        "Documents\\AoC2025\\maven-java21\\src\\resources\\input" + DAY + ".txt";

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

// ------------------------------------------------------------------------//
// ------------------------------------------------------------------------//
// ------------------------------------------------------------------------//
// ------------------------------------------------------------------------//
// ------------------------------------------------------------------------//
// ------------------------------------------------------------------------//
// ------------------------------------------------------------------------//
// ------------------------------------------------------------------------//
// ------------------------------------------------------------------------//
    private static long minValue(String r1) {
                return Long.parseLong(r1.split("-")[0]);
    }

    private static long maxValue(String r1) {
        return Long.parseLong(r1.split("-")[1]);
    }


    private static void exo2(List<String> lines)  {
        long zeros = 0;
        int RangeNumber = 0;
        while (lines.get(RangeNumber).length() > 0) {
            RangeNumber++;
        }
        List<String> ranges = lines.subList(0, RangeNumber);
        Collections.sort(ranges,new java.util.Comparator<String>() {
            @Override
            public int compare(String r1, String r2) {
                long min1 = minValue(r1);
                long min2 = minValue(r2);
                return Long.compare(min1, min2);
            }
        });

        List<String> rangesMerged = new ArrayList<>();

        String currentRange = ranges.get(0);
        for ( int i = 1; i < ranges.size() - 1; i++) {
            String nextRange = ranges.get(i + 1);
            long min1 = minValue(currentRange);
            long max1 = maxValue(currentRange);
            long min2 = minValue(nextRange);
            long max2 = maxValue(nextRange);
            if(min2 <= max1 + 1) {
                long newMax = Math.max(max1, max2);
                currentRange = min1 + "-" + newMax;
            } else {
                rangesMerged.add(currentRange);
                currentRange = nextRange;
            }   
        }
        rangesMerged.add(currentRange);

        for (int i = 0; i < rangesMerged.size(); i++) {  
            zeros += maxValue(rangesMerged.get(i)) - minValue(rangesMerged.get(i)) + 1;
        }
        System.out.println(zeros);
    }



     private static void exo1(List<String> lines) {
        long zeros = 0;
        int RangeNumber = 0;
        while (lines.get(RangeNumber).length() > 0) {
            RangeNumber++;
        }
        List<String> ranges = lines.subList(0, RangeNumber);
        List<String> ids = lines.subList(RangeNumber + 1, lines.size());

        for (String id : ids) { 
            long idAsNumber = Long.parseLong(id);
            for (String range : ranges) {
                long min = minValue(range);
                long max = maxValue(range);
                if (idAsNumber >= min && idAsNumber <= max) {
                    zeros++;
                    break;
                }
            }  
        }

        System.out.println(zeros);
    }


}
