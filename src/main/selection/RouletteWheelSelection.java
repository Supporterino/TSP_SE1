package selection;

import base.Population;
import base.Tour;
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
        SortedMap<Double, Tour> numberBar = new TreeMap<>();
        double previousProbability = 0;
        for(Map.Entry<Double, Tour> entry : totalFitnessRelativity.entrySet()){
            double currentProbability = previousProbability + entry.getKey();
            numberBar.put(currentProbability, entry.getValue());
            previousProbability = currentProbability;
        }

        //choose a tour randomly according to how near it is to
        ArrayList<Tour> rouletteWheelOrder = new ArrayList<>();
        do{
            double randomNumber = new random.MersenneTwisterFast().nextDouble(0, numberBar.lastKey());
            for(Map.Entry<Double, Tour> numberBarSection : numberBar.entrySet()){
                if(randomNumber <= numberBarSection.getKey() && !rouletteWheelOrder.contains(numberBarSection.getValue())){
                rouletteWheelOrder.add(numberBarSection.getValue());
                break;
                }
            }
        } while(rouletteWheelOrder.size() != population.getTourList().size());
        return rouletteWheelOrder;
    }
}