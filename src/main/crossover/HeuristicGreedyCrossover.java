package crossover;

import base.City;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// HGX
public class HeuristicGreedyCrossover extends Crossover
{
    private MersenneTwisterFast random = new MersenneTwisterFast();

    public ArrayList<Tour> doCrossover(Tour tour01,
                                       Tour tour02)
    {
        Tour newTour = new Tour();
        Set<City> availableCitySet = new HashSet<>();
        availableCitySet.addAll(tour01.getCities());
        availableCitySet.addAll(tour02.getCities());

        City startingCity = getRandomCity(availableCitySet);
        while (!availableCitySet.isEmpty())
        {
            Tour cheaperCityArc = getEdgeDistancetoNextCity(startingCity, tour01) < getEdgeDistancetoNextCity(startingCity, tour02) ? tour01 : tour02;
            City nextcity = cheaperCityArc.getCity(cheaperCityArc.getCities().indexOf(startingCity) + 1 % cheaperCityArc.getSize());
            if (!availableCitySet.contains(nextcity)) nextcity = getRandomCity(availableCitySet);
            newTour.addCity(nextcity);
            availableCitySet.remove(nextcity);
        }

        return new ArrayList<>(Collections.singleton(newTour));
    }

    private static double getEdgeDistancetoNextCity(City city1,
                                                    Tour tour)
    {
        City city2 = tour.getCity(tour.getCities().indexOf(city1) + 1 % tour.getSize());
        return Tour.euclideanDistance(city1.getX(), city1.getY(), city2.getX(), city2.getY());

    }

    private City getRandomCity(Set<City> cities)
    {
        List<City> cityList = new ArrayList<>(cities);
        return cityList.get(random.nextInt(cityList.size()));
    }
}