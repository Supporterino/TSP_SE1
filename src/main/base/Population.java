package base;

import java.util.ArrayList;

public class Population {
    private ArrayList<Tour> tourList;

    public Population(Object populationSize, double crossoverRatio, Object elitismRatio, double mutationRatio) {
    }

    public ArrayList<Tour> getTourList() {
        return tourList;
    }

    public void setTourList(ArrayList<Tour> tourList) {
        this.tourList = tourList;
    }
}