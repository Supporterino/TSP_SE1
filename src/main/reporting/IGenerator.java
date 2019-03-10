package reporting;

import org.jfree.chart.JFreeChart;

import java.util.ArrayList;

public interface IGenerator {

    /**
     * Creates a Box Plot from DB data
     *
     * @return the finished chart object
     */
    JFreeChart generateBox();

    /**
     * Creates a Dot Plot from DB data
     *
     * @return the finished chart object
     */
    JFreeChart generateDot();

    /**
     * Creates a Hist Plot from DB data
     *
     * @return the finished chart object
     */
    JFreeChart generateHist();

    /**
     * Creates all charts and packages them in a list
     *
     * @return list of all generated charts
     */
    ArrayList<JFreeChart> generateAll();

    /**
     * creates all charts and calls their .show method
     */
    void showAll();


}
