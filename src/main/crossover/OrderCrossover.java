package crossover;

import base.City;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Arrays;

// OX1
public class OrderCrossover extends crossover.Crossover {

    random.MersenneTwisterFast random = new MersenneTwisterFast();

    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {
        base.City[] parent1 = (base.City[]) tour01.getCities().toArray();
        base.City[] parent2 = (base.City[]) tour02.getCities().toArray();
        base.City[] child1 = new City[280];
        base.City[] child2 = new City[280];
        ArrayList<base.City> ordered1 = new ArrayList<>();
        ArrayList<base.City> ordered2 = new ArrayList<>();

        //Grenzen Erzeugen
        int firstCut = random.nextInt(0, 277);//cut nach eins bis vorletzte stelle
        int secondCut = random.nextInt(firstCut + 1, 278);//cut nach erstem und vor letzter stelle

        //sortiert die Parents um, beginnend nach secondCut
        for(int x = secondCut + 1; x != secondCut; x++){
            if(x >= 280) x = 0;

            ordered1.add(parent2[x]);
            ordered2.add(parent1[x]);
        }
        //fuegt die Gene an stelle secondCut ein
        ordered1.add(parent2[secondCut]);
        ordered2.add(parent1[secondCut]);

        //Mittleren Teil uebertragen und ordered bereinigen
        for(int x = firstCut+1; x <= secondCut; x++){
            //uebertraegt von parent an child
            child1[x] = parent1[x];
            child2[x] = parent2[x];

            //entfernt die bereits im child vorhandenen Gene
            ordered1.remove(child1[x]);
            ordered2.remove(child2[x]);
        }

        //Uebertraegt geordneten rest in childs
        for(int x = secondCut + 1; x != firstCut + 1; x++){
            if(x >= 280) x = 0;
            child1[x] = ordered1.remove(0);
            child2[x] = ordered2.remove(0);
        }

        //erstellt das array fuer die Rueckgabe
        ArrayList<Tour> childList = new ArrayList<>();
        //child1
        ArrayList<City> genList = new ArrayList<>(Arrays.asList(child1));
        Tour tour = new Tour();
        tour.setCities(genList);
        childList.add(tour);
        //child2
        genList = new ArrayList<>(Arrays.asList(child2));
        tour = new Tour();
        tour.setCities(genList);
        childList.add(tour);


        return childList;
    }
}