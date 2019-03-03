package statistics;

import java.util.Comparator;

public class DoubleComparator implements Comparator<Double> {
    @Override
    public int compare(Double d1, Double d2) {
        double number = d1 - d2;
        if (number > 0)
            return 1;
        else if (number < 0)
            return -1;
        else
            return 1;
    }
}
