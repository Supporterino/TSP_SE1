package main.selection;

import main.base.Population;
import main.base.Tour;
import java.util.*;

// RWS
public class RouletteWheelSelection extends Selection {

    public ArrayList<Tour> doSelection(Population population) {
        //calculate total fitness of population
        double totalFitness = 0;
        for(Tour tour : population.getTourList()){
            totalFitness = totalFitness + tour.getFitness();
        }

        //sort tours by relative fitness
        SortedMap<Double, Tour> totalFitnessRelativity = new TreeMap<>();
        for(Tour tour: population.getTourList()){
            double key = tour.getFitness()/totalFitness;
            totalFitnessRelativity.put(key, tour);
        }

        //put tours on a number bar
        Map<Double, Tour> numberBar = new HashMap<>();
        double previousProbability = 0;
        for(Map.Entry<Double, Tour> entry : totalFitnessRelativity.entrySet()){
            double currentProbability = previousProbability + entry.getKey();
            numberBar.put(currentProbability, entry.getValue());
            previousProbability = currentProbability;
        }

        //choose a tour randomly according to how near it is to
        ArrayList<Tour> rouletteWheelOrder = new ArrayList<>();
        while(!numberBar.isEmpty()) {
            double randomNumber = new random.MersenneTwisterFast().nextDouble();
            for(Map.Entry<Double, Tour> numberBarSection : numberBar.entrySet()){
                if(randomNumber <= numberBarSection.getKey()){
                rouletteWheelOrder.add(numberBarSection.getValue());
                numberBar.remove(numberBarSection.getKey());
                break;
                }
            }
        }
        return rouletteWheelOrder;
    }
}