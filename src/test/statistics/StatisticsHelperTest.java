package statistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsHelperTest {
    private List<Double> evaluations;

    @BeforeEach
    void setUp() {
        evaluations = new ArrayList<>(
                Arrays.asList(10.0, 20.0, 30.0, 40.0, 50.0, 50.0, 60.0, 70.0)
        );
    }

    @Test
    void calculateMedian() {
        double value = StatisticsHelper.calculateMedian(evaluations);
        assertEquals(45.0, value);
    }

    @Test
    void calculateMean() {
        double value = StatisticsHelper.calculateMean(evaluations);
        assertEquals(41.25, value);
    }

    @Test
    void calculateMode() {
        double value = StatisticsHelper.calculateMode(evaluations);
        assertEquals(50.0, value);
    }

    @Test
    void calculateQuantile() {
        double quantile = 0.3;
        double value = StatisticsHelper.calculateQuantile(evaluations, quantile);
        assertEquals(30.0, value);

        quantile = 0.0;
        value = StatisticsHelper.calculateQuantile(evaluations, quantile);
        assertEquals(10.0, value);

        quantile = 1.0;
        value = StatisticsHelper.calculateQuantile(evaluations, quantile);
        assertEquals(70.0, value);
    }

    @Test
    void calculateRange() {
        double value = StatisticsHelper.calculateRange(evaluations);
        assertEquals(60.0, value);
    }

    @Test
    void calculateIqr() {
        double iqr = 0.3;
        List<Double> value = StatisticsHelper.calculateIqr(evaluations, iqr);
        assertEquals(20.0, value.get(0).doubleValue());
        assertEquals(50.0, value.get(1).doubleValue());
        assertEquals(30.0, value.get(2).doubleValue());

        iqr = 0.0;
        value = StatisticsHelper.calculateIqr(evaluations, iqr);
        assertEquals(0.0, value.get(0).doubleValue());
        assertEquals(40.0, value.get(1).doubleValue());
        assertEquals(40.0, value.get(2).doubleValue());

        iqr = 1.0;
        value = StatisticsHelper.calculateIqr(evaluations, iqr);
        assertEquals(60.0, value.get(0).doubleValue());
        assertEquals(70.0, value.get(1).doubleValue());
        assertEquals(10.0, value.get(2).doubleValue());
    }

    @Test
    void calculateSd() {
        double value = StatisticsHelper.calculateSd(evaluations);
        assertEquals(18.99835519196333, value);
    }
}