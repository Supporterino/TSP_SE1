package reporting.test_applications;

import reporting.generators.demos.BoxDemo;
import reporting.generators.demos.DotDemo;
import reporting.generators.demos.HistDemo;

/**
 * Kick off Demo generation process
 */
public class DemoApplication {

    /**
     * show all demo charts in order to showcase functionality
     *
     * @param args not in use
     */
    public static void main(String[] args) {
        new BoxDemo().show();
        new DotDemo().show();
        new HistDemo().show();

    }


}
