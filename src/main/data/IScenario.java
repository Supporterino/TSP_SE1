package data;

public interface IScenario {
    long getId();

    void setId(long id);

    int getNumber_of_iterations();

    void setNumber_of_iterations(int number_of_iterations);

    int getPopulation_size();

    void setPopulation_size(int population_size);

    String getSelection_method();

    void setSelection_method(String selection_method);

    String getCrossover_method();

    void setCrossover_method(String crossover_method);

    double getCrossover_ratio();

    void setCrossover_ratio(double crossover_ratio);

    String getMutation_method();

    void setMutation_method(String mutation_method);

    double getMutation_ratio();

    void setMutation_ratio(long mutation_ratio);
}
