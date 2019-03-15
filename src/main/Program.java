

import base.City;
import configuration.Configuration;
import crossover.*;
import data.HSQLManagerForEvolution.HSQLManager;
import data.InstanceReader;
import data.TSPLIBReader;
import mutation.*;
import selection.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

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
        Program program = new Program();

    }

    public Program() {

        ApplicationTSP applicationTSP = new ApplicationTSP();
    }

    public void startupHSQLDB() {
        HSQLManager.tsp.startup();
    }

    //Drops all tables
    public void initHSQLDB(){
        HSQLManager.tsp.init();
    }

    public void shutdownHSQLDB() {
        HSQLManager.tsp.close();
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

        String selectionAlgorithm;
        String crossoverAlgorithm;
        String mutationAlgorithm;
        Scanner inputReader = new Scanner(System.in);

        // base
        System.out.print("population size (50-300): ");
        Configuration.instance.sizeOfPopulation = inputReader.nextInt();

        System.out.print("maximum number of generations: ");
        Configuration.instance.maximumNumberOfGenerations = inputReader.nextInt();

        // selection
        System.out.print("selection algorithm (RWS = roulette, TS = tournament, RBRWS = rank , PRWS = proportional): ");
        selectionAlgorithm = inputReader.next();

        if (selectionAlgorithm == "RWS") {
            Configuration.instance.selection = new RouletteWheelSelection();
        } else if (selectionAlgorithm == "TS") {
            Configuration.instance.selection = new TournamentSelection();
            System.out.print("k-value for tournament selection (): ");

        } else if (selectionAlgorithm == "RBRWS") {
            Configuration.instance.selection = new RankBasedRouletteWheelSelection();
            System.out.print("k-value for tournament selection (): ");

        } else if (selectionAlgorithm == "PRWS") {
            Configuration.instance.selection = new ProportionalRouletteWheelSelection();
            System.out.print("k-value for tournament selection (): ");

        }

        // crossover
        System.out.print("crossover ratio (0.50-0.80): ");
        Configuration.instance.crossoverRatio = inputReader.nextDouble();

        System.out.print("crossover algorithm (AEX = alternating, CSEX = complete , CX = cycle , DPX = distance , ERX = edge , GSX = greedy , HX = heuristic , HGX = heuristicGreedy , MX = masked , MPX = maximal , MOX = modified Order , MPMX = Modified Partially Mapped , NWOX = Non Wrapping , OBX = Order Based , OX = order , PMX = Partially Mapped , PX = position , SCX = sequential , SMX = sorted , UPMX = uniform , VRX = voting ): ");
        crossoverAlgorithm = inputReader.next();

        if (crossoverAlgorithm == "AEX") {
            Configuration.instance.crossover = new AlternatingEdgesCrossover();

            Configuration.instance.numberOfNCrossoverSlicePoints = inputReader.nextInt();
        } else if (crossoverAlgorithm == "CSEX") {
            Configuration.instance.crossover = new CompleteSubTourExchangeCrossover();}
        else if (crossoverAlgorithm == "CX") {
            Configuration.instance.crossover = new CycleCrossover();}
        else if (crossoverAlgorithm == "DPX") {
            Configuration.instance.crossover = new DistancePreservingCrossover();}
        else if (crossoverAlgorithm == "ERX") {
            Configuration.instance.crossover = new EdgeRecombinationCrossover();}
        else if (crossoverAlgorithm == "GSX") {
            Configuration.instance.crossover = new GreedySubTourCrossover();}
        else if (crossoverAlgorithm == "HX") {
            Configuration.instance.crossover = new HeuristicCrossover();}
        else if (crossoverAlgorithm == "HGX") {
            Configuration.instance.crossover = new HeuristicGreedyCrossover();}
        else if (crossoverAlgorithm == "MX") {
            Configuration.instance.crossover = new MaskedCrossover();}
        else if (crossoverAlgorithm == "MPX") {
            Configuration.instance.crossover = new MaximalPreservationCrossover();}
        else if (crossoverAlgorithm == "MOX") {
            Configuration.instance.crossover = new ModifiedOrderCrossover();}
        else if (crossoverAlgorithm == "MPMX") {
            Configuration.instance.crossover = new ModifiedPartiallyMappedCrossover();}
        else if (crossoverAlgorithm == "NWOX") {
            Configuration.instance.crossover = new NonWrappingOrderedCrossover();}
        else if (crossoverAlgorithm == "OBX") {
            Configuration.instance.crossover = new OrderBasedCrossover();}
        else if (crossoverAlgorithm == "OX") {
            Configuration.instance.crossover = new OrderCrossover();}
        else if (crossoverAlgorithm == "PMX") {
            Configuration.instance.crossover = new PartiallyMappedCrossover();}
        else if (crossoverAlgorithm == "PX") {
            Configuration.instance.crossover = new PositionCrossover();}
        else if (crossoverAlgorithm == "SCX") {
            Configuration.instance.crossover = new SequentialConstructiveCrossover();}
        else if (crossoverAlgorithm == "SMX") {
            Configuration.instance.crossover = new SortedMatchCrossover();}
        else if (crossoverAlgorithm == "UPMX") {
            Configuration.instance.crossover = new UniformPartiallyMappedCrossover();}
        else if (crossoverAlgorithm == "VRX") {
            Configuration.instance.crossover = new VotingRecombinationCrossover();
        }

        // mutation
        System.out.print("mutation ratio (0.001-0.0005): ");
        Configuration.instance.mutationRatio = inputReader.nextDouble();

        System.out.print("mutation algorithm (DM = displacement, EM = exchange, HM = heuristic , INSM = insert , INVM = inverse): ");
        mutationAlgorithm = inputReader.next();

        if (mutationAlgorithm == "DM") {
            Configuration.instance.mutation = new DisplacementMutation();
            System.out.print("number of mutation points (usually 1-4): ");
            Configuration.instance.numberOfNMutationPoints = inputReader.nextInt();
        } else if (mutationAlgorithm == "EM") {
            Configuration.instance.mutation = new ExchangeMutation();
        } else if (mutationAlgorithm == "HM") {
            Configuration.instance.mutation = new HeuristicMutation();
        } else if (mutationAlgorithm == "INSM") {
            Configuration.instance.mutation = new InsertionMutation();}
        else if (mutationAlgorithm == "INVM") {
            Configuration.instance.mutation = new InversionMutation();
        }
    }

    /*public void execute() {
        System.out.println("--- GeneticAlgorithm.execute()");
        HSQLDBManager.instance.insert("hello world");
    }*/

}