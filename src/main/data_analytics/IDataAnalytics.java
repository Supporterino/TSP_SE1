package data_analytics;

import data.HSQLManagerForEvolution.IHSQLManager;
import data.HSQLManagerForEvolution.IScenario;

import java.util.Map;

public interface IDataAnalytics {
    //In welchem abschnitt ist die durchschnittliche verbesserung pro iteration am h/n
    //Returns: int (section number), double (average)
    Map.Entry<Integer, Double> ai(boolean trueMinFalseMax, long scenarioID, IHSQLManager manager);

    //Abschnitt mit längste Kette keine Verbesserung
    //Returns: int (section number), int (chain length)
    Map.Entry<Integer, Integer> lc(long scenarioID, IHSQLManager manager);

    //In welchem abschnitt differenz zw min max in iterationen am h/nl
    //Returns: int (section), int(difference)
    Map.Entry<Integer, Integer> df(long scenarioID, boolean trueMinFalseMax, IHSQLManager manager);

    //welches szenario liefert nach x% der iterationen die beste lösungsqualität
    //Returns: iscenario (which scenario), integer (fitness)
    Map.Entry<IScenario, Integer> qt(QualityStepsQT perc, IHSQLManager manager);

    //in welchem abschnitt wird eine lösungsqualität von x% erreicht
    //Returns: int(fitness)
    int lq(long scenarioID, QualityStepsLQ perc, IHSQLManager manager);

    //was sind pro szenario/Bereich am häufigsten vorkommende werte?
    Map<Integer, Map<Integer, Integer>> df(long scenarioID, IHSQLManager manager);

    //welches szenario hat die niedrigste Standardabweichung?
    //Returns: iscenario (scenario), double (standard deviation)
    Map.Entry<IScenario, Double> sd(IHSQLManager manager);
}
