package reporting.generators.individual;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

public class BoxGenerator extends ChartGenerator {
    protected DefaultBoxAndWhiskerCategoryDataset dataset;

    public BoxGenerator() {
        super("Box Chart");
        this.dataset = new DefaultBoxAndWhiskerCategoryDataset();
    }

    protected void fillDataset() {
        //TODO get data from db
    }

    @Override
    public JFreeChart generateChart() {
        fillDataset();
        return ChartFactory.createBoxAndWhiskerChart(name, "TODO", "TODO", dataset, true);//TODO label fields
    }


}
