package crossover;

import base.City;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// SCX
public class SequentialConstructiveCrossover extends Crossover
{
    public ArrayList<Tour> doCrossover(Tour tour01,
        Tour tour02)
    {
        Tour newTour = new Tour();
        Set<City> availiableCitySet = new HashSet<>();

        availiableCitySet.addAll(tour01.getCities());
        availiableCitySet.addAll(tour02.getCities());
        City startingCity = tour01.getCity(0);

        while (!availiableCitySet.isEmpty())
        {
            City citytour1 = getNextCity(startingCity, tour01);
            City starting = citytour1;
            //Check is not used
            while (!availiableCitySet.contains(citytour1))
            {
                citytour1 = getNextCity(citytour1, tour01);
                if (starting.equals(citytour1))
                    return new ArrayList<>(Collections.singleton(newTour));

            }
            City citytour2 = getNextCity(startingCity, tour02);
            starting = citytour2;
            while (!availiableCitySet.contains(citytour2))
            {
                citytour2 = getNextCity(citytour2, tour02);
                if (starting.equals(citytour2))
                    return new ArrayList<>(Collections.singleton(newTour));
            }
            startingCity = getEdgeDistance(startingCity, citytour1) > getEdgeDistance(startingCity, citytour1) ? citytour1 : citytour2;
            newTour.addCity(startingCity);
            availiableCitySet.remove(startingCity);
        }

        return new ArrayList<>(Collections.singleton(newTour));
    }

    private static City getNextCity(final City city,
        final Tour tour)
    {
        return tour.getCity(tour.getCities().indexOf(city) + 1 % tour.getSize());
    }

    private static double getEdgeDistance(City city1,
        City city2)
    {
        return Tour.euclideanDistance(city1.getX(), city1.getY(), city2.getX(), city2.getY());
    }

}