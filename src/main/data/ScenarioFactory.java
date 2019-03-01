package main.data.HSQLManagerForEvolution;

import java.util.List;
import java.util.stream.Collectors;

public abstract  class ScenarioFactory {
    public static List<IScenario> getMultipleInstancesFromRows(List<List<Object>> rows) {
        return rows.stream().map(Scenario::new).collect(Collectors.toList());
    }
}
