package crossover;

import base.Tour;

import java.util.ArrayList;

// DPX
public class DistancePreservingCrossover extends Crossover {
    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {

        Tour child1 = null, child2 = null;

        //Detect common sub-paths

        int currentSubpathLength;   //The current length of the subpath-1 need to be added to i
        // otherwise there'll be mutliple subpaths of the same cities/tours
        int currentTour02Index = 0;         //This integer will be used to save the index of the start of the subpath
        int lorSubpath;                  // Left or Right Subpath, -1 means the subpath continues to the left, 1 to the right
        // 0 means the subpath contains only one city
        for (int i = 0; i <= tour01.getSize(); i++) {

            currentSubpathLength = 0;
            Tour tmpTour = null;               //This tour temporarily saves the subpaths before they're added to the children

            tour01.getCity(i).getId();  //Beginning of subpath

            for (int j = 0; j <= tour02.getSize(); j++) { //Searching for the beginning of the subpath in tour02

                if (tour01.getCity(i).getId() == tour02.getCity(j).getId())   //City from subpath found found
                    currentTour02Index = j;
                else
                    currentTour02Index = -1;                                   // If the Ciry can not be found

            }

            //Now to check if the subpath in Tour02 continues to the right or left
            if (currentTour02Index != -1) {

                if (tour01.getCity(i + 1).getId() == tour02.getCity(currentTour02Index + 1).getId()) //Check if it continues to the right
                    lorSubpath = 1;
                else if (tour01.getCity(i + 1).getId() == tour02.getCity(currentTour02Index - 1).getId()) //Check if it continues to the left
                    lorSubpath = -1;
                else                //Otherwise the subpath contains only one city
                    lorSubpath = 0;


                // Add the subpath to tmpTour

                if (lorSubpath == 0) {   //No need to use tmpTour as the subtour contains only one city
                    child1.addCity(tour01.getCity(i));
                    child2.addCity(tour01.getCity(i));
                } else if (lorSubpath == 1) {
                    tmpTour.addCity(tour01.getCity(i));


                    for (int j = 1; (j <= tour01.getSize()) && (j <= tour01.getSize() && j >= 0); j++) {
                        if (tour01.getCity(i + j).getId() == tour02.getCity(currentTour02Index + (j * lorSubpath)).getId()) {
                            currentSubpathLength += 1;
                            tmpTour.addCity(tour01.getCity(i + j));
                        } else
                            break;      // If the subtour no longer share the same cities, this will be stopped and tmpTour is finalized

                    }

                    // tmpTour is now finalized and the children can be assigned
                    // Child1 will be assigned forward and child2 backwards

                    for (int k = 0; k <= tmpTour.getSize(); k++)
                        child1.addCity(tmpTour.getCity(k));

                    for (int k = tmpTour.getSize(); k >= 0; k--)
                        child2.addCity(tmpTour.getCity(k));

                }
            }

        }

        //Special Case:
        // The two parents didn't share the same cities and the children are not the same length as the parents
        // This case is not described
        // Offered Solution:
        // The missing cities will be copied from the parents (child1 from parent 2, child 2 from parent 1)

        while (child1.getSize() < tour02.getSize())
            child1.addCity(tour02.getCity(child1.getSize()));

        while (child2.getSize() < tour01.getSize())
            child2.addCity(tour01.getCity(child2.getSize()));

        ArrayList<Tour> result = new ArrayList<>();
        result.add(child1);
        result.add(child2);

        return result;
    }
}