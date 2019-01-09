package selection;

import base.Population;
import base.Tour;
import configuration.Configuration;

import java.util.ArrayList;

// TS
public class TournamentSelection extends Selection {
    public ArrayList<Tour> doSelection(Population population) {
        ArrayList<Tour> winners = new ArrayList<Tour>();
        // Copy of population because Tours get removed in the process
        ArrayList<Tour> populationCopy = new ArrayList<Tour>(population.getTourList());

        if(populationCopy.size() <= 0 || populationCopy.size() % 2 == 1)
            throw new java.lang.RuntimeException("Population size is too small or not even");

        ArrayList<Fight> fights = new ArrayList<Fight>();

        // Pick randomly fighters from the population and combine to random fighters to a new fight
        while(populationCopy.size() > 0) {
            int randomIndex1 = Configuration.instance.randomGenerator.nextInt(0, populationCopy.size());
            Tour fighter1 = populationCopy.get(randomIndex1);
            populationCopy.remove(fighter1);
            int randomIndex2 = Configuration.instance.randomGenerator.nextInt(0, populationCopy.size());
            Tour fighter2 = populationCopy.get(randomIndex2);
            populationCopy.remove(fighter2);
            Fight fight = new Fight(fighter1, fighter2);
            fights.add(fight);
        }

        for (Fight fight : fights) {
            winners.add(executeFight(fight));
        }

        // If second fight round should happen (selectionPressureProbability in Configuration)
        if(Configuration.instance.randomGenerator.nextDouble(0.0, 1.0) <= Configuration.instance.selectionPressureProbability) {
            // selectionPressureRatio is the proportion of winners (1st round) who sould fight again
            int numberOfFightersForNextFight = (int)(winners.size() * Configuration.instance.selectionPressureRatio);
            if (numberOfFightersForNextFight % 2 == 1)
                numberOfFightersForNextFight--;

            ArrayList<Tour> fighters = new ArrayList<Tour>();
            ArrayList<Fight> secondFights = new ArrayList<Fight>();

            while(fighters.size() > 0) {
                int randomIndex1 = Configuration.instance.randomGenerator.nextInt(0, fighters.size());
                Tour fighter1 = fighters.get(randomIndex1);
                fighters.remove(fighter1);
                int randomIndex2 = Configuration.instance.randomGenerator.nextInt(0, fighters.size());
                Tour fighter2 = fighters.get(randomIndex2);
                fighters.remove(fighter2);
                Fight fight = new Fight(fighter1, fighter2);
                secondFights.add(fight);
            }

            for (Fight fight : secondFights) {
                winners.add(executeFight(fight));
            }
        }

        return winners;
    }

    // Tour with the higher fitness value wins and gets returned
    private Tour executeFight(Fight fight) {
        return (fight.getTour1().getFitness() > fight.getTour2().getFitness()) ? fight.getTour1() : fight.getTour2();
    }
}