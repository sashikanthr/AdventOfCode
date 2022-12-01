package com.adventofcode2021.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HydrothermalVenture {

    private final List<LineOfVent> state = new ArrayList<>();
    private int max = Integer.MIN_VALUE;
    private Diagram diagram;

    public static void main(String[] args) throws IOException {
        HydrothermalVenture state = new HydrothermalVenture();
        state.initialize();
        state.updateDiagram();
        state.getScore();
    }

    public void getScore() {
        System.out.println("Count >> " + diagram.score());
    }

    private void updateDiagram() {
        diagram = new Diagram(++max);
        state.stream().filter(LineOfVent::isEligible).forEach(this::updateCoordinates);
    }

    private void updateCoordinates(LineOfVent lineOfVent) {
        if (lineOfVent.isHorizontal()) {
            for (int i = lineOfVent.minHorizontal(); i <= lineOfVent.maxHorizontal(); i++) {
                diagram.incrementCounter(lineOfVent.getX1(), i);
            }
        }

        if (lineOfVent.isVertical()) {
            for (int i = lineOfVent.minVertical(); i <= lineOfVent.maxVertical(); i++) {
                diagram.incrementCounter(i, lineOfVent.getY1());
            }
        }

        //Added for part 2
        if (lineOfVent.isDiagonal()) {
            int diff = lineOfVent.getX2() - lineOfVent.getX1() < 0 ? -1 : 1;
            for (int i = lineOfVent.getX1(), j = lineOfVent.getY1(); i != lineOfVent.getX2() + diff; j += diff * lineOfVent.getSlope(), i += diff) {
                diagram.incrementCounter(i, j);
            }
        }
    }

    private void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nextLine;
        while ((nextLine = reader.readLine()) != null) {
            processRecord(nextLine);
        }
    }

    private void processRecord(String nextLine) {
        String[] coordinates = nextLine.split("->");
        int[] merged = IntStream.concat(IntStream.of(Stream.of(coordinates[0].trim().split(",")).mapToInt(Integer::parseInt).toArray()), IntStream.of(Stream.of(coordinates[1].trim().split(",")).mapToInt(Integer::parseInt).toArray())).toArray();
        LineOfVent line = new LineOfVent(merged[0], merged[1], merged[2], merged[3]);
        int max = Arrays.stream(merged).max().getAsInt();
        if (max > this.max) {
            this.max = max;
        }
        state.add(line);
    }

    private static class LineOfVent {

        private final int x1;

        private final int y1;

        private final int x2;

        private final int y2;

        private final boolean isHorizontal;

        private final boolean isVertical;

        private final boolean isDiagonal;

        private final int slope;

        public LineOfVent(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            isHorizontal = this.x1 == this.x2;
            isVertical = this.y1 == this.y2;


            //Added for Part-2, slope is 1 for 45 degrees
            if (x2 - x1 != 0) {
                slope = (y2 - y1) / (x2 - x1);
                isDiagonal = Math.abs(slope) == 1;
            } else {
                isDiagonal = false;
                slope = 0;
            }
        }

        public boolean isHorizontal() {
            return isHorizontal;
        }

        public boolean isVertical() {
            return isVertical;
        }

        public boolean isDiagonal() {
            return isDiagonal;
        }

        public boolean isEligible() {
            return isHorizontal || isVertical || isDiagonal;
        }

        public int getX1() {
            return x1;
        }

        public int getY1() {
            return y1;
        }

        public int minHorizontal() {
            return Math.min(y1, y2);
        }

        public int maxHorizontal() {
            return Math.max(y1, y2);
        }

        public int minVertical() {
            return Math.min(x1, x2);
        }

        public int getX2() {
            return x2;
        }

        public int getSlope() {
            return slope;
        }

        public int maxVertical() {
            return Math.max(x1, x2);
        }

        @Override
        public String toString() {
            return "LineOfVent{" +
                    "x1=" + x1 +
                    ", y1=" + y1 +
                    ", x2=" + x2 +
                    ", y2=" + y2 +
                    ", isHorizontal=" + isHorizontal +
                    ", isVertical=" + isVertical +
                    ", isHorizontal=" + isDiagonal +
                    '}';
        }
    }

}
