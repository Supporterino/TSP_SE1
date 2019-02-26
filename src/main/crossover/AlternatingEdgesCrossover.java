package crossover;

import base.City;
import base.Tour;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

// Mirco Käsmann
// AEX
public class AlternatingEdgesCrossover extends Crossover {
    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {
        Tour child = new Tour();

        //the child takes the arc from parent01
        child.addCity(tour01.getCity(0));
        child.addCity(tour01.getCity(1));

        //defines from which parent the next arc should be taken, start with parent2
        boolean firstIsCurrentParent = false;

        for (int i = 1; i < tour01.getSize() - 1; i++){
            City nextCity = null;

            //get the city of the current parent
            if (firstIsCurrentParent){
                nextCity = getNextCityOfParentTour(tour01, child.getCity(i));
            }
            else {
                nextCity = getNextCityOfParentTour(tour02, child.getCity(i));
            }

            //if the arc of the parent is not already part of the tour add it, else add a random unvisited city
            if (!cityAlreadyInChild(child, nextCity)){
                child.addCity(nextCity);
            }
            else {
                child.addCity(getUnvisitedCity(child.getCities(), tour01.getCities()));
            }

            //change the current parent
            firstIsCurrentParent = !firstIsCurrentParent;
        }

        ArrayList<Tour> output = new ArrayList<>();
        output.add(child);

        return output;
    }

    private City getNextCityOfParentTour(Tour tour, City currentCity){
        for (int i = 0; i < tour.getSize() - 1; i++){
            //returns the city after the current city
            if (tour.getCity(i) == currentCity) return tour.getCity(i+1);
        }
        //returns the first city, for only ends when current city == the last city of the tour
        return tour.getCity(0);
    }

    //check if city already is part of the child route
    private Boolean cityAlreadyInChild(Tour child, City city){
        for (int i = 0; i < child.getSize(); i++){
            if (child.getCity(i) == city) return true;
        }
        return false;
    }

    //returns a random city which is not in the child yet
    private City getUnvisitedCity (ArrayList<City> visitedCities, ArrayList<City> allCities){
        allCities.removeAll(visitedCities);
        return allCities.get(ThreadLocalRandom.current().nextInt(0, allCities.size()));
    }
}