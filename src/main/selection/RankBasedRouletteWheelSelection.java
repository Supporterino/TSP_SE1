package selection;

import base.Population;
import base.Tour;
import configuration.Configuration;

import java.util.ArrayList;
import java.util.Collections;

// RBRWS
public class RankBasedRouletteWheelSelection extends Selection {
    public ArrayList<Tour> doSelection(Population population) {
        ArrayList<Tour> populationTourList = population.getTourList();
        ArrayList<Tour> returnPopulationTourList = new ArrayList<Tour>();
        Collections.sort(populationTourList);

        // Generates the number of tours for the roulette wheel (maximum 10%)
        int maximumNumberOfIndividualsSelected = (int) Configuration.instance.randomGenerator.nextDouble(0, Configuration.instance.selectionRankBasedRWMaximumNumberOfIndividuals) * populationTourList.size();
        if (maximumNumberOfIndividualsSelected % 2 != 0)
            maximumNumberOfIndividualsSelected += 1;
        // Sum from all possible ranks (Formula: "sum of first n natural numbers" (Gauss))
        int rankSum = (populationTourList.size()*(populationTourList.size()+1))/2;

        // Iterates through the tourlist to choose the winners (iterationcount = maximumNumberOfIndividualsSelected)
        for (int i = 0; i < maximumNumberOfIndividualsSelected; i++) {
            // Random number to choose the winner from the roulette wheel
            double randomInt = Configuration.instance.randomGenerator.nextInt(0, rankSum + 1);

            // Searches the winner in the populationTourList
            for (int j = 0; j < populationTourList.size(); j++) {
                // randomInt <= (sum of the (previous) ranks with the "sum of first n natural numbers" (Gauss))
                if (randomInt <= (((j+1)*(j+2))/2)) {
                    // If the winner is already in the returnList, than this turn of the loop is reset
                    if (returnPopulationTourList.contains(populationTourList.get(j))){
                        j = populationTourList.size();
                        i--;
                    }
                    // If the winner is not in the returnList, than he will be added
                    else {
                        returnPopulationTourList.add(populationTourList.get(j));
                    }
                }
            }
        }

        return returnPopulationTourList;
    }
}