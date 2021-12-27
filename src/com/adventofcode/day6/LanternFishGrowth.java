package com.adventofcode.day6;

/*
    Understood what the actual intention of the problem is. It is not to maintain the actual state of the growth but
    build a model to evaluate the growth percentage. The LanternFishState fails for higher iterations or if the initial
    input is more.


    Exponential growth can be modeled. More material can be found -
    https://courses.lumenlearning.com/ivytech-collegealgebra/chapter/model-exponential-growth-and-decay/
    https://www.khanacademy.org/science/ap-biology/ecology-ap/population-ecology-ap/a/exponential-logistic-growth

    Edit: This model is fast but the predictability doesn't consider all factors. Implemented with the use of arrays as part of LanternFish.java
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class LanternFishGrowth {

    private long initialPopulation;

    private long populationGrowth;

    private int t;

    private double k;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        LanternFishGrowth growth = new LanternFishGrowth();
        growth.initialize(line);
        //Exponential growth can be modeled as populationGrowth = initialPopulation * exp^kt (k is growth constant and t is time)
        growth.calculateK();
        double newGrowth = growth.calculateGrowth();
        System.out.println(new BigDecimal(newGrowth).toBigInteger());
        //Need to subtract as the assumption here is 7 day for double time. But, it will be 9 days for


    }

    private double calculateGrowth() {
        System.out.println("Exp .. " + Math.exp(k * 18));
        return initialPopulation * Math.exp(k * 18);
    }

    private void initialize(String line) {
        initialPopulation = Stream.of(line.trim().split(",")).mapToInt(Integer::parseInt).count();
        //Utilizing the LanternFishState class to get growth after t=18
        LanternFishState state = new LanternFishState();
        t = LanternFishState.MAX_DAYS;
        state.begin(line);
        populationGrowth = state.getCount();
        System.out.println("Population Growth" + populationGrowth);
    }


    private void calculateK() {
        k = Math.log((double) populationGrowth / initialPopulation) / t;
        System.out.println(k);
    }

}
