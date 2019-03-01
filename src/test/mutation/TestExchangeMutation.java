package mutation;

import base.Tour;
import mutation.ExchangeMutation;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import static org.junit.Assert.assertTrue;

public class TestExchangeMutation {
    @Test
    public void testMutation() {
        ArrayList<base.City> cities = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            cities.add(new base.City(i, 2, 2));
        }
        Tour testTour = new Tour();
        testTour.setCities(cities);
        ExchangeMutation mut = new ExchangeMutation();
        mut.doMutation(testTour);
        Assert.assertNotNull(testTour);

        LinkedHashSet hashSet = new LinkedHashSet();
        hashSet.addAll(cities);
        assertTrue(hashSet.size() == cities.size());
    }
}