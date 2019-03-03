package crossover;

import base.Tour;
import crossover.ModifiedPartiallyMappedCrossover;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestModifiedPartiallyMappedCrossover {
    @Test
    public void doCrossover() {

        ModifiedPartiallyMappedCrossover maskedCrossover = new ModifiedPartiallyMappedCrossover();

        Tour tour2 = new Tour();
        Tour tour1 = new Tour();

        ArrayList<Tour> tours = new ArrayList<>();
        tours = maskedCrossover.doCrossover(tour1,tour2);

        assertTrue(tours.size()==2);
        assertTrue(maskedCrossover.doCrossover(tour1,tour2) != null);

        LinkedHashSet hashSet = new LinkedHashSet();

        hashSet.addAll(tours);

        assertTrue(hashSet.size()==tours.size());
    }
}
