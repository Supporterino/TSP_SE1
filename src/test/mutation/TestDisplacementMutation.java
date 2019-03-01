package mutation;

import base.City;
import base.Tour;
import mutation.DisplacementMutation;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import static org.junit.Assert.assertTrue;

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

        LinkedHashSet hashSet = new LinkedHashSet();
        hashSet.addAll(cities);
        assertTrue(hashSet.size()==cities.size());
    }
}