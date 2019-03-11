package data_analytics;

import data.HSQLManagerForEvolution.IEvaluation;
import data.HSQLManagerForEvolution.IScenario;

import java.util.List;
import java.util.Map;

public interface IDataAnalyticsLogic {
    //In welchem abschnitt ist die durchschnittliche verbesserung pro iteration am h/n
    Map.Entry<Integer, Double> ai(boolean trueMinFalseMax, List<IEvaluation> evList);

    //Abschnitt mit längste Kette keine Verbesserung
    Map.Entry<Integer, Integer> lc(List<IEvaluation> evList);

    //In welchem abschnitt differenz zw min max in iterationen am h/nl
    Map.Entry<Integer, Integer> df(boolean trueMinFalseMax, List<IEvaluation> evList);

    //welches szenario liefert nach x% der iterationen die beste lösungsqualität
    Map.Entry<IScenario, Integer> qt(Map<IScenario, List<IEvaluation>> scEv, QualityStepsQT perc);

    //in welchem abschnitt wird eine lösungsqualität von x% erreicht
    int lq(List<IEvaluation> evList, QualityStepsLQ perc);

    //was sind pro szenario/Bereich am häufigsten vorkommende werte?
    Map<Integer, Map<Integer, Integer>> df(List<IEvaluation> evList);

    //welches szenario hat die niedrigste Standardabweichung?
    Map.Entry<IScenario, Double> sd(Map<IScenario, List<IEvaluation>> scEv);
}
