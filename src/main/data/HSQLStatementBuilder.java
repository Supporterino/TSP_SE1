package data;

class HSQLStatementBuilder implements IHSQLStatementBuilder {

    private static final String evaluationTableName = "evaluation";
    private static final String scenarioTableName = "scenario";

    public static String getEvaluationTableName() {
        return evaluationTableName;
    }

    public static String getScenarioTableName() {
        return scenarioTableName;
    }

    @Override
    public String buildSQLStatementGetById(long id, Table table) {
        String tableName = "";
        switch (table) {
            case Evaluation:
                tableName = evaluationTableName;
                break;
            case Scenario:
                tableName = scenarioTableName;
                break;
            default:
                throw new IllegalArgumentException("tablename");
        }
        String statement = "SELECT * FROM " + tableName + " WHERE id=" + id;
        return statement;
    }

    @Override
    public String buildSQLStatementGetEvaluationByScenarioAndRun(long scenario_id, int run) {
        return "SELECT * FROM " + evaluationTableName + " WHERE scenario_id=" + scenario_id + " AND run=" + run + ";";
    }

    @Override
    public String buildSQLStatementInsertEvaluation(long scenario_id, int run, int value) {
        return "INSERT INTO " + evaluationTableName + " (id,scenario_id, run, value) VALUES ("
                + System.nanoTime() + "," + scenario_id + "," + run + ", " + value + ");";
    }

    @Override
    public String buildSQLStatementInsertScenario(long id, int maximum_number_of_iterations, int population_size,
                                                  String selection_method, String crossover_method, double crossover_ratio,
                                                  String mutation_method, double mutation_ratio) {
        return "INSERT INTO " + scenarioTableName + " (id,maximum_number_of_iterations, population_size, selection_method, crossover_method, crossover_ratio," +
                "mutation_method, mutation_ratio) VALUES (" + id + "," + maximum_number_of_iterations + "," + population_size + ",'" +
                selection_method + "','" + crossover_method + "'," + crossover_ratio + ",'" + mutation_method + "'," + mutation_ratio + ");";
    }

    @Override
    public String buildSQLStatementGetAllEvaluationsForScenario(long scenario_id) {
        return "SELECT * FROM " + evaluationTableName + " WHERE scenario_id=" + scenario_id + ";";
    }

    @Override
    public String buildSQLStatementGetLastEvaluationFromScenario(long scenario_id) {
        return buildSQLStatementGetAllEvaluationsForScenario(scenario_id).replace(";", " ") + "ORDER BY run DESC LIMIT 1";
    }

    @Override
    public String buildSQLStatementGetAllScenarios(){
        return "SELECT * FROM " + scenarioTableName;
    }
}
