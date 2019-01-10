package mutation;

import base.City;
import base.Tour;
import configuration.Configuration;

import java.util.ArrayList;
import java.util.List;


// DM
public class DisplacementMutation extends Mutation {

    public void doMutation(Tour tour) {
        List<City> subTour = new ArrayList<>();
        int oldIndex = generateRandomNumber(tour.getSize() - 1);
        int length = generateRandomNumber(tour.getSize() - 1 - oldIndex);
        int newIndex = generateRandomNumber(tour.getSize() - 1);
        //create SubTour
        for (int index = oldIndex + length; index > oldIndex; index--) {
            subTour.add(tour.getCity(index));
            tour.getCities().remove(index);
        }
        //insert SubTour at newIndex
        for (int iterations = 0; iterations < length; iterations++) {
            tour.addCity(newIndex + iterations, subTour.get(iterations));
        }
    }

    private int generateRandomNumber(int bound) {
        int randomValue;
        do {
            randomValue = Configuration.instance.randomGenerator.nextInt();
        } while (randomValue <= bound);
        return randomValue;
    }
}