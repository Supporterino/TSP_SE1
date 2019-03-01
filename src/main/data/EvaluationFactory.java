package data;

import java.util.List;
import java.util.stream.Collectors;

public class EvaluationFactory{

    public static List<IEvaluation> getMultipleInstancesFromRows(List<List<Object>> rows) {
        return rows.stream().map(Evaluation::new).collect(Collectors.toList());
    }
}
