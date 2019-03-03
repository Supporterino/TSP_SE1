package reporting.generators.demos;

import reporting.generators.HistGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class HistDemo extends HistGenerator {

    public HistDemo() {
        super(null);
    }

    /**
     * fills series with random values between 1 and 100
     */
    @Override
    protected void fillDataset() {
        series = new HashMap<>();
        ArrayList<Double> list;
        for (int i = 0; i < 10; i++) {
            list = new ArrayList<>();
            for (int j = 0; j < ((int) ((Math.random() * 10) + 2)); j++) {
                list.add(Math.random() * 500);
            }
            series.put((long) i, list);
        }
    }
}
