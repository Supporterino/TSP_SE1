import base.Population;
import base.Tour;
import base.City;
import configuration.Configuration;
import crossover.AlternatingEdgesCrossover;
import crossover.CompleteSubTourExchangeCrossover;
import crossover.CycleCrossover;
import crossover.DistancePreservingCrossover;
import crossover.EdgeRecombinationCrossover;
import crossover.GreedySubTourCrossover;
import crossover.HeuristicCrossover;
import crossover.HeuristicGreedyCrossover;
import crossover.MaskedCrossover;
import crossover.MaximalPreservationCrossover;
import crossover.ModifiedOrderCrossover;
import crossover.ModifiedPartiallyMappedCrossover;
import crossover.NonWrappingOrderedCrossover;
import crossover.OrderBasedCrossover;
import crossover.OrderCrossover;
import crossover.PartiallyMappedCrossover;
import crossover.PositionCrossover;
import crossover.SequentialConstructiveCrossover;
import crossover.SortedMatchCrossover;
import crossover.UniformPartiallyMappedCrossover;
import crossover.VotingRecombinationCrossover;

import mutation.DisplacementMutation;
import mutation.ExchangeMutation;
import mutation.HeuristicMutation;
import mutation.InsertionMutation;
import mutation.InversionMutation;

import selection.ProportionalRouletteWheelSelection;
import selection.RankBasedRouletteWheelSelection;
import selection.RouletteWheelSelection;
import selection.TournamentSelection;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GeneticAlgorithm {
    public static void main(String... args) {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-p")) {
                    Configuration.instance.sizeOfPopulation = Integer.parseInt(args[i+1]);
                    System.out.println("sizeOfPopulation           : " + Configuration.instance.sizeOfPopulation);
                } else if (args[i].equals("-n")) {
                    Configuration.instance.maximumNumberOfGenerations = Integer.parseInt(args[i+1]);
                    System.out.println("maximumNumberOfGenerations : " + Configuration.instance.maximumNumberOfGenerations);
                } else if (args[i].equals("-s")) {
                    if (Integer.parseInt(args[i+1]) == 0)
                        Configuration.instance.selection = new RouletteWheelSelection();
                    else if (Integer.parseInt(args[i+1]) == 1){
                        Configuration.instance.selection = new TournamentSelection();
                    System.out.println("selection                  : " + Configuration.instance.selection);}
                    else if (Integer.parseInt(args[i+1]) == 2){
                        Configuration.instance.selection = new RankBasedRouletteWheelSelection();
                        System.out.println("selection                  : " + Configuration.instance.selection);}
                    else if (Integer.parseInt(args[i+1]) == 3)
                        Configuration.instance.selection = new ProportionalRouletteWheelSelection();
                        System.out.println("selection                  : " + Configuration.instance.selection);



                } else if (args[i].equals("-cr")) {
                    Configuration.instance.crossoverRatio = Double.parseDouble(args[i+1]);
                    System.out.println("crossoverRatio             : " + Configuration.instance.crossoverRatio);
                } else if (args[i].equals("-c")) {
                    if (Integer.parseInt(args[i+1]) == 0)
                        Configuration.instance.crossover = new AlternatingEdgesCrossover();
                    else if (Integer.parseInt(args[i+1]) == 1){
                        Configuration.instance.crossover = new CompleteSubTourExchangeCrossover();
                    System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 2){
                        Configuration.instance.crossover = new CycleCrossover();
                    System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 3){
                        Configuration.instance.crossover = new DistancePreservingCrossover();
                    System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 4){
                        Configuration.instance.crossover = new EdgeRecombinationCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 5){
                        Configuration.instance.crossover = new GreedySubTourCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 6){
                        Configuration.instance.crossover = new HeuristicCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 7){
                        Configuration.instance.crossover = new HeuristicGreedyCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 8){
                        Configuration.instance.crossover = new MaskedCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 9){
                        Configuration.instance.crossover = new MaximalPreservationCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 10){
                        Configuration.instance.crossover = new ModifiedOrderCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 11){
                        Configuration.instance.crossover = new ModifiedPartiallyMappedCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 12){
                        Configuration.instance.crossover = new NonWrappingOrderedCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 13){
                        Configuration.instance.crossover = new OrderBasedCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 14){
                        Configuration.instance.crossover = new OrderCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 15){
                        Configuration.instance.crossover = new PartiallyMappedCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 16){
                        Configuration.instance.crossover = new PositionCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 17){
                        Configuration.instance.crossover = new SequentialConstructiveCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 18){
                        Configuration.instance.crossover = new SortedMatchCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 19){
                        Configuration.instance.crossover = new UniformPartiallyMappedCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);}
                    else if (Integer.parseInt(args[i+1]) == 20)
                        Configuration.instance.crossover = new VotingRecombinationCrossover();
                        System.out.println("crossover                  : " + Configuration.instance.crossover);


                } else if (args[i].equals("-mr")) {
                    Configuration.instance.mutationRatio = Double.parseDouble(args[i+1]);
                    System.out.println("mutationRatio              : " + Configuration.instance.mutationRatio);
                } else if (args[i].equals("-m")) {
                    if (Integer.parseInt(args[i+1]) == 0) {
                        Configuration.instance.mutation = new DisplacementMutation();
                    } else if (Integer.parseInt(args[i+1]) == 1){
                        Configuration.instance.mutation = new ExchangeMutation();
                    System.out.println("mutation                   : " + Configuration.instance.mutation);}
                    else if (Integer.parseInt(args[i+1]) == 2){
                        Configuration.instance.mutation = new HeuristicMutation();
                        System.out.println("mutation                   : " + Configuration.instance.mutation);}
                    else if (Integer.parseInt(args[i+1]) == 3){
                        Configuration.instance.mutation = new InsertionMutation();
                        System.out.println("mutation                   : " + Configuration.instance.mutation);}
                    else if (Integer.parseInt(args[i+1]) == 4){
                        Configuration.instance.mutation = new InversionMutation();
                        System.out.println("mutation                   : " + Configuration.instance.mutation);}
                }
            }
        } else {
            geneticAlgorithm.getConfigurationByUserInput();
        }

        geneticAlgorithm.doSelection();
        geneticAlgorithm.doMutation();
        geneticAlgorithm.doCrossover();
    }

    public void doSelection(){

    }

    public void doMutation(){

    }

    public void doCrossover(){

    }

    public void getConfigurationByUserInput() {
        int selectionAlgorithm;
        int crossoverAlgorithm;
        int mutationAlgorithm;
        Scanner inputReader = new Scanner(System.in);

        // base
        System.out.print("population size (50-300): ");
        Configuration.instance.sizeOfPopulation = inputReader.nextInt();

        System.out.print("maximum number of generations: ");
        Configuration.instance.maximumNumberOfGenerations = inputReader.nextInt();

        // selection
        System.out.print("selection algorithm (0 = roulette, 1 = tournament, 2 = rank , 3 = proportional): ");
        selectionAlgorithm = inputReader.nextInt();

        if (selectionAlgorithm == 0) {
            Configuration.instance.selection = new RouletteWheelSelection();
        } else if (selectionAlgorithm == 1) {
            Configuration.instance.selection = new TournamentSelection();
            System.out.print("k-value for tournament selection (): ");

        } else if (selectionAlgorithm == 2) {
            Configuration.instance.selection = new RankBasedRouletteWheelSelection();
            System.out.print("k-value for tournament selection (): ");

        } else if (selectionAlgorithm == 3) {
            Configuration.instance.selection = new ProportionalRouletteWheelSelection();
            System.out.print("k-value for tournament selection (): ");

        }

        // crossover
        System.out.print("crossover ratio (0.50-0.80): ");
        Configuration.instance.crossoverRatio = inputReader.nextDouble();

        System.out.print("crossover algorithm (0 = alternating, 1 = complete , 2 = cycle , 3 = distance , 4 = edge , 5 = greedy , 6 = heuristic , 7 = heuristicGreedy , 8 = masked , 9 = maximal , 10 = modified Order , 11 = Modified Partially Mapped , 12 = Non Wrapping , 13 = Order Based , 14 = order , 15 = Partially Mapped , 16 = position , 17 = sequential , 18 = sorted , 19 = uniform , 20 = voting ): ");
        crossoverAlgorithm = inputReader.nextInt();

        if (crossoverAlgorithm == 0) {
            Configuration.instance.crossover = new AlternatingEdgesCrossover();

            Configuration.instance.numberOfNCrossoverSlicePoints = inputReader.nextInt();
        } else if (crossoverAlgorithm == 1) {
            Configuration.instance.crossover = new CompleteSubTourExchangeCrossover();}
        else if (crossoverAlgorithm == 2) {
            Configuration.instance.crossover = new CycleCrossover();}
        else if (crossoverAlgorithm == 3) {
            Configuration.instance.crossover = new DistancePreservingCrossover();}
        else if (crossoverAlgorithm == 4) {
            Configuration.instance.crossover = new EdgeRecombinationCrossover();}
        else if (crossoverAlgorithm == 5) {
            Configuration.instance.crossover = new GreedySubTourCrossover();}
        else if (crossoverAlgorithm == 6) {
            Configuration.instance.crossover = new HeuristicCrossover();}
        else if (crossoverAlgorithm == 7) {
            Configuration.instance.crossover = new HeuristicGreedyCrossover();}
        else if (crossoverAlgorithm == 8) {
            Configuration.instance.crossover = new MaskedCrossover();}
        else if (crossoverAlgorithm == 9) {
            Configuration.instance.crossover = new MaximalPreservationCrossover();}
        else if (crossoverAlgorithm == 10) {
            Configuration.instance.crossover = new ModifiedOrderCrossover();}
        else if (crossoverAlgorithm == 11) {
            Configuration.instance.crossover = new ModifiedPartiallyMappedCrossover();}
        else if (crossoverAlgorithm == 12) {
            Configuration.instance.crossover = new NonWrappingOrderedCrossover();}
        else if (crossoverAlgorithm == 13) {
            Configuration.instance.crossover = new OrderBasedCrossover();}
        else if (crossoverAlgorithm == 14) {
            Configuration.instance.crossover = new OrderCrossover();}
        else if (crossoverAlgorithm == 15) {
            Configuration.instance.crossover = new PartiallyMappedCrossover();}
        else if (crossoverAlgorithm == 16) {
            Configuration.instance.crossover = new PositionCrossover();}
        else if (crossoverAlgorithm == 17) {
            Configuration.instance.crossover = new SequentialConstructiveCrossover();}
        else if (crossoverAlgorithm == 18) {
            Configuration.instance.crossover = new SortedMatchCrossover();}
        else if (crossoverAlgorithm == 19) {
            Configuration.instance.crossover = new UniformPartiallyMappedCrossover();}
        else if (crossoverAlgorithm == 20) {
            Configuration.instance.crossover = new VotingRecombinationCrossover();
        }

        // mutation
        System.out.print("mutation ratio (0.001-0.0005): ");
        Configuration.instance.mutationRatio = inputReader.nextDouble();

        System.out.print("mutation algorithm (0 = displacement, 1 = exchange, 2 = heuristic , 3 = insert , 4 = inverse): ");
        mutationAlgorithm = inputReader.nextInt();

        if (mutationAlgorithm == 0) {
            Configuration.instance.mutation = new DisplacementMutation();
            System.out.print("number of mutation points (usually 1-4): ");
            Configuration.instance.numberOfNMutationPoints = inputReader.nextInt();
        } else if (mutationAlgorithm == 1) {
            Configuration.instance.mutation = new ExchangeMutation();
        } else if (mutationAlgorithm == 2) {
            Configuration.instance.mutation = new HeuristicMutation();
        } else if (mutationAlgorithm == 3) {
            Configuration.instance.mutation = new InsertionMutation();}
        else if (mutationAlgorithm == 4) {
            Configuration.instance.mutation = new InversionMutation();
        }

    }

    /*
    public void execute() {
        try {
            // load data: capacity
            Scanner maximumCapacityScanner = new Scanner(new File(Configuration.instance.dataDirectory + "capacity.txt"));
            Configuration.instance.maximumKnapsackCapacity = maximumCapacityScanner.nextInt();
            maximumCapacityScanner.close();

            // load data: weight
            Scanner weightScanner = new Scanner(new File(Configuration.instance.dataDirectory + "weight.txt"));
            while (weightScanner.hasNextInt()) {
                Configuration.instance.numberOfItems++;
                int weight = weightScanner.nextInt();
                Configuration.instance.weightList.add(weight);
            }
            weightScanner.close();

            // load data: value
            Scanner valueScanner = new Scanner(new File(Configuration.instance.dataDirectory + "value.txt"));
            while (valueScanner.hasNextInt()) {
                int value = valueScanner.nextInt();
                Configuration.instance.valueList.add(value);
                Configuration.instance.totalValue += value;
            }
            valueScanner.close();

            double temp;
            for (int i = 0; i < Configuration.instance.numberOfItems; i++) {
                Configuration.instance.offset += Configuration.instance.valueList.get(i);
                temp = ((double) Configuration.instance.valueList.get(i)) / Configuration.instance.weightList.get(i);
                if (temp > Configuration.instance.penalty)
                    Configuration.instance.penalty = temp;
            }

            Configuration.instance.offset *= .3;
            Scanner solutionScanner = new Scanner(new File(Configuration.instance.dataDirectory + "solution.txt"));
            Configuration.instance.optimalSolution = new boolean[Configuration.instance.numberOfItems];

            for (int i = 0; i < Configuration.instance.numberOfItems && solutionScanner.hasNextInt(); i++) {
                if (solutionScanner.nextInt() == 0) {
                    Configuration.instance.optimalSolution[i] = false;
                    Configuration.instance.optimalSolutionString.append("0");
                } else {
                    Configuration.instance.optimalSolution[i] = true;
                    Configuration.instance.optimalSolutionString.append("1");
                    Configuration.instance.optimalSolutionWeight += Configuration.instance.weightList.get(i);
                    Configuration.instance.optimalSolutionValue += Configuration.instance.valueList.get(i);
                }
            }

            Configuration.instance.optimalSolutionFitness = fitness(Configuration.instance.optimalSolution,
                    Configuration.instance.optimalSolutionWeight);

            solutionScanner.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        // print
        System.out.println(" item # |  value |   weight |");

        for (int i = 0; i < Configuration.instance.numberOfItems; i++)
            System.out.printf("%7d |%7d |%7d |\n", i, Configuration.instance.valueList.get(i), Configuration.instance.weightList.get(i));

        System.out.println("maximumKnapsackCapacity : " + Configuration.instance.maximumKnapsackCapacity);
        System.out.println("numberOfItems           : " + Configuration.instance.numberOfItems);
        System.out.println("totalValue              : " + Configuration.instance.totalValue);
        System.out.println();

        // generate initial chromosomes
        boolean[][] chromosomePool = new boolean[Configuration.instance.sizeOfPopulation][Configuration.instance.numberOfItems];
        double[] fitnessPool = new double[Configuration.instance.sizeOfPopulation];
        int[] weightPool = new int[Configuration.instance.sizeOfPopulation];

        for (int i = 0; i < Configuration.instance.sizeOfPopulation; i++) {
            for (int j = 0; j < Configuration.instance.numberOfItems; j++) {
                chromosomePool[i][j] = Configuration.instance.randomGenerator.nextBoolean();
                if (chromosomePool[i][j])
                    weightPool[i] += Configuration.instance.weightList.get(j);
            }
            fitnessPool[i] = fitness(chromosomePool[i], weightPool[i]);
        }

        // main loop
        double maxFitnessSoFar = 0;

        for (int currentGeneration = 0; currentGeneration < Configuration.instance.maximumNumberOfGenerations; currentGeneration++) {
            // selection
            int[] selectedParentPool = Configuration.instance.selection.doSelection(Population population);

            // crossover
            boolean[][] childPool = Configuration.instance.crossover.doCrossover(Tour tour01, Tour tour02);

            // mutation
            for (int i = 0; i < Configuration.instance.sizeOfPopulation; i++)
                if (Configuration.instance.randomGenerator.nextDouble() < Configuration.instance.mutationRatio)
                    childPool[i] = Configuration.instance.mutation.doMutation(Tour tour);

            int currentBestIndex = 0;
            int globalBestIndex = 1;

            for (int i = 1; i < Configuration.instance.sizeOfPopulation; i++) {
                if (fitnessPool[i] > fitnessPool[currentBestIndex]) {
                    globalBestIndex = currentBestIndex;
                    currentBestIndex = i;
                } else if (fitnessPool[i] > fitnessPool[globalBestIndex]) {
                    globalBestIndex = i;
                }
            }

            if (fitnessPool[currentBestIndex] > maxFitnessSoFar) {
                maxFitnessSoFar = fitnessPool[currentBestIndex];
                if (currentGeneration > 100) {
                    double percent = (fitnessPool[0] / Configuration.instance.optimalSolutionFitness) * 100;
                    System.out.printf(currentGeneration + " - best fitness : %,.2f%% of known optimum\n", percent);
                }
            }

            childPool[0] = chromosomePool[currentBestIndex];
            childPool[1] = chromosomePool[globalBestIndex];
            double tempFitness = fitnessPool[globalBestIndex];
            fitnessPool[0] = fitnessPool[currentBestIndex];
            fitnessPool[1] = tempFitness;
            int tempWeight = weightPool[globalBestIndex];
            weightPool[0] = weightPool[currentBestIndex];
            weightPool[1] = tempWeight;
            chromosomePool = childPool;

            for (int i = 2; i < Configuration.instance.sizeOfPopulation; i++) {
                weightPool[i] = 0;
                for (int j = 0; j < Configuration.instance.numberOfItems; j++) {
                    if (chromosomePool[i][j])
                        weightPool[i] += Configuration.instance.weightList.get(j);
                }
                fitnessPool[i] = fitness(chromosomePool[i], weightPool[i]);
            }
        }

        // determine best fitness
        int indexBestFitness = 0;
        for (int i = 1; i < Configuration.instance.sizeOfPopulation; i++)
            if (fitnessPool[i] > fitnessPool[indexBestFitness] && weightPool[i] <= Configuration.instance.maximumKnapsackCapacity)
                indexBestFitness = i;
        StringBuilder bestSolutionString = new StringBuilder();
        int bestSolutionValue = 0;

        for (int i = 0; i < Configuration.instance.numberOfItems; i++) {
            if (chromosomePool[indexBestFitness][i]) {
                bestSolutionString.append("1");
                bestSolutionValue += Configuration.instance.valueList.get(i);
            } else
                bestSolutionString.append("0");
        }

        System.out.println();
        System.out.println("optimal solution : " + Configuration.instance.optimalSolutionString);
        System.out.println("optimal fitness  : " + fitness(Configuration.instance.optimalSolution, Configuration.instance.optimalSolutionWeight));
        System.out.println("optimal value    : " + Configuration.instance.optimalSolutionValue);
        System.out.println();
        System.out.println("solution         : " + bestSolutionString);
        System.out.println("fitness          : " + fitnessPool[indexBestFitness]);
        System.out.println("value            : " + bestSolutionValue);
    }

    public double fitness(boolean[] chromosome, int s) {
        int value = 0;
        for (int i = 0; i < Configuration.instance.numberOfItems; i++)
            if (chromosome[i])
                value += Configuration.instance.valueList.get(i);
        if (s > Configuration.instance.maximumKnapsackCapacity) {
            double result = value - ((s - Configuration.instance.maximumKnapsackCapacity) * Configuration.instance.penalty + Configuration.instance.offset);
            if (result < 0.1)
                return 0.1;
            else
                return result;
        } else
            return value;
    }*/
}
