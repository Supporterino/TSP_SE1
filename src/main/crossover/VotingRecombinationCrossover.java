package crossover;

import base.City;
import base.Tour;

import java.util.ArrayList;
import java.util.Arrays;

// VRX
public class VotingRecombinationCrossover extends crossover.Crossover {

    random.MersenneTwisterFast random = new random.MersenneTwisterFast();

    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {
        base.City[][] parents = {(base.City[]) tour01.getCities().toArray(), (base.City[]) tour02.getCities().toArray()};
        base.City[][] childs = new base.City[2][280];
        ArrayList[] flags = new ArrayList[]{tour01.getCities(), tour02.getCities()};

        //prueft ob die parents gemeinsamkeiten haben, falls ja wird diese an die passende stelle im child geschrieben
        for(int x = 0; x < 280; x++){
            if(parents[0][x].getId() == parents[1][x].getId()){
                childs[0][x] = parents[0][x];
                childs[1][x] = parents[0][x];
            }
        }

        //bereinigt flags von bereits vorhandenen staedten
        for(int x = 0; x < 280; x++){
            if(childs[0][x] != null){
                flags[0].remove(childs[0][x]);
                flags[1].remove(childs[1][x]);
            }
        }

        //waehlt zufaellig einen parent aus von dem an stelle x die City vererbt wird
        //falls dieser bereits vorhanden ist wird zufällig einer der übrigen Citys ausgewaehlt
        boolean randBool;
        for(int w = 0; w < 2; w++){
            for(int x = 0; x < 280; x++){
                if(childs[w][x] != null) continue;

                randBool = random.nextBoolean();

                if(randBool && !checkContains(parents[0][x].getId(), childs[w])){
                    childs[w][x] = parents[0][x];
                    flags[w].remove(parents[0][x]);
                    continue;
                }
                if(!randBool && !checkContains(parents[1][x].getId(), childs[w])){
                    childs[w][x] = parents[1][x];
                    flags[w].remove(parents[1][x]);
                    continue;
                }

                childs[w][x] = (City) flags[w].remove(random.nextInt(0, flags[w].size()));
            }
        }


        //erstellt das array fuer die Rueckgabe
        ArrayList<Tour> childList = new ArrayList<>();
        //child1
        ArrayList<base.City> genList = new ArrayList<>(Arrays.asList(childs[0]));
        Tour tour = new Tour();
        tour.setCities(genList);
        childList.add(tour);
        //child2
        genList = new ArrayList<>(Arrays.asList(childs[1]));
        tour = new Tour();
        tour.setCities(genList);
        childList.add(tour);


        return childList;
    }

    public boolean checkContains(int id, base.City[] toCheck){
        for(int x = 0; x < 280; x++){
            if(toCheck[x] != null && toCheck[x].getId()==id) return true;
        }

        return false;
    }
}