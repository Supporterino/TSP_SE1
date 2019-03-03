package crossover;

import base.City;
import base.Tour;
import crossover.Crossover;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Arrays;

// PMX
public class PartiallyMappedCrossover extends Crossover {

    @SuppressWarnings("Duplicates")
    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {
        base.City[] parent1 = (base.City[]) tour01.getCities().toArray();
        base.City[] parent2 = (base.City[]) tour02.getCities().toArray();
        base.City[] child1 = new City[280];
        base.City[] child2 = new City[280];

        //new instance of MersenneTwister
        random.MersenneTwisterFast random = new MersenneTwisterFast();

        //define begin of swath         -- swath mind. 2 Stellen groß
        int beginOfSwath = random.nextInt(0, 277); //kleineZahl
        int endOfSwath = random.nextInt(beginOfSwath + 1, 279); //größere Zahl

        //crossover algorithm

        //child1 begin
        //copy defined swath variables to output child
        for(int countSwath = beginOfSwath; countSwath <= endOfSwath; countSwath++) {
            child1[countSwath] = parent1[countSwath];
        }


            for (int countSwath = beginOfSwath; countSwath <= endOfSwath; countSwath++) {
                //checks if city equals city in same position as in parent 2 -> break
                if (parent1[countSwath] == parent2[countSwath]) {
                    continue;
                } else {

                    // looks for the same number in the swatch -> breaks
                    for (int x = 0; x < endOfSwath - countSwath; x++)

                        if (parent1[countSwath] == parent2[beginOfSwath + x]) {
                            continue;
                        }
                        //takes the number to the position of parent2
                        else {
                            for (int y = 0; y <= 279; y++) {
                                if (parent2[y] == parent1[countSwath]) {
                                    child1[y] = parent2[y];
                                }
                            }
                        }
                }
            }
            for (int n = 0; n <= 279; n++) {
                if (child1[n] == null) {
                    child1[n] = parent2[n];
                }
            }

        //child1 finished

        //child2 begin

        for(int countSwath = beginOfSwath; countSwath <= endOfSwath; countSwath++){


            //checks if city equals city in same position as in parent 1 -> break

            if(parent2[countSwath] == parent1[countSwath]) {
                continue; }

                else{

                // looks for the same number in the swatch -> breaks

                for(int x = 0; x < endOfSwath - countSwath; x++ )

                    if(parent2[countSwath] == parent1[beginOfSwath+x]){
                        continue;
                    }

                    //if city is not in swatch parent1 is getting scanned and var goes at that position in output
                    else{
                        for(int y = 0; y <= 279; y++){
                            if(parent1[y] == parent2[countSwath]){
                                child2[y]= parent1[y];
                                break;
                            }
                        }
                    }
            }
        }

        //put citys from parent1 to empty child2
        for(int n = 0; n <=279; n++ ){
            if(child2[n] == null){
                child2[n] = parent1[n];
            }
        }
        //child2 finished


        //creation of Output
        ArrayList<Tour> outputTourList = new ArrayList<>();


        //child1
        ArrayList<City> genList = new ArrayList<>(Arrays.asList(child1));
        Tour tourchild1 = new Tour();
        tourchild1.setCities(genList);
        outputTourList.add(tourchild1);


        //child2
        genList = new ArrayList<>(Arrays.asList(child2));
        Tour tourchild2 = new Tour();
        tourchild2.setCities(genList);
        outputTourList.add(tourchild2);


        return outputTourList;

    }
}