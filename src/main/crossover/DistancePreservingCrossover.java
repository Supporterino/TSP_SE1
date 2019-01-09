package crossover;

import base.City;
import base.Tour;
import random.MersenneTwisterFast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// DPX
public class DistancePreservingCrossover extends Crossover {
    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {
        ArrayList<Tour> results = new ArrayList<>();
        Map<Integer, Integer> subtour = new HashMap<>();

        MersenneTwisterFast randomGen = new MersenneTwisterFast();
        int random = randomGen.nextInt(1,13);
        int i = 0;

        //Phase 1: Construct Subtour from Parent1
        do {
            random = randomGen.nextInt(1,13); //random equals the length of the subtour
            subtour.put(i, random);




            i += random;
        } while((i+random)<=tour01.getCities().size());






        return results;
    }
}