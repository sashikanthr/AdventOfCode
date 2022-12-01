package com.adventofcode2021.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class LanternFish {

    // Need an array of size 10 instead of 9 because when we traverse the loop 6 and 8 indices will actually reflect 5 and 7 after one iteration.
    // Placing them in 7 and 9 will put them in the right place.

    private static final long[] counters = new long[10];

    public static void main(String[] args) throws IOException {

        int MAX_DAYS = 256;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        Stream.of(line.trim().split(",")).mapToInt(Integer::parseInt).forEach(i -> counters[i]++);

        for (int i = 0; i < MAX_DAYS; i++) {
            generate_fish();
        }
        print_counter();
        System.out.println(Arrays.stream(counters).sum());
    }

    public static void print_counter() {
        for (long counter : counters) {
            System.out.print(counter + " ");
        }
        System.out.println();
    }

    public static void generate_fish() {

        for (int i = 0; i < counters.length; i++) {
            if (i == 0) {
                counters[7] += counters[i];
                counters[9] += counters[i];
            } else {
                counters[i - 1] += counters[i];
            }
            counters[i] -= counters[i];
        }
    }
}
