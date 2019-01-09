package crossover;

import base.City;
import base.Tour;
import random.MersenneTwisterFast;

import java.lang.reflect.Array;
import java.util.ArrayList;
/*
    NON-WRAPPING ORDERED CROSSOVER
    URL: http://www.cs.bham.ac.uk/~wbl/biblio/gecco2006/docs/p1125.pdf Stand: 8.01.2019
 */
// NWOX
public class NonWrappingOrderedCrossover extends Crossover {
    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {

          int a = 4;
          int b = 7;

          ArrayList<City> child01 = new ArrayList<>(tour01.getCities());
          ArrayList<City> child02 = new ArrayList<>(tour02.getCities());

          for(int i = a;i<=b;i++) {
              if(child02.contains(tour01.getCity(i))){
                  for(int j = 0; j<=child02.size()-1;j++) {
                      if(child02.get(j) == tour01.getCity(i)) {
                          child02.set(j, null);
                      }
                  }
              }
          }


        for (int i = a; i <= b; i++) {
            if (child01.contains(tour02.getCity(i))) {
                for (int j = 0; j <= child01.size() - 1; j++) {
                    if (child01.get(j) == tour02.getCity(i)) {
                        child01.set(j, null);
                    }
                }
            }
        }

        /*
        Phase 2: Pushing aside elements between a and b
         */
        int middle = ((b - a) / 2); //Separting left and right inside a and b
        for (int i = 0; i < middle; i++) {
            if (child01.get(i) != null) {
                continue;
            } else {
                child01.set(i, child01.get(i + 1));
                child01.set(i + 1, null);
            }
        }

        for (int i = child01.size() - 1; i >= middle; i--) {
            if (child01.get(i) != null) {
                continue;
            } else {
                child01.set(i, child01.get(i - 1));
                child01.set(i - 1, null);
            }
        }

        for (int i = 0; i < middle; i++) {
            if (child02.get(i) != null)
                continue;
            else {
                child02.set(i, child02.get(i + 1));
                child02.set(i + 1, null);
            }
        }
        for (int i = child02.size() - 1; i >= middle; i--) {
            if (child02.get(i) != null)
                continue;
            else {
                child02.set(i, child02.get(i - 1));
                child02.set(i - 1, null);
            }
        }

        /*
        Phase 3: Replacing nulls in child Arraylists
         */
        for (int i = 0; i <= child01.size() - 1; i++) {
            if (child01.get(i) == null) {
                child01.set(i, tour02.getCity(i));
            }
            if (child02.get(i) == null) {
                child02.set(i, tour01.getCity(i));
            }
        }

        /*
        Converting into Tour Object
         */
        Tour child1 =  new Tour();
        Tour child2 = new Tour();
        child1.setCities(child01);
        child2.setCities(child02);


        ArrayList<Tour> tour = new ArrayList<>();
        tour.add(child1);
        tour.add(child2);
        //child01 need to be returned by the function

        return tour;
    }
}
