package crossover;

import base.City;
import base.Tour;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Random;

// Herbert Bärschneider
// Orientierung an der Erklärung von MX auf <http://www.tomaszgwiazda.com/maskedX.htm>
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
        // die einzelnen Bits stelle ich durch boolean-Werte da: '1' -> true, '0' -> false
        boolean[] mask01 = new boolean[tour01.getSize()];
        boolean[] mask02 = new boolean[tour02.getSize()];
        // Füllen der Masken mit Werten
        Random RNG = new MersenneTwisterFast();
        for(int k=0; k<tour01.getSize(); k++) {
            mask01[k] = RNG.nextBoolean();
            mask02[k] = RNG.nextBoolean();
        }
        // Masked Crossover:
        for(int i=0; i<tour01.getSize(); i++) {
            if(mask01[i] && !mask02[i]) {   // tour01 dominant über tour02
                cityCrossover(i, child02, tour01);
            }
            if(!mask01[i] && mask02[i]) {   // tour02 dominant über tour01
                cityCrossover(i, child01, tour02);
            }
        }
        ArrayList<Tour> output = new ArrayList<Tour>();
        output.add(child01);
        output.add(child02);
        return output;
    }

    private void cityCrossover(int pos, Tour child, Tour dominantParent) {
        City helpObj = child.getCity(pos);
        child.addCity(pos, dominantParent.getCity(pos));    // Child übernimmt City an geg. Position von Parent
        // um doppelte und fehlende Städte zu vermeiden, wird nun danach gesucht
        // falls solche Fälle gefunden werden, wird die neue Änderung behalten, und die alte Position mit der verdrängten City ersetzt
        for(int k=0; k<dominantParent.getSize(); k++) {
            if(child.getCity(k)==dominantParent.getCity(pos) && k!=pos) {
                child.addCity(k, helpObj);
            }
        }
    }

}
/* Fragen:
    - Muss beim Crossover darauf geachtet werden, dass alle Städte nur einmal vorkommen?
        ja, jede Stadt darf nur einmal vorkommen (unique)
    - Wo sind die Masks für den MX zu finden / implementieren?
        random für jeden aufruf neu zu generieren
 */