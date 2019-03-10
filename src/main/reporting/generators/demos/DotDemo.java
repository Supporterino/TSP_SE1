package reporting.generators.demos;

import reporting.generators.DotGenerator;

public class DotDemo extends DotGenerator {

    public DotDemo() {
        super(null);
    }

    /**
     * fills series with x and y values between 0 and 100
     */
    @Override
    protected void fillDataset() {
        for (int i = 0; i < (((int) Math.random() * 20) + 10); i++) {
            series.add(Math.random() * 100, Math.random() * 100);
        }
    }


}
