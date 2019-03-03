package reporting.generators.individual;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DotGenerator extends ChartGenerator {

    protected XYSeries series;

    public DotGenerator(String dbName) {
        super("Dot Chart", dbName);
        series = new XYSeries("Series");
    }

    @Override
    protected void fillDataset() {
        //TODO fill series from db
    }

    @Override
    public JFreeChart generateChart() {
        fillDataset();
        PlotOrientation orientation = PlotOrientation.HORIZONTAL;
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return ChartFactory.createScatterPlot(name, "TODO", "TODO", dataset, orientation, true, true, true);//TODO label fields
    }

}
