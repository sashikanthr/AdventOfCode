package com.adventofcode.day4;

import java.util.Arrays;

public class Bingo {

    private static final String ANSI_RED = "\u001B[31m"; //Color coding - Only verified in Intellij console

    private static final String ANSI_GREEN = "\u001B[32m";

    Cell[][] cells;

    //Introduced for Part - 2
    private boolean isBingo;

    Bingo(int rows, int columns) {
        cells = new Cell[rows][columns];
    }

    public void add(int row, int col, int value) {
        cells[row][col] = new Cell();
        cells[row][col].setValue(value);
    }

    public void mark(int value) {
        Arrays.stream(cells).flatMap(Arrays::stream).filter(c -> !c.isMarked() && c.getValue() == value).findFirst().ifPresent(Cell::mark);
    }

    public void loadRow(int rowIndex, int[] rowValues) {
        for (int i = 0; i < cells[0].length; i++) {
            add(rowIndex, i, rowValues[i]);
        }
    }

    public void reset() {
        Arrays.stream(cells).flatMap(Arrays::stream).filter(Cell::isMarked).forEach(Cell::unMark);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Bingo [\n");
        for (Cell[] cell : cells) {
            for (int j = 0; j < cells[0].length; j++) {
                Cell cellValue = cell[j];
                if (cellValue.isMarked()) {
                    builder.append(ANSI_RED).append(cellValue.getValue()).append(ANSI_RED);
                } else {
                    builder.append(ANSI_GREEN).append(cellValue.getValue()).append(ANSI_GREEN);
                }
                builder.append(" ");
            }
            builder.append(System.lineSeparator());
        }
        builder.append("]");
        return builder.toString();
    }

    public boolean verifyBingo() {

        for (int i = 0; i < cells.length; i++) {
            boolean rowBingo = true;
            boolean columnBingo = true;
            for (int j = 0; j < cells[0].length; j++) {
                if (!cells[i][j].isMarked()) {
                    rowBingo = false;
                }
                if (!cells[j][i].isMarked()) {
                    columnBingo = false;
                }
            }
            if (rowBingo || columnBingo) {
                isBingo = true;
                return true;
            }
        }
        return false;
    }

    public boolean isBingo() {
        return isBingo;
    }

    public int score(int winningNumber) {
        return Arrays.stream(cells).flatMap(Arrays::stream).filter(c -> !c.isMarked()).mapToInt(Cell::getValue).sum() * winningNumber;
    }

    private static class Cell {

        private int value;
        private boolean isMarked;

        public void mark() {
            this.isMarked = true;
        }

        public boolean isMarked() {
            return isMarked;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void unMark() {
            this.isMarked = false;
        }
    }
}
