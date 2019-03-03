package reporting.generators;

import org.jfree.chart.JFreeChart;
import reporting.generators.individual.BoxGenerator;
import reporting.generators.individual.DotGenerator;
import reporting.generators.individual.HistGenerator;

import java.util.ArrayList;

public class Generator implements IGenerator {
    String dbName;

    public Generator(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public JFreeChart generateBox() {
        return new BoxGenerator(dbName).generateChart();
    }

    @Override
    public JFreeChart generateDot() {
        return new DotGenerator(dbName).generateChart();
    }

    @Override
    public JFreeChart generateHist() {
        return new HistGenerator(dbName).generateChart();
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
        new BoxGenerator(dbName).show();
        new DotGenerator(dbName).show();
        new HistGenerator(dbName).show();
    }
}
