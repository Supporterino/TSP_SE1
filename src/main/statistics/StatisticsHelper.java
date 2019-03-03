package statistics;

import java.util.ArrayList;
import java.util.List;

class StatisticsHelper {
    static double calculateMedian(List<Double> evaluations) {
        int half = evaluations.size() / 2;
        if ((evaluations.size() % 2) == 0) {
            return ((evaluations.get(half - 1) + evaluations.get(half)) / 2);
        } else {
            return evaluations.get(half);
        }
    }

    static double calculateMean(List<Double> evaluations) {
        int sum = evaluations.stream().mapToInt(Double::intValue).sum();

        return (double) sum / evaluations.size();
    }

    static double calculateMode(List<Double> evaluations) {
        double mostOccurrenceNumber = 0;
        int numberFrequency = 0;

        double currentNumber = 0;
        int currentFrequency = 0;

        for (Double evaluation : evaluations) {
            double evaluationNumber = evaluation;

            if (evaluationNumber != currentNumber) {
                currentNumber = evaluationNumber;
                currentFrequency = 0;
            }

            if (evaluationNumber == mostOccurrenceNumber) {
                numberFrequency++;
            } else {
                currentFrequency++;

                if (currentFrequency > numberFrequency) {
                    mostOccurrenceNumber = evaluationNumber;
                    numberFrequency = currentFrequency;
                }
            }
        }

        return mostOccurrenceNumber;
    }

    static double calculateQuantile(List<Double> evaluations, double quantile) {
        if (quantile < 0 || quantile > 1)
            throw new IllegalArgumentException(ConfigurationStatistics.instance.wrongStatisticMode + quantile +
                    "\nQuantile must between 0.0 and 1.0.");

        int evaluationsIndex = (int) (quantile * evaluations.size());

        if ((evaluations.size() / quantile) % 1 == 0) {
            if (evaluationsIndex + 1 > evaluations.size() - 1)
                return evaluations.get(evaluations.size() - 1);
            else
                return (evaluations.get(evaluationsIndex) + evaluations.get(evaluationsIndex + 1)) / 2;
        } else {
            return evaluations.get(evaluationsIndex);
        }
    }

    static double calculateRange(List<Double> evaluations) {
        return evaluations.get(evaluations.size() - 1) - evaluations.get(0);
    }

    static List<Double> calculateIqr(List<Double> evaluations, double iqr) {
        if (iqr < 0 || iqr > 1)
            throw new IllegalArgumentException(ConfigurationStatistics.instance.wrongStatisticMode + iqr +
                    "\nIqr must between 0.0 and 1.0.");

        double upperEdge = (1 - ((1 - iqr) / 2)) * (evaluations.size() - 1);
        double lowerEdge = (1 - ((1 - iqr) / 2) - iqr) * (evaluations.size() - 1);
        List<Double> returnList = new ArrayList<>();

        returnList.add(evaluations.get((int) upperEdge) - evaluations.get((int) lowerEdge));
        returnList.add(evaluations.get((int) upperEdge));
        returnList.add(evaluations.get((int) lowerEdge));

        return returnList;
    }

    static double calculateSd(List<Double> evaluations) {
        double mean = calculateMean(evaluations);
        double numerator = 0;

        for (double evaluation : evaluations) {
            numerator += Math.pow(evaluation - mean, 2);
        }

        return Math.sqrt(numerator / evaluations.size());
    }
}
