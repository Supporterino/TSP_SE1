package data;

import java.util.List;
import java.util.stream.Collectors;

public class Scenario implements IScenario {
    private long id = 0;
    private int number_of_iterations = 0;
    private int population_size = 0;
    private String selection_method = "";
    private String crossover_method = "";
    private double crossover_ratio = 0;
    private String mutation_method = "";
    private double mutation_ratio = 0;

    public Scenario(long id, int number_of_iterations, int population_size, String selection_method, String crossover_method, double crossover_ratio, String mutation_method, double mutation_ratio) {
        this.id = id;
        this.number_of_iterations = number_of_iterations;
        this.population_size = population_size;
        this.selection_method = selection_method;
        this.crossover_method = crossover_method;
        this.crossover_ratio = crossover_ratio;
        this.mutation_method = mutation_method;
        this.mutation_ratio = mutation_ratio;
    }

    public Scenario(List<Object> object) {
        if (object.size() == 8) {
            List<String> theList = object.stream().map(o -> o.toString()).collect(Collectors.toList());
            id = Long.parseLong(theList.get(0));
            number_of_iterations = Integer.parseInt(theList.get(1));
            population_size = Integer.parseInt(theList.get(2));
            selection_method = theList.get(3);
            crossover_method = theList.get(4);
            crossover_ratio = Double.parseDouble(theList.get(5));
            mutation_method = theList.get(6);
            mutation_ratio = Double.parseDouble(theList.get(7));
        } else {
            throw new IllegalArgumentException("List size not matching");
        }
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int getNumber_of_iterations() {
        return number_of_iterations;
    }

    @Override
    public void setNumber_of_iterations(int number_of_iterations) {
        this.number_of_iterations = number_of_iterations;
    }

    @Override
    public int getPopulation_size() {
        return population_size;
    }

    @Override
    public void setPopulation_size(int population_size) {
        this.population_size = population_size;
    }

    @Override
    public String getSelection_method() {
        return selection_method;
    }

    @Override
    public void setSelection_method(String selection_method) {
        this.selection_method = selection_method;
    }

    @Override
    public String getCrossover_method() {
        return crossover_method;
    }

    @Override
    public void setCrossover_method(String crossover_method) {
        this.crossover_method = crossover_method;
    }

    @Override
    public double getCrossover_ratio() {
        return crossover_ratio;
    }

    @Override
    public void setCrossover_ratio(double crossover_ratio) {
        this.crossover_ratio = crossover_ratio;
    }

    @Override
    public String getMutation_method() {
        return mutation_method;
    }

    @Override
    public void setMutation_method(String mutation_method) {
        this.mutation_method = mutation_method;
    }

    @Override
    public double getMutation_ratio() {
        return mutation_ratio;
    }

    @Override
    public void setMutation_ratio(long mutation_ratio) {
        this.mutation_ratio = mutation_ratio;
    }
}
