package reporting.generators;

import data.HSQLManagerForEvolution.HSQLManager;
import data.HSQLManagerForEvolution.Scenario;
import reporting.gui.ChartFrame;

import java.util.ArrayList;
import java.util.List;

public abstract class ChartGenerator implements IChartGenerator {

    protected String name;

    /**
     * connecter used to read data from the db in order to fill the datasets
     */
    protected HSQLManager database;

    public ChartGenerator(String name, HSQLManager db) {
        this.name = name;
        database = db;
    }

    protected ArrayList<Scenario> genScenarios() {
        ArrayList<Scenario> scenarios = new ArrayList<>();
        for (List<Object> s : database.getAllScenarios()) {
            scenarios.add(new Scenario(s));
        }
        return scenarios;
    }

    @Override
    public void show() {
        new ChartFrame(name, generateChart());
    }

    /**
     * fills the series with data in order to display it as a chart
     * capulation in seperate function to enable demos to add test values more easily
     */
    protected abstract void fillDataset();
}
