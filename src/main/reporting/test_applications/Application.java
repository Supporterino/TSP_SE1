package reporting.test_applications;

import data.HSQLManagerForEvolution.HSQLManager;
import data.HSQLManagerForEvolution.IScenario;
import data.HSQLManagerForEvolution.Scenario;
import reporting.Generator;

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
        HSQLManager db = HSQLManager.tsp;

        //Trying to add data to a db for testing purpose
        IScenario scenario = new Scenario(1, 1, 1, "", "", 1, "", 1);
        System.out.println("added scenario " + db.insertScenario(scenario));
        System.out.println("scenario size " + db.getAllScenarios().size());


        new Generator(db).showAll();
    }
}
