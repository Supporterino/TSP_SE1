package data;

import java.util.List;
import java.util.stream.Collectors;

public class Evaluation implements IEvaluation {
    private long scenario_id = 0;
    private int run = 0;
    private int value = 0;

    public Evaluation(long scenario_id, int run, int value) {
        this.scenario_id = scenario_id;
        this.run = run;
        this.value = value;
    }

    public Evaluation(List<Object> object) {
        if (object.size() == 4) {
            List<String> theList = object.stream().map(o -> o.toString()).collect(Collectors.toList());
            scenario_id = Long.parseLong(theList.get(1));
            run = Integer.parseInt(theList.get(2));
            value = Integer.parseInt(theList.get(3));
        } else {
            throw new IllegalArgumentException("List size not matching");
        }
    }

    @Override
    public long getScenario_id() {
        return scenario_id;
    }

    @Override
    public void setScenario_id(long scenario_id) {
        this.scenario_id = scenario_id;
    }

    @Override
    public int getRun() {
        return run;
    }

    @Override
    public void setRun(int run) {
        this.run = run;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }
}
