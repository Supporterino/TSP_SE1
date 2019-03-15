

import base.Population;
import configuration.Configuration;
import utilities.LogEngine;
import java.util.Scanner;

public class ApplicationTSP {
    private static ApplicationTSP applicationTSP;
    private Scanner inputReader;



    public void setupConfiguration() {
        setupConfigurationSelection();
        setupConfigurationCrossover();
        setupConfigurationMutation();
    }

    private void setupConfigurationSelection() {

        System.out.print("selection algorithm (0 = roulette, 1 = tournament): ");
        Configuration.instance.commandLine = inputReader.nextInt();

        if (Configuration.instance.selectionAlgorithmChoice == 1) {
            System.out.print("k-value for tournament selection (.60-.85): ");
           // Configuration.instance.tournamentSelectionKValue = inputReader.nextDouble();
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


    private void setupConfigurationMutation() {
        System.out.print("mutation algorithm (0 = n-point, 1 = invert): ");
        Configuration.instance.mutationAlgorithmChoice = inputReader.nextInt();

        if (Configuration.instance.mutationAlgorithmChoice == 0) {
            System.out.print("number of mutation points (usually 1-4): ");
            Configuration.instance.numberOfMutationPoints = inputReader.nextInt();
        }

        System.out.print("mutation rate (0.005-0.01): ");
        Configuration.instance.mutationRatio = inputReader.nextDouble();
    }

}
