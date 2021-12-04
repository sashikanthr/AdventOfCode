package com.adventofcode.day1;

import java.util.Scanner;

public class SonarSweepSlidingWindow {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int incrementCounter = 0;
        int measureWindow1 = 0;
        int measureWindow2 = 0;
        int windowCounter1 = 1;
        int windowCounter2 = 0;
        int tempMeasureWindow1 = in.nextInt();
        while (in.hasNextInt()) {
            int current = in.nextInt();
            tempMeasureWindow1+=current;
            windowCounter1++;
            //Overlap of 3 elements when the windows are sliding
            if(windowCounter1==3) {
                measureWindow1 = tempMeasureWindow1;
                tempMeasureWindow1 = current;
                windowCounter1 = 1;
            }
            measureWindow2+=current;
            windowCounter2++;

            if(windowCounter2 == 3) {
                if(measureWindow2 > measureWindow1) {
                    incrementCounter++;
                }
                //Overlap of 3 elements when the windows are sliding
                measureWindow1 = measureWindow2;
                windowCounter2 = windowCounter1;
                measureWindow2 = tempMeasureWindow1;
                tempMeasureWindow1 = current;
                windowCounter1 = 1;

            }
        }

        System.out.println("Number of increments >>>"+incrementCounter);
    }
}
