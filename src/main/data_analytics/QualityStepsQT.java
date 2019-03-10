package data_analytics;

import java.util.Arrays;
import java.util.HashSet;

public class QualityStepsQT extends SpecificNumber {
    public QualityStepsQT(int number){
        super(number, new HashSet<>(Arrays.asList(25,50,75,100)));
    }
}
