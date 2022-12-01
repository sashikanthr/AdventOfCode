package com.adventofcode2021.day6;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
    Maintains the state of the Lantern Fish hierarchy. This was in the anticipation of the second part. Both 1) and 2) can be solved as part of LanternFish.java
 */

public class LanternFishState {

    static final int MAX_DAYS = 7; //Do not utilize this for higher number of days
    private static final int RESET_TIMER = 6;
    private static final int NEW_TIMER = 8;
    private static final List<LanternFish> state = new ArrayList<>();

    private long count;

    public static void main(String[] args) throws IOException {
        LanternFishState lanternFishState = new LanternFishState();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        lanternFishState.begin(line);
    }

    public void begin(String line) {
        initialize(line);
        for (int j = 1; j < 21; j++) {
            for (int i = 0; i < MAX_DAYS; i++) {
                state.forEach(LanternFish::decrementTimer);
            }
            this.count = state.stream().mapToLong(LanternFish::getCount).sum();
            System.out.println(MAX_DAYS * j + " " + count);
        }
    }

    private void initialize(String line) {

        Stream.of(line.trim().split(",")).mapToInt(Integer::parseInt).forEach(lf -> state.add(new LanternFish(lf)));

    }

    public long getCount() {
        return count;
    }

    private static class LanternFish {

        private final List<LanternFish> childFish;
        private int internalTimer;

        public LanternFish(int internalTimer) {
            childFish = new ArrayList<>();
            this.internalTimer = internalTimer;
        }

        private void createLanternFish() {
            childFish.add(new LanternFish(NEW_TIMER));
            resetTimer();
        }

        private void resetTimer() {
            this.internalTimer = RESET_TIMER;
        }

        private void decrementTimer() {
            childFish.forEach(LanternFish::decrementTimer);
            if (this.internalTimer == 0) {
                createLanternFish();
            } else {
                this.internalTimer -= 1;
            }
        }

        private long getCount() {
            return 1 + childFish.stream().mapToLong(LanternFish::getCount).sum();
        }
    }

}