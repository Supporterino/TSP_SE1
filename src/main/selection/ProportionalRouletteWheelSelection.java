package main.selection;

import main.base.Population;
import main.base.Tour;

import java.util.*;

// PRWS
public class ProportionalRouletteWheelSelection extends Selection {

    public ArrayList<Tour> doSelection(Population population) {
        //calculate total fitness of population
        double totalFitness = 0;
        for(Tour tour : population.getTourList()){
            totalFitness = totalFitness + tour.getFitness();
        }

        //sort tours by relative fitness
        SortedMap<Double, Tour> fitnessPercentage = new TreeMap<>();
        for(Tour tour: population.getTourList()){
            double key = tour.getFitness()/totalFitness;
            fitnessPercentage.put(key, tour);
        }

        //choose a tour randomly according to how near it is to
        ArrayList<Tour> rouletteWheelOrder = new ArrayList<>();
        while(!fitnessPercentage.isEmpty()) {
            double randomNumber = new random.MersenneTwisterFast().nextDouble(0, fitnessPercentage.lastKey());
            double closestValue = fitnessPercentage.keySet().stream().min(Comparator.comparingDouble(i -> i - randomNumber))
                    .orElseThrow(() -> new NoSuchElementException("No value present"));
            rouletteWheelOrder.add(fitnessPercentage.get(closestValue));
            fitnessPercentage.remove(closestValue);
        }
        return rouletteWheelOrder;
    }
}