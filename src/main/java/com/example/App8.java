package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class App8 {
    // Extrait dynamiquement le numéro du jour depuis le nom de la classe (ex: App5 -> 5)
    private static final int DAY =8;
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
    
    private static final int CONNECTIONS_TO_MAKE = 1000;
    private record Point3D(long x, long y, long z) {}
    private record PotentialConnection(long squaredDistance, int pointIndex1, int pointIndex2) {}



    private static void exo2(List<String> lines)  {
        long zeros = 0;
        zeros = calculateFinalConnectionXProduct(lines);
        System.out.println(zeros);
    }

    private static void exo1(List<String> lines) {
        long zeros = 0;
        List<Point3D> points = parsePoints(lines);
        int pointCount = points.size();

        List<PotentialConnection> allConnections = calculateAllPotentialConnections(points);
        allConnections.sort(Comparator.comparingLong(PotentialConnection::squaredDistance));
        List<PotentialConnection> shortestConnections = allConnections.subList(0, CONNECTIONS_TO_MAKE);

        DisjointSetUnion dsu = new DisjointSetUnion(pointCount);
        for (PotentialConnection conn : shortestConnections) {
            dsu.union(conn.pointIndex1(), conn.pointIndex2());
        }
        List<Integer> componentSizes = dsu.getComponentSizes();
        componentSizes.sort(Collections.reverseOrder());
        zeros = (long) componentSizes.get(0) * componentSizes.get(1) * componentSizes.get(2);
        System.out.println(zeros);
    }


   

   
    public static long calculateProductOfLargestCircuits(List<String> inputLines) {
        List<Point3D> points = parsePoints(inputLines);
        int pointCount = points.size();

        List<PotentialConnection> allConnections = calculateAllPotentialConnections(points);

        allConnections.sort(Comparator.comparingLong(PotentialConnection::squaredDistance));
        List<PotentialConnection> shortestConnections = allConnections.subList(0, 1000);

        DisjointSetUnion dsu = new DisjointSetUnion(pointCount);
        for (PotentialConnection conn : shortestConnections) {
            dsu.union(conn.pointIndex1(), conn.pointIndex2());
        }

        List<Integer> componentSizes = dsu.getComponentSizes();

        componentSizes.sort(Collections.reverseOrder());
        return (long) componentSizes.get(0) * componentSizes.get(1) * componentSizes.get(2);
    }
    
    
    public static long calculateFinalConnectionXProduct(List<String> inputLines) {
        List<Point3D> points = parsePoints(inputLines);
        int pointCount = points.size();

        List<PotentialConnection> allConnections = calculateAllPotentialConnections(points);
        allConnections.sort(Comparator.comparingLong(PotentialConnection::squaredDistance));

        DisjointSetUnion dsu = new DisjointSetUnion(pointCount);
        for (PotentialConnection conn : allConnections) {
            dsu.union(conn.pointIndex1(), conn.pointIndex2());
            if (dsu.getComponentCount() == 1) {
                Point3D p1 = points.get(conn.pointIndex1());
                Point3D p2 = points.get(conn.pointIndex2());
                return p1.x() * p2.x();
            }
        }
        throw new IllegalStateException("Should have found the final connection");
    }

    private static List<Point3D> parsePoints(List<String> lines) {
        return lines.stream()
            .map(line -> {
                String[] parts = line.split(",");
                return new Point3D(
                    Long.parseLong(parts[0]),
                    Long.parseLong(parts[1]),
                    Long.parseLong(parts[2])
                );
            })
            .collect(Collectors.toList());
    }

    private static List<PotentialConnection> calculateAllPotentialConnections(List<Point3D> points) {
        int pointCount = points.size();
        List<PotentialConnection> connections = new ArrayList<>();
        for (int i = 0; i < pointCount; i++) {
            for (int j = i + 1; j < pointCount; j++) {
                Point3D p1 = points.get(i);
                Point3D p2 = points.get(j);
                long dx = p1.x() - p2.x();
                long dy = p1.y() - p2.y();
                long dz = p1.z() - p2.z();
                long squaredDistance = dx * dx + dy * dy + dz * dz;
                connections.add(new PotentialConnection(squaredDistance, i, j));
            }
        }
        return connections;
    }


    private static class DisjointSetUnion {

    private final int[] parent;
    private final int[] componentSize;
    private int componentCount;

   
    public DisjointSetUnion(int numberOfElements) {
        this.componentCount = numberOfElements;
        this.parent = new int[numberOfElements];
        this.componentSize = new int[numberOfElements];
        for (int i = 0; i < numberOfElements; i++) {
            parent[i] = i;
            componentSize[i] = 1;
        }
    }


    public int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]); 
        return parent[u];
    }

    public void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU != rootV) {
            if (componentSize[rootU] < componentSize[rootV]) {
                int temp = rootU;
                rootU = rootV;
                rootV = temp;
            }
            parent[rootV] = rootU;
            componentSize[rootU] += componentSize[rootV];
            componentCount--;
        }
    }

    
    public List<Integer> getComponentSizes() {
        List<Integer> sizes = new ArrayList<>();
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) { // i is a root of a component
                sizes.add(componentSize[i]);
            }
        }
        return sizes;
    }

    public int getComponentCount() {
        return componentCount;
    }
}
    
}
