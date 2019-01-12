package mutation;

import base.City;
import base.Tour;
import configuration.Configuration;

public class InsertionMutation extends Mutation {
    public void doMutation(Tour tour) {
        int indexForPicking = Configuration.instance.randomGenerator.nextInt(tour.getSize());
        City tempCity = tour.getCity(indexForPicking);
        tour.getCities().remove(indexForPicking);
        int indexForInsertion = Configuration.instance.randomGenerator.nextInt(tour.getSize());
        tour.getCities().add(indexForInsertion + 1, tempCity);
    }
}