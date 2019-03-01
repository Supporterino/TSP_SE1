package main.data.HSQLManagerForEvolution;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

class HSQLExecutor implements IHSQLExecutor {
    private Connection connection;

    @Override
    public boolean startup(String databaseName) {
        try {
            //Register driver
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            String driverName = "jdbc:hsqldb:";
            String databaseURL = driverName + databaseName; //"datastore.db";
            String password = "";
            String username = "SA";
            connection = DriverManager.getConnection(databaseURL, username, password);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean init() {
        boolean ret = dropTables();
        ret = createTables() && ret;
        return ret;
    }

    @Override
    public synchronized boolean update(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            if (statement.executeUpdate(sqlStatement) == -1)
                System.out.println("error executing " + sqlStatement);
            statement.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }


    @Override
    public synchronized List<List<Object>> get(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            List<List<Object>> entries = new LinkedList<>();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                List<Object> row = new LinkedList<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getObject(i));
                }
                entries.add(row);
            }
            return entries;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return new LinkedList<>();
    }

    @Override
    public boolean dropTables() {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            boolean ev = true;
            boolean sc = true;
            List<String> tableNameList = new LinkedList<>();
            ResultSet rs = meta.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                tableNameList.add(rs.getString("TABLE_NAME").toLowerCase());
            }
            if (tableNameList.stream().anyMatch(s -> s.equals(HSQLStatementBuilder.getEvaluationTableName()))) {
                ev = update("DROP TABLE " + HSQLStatementBuilder.getEvaluationTableName());
            }
            if (tableNameList.stream().anyMatch(s -> s.equals(HSQLStatementBuilder.getScenarioTableName()))) {
                sc = update("DROP TABLE " + HSQLStatementBuilder.getScenarioTableName());
            }

            return sc && ev;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    @Override
    public boolean createTables() {
        String createSc = "CREATE TABLE " + HSQLStatementBuilder.getScenarioTableName() +
                " (" +
                " id BIGINT NOT NULL," +
                " maximum_number_of_iterations INT NOT NULL," +
                " population_size INT NOT NULL," +
                " selection_method VARCHAR(10) NOT NULL," +
                " crossover_method VARCHAR(10) NOT NULL," +
                " crossover_ratio DECIMAL(5,4) NOT NULL," +
                " mutation_method VARCHAR(10) NOT NULL," +
                " mutation_ratio DECIMAL(5,4) NOT NULL," +
                " PRIMARY KEY (id)" +
                ");";
        String createEv = "CREATE TABLE " + HSQLStatementBuilder.getEvaluationTableName() +
                " ( " +
                " id BIGINT NOT NULL, " +
                " scenario_id BIGINT NOT NULL," +
                " run INT NOT NULL," +
                " value INT NOT NULL," +
                " PRIMARY KEY (id)," +
                " FOREIGN KEY (scenario_id) REFERENCES scenario(id)" +
                ");";
        return update(createSc) && update(createEv);
    }

    @Override
    public boolean shutdown() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        }
    }

    @Override
    public boolean isClosed() {
        try {
            return connection == null || connection.isClosed();
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
}