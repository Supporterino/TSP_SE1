package reporting.generators.individual;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

public class BoxGenerator extends ChartGenerator {
    protected DefaultBoxAndWhiskerCategoryDataset dataset;

    public BoxGenerator(String dbName) {
        super("Box Chart", dbName);
        this.dataset = new DefaultBoxAndWhiskerCategoryDataset();
    }

    protected void fillDataset() {
        database.getDbDataset();
        //TODO get data from db
    }

    @Override
    public JFreeChart generateChart() {
        fillDataset();
        return ChartFactory.createBoxAndWhiskerChart(name, "Category", "Value", dataset, true);
    }


}
