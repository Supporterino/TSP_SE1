package reporting.generators;

import org.jfree.chart.JFreeChart;
import reporting.generators.individual.BoxGenerator;
import reporting.generators.individual.DotGenerator;
import reporting.generators.individual.HistGenerator;

import java.util.ArrayList;

public class Generator implements IGenerator {

    @Override
    public JFreeChart generateBox() {
        return new BoxGenerator().generateChart();
    }

    @Override
    public JFreeChart generateDot() {
        return new DotGenerator().generateChart();
    }

    @Override
    public JFreeChart generateHist() {
        return new HistGenerator().generateChart();
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
        new BoxGenerator().show();
        new BoxGenerator().show();
        new HistGenerator().show();
    }
}
