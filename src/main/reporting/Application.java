package reporting;

import reporting.generators.Generator;

/**
 * Class to kick off chart generation process
 */
public class Application {
    /**
     * Shows all db generated charts
     *
     * @param args
     */
    public static void main(String[] args) {
        new Generator().showAll();
    }
}
