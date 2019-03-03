package configuration;

import random.MersenneTwisterFast;

public enum Configuration {
    instance;

    public String fileSeparator = System.getProperty("file.separator");
    public String userDirectory = System.getProperty("user.dir");

    public String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;
    public String dataFilePath = dataDirectory + "TSP280.txt";

    public String databaseFile = dataDirectory + "datastore.db";

    public MersenneTwisterFast randomGenerator = new MersenneTwisterFast(System.currentTimeMillis());

    public CrossoverType crossoverType = CrossoverType.PMX;
    public MutationType mutationType = MutationType.DM;
    public SelectionType selectionType = SelectionType.RWS;
    public Object commandLine;
    public int selectionAlgorithmChoice;
    public double tournamentSelectionKValue;
    public int crossoverAlgorithmChoice;
    public int numberOfSlicePoints;
    public double crossoverRatio;
    public double mutationRatio;
  

    public double selectionPressureProbability = 0.7;
    public double selectionPressureRatio = 0.1;
    public double selectionRankBasedRWMaximumNumberOfIndividuals = 0.1;
    public int numberOfMutationPoints;
    public int mutationAlgorithmChoice;
}