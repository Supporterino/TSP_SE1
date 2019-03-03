package crossover;

import base.City;
import base.Tour;
import configuration.Configuration;

import java.util.ArrayList;
import java.util.Collections;

// OX2
public class OrderBasedCrossover extends Crossover {
    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {
        ArrayList<Tour> children = new ArrayList<>();
        ArrayList<City> parent1 = tour01.getCities(),
                parent2 = tour02.getCities(),
                randomCitiesParent2 = new ArrayList<>(),
                randomCitiesParent1 = new ArrayList<>(),
                child1,
                child2;
        ArrayList<Integer> randomPositions = new ArrayList<>(),
                indexParent1 = new ArrayList<>(),
                indexParent2 = new ArrayList<>();
        int randomPos;

        /* Anzahl der ausgewaehlten Elemente zwischen 1/3 und 1/2 der Gesamtlaenge der Individuen
         * l=280 -> Anzahl zwischen 94 und 140
         */
        for (int i = 0; i < tour01.getSize() / 2; i++) {
            do {
                randomPos = Configuration.instance.randomGenerator.nextInt(1, tour01.getSize());
            } while (randomPositions.contains(randomPos));
            randomPositions.add(randomPos);
        }
        Collections.sort(randomPositions);

        for (int i : randomPositions) {
            randomCitiesParent2.add(parent2.get(i - 1));
            randomCitiesParent1.add(parent1.get(i - 1));
        }

        for (City c : randomCitiesParent2) {
            indexParent1.add(parent1.indexOf(c));
        }

        for (City c : randomCitiesParent1) {
            indexParent2.add(parent2.indexOf(c));
        }

        Collections.sort(indexParent1);
        Collections.sort(indexParent2);

        child1 = (ArrayList<City>) parent1.clone();
        child2 = (ArrayList<City>) parent2.clone();

        for (int i = 0; i < randomPositions.size(); i++) {
            child1.set(indexParent1.get(i), randomCitiesParent2.get(i));
            child2.set(indexParent2.get(i), randomCitiesParent1.get(i));
        }

        Tour tourChild1 = new Tour(), tourChild2 = new Tour();
        tourChild1.setCities(child1);
        tourChild2.setCities(child2);

        children.add(tourChild1);
        children.add(tourChild2);

        return children;
    }
}