package com.adventofcode2021.day1;

import java.util.Scanner;

public class SonarSweep {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int incrementCounter = 0;
        int lastInt = in.nextInt();
        while (in.hasNextInt()) {
            int nextInt = in.nextInt();
            if (nextInt > lastInt) {
                incrementCounter++;
            }
            lastInt = nextInt;
        }

        System.out.println("Number of increments >>>" + incrementCounter);
    }
}
