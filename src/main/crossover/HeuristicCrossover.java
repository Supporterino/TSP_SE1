package crossover;

import base.City;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// HX
public class HeuristicCrossover extends Crossover
{
    private MersenneTwisterFast random = new MersenneTwisterFast();

    public ArrayList<Tour> doCrossover(Tour tour01,
        Tour tour02)
    {
        Tour newTour = new Tour();
        Set<City> avaliablecitySet = new HashSet<>();
        avaliablecitySet.addAll(tour01.getCities());
        avaliablecitySet.addAll(tour02.getCities());

        City startingCity = getRndCity(avaliablecitySet);
        while (!avaliablecitySet.isEmpty())
        {
            Tour useingTour = getEdgeDistancetoNextCity(startingCity, tour01) < getEdgeDistancetoNextCity(startingCity, tour02) ? tour01 : tour02;
            City nextcity = useingTour.getCity(useingTour.getCities().indexOf(startingCity) + 1 % useingTour.getSize());
            if (!avaliablecitySet.contains(nextcity)) nextcity = getRndCity(avaliablecitySet);
            newTour.addCity(nextcity);
            avaliablecitySet.remove(nextcity);
        }

        return new ArrayList<>(Collections.singleton(newTour));
    }

    private static double getEdgeDistancetoNextCity(City city1,
        Tour tour)
    {
        City city2 = tour.getCity(tour.getCities().indexOf(city1) + 1 % tour.getSize());
        return Tour.euclideanDistance(city1.getX(), city1.getY(), city2.getX(), city2.getY());

    }

    private City getRndCity(Set<City> cities)
    {
        List<City> cityList = new ArrayList<>(cities);
        return cityList.get(random.nextInt(cityList.size()));
    }
}