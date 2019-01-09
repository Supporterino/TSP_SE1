package selection;

import base.Population;
import base.Tour;
import configuration.Configuration;

import java.util.ArrayList;

// TS
public class TournamentSelection extends Selection {

    // If doubled Tours are a problem in the returned list, doubles can easily be deleted
    public ArrayList<Tour> doSelection(Population population) {
        ArrayList<Tour> winners = new ArrayList<Tour>();
        ArrayList<Tour> populationCopy = new ArrayList<Tour>(population.getTourList());

        if(populationCopy.size() <= 0 || populationCopy.size() % 2 == 1)
            throw new java.lang.RuntimeException("Population size is too small or not even");

        ArrayList<Fight> fights = new ArrayList<Fight>();

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

        // If second fight round should happen
        if(Configuration.instance.randomGenerator.nextDouble(0.0, 1.0) <= Configuration.instance.selectionPressureProbability) {
            int numberOfFightersForNextFight = (int)(winners.size() * Configuration.instance.selectionPressureRatio);
            if (numberOfFightersForNextFight % 2 == 1)
                numberOfFightersForNextFight--;

            ArrayList<Tour> fighters = new ArrayList<Tour>();
            while(fighters.size() < numberOfFightersForNextFight) {
                int randomIndex = Configuration.instance.randomGenerator.nextInt(0, fighters.size());
                Tour fighter = fighters.get(randomIndex);
                fighters.add(fighter);
                winners.remove(fighter);
            }

            ArrayList<Fight> secondFights = new ArrayList<Fight>();
            for (Fight fight : secondFights) {
                winners.add(executeFight(fight));
            }
        }

        return winners;
    }

    private Tour executeFight(Fight fight) {
        return (fight.getTour1().getFitness() > fight.getTour2().getFitness()) ? fight.getTour1() : fight.getTour2();
    }
}