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

public class App3 {
    private static final int DAY = 3;
    private static final String FILE_PATH = "C:\\Users\\fpernias\\OneDrive - Capgemini\\"+
    "Documents\\AoC2025\\maven-java21\\src\\resources\\input"+ DAY +".txt";

    public static void main(String[] args) throws IOException {
        List<String> lines = readFileLines(FILE_PATH);
        exo1(lines);
        exo2(lines);
    }

    private static void exo2(List<String> lines) {
        long zeros = 0;
        for (String string : lines) {       
            String maxSubseq = findMaxSubsequenceOfLength(string, 12);
            zeros += Long.parseLong(maxSubseq);
        }
        System.out.println(zeros);
    }

    private static String findMaxSubsequenceOfLength(String digits, int requiredLength) {

        int totalLength = digits.length();
        int digitsToRemove = totalLength - requiredLength;

        Deque<Character> stack = new ArrayDeque<>();
        stack.addLast(digits.charAt(0));
        for (int i = 1; i < totalLength; i++) {
            char current = digits.charAt(i);
            while (!stack.isEmpty() && digitsToRemove > 0  && stack.peekLast() < current) {
                stack.removeLast();
                digitsToRemove--;
            }
            stack.addLast(current);
        }
        while (stack.size() > requiredLength) {
            stack.removeLast();
        }

        StringBuilder result = new StringBuilder(requiredLength);
        for (char c : stack) {
            result.append(c);
        }
        return result.toString();
    }

    private static void exo1(List<String> lines) {
        long zeros = 0;
        for (String string : lines) {       
            String maxSubseq = findMaxSubsequenceOfLength(string, 2);
            zeros += Long.parseLong(maxSubseq);
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
