package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App6 {
    private static final int DAY =6;
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
        String line = lines.getLast();
        line = removeDuplicatesSpaces(line);
        List<Integer> columnsWidths = getWidths(lines,line);
        int colomnIndex = 0;
        for (String operator: line.split(" ")) {
            if (operator.equals("+")) {
                zeros += sumColomunAtIndexWithSignificantDigitAtRight(colomnIndex,lines,columnsWidths);
            } else {
                zeros += multiColomunAtIndexWithSignificantDigitAtRight(colomnIndex,lines,columnsWidths);
            }
            colomnIndex++;
        }
        System.out.println(zeros);
    }


    private static List<Integer> getWidths(List<String> lines, String line) {
        List<Integer> widths = new ArrayList<>();
        int currentIndex = 0;
        for (String operator: line.split(" ")) {
            int maxWidth = 0;
            for (int i = 0; i < lines.size() -1; i++) {
                String currentLine = lines.get(i);
                currentLine = removeDuplicatesSpaces(currentLine);
                String[] values = currentLine.split(" ");
                if (values[currentIndex].length() > maxWidth) {
                    maxWidth = values[currentIndex].length();
                }   
            }
            widths.add(maxWidth);
            currentIndex++;
        }
        return widths;
    }

    private static long multiColomunAtIndexWithSignificantDigitAtRight(int colomnIndex, List<String> lines, List<Integer> columnsWidths) {
        long result = 1;
        int lenght = findNumbersLenght(colomnIndex, lines);
        List<String> paddedValues = getPaddedValues(colomnIndex, lines, columnsWidths);   
        for (int digitIndex = lenght -1; digitIndex >=0; digitIndex--) {
            long partialResult = 0;
            for (String value : paddedValues) {
                char c = value.charAt(digitIndex);
                if (c != ' ') {
                    partialResult = 10*partialResult + Character.getNumericValue(c);
                }
            }
            result *= partialResult;
        }
        return result;
    }

    private static List<String> getPaddedValues(int colomnIndex, List<String> lines, List<Integer> columnsWidths) {
        int offset = 0;
        for (int i = 0; i < colomnIndex; i++) {
            offset += columnsWidths.get(i) + 1; // +1 for space
        }
        int targetWidth = columnsWidths.get(colomnIndex);
        List<String> paddedValues = new ArrayList<>(); 
        for (int i = 0; i < lines.size() -1; i++) {
            String line = lines.get(i);
            String paddedValue = line.substring(offset, offset + targetWidth);
            paddedValues.add(paddedValue);
        }
        return paddedValues;
    }

    private static int findNumbersLenght(int colomnIndex, List<String> lines) {
        int lenght = 0;
        for (int i = 0; i < lines.size() -1; i++) {
            String line = lines.get(i);
            line = removeDuplicatesSpaces(line);
            String[] values = line.split(" ");
            if (values[colomnIndex].length() > lenght) {
                lenght = values[colomnIndex].length();
            }   
        }
        return lenght;
    }   

     private static long sumColomunAtIndexWithSignificantDigitAtRight(int colomnIndex, List<String> lines, List<Integer> columnsWidths) {
         long result = 0;
        int lenght = findNumbersLenght(colomnIndex, lines);
        List<String> paddedValues = getPaddedValues(colomnIndex, lines, columnsWidths);   
        for (int digitIndex = lenght -1; digitIndex >=0; digitIndex--) {
            long partialResult = 0;
            for (String value : paddedValues) {
                char c = value.charAt(digitIndex);
                if (c != ' ') {
                    partialResult = 10*partialResult + Character.getNumericValue(c);
                }
            }
            result += partialResult;
        }
        return result;
    }
    

     private static void exo1(List<String> lines) {
        long zeros = 0;
        String line = lines.getLast();
        line = removeDuplicatesSpaces(line);
        int colomnIndex = 0;
        for (String operator: line.split(" ")) {
            if (operator.equals("+")) {
                zeros += sumColomunAtIndex(colomnIndex,lines);
            } else {
                zeros += multiColomunAtIndex(colomnIndex,lines);
            }
            colomnIndex++;
        }
        System.out.println(zeros);
    }

private static long multiColomunAtIndex(int colomnIndex, List<String> lines) {
        long result = 1;
        for (int i = 0; i < lines.size() -1; i++) {
            String line = lines.get(i);
            line = removeDuplicatesSpaces(line);
            String[] values = line.split(" ");
            result *= Long.parseLong(values[colomnIndex]);
        }   
        return result;
    }   

     private static long sumColomunAtIndex(int colomnIndex, List<String> lines) {
        long result = 0;
        for (int i = 0; i < lines.size() -1; i++) {
            String line = lines.get(i);
            line = removeDuplicatesSpaces(line);
            String[] values = line.split(" ");
            result += Long.parseLong(values[colomnIndex]);
        }   
        return result;
    }

    private static String removeDuplicatesSpaces(String line) {
        return line.replaceAll("\\s+", " ").trim();
    }

}
