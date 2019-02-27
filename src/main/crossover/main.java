package crossover;

import base.City;
import base.Tour;

import java.util.ArrayList;

public class main {
    public static void main(String... args) {
        Tour tour = new Tour();
        Tour tour2 = new Tour();
        City city0 = new City(0, 2, 4);
        City city1 = new City(1, 5, 6);
        City city2 = new City(2, 7, 8);
        City city3 = new City(3, 9, 10);
        City city4 = new City(4, 11, 12);
        City city5 = new City(5, 13, 14);
        City city6 = new City(6, 15, 16);
        City city7 = new City(7, 17, 18);
        City city8 = new City(8, 19, 20);
        City city9 = new City(9, 21, 22);

        tour.addCity(city0);
        tour.addCity(city1);
        tour.addCity(city2);
        tour.addCity(city3);
        tour.addCity(city4);
        tour.addCity(city5);
        tour.addCity(city6);
        tour.addCity(city7);
        tour.addCity(city8);
        tour.addCity(city9);

        tour2.addCity(city9);
        tour2.addCity(city6);
        tour2.addCity(city1);
        tour2.addCity(city4);
        tour2.addCity(city7);
        tour2.addCity(city5);
        tour2.addCity(city8);
        tour2.addCity(city3);
        tour2.addCity(city2);
        tour2.addCity(city0);

        NonWrappingOrderedCrossover test = new NonWrappingOrderedCrossover();
        ArrayList<Tour> testTour = test.doCrossover(tour, tour2);
//        for (Tour tour3 : testTour) {
//            tour3.getCities().forEach(city -> System.out.println(city.toString()));
//        }
        for(int i = 0; i<=testTour.get(0).getSize()-1;i++) {
            System.out.println(testTour.get(0).getCity(i).toString());
        }
        System.out.println("------------------------------");
        for(int i = 0; i<=testTour.get(0).getSize()-1;i++) {
            System.out.println(testTour.get(1).getCity(i).toString());
        }
    }
}
