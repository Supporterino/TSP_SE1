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
        ArrayList<Tour> returnList = new ArrayList<Tour>();
        Collections.sort(populationTourList);
        int rankSum = (populationTourList.size()*(populationTourList.size()+1))/2;


        for (int i = 0; i < 100; i++) { // 10% maximumNumberOfIndividualSelected = 10% FEHLERKONTROLLE mod(2)
            double randomInt = Configuration.instance.randomGenerator.nextInt(0, rankSum + 1);
            for (int j = 0; j < populationTourList.size(); j++) {
                if (randomInt <= (((j+1)*(j+2))/2)) {
                    if (returnList.contains(populationTourList.get(j))){
                        j = populationTourList.size();
                        i--;
                    }
                    else {
                        returnList.add(populationTourList.get(j));
                    }
                }
            }
        }

        return returnList;
    }
}