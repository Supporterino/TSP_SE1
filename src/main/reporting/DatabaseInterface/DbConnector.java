package reporting.DatabaseInterface;

import data.HSQLManager;

import java.util.ArrayList;
import java.util.List;

public class DbConnector implements IDbConnector {

    HSQLManager manager;

    public DbConnector(String dbName) {
        System.err.println("\nDatabase connection not implemented due to missing documentation of db team\n");
        manager = HSQLManager.tsp;

        //Option for other repo
        //manager = HSQLManager.Knapsack;
    }

    @Override
    public ArrayList<Object> getDbDataset() {
        List<List<Object>> data = manager.getAllScenarios();
        for (List<Object> subList : data
        ) {
            for (Object element : subList
            ) {
                //TODO do something the the single elements to build datasets
            }
        }

        //TODO use manager to retrieve data and repackage in a way that is useful for chart datasets
        return null;
    }
}
