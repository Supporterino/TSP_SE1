package crossover;

import base.City;
import base.Tour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;

// MPX
public class MaximalPreservationCrossover extends Crossover {
    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {
        base.City[] parent1 = (base.City[]) tour01.getCities().toArray();
        base.City[] parent2 = (base.City[]) tour02.getCities().toArray();
        base.City[] child = new City[280];

        int rndLength = new SplittableRandom().nextInt(10, ((parent1.length / 2) + 1));
        int rndStart = new SplittableRandom().nextInt(0, ((parent1.length - rndLength) - 1));

        List<City> substring = new ArrayList<>();
        for (int i = 0; i < rndLength; i++) {
            substring.add(parent1[rndStart + i]);
            child[i] = parent1[rndStart + i];
        }
        for (City c : parent2) {
            if (!substring.contains(c)) {
                child[rndLength] = c;
                rndLength++;
            }
        }

        ArrayList<Tour> outputTourList = new ArrayList<>();

        ArrayList<City> genList = new ArrayList<>(Arrays.asList(child));
        Tour childTour = new Tour();
        childTour.setCities(genList);
        outputTourList.add(childTour);

        return outputTourList;

    }
}