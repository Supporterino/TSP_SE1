package mutation;

import base.City;
import base.Tour;
import configuration.Configuration;
import java.util.ArrayList;
import java.util.List;

public class InversionMutation extends Mutation {
    public void doMutation(Tour tour) {
        if (tour != null && tour.getCities() != null && tour.getSize() > 0) {
            List<City> subList = new ArrayList<>();
            int oldIndex = Configuration.instance.randomGenerator.nextInt(tour.getSize());
            int length = Configuration.instance.randomGenerator.nextInt(tour.getSize() - oldIndex);
            for (int index = oldIndex + length - 1; index > oldIndex - 1; index--) {
                subList.add(tour.getCity(index));
                tour.getCities().remove(index);
            }
            int newIndex = Configuration.instance.randomGenerator.nextInt(tour.getSize());
            tour.getCities().addAll(newIndex + 1, subList);
        }
    }
}