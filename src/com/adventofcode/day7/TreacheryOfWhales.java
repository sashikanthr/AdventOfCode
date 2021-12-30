package com.adventofcode.day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class TreacheryOfWhales {

    /*
        Initial solution was a brute force approach using collections framework. After digging into the reddit channel (https://www.reddit.com/r/adventofcode/)
        and some Math, the solution is rather calculating the mean and median.
     */

    private static int[] horizontal_positions;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        //Sorting the values to calculate the median
        horizontal_positions = Stream.of(line.trim().split(",")).mapToInt(Integer::parseInt).sorted().toArray();
        System.out.println("Minimum fuel consumption Part 1 >> " + get_minimum_fuel_l1());
        System.out.println("Minimum fuel consumption Part 2 >> " + get_minimum_fuel_l2());
    }

    private static int get_minimum_fuel_l1() {
        //Median is the position where the fuel cost would be minimum.
        //Explanation - https://math.stackexchange.com/questions/113270/the-median-minimizes-the-sum-of-absolute-deviations-the-ell-1-norm
        int median = get_median();
        System.out.println("Median is >> " + median);
        int sum = 0;
        for (int position : horizontal_positions) {
            sum += Math.abs(median - position);
        }
        return sum;
    }

    private static int get_minimum_fuel_l2() {
        // Mean is the position where the fuel cost would be minimum. Here it is the L2 norm because it is the sum of N numbers.
        // https://math.stackexchange.com/questions/696622/intuition-on-why-the-average-minimizes-the-euclidean-distance
        double mean = get_mean();
        System.out.println("Mean is >> " + mean);
        return Math.min(calculate_value(Math.floor(mean)), calculate_value(Math.ceil(mean)));

    }

    private static int calculate_value(double mean) {
        int sum = 0;
        for (int position : horizontal_positions) {
            //Gauss - N(N+1)/2
            int fuel_exp = (int) Math.abs(mean - position);
            sum += (fuel_exp * (fuel_exp + 1)) / 2;
        }
        return sum;
    }

    private static int get_median() {
        int n = horizontal_positions.length;
        if (n % 2 == 0) {
            return (horizontal_positions[(n - 1) / 2] + horizontal_positions[n / 2]) / 2;
        }
        return horizontal_positions[n / 2];
    }

    private static double get_mean() {
        OptionalDouble mean = Arrays.stream(horizontal_positions).average();
        if (mean.isPresent()) {
            return mean.getAsDouble();
        }
        throw new RuntimeException("Error: Mean cannot be calculated");
    }
}
