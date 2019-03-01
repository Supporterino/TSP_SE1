package main.data.HSQLManagerForEvolution;

import java.util.LinkedList;
import java.util.List;

public enum HSQLManager implements IHSQLManager {
    tsp("db.tsp"),
    Knapsack("db.knapsack");
    private final IHSQLExecutor executor;
    private final IHSQLStatementBuilder builder;
    private final String dbName;

    HSQLManager(String databaseName) {
        executor = new HSQLExecutor();
        builder = new HSQLStatementBuilder();
        dbName = databaseName;

        if (!this.startup()) {
            throw new IllegalStateException("DB could not be start up");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public boolean startup() {
        return executor.startup(dbName);
    }

    @Override
    public boolean init() {
        return executor.init();
    }

    @Override
    public void close() {
        if (!executor.isClosed()) {
            if (!executor.shutdown()) {
                throw new IllegalStateException("DB could not be shut down");
            }
        }
    }

    @Override
    public List<Object> getById(long id, Table table) {
        List<List<Object>> ret = executor.get(builder.buildSQLStatementGetById(id, table));
        return ret == null || ret.size() == 0 || ret.get(0).size() == 0 ? new LinkedList<>() : ret.get(0);
    }

    @Override
    public List<Object> getEvaluationByScenarioIdAndRun(long scenario_id, int run) {
        List<List<Object>> ret = executor.get(builder.buildSQLStatementGetEvaluationByScenarioAndRun(scenario_id, run));
        return ret == null || ret.size() == 0 || ret.get(0).size() == 0 ? new LinkedList<>() : ret.get(0);
    }

    @Override
    public boolean insertEvaluation(long scenario_id, int run, int value) {
        return executor.update(builder.buildSQLStatementInsertEvaluation(scenario_id, run, value));
    }

    @Override
    public boolean insertEvaluation(IEvaluation data) {
        return insertEvaluation(data.getScenario_id(), data.getRun(), data.getValue());
    }

    @Override
    public boolean insertScenario(long id, int maximum_number_of_iterations, int population_size,
                                  String selection_method, String crossover_method, double crossover_ratio,
                                  String mutation_method, double mutation_ratio) {
        return executor.update(builder.buildSQLStatementInsertScenario(id, maximum_number_of_iterations, population_size,
                selection_method, crossover_method, crossover_ratio, mutation_method, mutation_ratio));
    }

    @Override
    public boolean insertScenario(IScenario data) {
        return insertScenario(data.getId(), data.getNumber_of_iterations(), data.getPopulation_size(), data.getSelection_method(),
                data.getCrossover_method(), data.getCrossover_ratio(), data.getMutation_method(), data.getMutation_ratio());
    }

    @Override
    public List<List<Object>> getAllEvaluationsForScenario(long scenario_id) {
        return executor.get(builder.buildSQLStatementGetAllEvaluationsForScenario(scenario_id));
    }

    @Override
    public List<Object> getLastEvaluationFromScenario(long scenario_id) {
        return executor.get(builder.buildSQLStatementGetLastEvaluationFromScenario(scenario_id)).stream().findFirst().get();
    }

    @Override
    public List<List<Object>> getAllScenarios(){
        return executor.get(builder.buildSQLStatementGetAllScenarios());
    }
}
