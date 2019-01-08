package crossover;

import base.Tour;

import java.util.ArrayList;

public abstract class Crossover {
    public abstract ArrayList<Tour> doCrossover(Tour tour01, Tour tour02);

    public String toString() {
        return getClass().getSimpleName();
    }
}