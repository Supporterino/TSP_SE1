package reporting.generators.individual;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

public class HistGenerator extends ChartGenerator {

    protected double[] series;

    public HistGenerator(String dbName) {
        super("Hist Chart", dbName);
    }

    @Override
    protected void fillDataset() {
        //TODO fill series from db
    }

    @Override
    public JFreeChart generateChart() {
        fillDataset();
        HistogramDataset dataset = new HistogramDataset();
        try {
            dataset.addSeries("TODO", series, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Series not filled. Chart cannot be created");
            e.printStackTrace();
            series=new double[1];
            series[0]=10;
            dataset.addSeries("TODO", series, 10);
        }
        return ChartFactory.createHistogram(name, "TODO", "TODO", dataset, PlotOrientation.VERTICAL, true, true, true);//TODO label fields
    }
}
