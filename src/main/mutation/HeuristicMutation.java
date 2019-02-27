package mutation;

import base.City;
import base.Tour;
import configuration.Configuration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeuristicMutation extends Mutation {
    public void doMutation(Tour tour) {
        if (tour != null && tour.getCities() != null && tour.getCities().size() > 2) {
            // create list for cities permutations
            List<List<City>> originalCities = new ArrayList<>();
            for (int counter = 0; counter < 6; counter++) {
                originalCities.add(new ArrayList<>(tour.getCities()));
            }

            // get random positions as integer in list
            List<Integer> positionList = new ArrayList<>();
            for (int counter = 0; counter < 3; counter++) {
                positionList.add(Configuration.instance.randomGenerator.nextInt(tour.getCities().size()));
            }

            // create list for position permutations
            List<List<Integer>> shuffledPositions = new ArrayList<>();
            shufflePermutation(positionList, shuffledPositions);

            // list for permuted cities
            List<List<City>> permutedCities = new ArrayList<>();
            for (int counter = 0; counter < shuffledPositions.size(); counter++) {
                List<City> tmp = setPermutation(shuffledPositions.get(counter), positionList, originalCities.get(counter));
                permutedCities.add(counter, tmp);
            }

            // check fitness
            List<Tour> tours = new ArrayList<>();
            for (List<City> permutedCity : permutedCities) {
                Tour tempTour = new Tour();
                tempTour.setCities((ArrayList<City>)permutedCity);
                tours.add(tempTour);
            }

            tour = getFittest(tours);
        }
    }

    private void shufflePermutation(List<Integer> positionList, List<List<Integer>> shuffledList) {
        for (int counter = 0; counter < 6; counter++) {
            List<Integer> tmp = new ArrayList<>(positionList);

            if (shuffledList.isEmpty()) {
                Collections.shuffle(tmp);
                shuffledList.add(tmp);
            } else {
                while (shuffledList.contains(tmp)) {
                    Collections.shuffle(tmp);
                }
                shuffledList.add(tmp);
            }
        }
    }

    private List<City> setPermutation(List<Integer> shuffledPositions, List<Integer> positionList, List<City> permutedList) {
        List<City> temp = new ArrayList<>(permutedList);
        for (int counter = 0; counter < positionList.size(); counter++) {
            permutedList.set(positionList.get(counter), temp.get(shuffledPositions.get(counter)));
        }
        return permutedList;
    }

    private Tour getFittest(List<Tour> tours) {
        Tour fittestTour = new Tour();

        double fitnessStart = 0;
        for (Tour innerTour : tours) {
            double fitness = innerTour.getFitness();
            if (fitnessStart < fitness) {
                fittestTour = innerTour;
                fitnessStart = fitness;
            }
        }
        return fittestTour;
    }
}