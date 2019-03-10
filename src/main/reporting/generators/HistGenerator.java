package reporting.generators;

import data.HSQLManagerForEvolution.HSQLManager;
import data.HSQLManagerForEvolution.Scenario;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * plots crossover ratio for each id
 */
public class HistGenerator extends ChartGenerator {

    protected HashMap<Long, ArrayList<Double>> series;

    public HistGenerator(HSQLManager db) {
        super("Hist Chart", db);
    }

    @Override
    protected void fillDataset() {
        ArrayList<Scenario> scenarios = genScenarios();
        series = new HashMap<>();
        ArrayList<Double> current;
        for (Scenario s : scenarios
        ) {
            current = series.get(s.getId());
            if (current == null) {
                current = new ArrayList<>();
                series.put(s.getId(), current);
            }
            current.add(s.getCrossover_ratio());

        }
    }

    @Override
    public JFreeChart generateChart() {
        fillDataset();
        HistogramDataset dataset = new HistogramDataset();
        try {
            double[] currentSet;
            ArrayList<Double> currentList;
            for (Map.Entry<Long, ArrayList<Double>> e : series.entrySet()
            ) {
                currentList = e.getValue();
                currentSet = new double[currentList.size()];
                for (int i = 0; i < currentList.size(); i++) {
                    currentSet[i] = currentList.get(i);
                }

                dataset.addSeries(e.getKey(), currentSet, 10);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Series not filled. Chart cannot be created");
            e.printStackTrace();
            double[] backup = new double[1];
            backup[0] = 10;
            dataset.addSeries("BACKUP VALUE TO KEEP AWAY FROM CRASHING", backup, 10);
        }
        return ChartFactory.createHistogram(name, "ID", "Crossover Ratio", dataset, PlotOrientation.VERTICAL, true, true, true);//TODO label fields
    }
}
