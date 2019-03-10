package data_analytics;

import data.HSQLManagerForEvolution.IEvaluation;
import data.HSQLManagerForEvolution.IScenario;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

class DataAnalyticsLogic implements IDataAnalyticsLogic {

    //In welchem abschnitt ist die durchschnittliche verbesserung pro iteration am h/n
    @Override
    public Map.Entry<Integer, Double> ai(boolean trueMinFalseMax, List<IEvaluation> evList) {
        return EvaluateSectionsMinMaxVal(trueMinFalseMax, evList, sec -> {
            List<IEvaluation> evListFromSec = sec.getValue();
            int evSize = evListFromSec.size();
            List<Integer> improvement = new LinkedList<>();
            for (int i = 0; i < evSize - 1; i++) {
                improvement.add(evListFromSec.get(i + 1).getValue() - evListFromSec.get(i).getValue());
            }
            return new AbstractMap.SimpleEntry<>(sec.getKey(), improvement.stream().mapToInt(i -> i).sum() / (double) (evSize - 1));
        });
    }


    private int compareToNearGreaterThanValue(double val1, double val2, double valNearTo) {
        double difference1 = val1 - valNearTo;
        double difference2 = val2 - valNearTo;
        if (difference1 < 0) {
            return Integer.MAX_VALUE;
        } else if (difference2 < 0) {
            return Integer.MIN_VALUE;
        } else {
            return (int) (1000 * (difference1 - difference2));
        }
    }

    private Map.Entry<Integer, Double> EvaluateSectionsMinMaxVal(
            boolean trueMinFalseMax,
            List<IEvaluation> evList,
            Function<Map.Entry<Integer, List<IEvaluation>>, Map.Entry<Integer, Double>> funcToMap) {
        Map<Integer, List<IEvaluation>> evSecMap = getAsSections(evList);
        Stream<Map.Entry<Integer, Double>> map = evSecMap.entrySet().stream().map(funcToMap);
        Comparator<Map.Entry<Integer, Double>> comp = Comparator.comparingDouble(Map.Entry::getValue);
        return trueMinFalseMax ? map.min(comp).get() : map.max(comp).get();
    }


    private Map<Integer, List<IEvaluation>> getAsSections(List<IEvaluation> evList) {
        int evInSectionCount = 500;
        return evList.stream().collect(groupingBy(ie -> ie.getRun() / evInSectionCount));
    }

    //Abschnitt mit längste Kette keine Verbesserung
    @Override
    public Map.Entry<Integer, Integer> lc(List<IEvaluation> evList) {
        Map.Entry<Integer, Double> ret = EvaluateSectionsMinMaxVal(false,evList, sec -> {
            List<Integer> evListValueFromSec = sec.getValue().stream().map(IEvaluation::getValue).collect(Collectors.toList());
            int noImprovementSize = 0;
            int noImprovementSizeTemp = 0;
            int momentValue = 0;
            for (int val : evListValueFromSec) {
                if (momentValue != val) {
                    noImprovementSizeTemp = 0;
                    momentValue = val;
                }
                noImprovementSizeTemp++;
                if (noImprovementSizeTemp > noImprovementSize) {
                    noImprovementSize = noImprovementSizeTemp;
                }
            }
            return new AbstractMap.SimpleEntry<>(sec.getKey(), (double) noImprovementSize);
        });
        return new AbstractMap.SimpleEntry<>(ret.getKey(), (int) ret.getValue().doubleValue());
    }

    //In welchem abschnitt differenz zw min max in iterationen am h/n
    @Override
    public Map.Entry<Integer, Integer> df(boolean trueMinFalseMax, List<IEvaluation> evList) {
        Map.Entry<Integer, Double> ret = EvaluateSectionsMinMaxVal(trueMinFalseMax, evList, sec -> {
            List<Integer> evListValueFromSec = sec.getValue().stream().map(IEvaluation::getValue).collect(Collectors.toList());
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int val : evListValueFromSec) {
                if (val > max) {
                    max = val;
                } else if (val < min) {
                    min = val;
                }
            }
            return new AbstractMap.SimpleEntry<>(sec.getKey(), (double) (max - min));
        });
        return new AbstractMap.SimpleEntry<>(ret.getKey(), (int) ret.getValue().doubleValue());
    }

    //welches szenario liefert nach x% der iterationen die beste lösungsqualität
    @Override
    public Map.Entry<IScenario, Integer> qt(Map<IScenario, List<IEvaluation>> scEv, QualityStepsQT perc) {
        double percInDouble = (double) perc.getNumber() / 100;
        return scEv.entrySet().stream().map(s -> {
            int numIt = s.getKey().getNumber_of_iterations();
            return new AbstractMap.SimpleEntry<>(s.getKey(), s.getValue().stream()
                    .map(ie -> new AbstractMap.SimpleEntry<>(ie, ie.getValue()))
                    .sorted((se, ose) -> {
                        double val1 = (se.getKey().getRun() / (double) numIt);
                        double val2 = (ose.getKey().getRun() / (double) numIt);
                        return compareToNearGreaterThanValue(val1, val2, percInDouble);
                    }).map(AbstractMap.SimpleEntry::getValue).findFirst().get());

        }).sorted(Comparator.comparingInt(e -> -e.getValue())).findFirst().get();
    }

    //in welchem abschnitt wird eine lösungsqualität von x% erreicht
    @Override
    public int lq(List<IEvaluation> evList, QualityStepsLQ perc) {
        Map<Integer, List<IEvaluation>> evSectionMap = getAsSections(evList);
        return evSectionMap.entrySet().stream().map(sec -> new AbstractMap.SimpleEntry<>(sec.getKey(),
                sec.getValue().stream().map(IEvaluation::getValue).max(Comparator.comparingInt(v -> v)).get()))
                .sorted((val1, val2) -> compareToNearGreaterThanValue(val1.getValue(), val2.getValue(), perc.getNumber()))
                .findFirst().get().getKey();
    }

    //was sind pro szenario/Bereich am häufigsten vorkommende werte?
    @Override
    public Map<Integer, Map<Integer, Integer>> df(List<IEvaluation> evList) {
        Map<Integer, Map<Integer, Integer>> ret = new HashMap<>();
        Map<Integer, List<IEvaluation>> evSectionMap = getAsSections(evList);
        return evSectionMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                entry -> entry.getValue().stream().collect(groupingBy(IEvaluation::getValue))
                        .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()))));
    }

    //welches szenario hat die niedrigste Standardabweichung?
    @Override
    public Map.Entry<IScenario, Double> sd(Map<IScenario, List<IEvaluation>> scEv) {
        return scEv.entrySet().stream().map(sel -> {
            List<IEvaluation> evList = sel.getValue();
            DoubleStream evValStream = evList.stream().mapToDouble(IEvaluation::getValue);
            int size = evList.size();
            double sum = evValStream.sum();
            double average = size / sum;
            return new AbstractMap.SimpleEntry<>(sel.getKey(), Math.sqrt(evValStream.map(val -> Math.pow(val - average, 2)).sum() / size));
        }).sorted(Comparator.comparingDouble(AbstractMap.SimpleEntry::getValue)).findFirst().get();
    }
}