package reporting.DatabaseInterface;

import java.util.ArrayList;

/**
 * Interface to model connection to the HSQL database in order to read data
 */
public interface IDbConnector {

    /**
     * dummy to model the process of getting data from the db
     *
     * @return
     */
    ArrayList<Object> getDbDataset();
}
