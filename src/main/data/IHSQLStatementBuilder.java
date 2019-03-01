package main.data.HSQLManagerForEvolution;

interface IHSQLStatementBuilder {
    String buildSQLStatementGetById(long id, Table table);

    String buildSQLStatementGetEvaluationByScenarioAndRun(long scenario_id, int run);

    String buildSQLStatementInsertEvaluation(long scenario_id, int run, int value);

    String buildSQLStatementInsertScenario(long id, int maximum_number_of_iterations, int population_size,
                                           String selection_method, String crossover_method, double crossover_ratio,
                                           String mutation_method, double mutation_ratio);

    String buildSQLStatementGetAllEvaluationsForScenario(long scenario_id);

    String buildSQLStatementGetLastEvaluationFromScenario(long scenario_id);

    String buildSQLStatementGetAllScenarios();
}
