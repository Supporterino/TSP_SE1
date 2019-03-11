package reporting.generators;

import data.HSQLManagerForEvolution.HSQLManager;
import data.HSQLManagerForEvolution.Scenario;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import java.util.ArrayList;

/**
 * generates a boxplot for population,crossover and mutation sizes in the db
 */
public class BoxGenerator extends ChartGenerator {
    protected DefaultBoxAndWhiskerCategoryDataset dataset;

    public BoxGenerator(HSQLManager db) {
        super("Box Chart", db);
        this.dataset = new DefaultBoxAndWhiskerCategoryDataset();
    }

    protected void fillDataset() {
        ArrayList<Scenario> scenarios = genScenarios();

        ArrayList<Double> population = new ArrayList<>();
        ArrayList<Double> mutation = new ArrayList<>();
        ArrayList<Double> mutationRatio = new ArrayList<>();
        ArrayList<Double> crossover = new ArrayList<>();
        for (Scenario s : scenarios
        ) {
            population.add((double) s.getPopulation_size());
            mutation.add(s.getMutation_ratio());
            crossover.add(s.getCrossover_ratio());
            mutationRatio.add(s.getMutation_ratio());
        }

        dataset.add(population, "Population size", "");
        dataset.add(mutation, "Mutation rotation", "");
        dataset.add(mutationRatio, "Mutation ratio", "");
        dataset.add(crossover, "Crossover", "");
    }

    @Override
    public JFreeChart generateChart() {
        fillDataset();
        return ChartFactory.createBoxAndWhiskerChart(name, "Category", "Value", dataset, true);
    }


}
