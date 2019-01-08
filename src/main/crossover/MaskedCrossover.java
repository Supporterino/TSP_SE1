package crossover;

import base.Tour;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Random;

// Herbert Bärschneider
// MX
public class MaskedCrossover extends Crossover {
    public ArrayList<Tour> doCrossover(Tour tour01, Tour tour02) {
        Tour child01 = new Tour();
        Tour child02 = new Tour();
        // zunächst müssen die Kinder als Kopien der Eltern angelegt werden
        child01.setCities(new ArrayList<>(tour01.getCities()));
        child02.setCities(new ArrayList<>(tour02.getCities()));
        // die Masken sind eigentlich Vektoren von '1' und '0'
        // die Länge muss der Länge des Lösungsansatzes entsprechen
        // die einzelnen Bits stelle ich durch boolean-Werte da
        boolean[] mask01 = new boolean[tour01.getSize()];
        boolean[] mask02 = new boolean[tour02.getSize()];
        // Füllen der Masken mit Werten
        Random RNG = new MersenneTwisterFast();
        for(int k=0; k<tour01.getSize(); k++) {
            mask01[k] = RNG.nextBoolean();
            mask02[k] = RNG.nextBoolean();
        }

        return null;
        /*
        ArrayList<Tour> output = new ArrayList<Tour>();
        output.add(child01);
        output.add(child02);
        return output;
         */
    }
}
/* Fragen:
    - Muss beim Crossover darauf geachtet werden, dass alle Städte nur einmal vorkommen?
        ja, jede Stadt darf nur einmal vorkommen (unique)
    - Wo sind die Masks für den MX zu finden / implementieren?
        random für jeden aufruf neu zu generieren
 */