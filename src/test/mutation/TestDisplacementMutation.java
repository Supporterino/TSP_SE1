package mutation;

import base.City;
import base.Tour;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class TestDisplacementMutation {

    @Test
    public void testMutation() {
        ArrayList<City> cities = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            cities.add(new City(i,2,2));
        }
        Tour testTour = new Tour();
        testTour.setCities(cities);
        DisplacementMutation mut = new DisplacementMutation();
        mut.doMutation(testTour);
        Assert.assertNotNull(testTour);
        for (int i = 0; i < 10; i++) {
            Assert.assertNotEquals(testTour.getCity(i), testTour.getCity(i + 1));
        }
    }
}