package selection;

import base.Population;
import base.Tour;

import java.util.ArrayList;

public abstract class Selection {
    public abstract ArrayList<Tour> doSelection(Population population);

    public String toString() {
        return getClass().getSimpleName();
    }
}