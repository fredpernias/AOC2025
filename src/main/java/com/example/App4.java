package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class App4 {
    public static void main(String[] args) throws IOException {
        List<String> lines = readFileLines("C:\\Users\\fpernias\\OneDrive - Capgemini\\"+
        "Documents\\AoC2025\\maven-java21\\src\\resources\\input4.txt");
        exo1(lines);
        exo2(lines);
        
    }

    private static void exo2(List<String> lines) throws IOException {
        long zeros = 0;
        char[][] grid = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();
        }
        char[][] tempGrid = grid.clone();
        for (int i = 0; i < grid.length; i++) {
            tempGrid[i] = grid[i].clone();
        }
        boolean changed = true;
        while(changed) {
            changed = false;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    tempGrid[i][j] = grid[i][j];
                    int minI = Math.max(0, i - 1);
                    int maxI = Math.min(grid.length - 1, i + 1);   
                    int minJ = Math.max(0, j - 1);
                    int maxJ = Math.min(grid[i].length - 1, j + 1);
                    int levelOfFreedom = 0;
                    for (int x = minI; x <= maxI; x++) {
                        for (int y = minJ; y <= maxJ; y++) {
                            if (x == i && y == j) continue; 
                            if (grid[x][y] == '@') {
                                levelOfFreedom++;
                            }
                        }
                    }
                    if (levelOfFreedom < 4 && grid[i][j] == '@') {
                        tempGrid[i][j] = '.';
                        zeros++;
                        changed = true;
                    }
                }
            }
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j] = tempGrid[i][j];
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                tempGrid[i][j] = grid[i][j];
                int minI = Math.max(0, i - 1);
                int maxI = Math.min(grid.length - 1, i + 1);   
                int minJ = Math.max(0, j - 1);
                int maxJ = Math.min(grid[i].length - 1, j + 1);
                int levelOfFreedom = 0;
                for (int x = minI; x <= maxI; x++) {
                    for (int y = minJ; y <= maxJ; y++) {
                        if (x == i && y == j) continue; 
                        if (grid[x][y] == '@') {
                            levelOfFreedom++;
                        }
                    }
                }
                if (levelOfFreedom < 4 && grid[i][j] == '@') {
                    tempGrid[i][j] = '.';
                    zeros++;
                }
            }
        }
        displayGridInFile(tempGrid,"C:\\Users\\fpernias\\OneDrive - Capgemini\\"+
        "Documents\\AoC2025\\maven-java21\\src\\resources\\output4.txt");

        System.out.println(zeros);
    }

    
    private static void exo1(List<String> lines) {
        long zeros = 0;
        char[][] grid = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();
        }
   
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int minI = Math.max(0, i - 1);
                int maxI = Math.min(grid.length - 1, i + 1);   
                int minJ = Math.max(0, j - 1);
                int maxJ = Math.min(grid[i].length - 1, j + 1);
                int levelOfFreedom = 0;
                for (int x = minI; x <= maxI; x++) {
                    for (int y = minJ; y <= maxJ; y++) {
                        if (x == i && y == j) continue; 
                        if (grid[x][y] == '@') {
                            levelOfFreedom++;
                        }
                    }
                }
                if (levelOfFreedom < 4 && grid[i][j] == '@') {
                    zeros++;
                }
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

    public static void displayGridInFile(char[][] grid, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            sb.append(new String(row)).append("\n");
        }
        Files.write(path, sb.toString().getBytes());
    }
}
