import base.Population;
import configuration.Configuration;
import mutation.DisplacementMutation;
import mutation.ExchangeMutation;
import mutation.InversionMutation;
import mutation.InsertionMutation;
import selection.RankBasedRouletteWheelSelection;
import selection.TournamentSelection;
import crossover.HeuristicCrossover;
import crossover.*;
import utilities.LogEngine;

import java.util.Scanner;

public class ApplicationKnapsack {

    private static ApplicationKnapsack applicationKnapsack;
    private Scanner inputReader;
    public static void main(String... args) {
        Program program = new Program(new CommandLine());
        // ApplicationTSP applicationTSP = new ApplicationTSP();
        //  applicationTSP.execute();



    }

    public  void setupConfiguration(){
        setupConfigurationSelection();
        setupConfigurationCrossover();
        setupConfigurationMutation();
    }

    private void setupConfigurationSelection() {

        System.out.print("selection algorithm (0 = roulette, 1 = tournament): ");
        Configuration.instance.commandLine = inputReader.nextInt();

        if (Configuration.instance.selectionAlgorithmChoice == 1) {
            System.out.print("k-value for tournament selection (.60-.85): ");
            Configuration.instance.tournamentSelectionKValue = inputReader.nextDouble();
        }
    }


    private void setupConfigurationCrossover() {
        System.out.print("crossover algorithm (0 = n-slice, 1 = uniform): ");
        Configuration.instance.crossoverAlgorithmChoice = inputReader.nextInt();

        if (Configuration.instance.crossoverAlgorithmChoice == 0) {
            System.out.print("n-value for n-slice (usually 2-4): ");
            Configuration.instance.numberOfSlicePoints = inputReader.nextInt();
        }

        System.out.print("crossover ratio (0.60-0.85): ");
        Configuration.instance.crossoverRatio = inputReader.nextDouble();
    }



    private void setupConfigurationMutation(){
        System.out.print("mutation algorithm (0 = n-point, 1 = invert): ");
        Configuration.instance.mutationAlgorithmChoice = inputReader.nextInt();

        if (Configuration.instance.mutationAlgorithmChoice == 0) {
            System.out.print("number of mutation points (usually 1-4): ");
            Configuration.instance.numberOfMutationPoints = inputReader.nextInt();
        }

        System.out.print("mutation rate (0.005-0.01): ");
        Configuration.instance.mutationRatio = inputReader.nextDouble();
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
}
