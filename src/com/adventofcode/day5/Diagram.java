package com.adventofcode.day5;

import java.util.Arrays;

public class Diagram {

    int[][] coordinates;

    Diagram(int max) {
        coordinates = new int[max][max];
    }

    public void incrementCounter(int x, int y) {
        coordinates[x][y]++;
    }

    public int score() {
        return (int) Arrays.stream(coordinates).flatMapToInt(Arrays::stream).filter(i -> i > 1).count();
    }

    //Visualizes the diagram
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < coordinates.length; i++) {
            for (int[] coordinate : coordinates) {
                if (coordinate[i] == 0) {
                    builder.append(".");
                } else {
                    builder.append(coordinate[i]);
                }
                builder.append(" ");
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
