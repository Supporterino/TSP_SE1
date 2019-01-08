package selection;

import base.Population;
import base.Tour;
import configuration.Configuration;

import java.util.ArrayList;

// TS
public class TournamentSelection extends Selection {

    // If doubled Tours are a problem in the returned list, doubles can easily be deleted
    public ArrayList<Tour> doSelection(Population population) {
        int desiredOutputSize = population.getTourList().size() / Configuration.instance.tournamentSelectionSize;
        ArrayList<Tour> winners = new ArrayList<Tour>();

        do {
            ArrayList<Tour> fighters = new ArrayList<Tour>();
            for (int i = 0; i < Configuration.instance.tournamentSelectionSize; i++) {
                Tour fighter;
                do {
                    int index = Configuration.instance.randomGenerator.nextInt(0, population.getTourList().size());
                    fighter = population.getTourList().get(index);
                } while(fighters.contains(fighter));
                fighters.add(fighter);
            }

            double bestFitness = Double.MIN_VALUE;
            Tour bestFighter = fighters.get(0); // Initialization
            for(Tour fighter: fighters) {
                if(fighter.getFitness() > bestFitness) {
                    bestFitness = fighter.getFitness();
                    bestFighter = fighter;
                }
            }
            winners.add(bestFighter);
        } while (winners.size() < desiredOutputSize);

        return winners;
    }
}