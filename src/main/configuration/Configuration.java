package configuration;

import crossover.Crossover;
import mutation.Mutation;
import random.MersenneTwisterFast;
import selection.Selection;

import java.util.ArrayList;

public enum Configuration {
    instance;

    public String fileSeparator = System.getProperty("file.separator");
    public String userDirectory = System.getProperty("user.dir");

    public String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;
    public String dataFilePath = dataDirectory + "TSP280.txt";

    public String databaseFile = dataDirectory + "datastore.db";

    public MersenneTwisterFast randomGenerator = new MersenneTwisterFast(System.currentTimeMillis());


    // base
    public int maximumTSPCapacity = 0;
    public int numberOfItems = 0;
    public ArrayList<Integer> weightList = new ArrayList<>();
    public ArrayList<Integer> valueList = new ArrayList<>();
    public int totalValue = 0;
    public double offset = 0;
    public double penalty = 0;
    public int sizeOfPopulation = 100;
    public int maximumNumberOfGenerations = 1000;

    //Selection
    public Selection selection;


    // crossover
    public Crossover crossover;
    public double crossoverRatio = 0.7;
    public int numberOfNCrossoverSlicePoints = 2;

    // mutation
    public Mutation mutation;
    public double mutationRatio = 0.0002;
    public int numberOfNMutationPoints = 2;

    // solution
    public boolean[] optimalSolution;
    public StringBuilder optimalSolutionString = new StringBuilder();
    public int optimalSolutionWeight = 0;
    public int optimalSolutionValue = 0;
    public double optimalSolutionFitness = 0;


public Object commandLine;
    public int selectionAlgorithmChoice;
   // public double tournamentSelectionKValue;
    public int crossoverAlgorithmChoice;
    public int numberOfSlicePoints;
//    public double crossoverRatio;
  //  public double mutationRatio;
    public int maxIterations;


    public double selectionPressureProbability = 0.7;
    public double selectionPressureRatio = 0.1;
    public double selectionRankBasedRWMaximumNumberOfIndividuals = 0.1;
    public int numberOfMutationPoints;
    public int mutationAlgorithmChoice;
    public String lineSeparator;
    public String logFile;
    public Object populationSize;
    public Object elitismRatio;
}