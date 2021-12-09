package com.adventofcode.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubmarineDive {

    public static void main(String[] args) throws IOException {

        SubmarineDive.SubmarineState state = new SubmarineState();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nextLine;
        while((nextLine = reader.readLine())!=null) {
            String[] instruction = nextLine.split(" ");
            switch(instruction[0]) {
                case "forward": {state.forward(Integer.parseInt(instruction[1])); break;}
                case "down": {state.down(Integer.parseInt(instruction[1])); break;}
                case "up": {state.up(Integer.parseInt(instruction[1])); break;}
            }
        }

        System.out.println("Multiplication of horizontal position and depth:"+state.calculate());
    }


    private static class SubmarineState {

        //Contains only Day 2 part 2 changes. Day 2 part 1 change didn't have aim. The methods will update state.

        private int horizontalPosition = 0;
        private int depth = 0;
        private int aim = 0;


        void forward(int x) {
            horizontalPosition+=x;
            depth+=(aim*x);
        }

        void down(int x){
            aim+=x;
        }

        void up(int x) {
            aim-=x;
        }

        int calculate() {
            return horizontalPosition*depth;
        }
    }
}
