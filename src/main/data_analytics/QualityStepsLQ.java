package data_analytics;

import java.util.Arrays;
import java.util.HashSet;

public class QualityStepsLQ extends SpecificNumber {
    public QualityStepsLQ(int number){
        super(number, new HashSet<>(Arrays.asList(50,75,80,90)));
    }
}
