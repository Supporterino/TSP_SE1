package statistics;

import data.EvaluationFactory;
import data.HSQLManager;
import data.IEvaluation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Statistics {
    private static HSQLManager database;

    public static void main(String[] args) {
        StringBuilder line = new StringBuilder();

        for (String argument : args)
            line.append(argument);

        String[] pars = line.toString().split(ConfigurationStatistics.instance.splitSymbol);

        if (pars.length != 5)
            throw new IllegalArgumentException("Wrong number of arguments.");

        if (!pars[1].toLowerCase().equals(ConfigurationStatistics.instance.firstArgument))
            throw new IllegalArgumentException("Wrong argument -" + pars[0]);

        try {
            getDatabase(pars[2]);
            getStatisticResult(pars[4], getListOfScenarios(pars[3]));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getStatisticResult(String statisticMode, List<Integer> scenarioIDs) {
        int endOfWord = statisticMode.length();
        for (int i = 0; i < statisticMode.length(); i++) {
            if (!Character.isAlphabetic(statisticMode.charAt(i))) {
                endOfWord = i;
                break;
            }
        }

        for (int scenarioID : scenarioIDs) {
            Set<Double> evaluations = new TreeSet<>(new DoubleComparator());
            getScenarioEvaluation(scenarioID).forEach(element -> evaluations.add((double) element.getValue()));
            List<Double> evaluationDouble = new ArrayList<>(evaluations);
            System.out.println("Scenario " + scenarioID + " in " + database.toString());

            switch (statisticMode.substring(0, endOfWord).toLowerCase()) {
                case ConfigurationStatistics.median:
                    if (statisticMode.toLowerCase().equals(ConfigurationStatistics.median))
                        System.out.println(ConfigurationStatistics.median + ": " + StatisticsHelper.calculateMedian(evaluationDouble));
                    else
                        throw new IllegalArgumentException(ConfigurationStatistics.instance.wrongStatisticMode + statisticMode);
                    break;

                case ConfigurationStatistics.mean:
                    if (statisticMode.toLowerCase().equals(ConfigurationStatistics.mean))
                        System.out.println(ConfigurationStatistics.mean + ": " + StatisticsHelper.calculateMean(evaluationDouble));
                    else
                        throw new IllegalArgumentException(ConfigurationStatistics.instance.wrongStatisticMode + statisticMode);
                    break;

                case ConfigurationStatistics.mode:
                    if (statisticMode.toLowerCase().equals(ConfigurationStatistics.mode))
                        System.out.println(ConfigurationStatistics.mode + ": " + StatisticsHelper.calculateMode(evaluationDouble));
                    else
                        throw new IllegalArgumentException(ConfigurationStatistics.instance.wrongStatisticMode + statisticMode);
                    break;

                case ConfigurationStatistics.quantile:
                    if (statisticMode.toLowerCase()
                            .substring(ConfigurationStatistics.quantile.length(), ConfigurationStatistics.quantile.length() + 1)
                            .equals(ConfigurationStatistics.instance.equalSymbol)) {
                        try {
                            double quantile = Double.parseDouble(statisticMode.substring(ConfigurationStatistics.quantile.length() + ConfigurationStatistics.instance.equalSymbol.length()));
                            System.out.println(ConfigurationStatistics.quantile + ": " + StatisticsHelper.calculateQuantile(evaluationDouble, quantile));
                        } catch (Exception e) {
                            throw new IllegalArgumentException(ConfigurationStatistics.instance.wrongStatisticMode + statisticMode);
                        }
                    } else {
                        throw new IllegalArgumentException(ConfigurationStatistics.instance.wrongStatisticMode + statisticMode);
                    }
                    break;

                case ConfigurationStatistics.range:
                    if (statisticMode.toLowerCase().equals(ConfigurationStatistics.range)) {
                        System.out.println(ConfigurationStatistics.range + ": " + StatisticsHelper.calculateRange(evaluationDouble));
                        System.out.println("With range from " + evaluationDouble.get(0) + " to " + evaluationDouble.get(evaluationDouble.size() - 1));
                    } else
                        throw new IllegalArgumentException(ConfigurationStatistics.instance.wrongStatisticMode + statisticMode);
                    break;

                case ConfigurationStatistics.iqr:
                    if (statisticMode.toLowerCase()
                            .substring(ConfigurationStatistics.iqr.length(), ConfigurationStatistics.iqr.length() + 1)
                            .equals(ConfigurationStatistics.instance.equalSymbol)) {
                        try {
                            double iqr = Double.parseDouble(statisticMode.substring(ConfigurationStatistics.iqr.length() + ConfigurationStatistics.instance.equalSymbol.length()));
                            List<Double> iqrResult = StatisticsHelper.calculateIqr(evaluationDouble, iqr);
                            System.out.println(ConfigurationStatistics.iqr + ": " + iqrResult.get(0));
                            System.out.println("With range from " + iqrResult.get(1) + " to " + iqrResult.get(2));
                        } catch (Exception e) {
                            throw new IllegalArgumentException(ConfigurationStatistics.instance.wrongStatisticMode + statisticMode);
                        }
                    } else {
                        throw new IllegalArgumentException(ConfigurationStatistics.instance.wrongStatisticMode + statisticMode);
                    }
                    break;

                case ConfigurationStatistics.sd:
                    if (statisticMode.toLowerCase().equals(ConfigurationStatistics.sd))
                        System.out.println(ConfigurationStatistics.sd + ": " + StatisticsHelper.calculateSd(evaluationDouble));
                    else
                        throw new IllegalArgumentException(ConfigurationStatistics.instance.wrongStatisticMode + statisticMode);
                    break;

                default:
                    throw new IllegalArgumentException(ConfigurationStatistics.instance.wrongStatisticMode + statisticMode);
            }
        }
    }

    private static void getDatabase(String database) {
        if (database.toLowerCase().equals(HSQLManager.tsp.toString().toLowerCase()))
            Statistics.database = HSQLManager.tsp;
        else if (database.toLowerCase().equals(HSQLManager.Knapsack.toString().toLowerCase()))
            Statistics.database = HSQLManager.Knapsack;
        else
            throw new IllegalArgumentException("No database with name: " + database + " found");
    }

    private static List<IEvaluation> getScenarioEvaluation(long scenarioID) {
        List<List<Object>> evaluations = Statistics.database.getAllEvaluationsForScenario(scenarioID);

        if (evaluations.size() == 0)
            throw new RuntimeException("ScenarioID not found");

        return EvaluationFactory.getMultipleInstancesFromRows(evaluations);
    }

    private static List<Integer> getListOfScenarios(String scenariosString) {
        if (scenariosString.equals(ConfigurationStatistics.instance.allScenarios)) {
            List<Integer> scenarios = new ArrayList<>();
            Statistics.database.getAllScenarios()
                    .forEach(list -> scenarios.add(Integer.parseInt(list.get(0).toString())));

            return scenarios;
        }

        String[] scenarioParsed = scenariosString.toLowerCase()
                .split(ConfigurationStatistics.instance.scenarioSeparator);

        List<Integer> returnList = new ArrayList<>();

        for (String scenario : scenarioParsed) {
            if (scenario.length() < 2 || scenario.charAt(0) != ConfigurationStatistics.instance.scenarioPrefix)
                throw new IllegalArgumentException("Please start a scenarioID with a 's'");

            scenario = scenario.substring(1);

            try {
                returnList.add(Integer.parseInt(scenario));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Wrong scenarioID: " + scenario);
            }
        }

        return returnList;
    }
}