package crossover;

import base.Tour;
import configuration.CrossoverType;

import java.util.ArrayList;

public abstract class Crossover {
    public static CrossoverType OnePX;
    public static CrossoverType TwoPX;
    public static CrossoverType AX;
    public static CrossoverType HX;
    public static CrossoverType IX;
    public static CrossoverType KPX;
    public static CrossoverType SX;
    public static CrossoverType UNX;


    public abstract ArrayList<Tour> doCrossover(Tour tour01, Tour tour02);

    public String toString() {
        return getClass().getSimpleName();
    }
}