package crossover;

import base.City;
import base.Tour;

import java.util.ArrayList;

// SMX
public class SortedMatchCrossover extends Crossover {
    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {
        ArrayList<City> parent1 = tour01.getCities();
        ArrayList<City> parent2 = tour02.getCities();

        //Iterate through first parent
        for (int index1 = 0; index1 < parent1.size(); index1++) {
            int index2 = parent2.indexOf(parent1.get(index1));
            int length1 = parent1.size() - index1;
            int length2 = parent2.size() - index2;
            int maxLength = length1 > length2 ? length1 : length2;
            //Check for maximum length
            for (int i = 1; i < maxLength; i++) {
                //Check for same end
                if (parent1.get(index1 + i) == parent2.get(index2 + i)) {
                    ArrayList<City> sub1 = new ArrayList<>();
                    ArrayList<City> sub2 = new ArrayList<>();
                    for (int j = 0; j <= i; j++) {
                        sub1.add(parent1.get(index1 + j));
                        sub2.add(parent2.get(index2 + j));
                    }
                    //Check if set is identical
                    if (sub1.containsAll(sub2) && sub2.containsAll(sub1)) {
                        if (!sub1.equals(sub2)) {
                            Tour subTour1 = new Tour();
                            Tour subTour2 = new Tour();
                            subTour1.setCities(sub1);
                            subTour2.setCities(sub2);

                            //Determine which subtour has the lower cost and replace them in the right parent
                            if (subTour1.getFitness() > subTour2.getFitness()) {
                                Tour output = new Tour();
                                for (int k = 0; k < index1; k++) {
                                    output.addCity(parent1.get(k));
                                }
                                for (int k = 0; k < i; k++) {
                                    output.addCity(parent2.get(index2 + k));
                                }
                                for (int k = index1 + i; k < parent1.size(); k++) {
                                    output.addCity(parent1.get(k));
                                }
                                ArrayList<Tour> outputTourList = new ArrayList<>();
                                outputTourList.add(output);
                                return outputTourList;
                            } else if (subTour2.getFitness() > subTour1.getFitness()) {
                                Tour output = new Tour();
                                for (int k = 0; k < index2; k++) {
                                    output.addCity(parent2.get(k));
                                }
                                for (int k = 0; k < i; k++) {
                                    output.addCity(parent1.get(index1 + k));
                                }
                                for (int k = index2 + i; k < parent2.size(); k++) {
                                    output.addCity(parent2.get(k));
                                }
                                ArrayList<Tour> outputTourList = new ArrayList<>();
                                outputTourList.add(output);
                                return outputTourList;
                            }
                        }
                    }
                }
            }
        }
        //If there is nothing to optimize return tour01
        ArrayList<Tour> outputTourList = new ArrayList<>();
        outputTourList.add(tour01);
        return outputTourList;
    }
}