
package crossover;

import base.City;
import base.Tour;
import random.MersenneTwisterFast;
import java.util.ArrayList;
import java.util.HashSet;

// CX
public class CycleCrossover extends Crossover {
    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {
        ArrayList<City> parent1 =  tour01.getCities();
        ArrayList<City> parent2 = tour02.getCities();

        int length = parent1.size();
        if (length != parent2.size()) {
            return null;
        }
        // and of the children: do a crossover copy to simplify the later processing
        ArrayList<City> child1 = new ArrayList<>(parent1);
        ArrayList<City> child2 = new ArrayList<>(parent2);

        // the set of all visited indices so far
        HashSet<Integer> visitedIndices = new HashSet<>(length);
        // the indices of the current cycle
        ArrayList<Integer> indices = new ArrayList<>(length);

        // determine the starting index
        MersenneTwisterFast mT = new MersenneTwisterFast();
        int idx = mT.nextInt(0,length-1);
        int cycle = 1;

        while (visitedIndices.size() < length) {
            indices.add(idx);

            City item = parent2.get(idx);
            idx = parent1.indexOf(item);

            while (idx != indices.get(0)) {
                // add that index to the cycle indices
                indices.add(idx);
                // get the item in the second parent at that index
                item = parent2.get(idx);
                // get the index of that item in the first parent
                idx = parent1.indexOf(item);
            }

            // for even cycles: swap the child elements on the indices found in this cycle
            if (cycle++ % 2 != 0) {
                for (int i : indices) {
                    City tmp = child1.get(i);
                    child1.set(i, child2.get(i));
                    child2.set(i, tmp);
                }
            }

            visitedIndices.addAll(indices);
            // find next starting index: last one + 1 until we find an unvisited index
            idx = (indices.get(0) + 1) % length;
            while (visitedIndices.contains(idx) && visitedIndices.size() < length) {
                idx++;
                if (idx >= length) {
                    idx = 0;
                }
            }
            indices.clear();
        }




        //return
        ArrayList<Tour> returnValue =new ArrayList<>();
        Tour tourchild1 = new Tour();
        tourchild1.setCities(child1);
        returnValue.add(tourchild1);
        Tour tourchild2 = new Tour();
        tourchild1.setCities(child2);
        returnValue.add(tourchild2);


        return returnValue;

    }
}