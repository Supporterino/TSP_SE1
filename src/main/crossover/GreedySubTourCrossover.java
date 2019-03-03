package crossover;

import base.City;
import base.Tour;
import configuration.Configuration;

import java.util.ArrayList;
import java.util.Vector;

// GSX
public class GreedySubTourCrossover extends Crossover {
    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {
        ArrayList<Tour> children = new ArrayList<>(2);
        int randomNumber = 0;
        int genomeSize = tour01.getSize();
        for (int i = 0; i < 2; i++) {
            Vector<City> child = new Vector<>(genomeSize);
            int indexParent1 = 0;
            if (i == 0) {
                indexParent1 = randomNumber = Configuration.instance.randomGenerator.nextInt(genomeSize);
            } else {
                do {
                    indexParent1 = Configuration.instance.randomGenerator.nextInt(genomeSize);
                } while (indexParent1 == randomNumber);
            }
            City startElement = tour01.getCity(indexParent1);
            int indexParent2 = tour02.getCities().indexOf(startElement);
            child.add(startElement);
            boolean useParent1 = true, useParent2 = true;
            do {
                if (useParent1) {
                    indexParent1--;
                    if (indexParent1 < 0) {
                        indexParent1 = genomeSize - 1;
                    }
                    City elementParent1 = tour01.getCity(indexParent1);
                    if (!child.contains(elementParent1)) {
                        child.insertElementAt(elementParent1, 0);
                    } else {
                        useParent1 = false;
                    }
                }
                if (useParent2) {
                    indexParent2++;
                    if (indexParent2 > genomeSize - 1) {
                        indexParent2 = 0;
                    }
                    City elementParent2 = tour02.getCity(indexParent2);
                    if (!child.contains(elementParent2)) {
                        child.add(elementParent2);
                    } else {
                        useParent2 = false;
                    }
                }
                if (!useParent1 && !useParent2) {
                    ArrayList<City> unusedElements = new ArrayList<>();
                    for (City element : tour01.getCities()) {
                        if (!child.contains(element)) {
                            unusedElements.add(element);
                        }
                    }
                    do {
                        int randIdx = Configuration.instance.randomGenerator.nextInt(unusedElements.size());
                        child.add(unusedElements.remove(randIdx));
                    } while (unusedElements.size() > 0);
                }
            } while (child.size() < genomeSize);
            Tour tmpChild = new Tour();
            tmpChild.setCities(new ArrayList<>(child));
            children.add(tmpChild);
        }
        return children;
    }
}