package crossover;

import base.City;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.*;

// PX
public class PositionCrossover extends Crossover {

    private MersenneTwisterFast random = new MersenneTwisterFast();

    @SuppressWarnings("Duplicates")
    public ArrayList<Tour> doCrossover(Tour tour01,
                                       Tour tour02)
    {
        base.City[] parent1 = (base.City[]) tour01.getCities().toArray();
        base.City[] parent2 = (base.City[]) tour02.getCities().toArray();
        base.City[] child1 = new City[280];
        base.City[] child2 = new City[280];

        //crossover parent1
        Set<Integer> indices1 = generateRandomNumberSet();

        for(int i : indices1){
            base.City c = parent1[i];
            child1[i] = c;
        }

        for (int i = 0; i < parent2.length; i++) {
            if (child1[i] != null) continue; //check if index needs to be repaired
            base.City c = parent2[i];
            child1[i] = c;
        }
        //end crossover parent1

        //crossover parent2
        Set<Integer> indices2 = generateRandomNumberSet();

        for(int i : indices2){
            base.City c = parent2[i];
            child2[i] = c;
        }

        for (int i = 0; i < parent1.length; i++) {
            if (child2[i] != null) continue; //check if index needs to be repaired
            base.City c = parent1[i];
            child2[i] = c;
        }
        //end crossover parent2

        //creation of Output
        ArrayList<Tour> outputTourList = new ArrayList<>();


        //child1
        ArrayList<City> genList = new ArrayList<>(Arrays.asList(child1));
        Tour tourChild1 = new Tour();
        tourChild1.setCities(genList);
        outputTourList.add(tourChild1);


        //child2
        genList = new ArrayList<>(Arrays.asList(child2));
        Tour tourChild2 = new Tour();
        tourChild2.setCities(genList);
        outputTourList.add(tourChild2);


        return outputTourList;
    }

    public Set<Integer> generateRandomNumberSet(){

        MersenneTwisterFast random = new MersenneTwisterFast();

        int size = random.nextInt(1,279); //Number of to be selected cities

        Set<Integer> randomUniqueNumbers = new HashSet<>();

        while (randomUniqueNumbers.size() < size){
            int randomIndex = random.nextInt(0, 279);

            if(randomUniqueNumbers.contains(randomIndex)) continue;

            randomUniqueNumbers.add(randomIndex);
        }

        return randomUniqueNumbers;
    }

}