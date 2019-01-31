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
            City helpObj = null;
            if(mask01[i] && !mask02[i]) {   // tour01 dominant über tour02
                helpObj = child02.getCity(i);
                child02.addCity(i, tour01.getCity(i)); // -> child02 übernimmt City an Position i von tour01
                // um doppelte und fehlende Städte zu vermeiden, wird nun danach gesucht
                // falls solche Fälle gefunden werden, wird die neue Änderung behalten, und die alte Position mit der verdrängten City ersetzt
                for(int k=0; k<tour01.getSize(); k++) {
                    if(child02.getCity(k)==tour01.getCity(i) && k!=i) {
                        child02.addCity(k, helpObj);
                    }
                }
            }
            if(!mask01[i] && mask02[i]) {   // tour02 dominant über tour01
                helpObj = child01.getCity(i);
                child01.addCity(i, tour02.getCity(i)); // -> child01 übernimmt City an Position i von tour02
                // siehe for-Schleife im IF-Block darüber
                for(int k=0; k<tour02.getSize(); k++) {
                    if(child01.getCity(k)==tour02.getCity(i) && k!=i) {
                        child01.addCity(k, helpObj);
                    }
                }
            }
        }
        ArrayList<Tour> output = new ArrayList<Tour>();
        output.add(child01);
        output.add(child02);
        return output;
    }
}
/* Fragen:
    - Muss beim Crossover darauf geachtet werden, dass alle Städte nur einmal vorkommen?
        ja, jede Stadt darf nur einmal vorkommen (unique)
    - Wo sind die Masks für den MX zu finden / implementieren?
        random für jeden aufruf neu zu generieren
 */