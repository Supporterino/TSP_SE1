package mutation;

import base.City;
import base.Tour;
import configuration.Configuration;

public class ExchangeMutation extends Mutation {
    public void doMutation(Tour tour) {
        if (tour != null && tour.getCities() != null && tour.getSize() > 0) {
            int firstIndex = Configuration.instance.randomGenerator.nextInt(tour.getSize());
            int secondIndex = Configuration.instance.randomGenerator.nextInt(tour.getSize());
            City temCity = tour.getCity(firstIndex);
            tour.getCities().set(firstIndex, tour.getCity(secondIndex));
            tour.getCities().set(secondIndex, temCity);
        }
    }
}