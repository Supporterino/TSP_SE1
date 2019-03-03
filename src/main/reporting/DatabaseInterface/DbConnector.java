package reporting.DatabaseInterface;

import data.HSQLManager;

import java.util.ArrayList;

public class DbConnector implements IDbConnector {

    HSQLManager manager;

    public DbConnector(String dbName) {
        System.err.println("\nDatabase connection not implemented due to missing documentation of db team\n");
        //manager=new HSQLManager(dbName);
    }

    @Override
    public ArrayList<Object> getDbDataset() {
        //TODO use manager to retrieve data and repackage in a way that is useful for chart datasets
        return null;
    }
}
