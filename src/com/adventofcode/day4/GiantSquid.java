package com.adventofcode.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GiantSquid {

    private int[] numbersToDraw;

    private int currentNumber;

    private int rows;

    private int cols;

    private int firstWinningIndex = -1;

    //Below variables were introduced for Part - 2 of the problem. The tracker helps to find out who finished last

    private int lastWinningIndex = -1;

    private List<Bingo> state;

    public static void main(String[] args) throws IOException {
        GiantSquid squid = new GiantSquid();
        squid.initializeState();
        int score = squid.drawNumbers();
        if (score != -1) {
            System.out.println("Final Score Part-1 >> " + score);
            System.out.println("Winning Grid Part-1 >> " + squid.state.get(squid.firstWinningIndex));
        } else {
            System.out.println("No Bingo!");
        }

        //Part-2
        System.out.println(" ----------------------------------------------------- ");
        squid.resetBoards();
        score = squid.drawNumbersToFindTheLastWinner();
        if (score != -1) {
            System.out.println("Final Score Part-2 >> " + score);
            System.out.println("Winning Grid Part-2 >> " + squid.state.get(squid.lastWinningIndex));
        } else {
            System.out.println("No Bingo!");
        }

    }

    private static int[] convertStringToIntArray(String text, String delimiter) {
        return Stream.of(text.trim().split(delimiter)).mapToInt(Integer::parseInt).toArray();
    }

    private void resetBoards() {
        state.forEach(Bingo::reset);
    }

    private int drawNumbersToFindTheLastWinner() {

        for (int next : numbersToDraw) {
            currentNumber = next;
            state.stream().filter(bingo -> !bingo.isBingo()).forEach(bingo -> bingo.mark(currentNumber));
            updateBingoStatus();
            if (state.stream().allMatch(Bingo::isBingo)) {
                return state.get(lastWinningIndex).score(currentNumber);
            }
        }
        return -1;
    }

    private int drawNumbers() {
        for (int next : numbersToDraw) {
            currentNumber = next;
            state.forEach(bingo ->
                    bingo.mark(currentNumber));


            verifyFirstBingo();
            if (firstWinningIndex != -1) {
                return state.get(firstWinningIndex).score(currentNumber);
            }
        }

        return -1;
    }

    private void verifyFirstBingo() {
        for (int i = 0; i < state.size(); i++) {
            boolean isBingo = state.get(i).verifyBingo();
            if (isBingo) {
                firstWinningIndex = i;
                return;
            }
        }

    }

    private void updateBingoStatus() {
        for (int i = 0; i < state.size(); i++) {
            if (!state.get(i).isBingo() && state.get(i).verifyBingo()) {
                lastWinningIndex = i;
            }
        }
    }

    private void initializeState() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        loadNumbersToDraw(reader.readLine());
        loadFirstGridConfig(reader);
        String nextLine;
        Bingo bingo = new Bingo(rows, cols);
        int rowIndex = 0;
        while ((nextLine = reader.readLine()) != null) {
            if (nextLine.isEmpty()) {
                state.add(bingo);
                bingo = new Bingo(rows, cols);
                rowIndex = 0;
                continue;
            }
            bingo.loadRow(rowIndex, convertStringToIntArray(nextLine, "\\s+"));
            rowIndex++;
        }
        state.add(bingo);

    }

    private void loadFirstGridConfig(BufferedReader reader) throws IOException {
        List<String> grid = new ArrayList<>();
        reader.readLine();
        String nextLine;
        while (!(nextLine = reader.readLine()).isEmpty()) {
            grid.add(nextLine);
        }
        rows = grid.size();
        cols = grid.get(0).split("\\s+").length;
        Bingo bingo = new Bingo(rows, cols);
        for (int i = 0; i < grid.size(); i++) {
            bingo.loadRow(i, convertStringToIntArray(grid.get(i), "\\s+"));
        }
        state = new ArrayList<>();
        state.add(bingo);
    }

    private void loadNumbersToDraw(String readLine) {
        if (readLine != null && !readLine.isEmpty()) {
            numbersToDraw = convertStringToIntArray(readLine, ",");
        } else {
            throw new RuntimeException("Number draw list cannot be loaded.");
        }
    }

}
