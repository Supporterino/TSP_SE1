package crossover;

import base.City;
import base.Tour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// ERX
public class EdgeRecombinationCrossover extends crossover.Crossover {
    public ArrayList<Tour> doCrossover(Tour parent01, Tour parent02) {
        ArrayList<Tour> childs =new ArrayList<>();
        Tour child01 = new Tour();          //neues Child(leer)
        Tour child02 = new Tour();          //neues Child(leer)

        createChild(parent01, parent02,child01);        //füllt child 1
        createChild(parent01,parent02,child02);         //füllt child 2

        childs.add(child01);        //child 1 zur Arrayliste hinzufügen
        childs.add(child02);        //child 2 zur Arrayliste hinzufügen

        return childs;
    }

    private Tour createChild(Tour parent01, Tour parent02, Tour child){


        base.City firstCity = parent01.getCity(0);
        Random number = new Random();
        int randomNumber = number.nextInt(20);

        if (randomNumber <= 9) {
            firstCity = parent01.getCity(randomNumber);
        }
        if (randomNumber > 9) {
            randomNumber = randomNumber - 10;
            firstCity = parent02.getCity(randomNumber);
        }

        child.addCity(0, firstCity);

        for (int i = 1; i == 9; i++) {
            ArrayList<base.City> neighborList = getNeighborList(parent01, parent02, child.getCity(child.getSize() - 1));
            base.City nextCity = getNextCity(neighborList, child,parent01);
            child.addCity(i, nextCity);
        }



        return child;
    }

    private ArrayList<base.City> getNeighborList(Tour parent01, Tour parent02, base.City city) {
        ArrayList<base.City> neighborList = new ArrayList<>();
        int parentSize = parent01.getSize();
        for (int i = 0; i < parentSize; i++) {
            if (parent01.getCity(i) == city) {
                if (i == 0) {
                    neighborList.add(parent01.getCity(i + 1));
                    neighborList.add(parent01.getCity(parentSize - 1));
                }
                if (i == parent01.getSize()) {
                    neighborList.add(parent01.getCity(i - 1));
                    neighborList.add(parent01.getCity(0));

                } else {
                    neighborList.add(parent01.getCity(i - 1));
                    neighborList.add(parent01.getCity(i + 1));
                }
            }
            if (parent02.getCity(i) == city) {
                neighborList.add(parent02.getCity(i - 1));
                neighborList.add(parent02.getCity(i + 1));
            }
        }

        return neighborList;
    }

    private base.City getNextCity(ArrayList<base.City> neighborList, Tour child01, Tour parent01) {

        base.City biggestCity = null;

        int tempCity = 0;


        for (int i = 0; i != neighborList.size(); i++)

            if (!(child01.containsCity(neighborList.get(i)))) {
                int tempFrequency = Collections.frequency(neighborList, neighborList.get(i));
                if (tempFrequency > tempCity) {
                    tempCity = tempFrequency;
                    biggestCity = neighborList.get(i);
                } else if (tempFrequency == tempCity) {

                    Random rand = new Random();
                    int randomNumber = rand.nextInt(2);
                    if (randomNumber == 0) {
                        tempCity = tempFrequency;
                        biggestCity = neighborList.get(i);
                    }


                }


            }
        if (biggestCity == null) {

            ArrayList<City> unusedCities = new ArrayList<>();

            for (int j = 0; j != parent01.getCities().size(); j++) {
                City temporaryCity = parent01.getCities().get(j);
                if (!child01.containsCity(temporaryCity)) {
                    unusedCities.add(temporaryCity);
                }
            }

            int amountUnusedCities = unusedCities.size();
            if (amountUnusedCities == 1) {
                biggestCity = unusedCities.get(0);
            }
            if (amountUnusedCities > 1) {
                Random rand = new Random();
                int randomNumber = rand.nextInt(amountUnusedCities);
                biggestCity = unusedCities.get(randomNumber);
            }

        }


        return biggestCity;
    }


}