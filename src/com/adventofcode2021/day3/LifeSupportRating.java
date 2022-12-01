package com.adventofcode2021.day3;

import java.io.IOException;
import java.util.List;

public class LifeSupportRating {

    private int bitLength;

    public static void main(String[] args) throws IOException {

        LifeSupportRating rating = new LifeSupportRating();
        rating.initialize();
    }

    public void initialize() throws IOException {

        BinaryDiagnostic diagnostic = new BinaryDiagnostic();
        diagnostic.initialize();
        bitLength = diagnostic.getBitLength();
        String oxygenGenRating = calculateRatings(diagnostic.getMostSignificant().toCharArray()[0], 0, diagnostic.getBitStrings(), true);
        String carbonScrubberRating = calculateRatings(diagnostic.getLeastSignificant().toCharArray()[0], 0, diagnostic.getBitStrings(), false);

        System.out.println("Life Support Rating >>> " + (Integer.parseInt(oxygenGenRating, 2) * Integer.parseInt(carbonScrubberRating, 2)));
    }

    public String calculateRatings(char bit, int currentBitIndex, List<String> bitStrings, boolean isMostSignificant) {

        BinaryDiagnostic diagnostic = new BinaryDiagnostic(bitLength, currentBitIndex);
        int indexPosition = currentBitIndex;
        bitStrings.removeIf(s -> s.toCharArray()[indexPosition] != bit);
        if (bitStrings.size() == 1) {
            return bitStrings.get(0);
        }
        bitStrings.forEach(diagnostic::processBitString);

        if (isMostSignificant) {
            return calculateRatings(diagnostic.getNextMostSignificantBit(), ++currentBitIndex, diagnostic.getBitStrings(), true);
        } else {
            return calculateRatings(diagnostic.getNextLeastSignificantBit(), ++currentBitIndex, diagnostic.getBitStrings(), false);
        }
    }
}
