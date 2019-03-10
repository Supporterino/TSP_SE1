package parameterRecommender;

import java.util.Scanner;

public enum Configuration {
    instance;

    public int numberOfProcessors = Runtime.getRuntime().availableProcessors();
    public int maxIterations = 1000000;
    public ApplicationParameterRecommender.Service bestFitnessService = null;

    public void setMaxIterations(int iterations){
        this.maxIterations = iterations;
    }

    public void scanMaxNumberOfIterations(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Set maximum number of iterations:");
        this.maxIterations = scanner.nextInt();
        System.out.println("Set number of cores:");
        int coresUser = scanner.nextInt();
        if(coresUser <= this.numberOfProcessors) this.numberOfProcessors = coresUser;

        scanner.close();
    }
}
