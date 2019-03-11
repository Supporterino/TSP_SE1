package reporting.generators;

import data.HSQLManagerForEvolution.HSQLManager;
import data.HSQLManagerForEvolution.Scenario;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;

/**
 * Maps population size for each id
 */
public class DotGenerator extends ChartGenerator {

    protected XYSeries series;

    public DotGenerator(HSQLManager db) {
        super("Dot Chart", db);
        series = new XYSeries("Series");
    }

    @Override
    protected void fillDataset() {

        ArrayList<Scenario> scenarios = genScenarios();
        for (Scenario s : scenarios
        ) {
            series.add(s.getId(), s.getPopulation_size());
        }

    }

    @Override
    public JFreeChart generateChart() {
        fillDataset();
        PlotOrientation orientation = PlotOrientation.VERTICAL;
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return ChartFactory.createScatterPlot(name, "ID", "Population Size", dataset, orientation, true, true, true);
    }

}
