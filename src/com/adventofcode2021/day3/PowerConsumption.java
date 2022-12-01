package com.adventofcode2021.day3;


import java.io.IOException;

public class PowerConsumption {

    public static void main(String[] args) throws IOException {
        PowerConsumption consumption = new PowerConsumption();
        consumption.calculatePowerConsumption();
    }

    public void calculatePowerConsumption() throws IOException {
        BinaryDiagnostic diagnostic = new BinaryDiagnostic();
        diagnostic.initialize();
        int gammaValue = Integer.parseInt(diagnostic.getMostSignificant(), 2);
        int epsilonValue = Integer.parseInt(diagnostic.getLeastSignificant(), 2);

        System.out.println("Power Consumption >> " + gammaValue * epsilonValue);

    }

}
