package reporting;

import data.HSQLManager;
import org.jfree.chart.JFreeChart;
import reporting.generators.BoxGenerator;
import reporting.generators.DotGenerator;
import reporting.generators.HistGenerator;

import java.util.ArrayList;

/**
 * Main interfacing class for the reporting
 * needs a filled database and will generate and return or display different charts
 */
public class Generator implements IGenerator {
    HSQLManager db;

    public Generator(HSQLManager db) {
        this.db = db;
    }

    @Override
    public JFreeChart generateBox() {
        return new BoxGenerator(db).generateChart();
    }

    @Override
    public JFreeChart generateDot() {
        return new DotGenerator(db).generateChart();
    }

    @Override
    public JFreeChart generateHist() {
        return new HistGenerator(db).generateChart();
    }

    @Override
    public ArrayList<JFreeChart> generateAll() {
        ArrayList<JFreeChart> charts = new ArrayList<>();
        charts.add(generateBox());
        charts.add(generateDot());
        charts.add(generateHist());
        return charts;
    }

    @Override
    public void showAll() {
        new BoxGenerator(db).show();
        new DotGenerator(db).show();
        new HistGenerator(db).show();
    }
}
