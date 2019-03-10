package parameterRecommender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ApplicationParameterRecommender {
    public static void main(String... args) {
        Configuration.instance.scanMaxNumberOfIterations(); // Lese Parameter

        ExecutorService executor = execute();
        try {
            while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        printMaxFitness();
    }

    public static ExecutorService execute() {
        ExecutorService executor = Executors.newFixedThreadPool(Configuration.instance.numberOfProcessors);

        ArrayList<String> cm = new ArrayList<>();
        cm.addAll(Arrays.asList("AEX", "CSEX", "CX", "DPX", "ERX", "GSX", "HGX", "HX", "MOX", "MPMX", "MPX", "MX", "NWOX", "OBX", "OX", "PMX", "PX", "SCX", "SMX", "UPMX", "VRX"));
        ArrayList<String> cr = new ArrayList<>();
        cr.addAll(Arrays.asList("0.5", "0.6", "0.7", "0.8"));
        ArrayList<String> mm = new ArrayList<>();
        mm.addAll(Arrays.asList("DM", "EM", "HM", "INSM", "INVM"));
        ArrayList<String> mr = new ArrayList<>();
        mr.addAll(Arrays.asList("0.001", "0.002", "0.003", "0.004", "0.005"));
        ArrayList<String> s = new ArrayList<>();
        s.addAll(Arrays.asList("PRWS", "RBRWS", "RWS", "TS"));

        for(String Crossover : cm) {
            for(String CrossoverRatio : cr) {
                for(String Mutation : mm) {
                    for(String MutationRatio : mr) {
                        for(String Selection : s) {
                            Runnable worker = new Service(Crossover, CrossoverRatio, Mutation, MutationRatio, Selection);
                            executor.execute(worker);
                        }
                    }
                }
            }
        }
        executor.shutdown();
        return executor;
    }

    public static void printMaxFitness(){
        System.out.println("Max fitness is: " + Configuration.instance.bestFitnessService.fitness);
        System.out.println("Parameters: {Crossover " + Configuration.instance.bestFitnessService.Crossover
                + ", CrossoverRatio "+ Configuration.instance.bestFitnessService.CrossoverRatio
                + ", Mutation "+ Configuration.instance.bestFitnessService.Mutation
                + ", MutationRatio "+ Configuration.instance.bestFitnessService.MutationRatio
                + ", Selection"+ Configuration.instance.bestFitnessService.Selection+"}");
    }

    public static class Service implements Runnable {
        private String Crossover;
        private String CrossoverRatio;
        private String Mutation;
        private String MutationRatio;
        private String Selection;
        private double fitness = 0;
        //private Application application = new Application();

        public double getFitness() {
            return fitness;
        }

        //TODO Population einfügen


        public Service(String Crossover, String CrossoverRatio, String Mutation, String MutationRatio, String Selection) {
            this.Crossover = Crossover;
            this.CrossoverRatio = CrossoverRatio;
            this.Mutation = Mutation;
            this.MutationRatio = MutationRatio;
            this.Selection = Selection;
        }

        public void run() {
            //application.main(Crossover, CrossoverRatio, Mutation, MutationRatio, Selection, Configuration.instance.maxIterations);
            //fitness = application.getFitness();
            if(fitness > Configuration.instance.bestFitnessService.fitness){
                Configuration.instance.bestFitnessService = this;
            }
        }


    }
}
