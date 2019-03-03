package reporting.generators.demos;

import reporting.generators.individual.HistGenerator;

public class HistDemo extends HistGenerator {

    /**
     * fills series with random values between 1 and 100
     */
    @Override
    protected void fillDataset() {
        series = new double[(((int) Math.random() * 20) + 10)];
        for (int i = 0; i < series.length; i++) {
            series[i] = (Math.random() * 100);
        }
    }
}
