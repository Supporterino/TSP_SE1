package data_analytics;

import data.HSQLManagerForEvolution.EvaluationFactory;
import data.HSQLManagerForEvolution.IHSQLManager;
import data.HSQLManagerForEvolution.IScenario;
import data.HSQLManagerForEvolution.ScenarioFactory;

import java.util.Map;
import java.util.stream.Collectors;

public enum DataAnalytics implements IDataAnalytics {
    instance;

    private IDataAnalyticsLogic logic = new DataAnalyticsLogic();

    //In welchem abschnitt ist die durchschnittliche verbesserung pro iteration am h/n
    //Returns: int (section number), double (average)
    @Override
    public Map.Entry<Integer, Double> ai(boolean trueMinFalseMax, long scenarioID, IHSQLManager manager) {
        return logic.ai(trueMinFalseMax, EvaluationFactory.getMultipleInstancesFromRows(manager.getAllEvaluationsForScenario(scenarioID)));
    }

    //Abschnitt mit längste Kette keine Verbesserung
    //Returns: int (section number), int (chain length)
    @Override
    public Map.Entry<Integer, Integer> lc(long scenarioID, IHSQLManager manager) {
        return logic.lc(EvaluationFactory.getMultipleInstancesFromRows(manager.getAllEvaluationsForScenario(scenarioID)));
    }

    //In welchem abschnitt differenz zw min max in iterationen am h/nl
    //Returns: int (section), int(difference)
    @Override
    public Map.Entry<Integer, Integer> df(long scenarioID, boolean trueMinFalseMax, IHSQLManager manager) {
        return logic.df(trueMinFalseMax, EvaluationFactory.getMultipleInstancesFromRows(manager.getAllEvaluationsForScenario(scenarioID)));
    }

    //welches szenario liefert nach x% der iterationen die beste lösungsqualität
    //Returns: iscenario (which scenario), integer (fitness)
    @Override
    public Map.Entry<IScenario, Integer> qt(QualityStepsQT perc, IHSQLManager manager) {
        return logic.qt(ScenarioFactory.getMultipleInstancesFromRows(manager.getAllScenarios())
                        .stream().collect(Collectors.toMap(s -> s,
                        s -> EvaluationFactory.getMultipleInstancesFromRows(manager.getAllEvaluationsForScenario(s.getId())))),
                perc);
    }

    //in welchem abschnitt wird eine lösungsqualität von x% erreicht
    //Returns: int(fitness)
    @Override
    public int lq(long scenarioID, QualityStepsLQ perc, IHSQLManager manager) {
        return logic.lq(EvaluationFactory.getMultipleInstancesFromRows(manager.getAllEvaluationsForScenario(scenarioID)), perc);
    }

    //was sind pro szenario/Bereich am häufigsten vorkommende werte?
    @Override
    public Map<Integer, Map<Integer, Integer>> df(long scenarioID, IHSQLManager manager) {
        return logic.df(EvaluationFactory.getMultipleInstancesFromRows(manager.getAllEvaluationsForScenario(scenarioID)));
    }

    //welches szenario hat die niedrigste Standardabweichung?
    //Returns: iscenario (scenario), double (standard deviation)
    @Override
    public Map.Entry<IScenario, Double> sd(IHSQLManager manager) {
        return logic.sd(ScenarioFactory.getMultipleInstancesFromRows(manager.getAllScenarios())
                .stream().collect(Collectors.toMap(s -> s,
                        s -> EvaluationFactory.getMultipleInstancesFromRows(manager.getAllEvaluationsForScenario(s.getId())))));
    }
}