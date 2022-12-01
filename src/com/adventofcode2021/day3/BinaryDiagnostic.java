package com.adventofcode2021.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BinaryDiagnostic {

    private int[] bitCounter;

    private final List<String> bitStrings = new ArrayList<>();

    private int currentBitIndex;

    BinaryDiagnostic() {
        this(0, -1);
    }

    BinaryDiagnostic(int initializeCounter, int currentBitIndex) {
        bitCounter = new int[initializeCounter];
        this.currentBitIndex = currentBitIndex;
    }

    public void processBitString(String nextLine) {
        bitStrings.add(nextLine);
        char[] bitArray = nextLine.toCharArray();
        for (int i = 0; i < bitCounter.length; i++) {
            if (bitArray[i] == '1') {
                bitCounter[i]++;
            }
        }
    }

    public String getMostSignificant() {

        StringBuilder builder = new StringBuilder();
        int totalNumberOfRecords = bitStrings.size();
        for (int i : bitCounter) {
            if ((totalNumberOfRecords - i) < (totalNumberOfRecords / 2.0)) {
                builder.append("1");
            } else {
                builder.append("0");
            }
        }

        return builder.toString();
    }

    public String getLeastSignificant() {

        StringBuilder builder = new StringBuilder();
        int totalNumberOfRecords = bitStrings.size();
        for (int i : bitCounter) {
            if ((totalNumberOfRecords - i) > (totalNumberOfRecords / 2.0)) {
                builder.append("1");
            } else {
                builder.append("0");
            }
        }

        return builder.toString();

    }

    public List<String> getBitStrings() {
        return new ArrayList<>(bitStrings);
    }

    public void initialize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nextLine = reader.readLine();
        bitCounter = new int[nextLine.length()];
        currentBitIndex = -1;
        do {
            processBitString(nextLine);
            nextLine = reader.readLine();
        } while (nextLine != null);
    }

    public int getBitLength() {
        return bitCounter.length;
    }

    public char getNextMostSignificantBit() {
        if (bitStrings.size() - bitCounter[++currentBitIndex] > bitStrings.size() / 2.0) {
            return '0';
        } else {
            return '1';
        }
    }

    public char getNextLeastSignificantBit() {
        if (bitStrings.size() - bitCounter[++currentBitIndex] > bitStrings.size() / 2.0) {
            return '1';
        } else {
            return '0';
        }
    }

}
