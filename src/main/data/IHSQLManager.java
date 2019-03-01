package main.data.HSQLManagerForEvolution;

import java.util.List;

public interface IHSQLManager extends AutoCloseable {
    boolean startup();

    boolean init();

    List<Object> getById(long id, Table table);

    List<Object> getEvaluationByScenarioIdAndRun(long scenario_id, int run);

    boolean insertEvaluation(long scenario_id, int run, int value);

    boolean insertEvaluation(IEvaluation data);

    boolean insertScenario(long id, int maximum_number_of_iterations, int population_size,
                           String selection_method, String crossover_method, double crossover_ratio,
                           String mutation_method, double mutation_ratio);

    boolean insertScenario(IScenario data);

    List<List<Object>> getAllEvaluationsForScenario(long scenario_id);

    List<Object> getLastEvaluationFromScenario(long scenario_id);

    List<List<Object>> getAllScenarios();
}
