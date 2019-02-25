package mutation;

import base.City;
import base.Tour;
import configuration.Configuration;
import java.util.ArrayList;
import java.util.List;

public class HeuristicMutation extends Mutation {
    public void doMutation(Tour tour) {
        if (tour != null && tour.getCities() != null && tour.getCities().size() > 2) {
            //do some BS
        }
    }
}