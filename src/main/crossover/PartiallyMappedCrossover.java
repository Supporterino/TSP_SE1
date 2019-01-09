package crossover;

import base.City;
import base.Tour;

import java.util.ArrayList;
import java.util.Arrays;

// PMX
public class PartiallyMappedCrossover extends Crossover {
    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {
        base.City parent1[] = (base.City[]) tour01.getCities().toArray();
        base.City parent2[] = (base.City[]) tour02.getCities().toArray();
        base.City child1[] = new City[280];
        base.City child2[] = new City[280];
        ArrayList<base.City> ordered1 = new ArrayList<>();
        ArrayList<base.City> ordered2 = new ArrayList<>();


        




        //creation of Output
        ArrayList<Tour> outputTourList = new ArrayList<>();


        //child1
        ArrayList<City> genList = new ArrayList<>(Arrays.asList(child1));
        Tour tourchild1 = new Tour();
        tourchild1.setCities(genList);
        outputTourList.add(tourchild1);


        //child2
        genList = new ArrayList<>(Arrays.asList(child2));
        Tour tourchild2 = new Tour();
        tourchild2.setCities(genList);
        outputTourList.add(tourchild2);


        return outputTourList;

    }
}