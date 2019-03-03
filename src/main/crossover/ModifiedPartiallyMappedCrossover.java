package crossover;

import base.City;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.ArrayList;

// MPMX
public class ModifiedPartiallyMappedCrossover extends Crossover {
    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02)
    {
        ArrayList<Tour> output = new ArrayList<>();
        output.add(createMPMXChild(tour01, tour02));
        output.add(createMPMXChild(tour02, tour01));

        return output;
    }

    public Tour createMPMXChild(Tour parent1, Tour parent2)
    {
        random.MersenneTwisterFast random = new MersenneTwisterFast();

        Tour child = new Tour();

        int cut1 = random.nextInt(0,parent1.getSize()-2);
        int cut2 = random.nextInt(cut1+1,parent1.getSize()-1);

        for(int i = 0; i < parent1.getSize(); i++)
        {
            if(i<cut1)
            {
                child.addCity(parent1.getCity(i));
            } else if (i>=cut1 && i<cut2)
            {
                child.addCity(parent2.getCity(i));
            } else
            {
                child.addCity(parent1.getCity(i));
            }
        }

        for(int i = 0, j = cut2; i < child.getSize(); i++, j++)
        {
            if(cut2-cut1 == 0)
            {
                break;
            }
            if(j == child.getSize())
            {
                j = 0;
            }
            if(cityDoubledInChild(child, child.getCity(j), j))
            {
                child.addCity(j,parent1.getCity(cut1));
                j--;
                i--;
                cut1++;
            }
        }

        return child;
    }

    private Boolean cityDoubledInChild(Tour child, City city, int index){
        for (int i = 0; i < child.getSize(); i++)
        {
            if(i == index)
            {
                continue;
            }
            if (child.getCity(i) == city)
            {
                return true;
            }
        }
        return false;
    }
}