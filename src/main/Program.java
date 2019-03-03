import base.City;
import base.Population;
import configuration.Configuration;
import crossover.Crossover;
import data.HSQLDBManager;
import data.InstanceReader;
import data.TSPLIBReader;
import mutation.Mutation;
import selection.Selection;
import utilities.LogEngine;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Program {
    private ArrayList<City> availableCities;
    private double[][] distances;

    private Crossover crossover;
    private Selection selection;
    private Mutation mutation;

/*    public static void main(String... args) {
        Program program = new Program();
        application.startupHSQLDB();
        application.loadData();
        application.initConfiguration();
        application.execute();
        application.shutdownHSQLDB();
    }*/

    public static void main(String... args) {
        ApplicationTSP applicationTSP = new ApplicationTSP();
        ApplicationKnapsack applicationKnapsack = new ApplicationKnapsack();
        applicationTSP.execute();
        applicationKnapsack.execute();
    }

    public void execute() {
        LogEngine.instance.init();

        long runtimeStart = System.currentTimeMillis();

        Population population = new Population(
                Configuration.instance.populationSize,
                Configuration.instance.crossoverRatio,
                Configuration.instance.elitismRatio,
                Configuration.instance.mutationRatio);

        int i = 0;
      Chromosome bestChromosome = population.getPopulation()[0];

        while ((i++ <= Configuration.instance.maximumNumberOfGenerations) && (bestChromosome.getFitness() != 0)) {
            LogEngine.instance.write("generation " + i + " : " + bestChromosome.getGene() + " - fitness : " + bestChromosome.getFitness());
            population.evolve();
            bestChromosome = population.getPopulation()[0];
        }

        LogEngine.instance.write("generation " + i + " : " + bestChromosome.getGene());
        LogEngine.instance.write("runtime : " + (System.currentTimeMillis() - runtimeStart) + " ms");

        LogEngine.instance.close();
    }
    public Program() {
    }

    public Program(CommandLine commandLine) {
    }

    public void startupHSQLDB() {
        HSQLDBManager.instance.startup();
        HSQLDBManager.instance.init();
    }

    public void shutdownHSQLDB() {
        HSQLDBManager.instance.shutdown();
    }

    public void printMatrix(double[][] matrix) {
        DecimalFormat decimalFormat = new DecimalFormat("000.00");

        for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < matrix[0].length; columnIndex++)
                System.out.print(decimalFormat.format(matrix[rowIndex][columnIndex]) + "\t");
            System.out.println();
        }
    }

    public void loadData() {
        System.out.println("--- GeneticAlgorithm.loadData()");
        InstanceReader instanceReader = new InstanceReader(Configuration.instance.dataFilePath);
        instanceReader.open();
        TSPLIBReader tspLibReader = new TSPLIBReader(instanceReader);

        availableCities = tspLibReader.getCities();
        System.out.println("availableCities (size) : " + availableCities.size());

        distances = tspLibReader.getDistances();

        printMatrix(distances);

        instanceReader.close();

        System.out.println();
    }

    public void initConfiguration() {
        System.out.println("--- GeneticAlgorithm.initConfiguration()");
        System.out.println();
    }

    /*public void execute() {
        System.out.println("--- GeneticAlgorithm.execute()");
        HSQLDBManager.instance.insert("hello world");
    }*/

}