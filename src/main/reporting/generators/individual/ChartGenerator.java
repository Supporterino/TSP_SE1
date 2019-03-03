package reporting.generators.individual;

import reporting.DatabaseInterface.DbConnector;
import reporting.gui.ChartFrame;

public abstract class ChartGenerator implements IChartGenerator {

    protected String name;

    /**
     * connecter used to read data from the db in order to fill the datasets
     */
    protected DbConnector database;

    public ChartGenerator(String name,String dbName) {
        this.name = name;
        database = new DbConnector(dbName);
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
