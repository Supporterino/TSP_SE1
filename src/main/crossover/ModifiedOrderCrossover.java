package crossover;

import base.City;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.ArrayList;

// MOX
public class ModifiedOrderCrossover extends Crossover {
    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02)
    {
        ArrayList<Tour> output = new ArrayList<>();
        output.add(createMOXChild(tour01, tour02));
        output.add(createMOXChild(tour02, tour01));

        return output;
    }

    public Tour createMOXChild(Tour parent1, Tour parent2)
    {
        random.MersenneTwisterFast random = new MersenneTwisterFast();

        Tour protoChild = new Tour();
        Tour child = new Tour();

        int cut2 = random.nextInt(0,parent1.getSize()-1);
        int cut3 = getShortestDistance(parent2);
        int cut4 = random.nextInt(cut3+1,parent2.getSize()-1);

        for(int i = 0, j = cut3, k = cut2; i < parent1.getSize() + (cut4 - cut3); i++)
        {
            if(j<cut4 && k < parent1.getSize())
            {
                protoChild.addCity(parent2.getCity(j));
                j++;
            } else if (j>=cut4 && k < parent1.getSize())
            {
                if(!cityAlreadyInChild(protoChild, parent1.getCity(k)))
                {
                    protoChild.addCity(parent1.getCity(k));
                }
                k++;
                if(k >= parent1.getSize())
                {
                    j = 0;
                }
            } else
            {
                if(!cityAlreadyInChild(protoChild, parent1.getCity(j)))
                {
                    protoChild.addCity(parent1.getCity(j));
                }
                j++;
            }
        }

        for(int i = 0, j = protoChild.getSize()-cut3; i < protoChild.getSize(); i++, j++)
        {
            if(j >= protoChild.getSize())
            {
                j = 0;
            }
            child.addCity(protoChild.getCity(j));
        }

        return child;
    }

    private Boolean cityAlreadyInChild(Tour child, City city){
        for (int i = 0; i < child.getSize(); i++){
            if (child.getCity(i) == city) return true;
        }
        return false;
    }

    private int getShortestDistance(Tour tour)
    {
        int shortestCityIndex = 0;
        double shortestDistance = 0;
        double distance = 0;
        for (int i = 0; i < tour.getSize() - 1; i++)
        {
            double x1 = tour.getCity(i).getX();
            double y1 = tour.getCity(i).getY();
            double x2 = tour.getCity(i + 1).getX();
            double y2 = tour.getCity(i + 1).getY();
            distance = Tour.euclideanDistance(x1, y1, x2, y2);
            if(i == 0)
            {
                shortestDistance = distance;
            }
            if(distance < shortestDistance)
            {
                shortestDistance = distance;
                shortestCityIndex = i;
            }
        }
        return shortestCityIndex;
    }
}