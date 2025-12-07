package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App7 {
    // Extrait dynamiquement le numéro du jour depuis le nom de la classe (ex: App5 -> 5)
    private static final int DAY =7;
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
    
    private static void exo2(List<String> lines)  {
        long zeros = 0;
        int startIndex = lines.get(0).indexOf("S");
        long[][] grid = new long[lines.size()][lines.get(0).length()];
        Set<Integer> occupiedPositions = new java.util.HashSet<>();        
        occupiedPositions.add(startIndex);
        grid[0][startIndex]++;
        for(int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            Set<Integer> newOccupiedPositions = new java.util.HashSet<>();
            for(Integer beamPos : occupiedPositions) {
                if (line.charAt(beamPos) == '^' ) {
                    newOccupiedPositions.add(beamPos-1);
                    grid[i][beamPos-1] += grid[i-1][beamPos];
                    newOccupiedPositions.add(beamPos+1);
                    grid[i][beamPos+1] += grid[i-1][beamPos];
                } else {
                    newOccupiedPositions.add(beamPos);
                    grid[i][beamPos] += grid[i-1][beamPos];
                }
            }
            occupiedPositions = new HashSet<>(newOccupiedPositions);
        }
        for (int i = 0; i < grid[0].length; i++) {
                zeros += grid[grid.length-1][i];   
        }
        System.out.println(zeros);
    }

     private static void exo1(List<String> lines) {
        long zeros = 0;
        int startIndex = lines.get(0).indexOf("S");
        Set<Integer> occupiedPositions = new java.util.HashSet<>();
        occupiedPositions.add(startIndex);
        for(int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            Set<Integer> newOccupiedPositions = new java.util.HashSet<>();
            for(Integer beamPos : occupiedPositions) {
                if (line.charAt(beamPos) == '^' ) {
                    newOccupiedPositions.add(beamPos-1);
                    newOccupiedPositions.add(beamPos+1);
                    zeros++;
                } else
                    newOccupiedPositions.add(beamPos);
            }
            occupiedPositions = new HashSet<>(newOccupiedPositions);
        }
        System.out.println(zeros);
    }


}
