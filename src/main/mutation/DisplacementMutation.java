package mutation;

import base.City;
import base.Tour;
import configuration.Configuration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// DM
public class DisplacementMutation extends Mutation {

    public void doMutation(Tour tour) {
        if (tour != null && tour.getCities() != null && tour.getSize() > 0) {
            List<City> subTour = new ArrayList<>();
            int oldIndex = Configuration.instance.randomGenerator.nextInt(tour.getSize());
            int length = Configuration.instance.randomGenerator.nextInt(tour.getSize() - oldIndex);
            for (int index = oldIndex + length - 1; index > oldIndex - 1; index--) {
                subTour.add(tour.getCity(index));
                tour.getCities().remove(index);
            }
            Collections.reverse(subTour);
            int newIndex = Configuration.instance.randomGenerator.nextInt(tour.getSize());
            tour.getCities().addAll(newIndex + 1, subTour);
        }
    }
}