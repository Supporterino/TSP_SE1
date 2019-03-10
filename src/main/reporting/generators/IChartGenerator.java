package reporting.generators;

import org.jfree.chart.JFreeChart;

public interface IChartGenerator {

    /**
     * generate a chart using the ChartFactory
     *
     * @return a finished chart object
     */
    JFreeChart generateChart();

    /**
     * generates and displays a chart
     */
    void show();
}
