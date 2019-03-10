package reporting.generators.demos;

import reporting.generators.BoxGenerator;

import java.util.ArrayList;
import java.util.List;

public class BoxDemo extends BoxGenerator {

    public BoxDemo() {
        super(null);
    }

    /**
     * creates a list filled with random values between 10 and 30
     *
     * @return
     */
    private List<Double> getInputData() {
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < (((int) Math.random() * 20) + 10); i++) {
            list.add(Math.random() * 100);
        }
        return list;
    }

    /**
     * adds 4 elements to the dataset to showcase functionality
     */
    @Override
    protected void fillDataset() {
        dataset.add(getInputData(), "DemoRow", "DemoKey");
        dataset.add(getInputData(), "DemoRow", "DemoKey");
        dataset.add(getInputData(), "DemoRow1", "DemoKey");
        dataset.add(getInputData(), "DemoRow1", "DemoKey1");
    }


}
