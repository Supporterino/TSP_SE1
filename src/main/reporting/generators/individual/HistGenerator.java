package reporting.generators.individual;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

public class HistGenerator extends ChartGenerator {

    protected double[] series;

    public HistGenerator() {
        super("Hist Chart");
    }

    @Override
    protected void fillDataset() {
        //TODO fill series from db
    }

    @Override
    public JFreeChart generateChart() {
        fillDataset();
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("TODO", series, 10);
        return ChartFactory.createHistogram(name, "TODO", "TODO", dataset, PlotOrientation.HORIZONTAL, true, true, true);//TODO label fields
    }
}
