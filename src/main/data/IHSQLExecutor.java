package main.data.HSQLManagerForEvolution;

import java.util.List;

interface IHSQLExecutor {
    boolean startup(String databaseName);

    boolean init();

    boolean update(String sqlStatement);

    List<List<Object>> get(String sqlStatement);

    boolean dropTables();

    boolean createTables();

    boolean shutdown();

    boolean isClosed();
}
