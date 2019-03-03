package reporting.gui;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Class to show the a chart
 */
public class ChartFrame extends ApplicationFrame {

    /**
     * Constructs and shows the chart
     *
     * @param title title of the Frame
     * @param chart chart to display
     */
    public ChartFrame(String title, JFreeChart chart) {
        super(title);
        setContentPane(new ChartPanel(chart));
        setSize(650, 650);
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }

}
