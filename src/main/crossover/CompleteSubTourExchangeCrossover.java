package crossover;

import base.City;
import base.Population;
import base.Tour;
import selection.TournamentSelection;

import java.util.ArrayList;
import java.util.List;

// CSEX
public class CompleteSubTourExchangeCrossover extends crossover.Crossover {

    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {
        City[] parent1 = (City[]) tour01.getCities().toArray();
        City[] parent2 = (City[]) tour02.getCities().toArray();
        int row = 0;

        int numberOfRows = 0;
        ArrayList<Tour> childList = new ArrayList<>();
        List<Integer> laengeFolgen = new ArrayList<>();
        List<base.City> folgen = new ArrayList<>();


        for (int i = 0; i <= parent1.length; i++) {
            if (parent1[i].getId() + 1 == parent1[i + 1].getId()) {
                int j = i;

                List<base.City> folge = new ArrayList<>();
                int laenge = 0;
                while (parent1[j].getId() + 1 == parent1[j + 1].getId()) {
                    row++;
                    j++;
                }
                j = i;

                while (parent1[j].getId() + 1 == parent1[j + 1].getId()) {
                    folge.add(parent1[j]);
                    j = j + 1;
                }
                folge.add(parent1[j]);
                for (int k = 0; k <= parent2.length; k++) {
                    if (parent2[k].getId() == folge.get(0).getId() && parent2[k + 1].getId() == folge.get(1).getId()) {
                        int l = 0;
                        while (parent2[k] == folge.get(l) && parent2[k + 1] == folge.get(l + 1)) {
                            folgen.add(folge.get(l));
                            l++;
                            k++;
                            laenge++;
                        }
                        laenge++;
                        folgen.add(folge.get(l));
                        numberOfRows = numberOfRows + 1;
                        laengeFolgen.add(laenge);
                    } else if ((parent2[k].getId() == folge.get(row).getId()) && (parent2[k + 1].getId() == folge.get(row - 1).getId())) {
                        int l = row;
                        while (l > 0 && parent2[k] == folge.get(l) && parent2[k + 1] == folge.get(l + 1)) {
                            folgen.add(folge.get(l));
                            l--;
                            k++;
                            laenge++;
                        }
                        laenge++;
                        folgen.add(folge.get(l));
                        numberOfRows = numberOfRows + 1;
                        laengeFolgen.add(laenge);
                    }
                }
            }
            i = i + row;
        }
        int g;
        for (int i = 0; i <= parent1.length; i++) {
            for (g = 0; g < numberOfRows; g++) {
                List<base.City> temp = new ArrayList<>();
                int h;
                for (h = 0; h < laengeFolgen.get(g); h++) temp.add(folgen.get(h));
                if (temp.get(0) == parent1[i] && temp.get(1) == parent1[i + 1]) {
                    int loop = laengeFolgen.get(g);
                    base.City[] child = parent1.clone();
                    int z = 0;
                    while (loop > 0) {
                        child[i + z] = temp.get(loop);
                        z++;
                        loop--;
                    }
                    Tour tour = new Tour();
                    for (City city : child) {
                        tour.addCity(city);
                    }
                    childList.add(tour);

                } else if (temp.get(temp.size() - 1) == parent1[i] && temp.get(temp.size() - 2) == parent1[i + 1]) {
                    int loop = laengeFolgen.get(g);
                    base.City[] child = parent1.clone();
                    int z = 0;
                    while (loop < temp.size()) {
                        child[i + z] = temp.get(loop);
                        z++;
                        loop++;
                    }
                    Tour tour = new Tour();
                    for (City city : child) {
                        tour.addCity(city);
                    }
                    childList.add(tour);
                }
                if (temp.get(0) == parent2[i] && temp.get(1) == parent2[i + 1]) {
                    int loop = laengeFolgen.get(g);
                    base.City[] child = parent2.clone();
                    int z = 0;
                    while (loop > 0) {
                        child[i + z] = temp.get(loop);
                        z++;
                        loop--;
                    }
                    Tour tour = new Tour();
                    for (City city : child) {
                        tour.addCity(city);
                    }
                    childList.add(tour);

                } else if (temp.get(temp.size() - 1) == parent2[i] && temp.get(temp.size() - 2) == parent2[i + 1]) {
                    int loop = laengeFolgen.get(g);
                    base.City[] child = parent2.clone();
                    int z = 0;
                    while (loop < temp.size()) {
                        child[i + z] = temp.get(loop);
                        z++;
                        loop++;
                    }
                    Tour tour = new Tour();
                    for (City city : child) {
                        tour.addCity(city);
                    }
                    childList.add(tour);

                }
            }
        }


        Population population = new Population();
        population.setTourList(childList);
        TournamentSelection selection = new TournamentSelection();
        ArrayList<Tour> winner = new ArrayList<>(selection.doSelection(population));
        //noinspection SuspiciousMethodCalls
        childList.remove(selection.doSelection(population));
        population.setTourList(childList);
        winner.addAll(selection.doSelection(population));


        return winner;
    }

}
