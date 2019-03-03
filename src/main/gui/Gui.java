package gui;

import base.City;
import base.Population;
import base.Tour;
import configuration.Configuration;
import crossover.AlternatingEdgesCrossover;
import data.HSQLDBManager;
import data.InstanceReader;
import data.TSPLIBReader;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import mutation.DisplacementMutation;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import selection.TournamentSelection;

import java.io.*;
import java.util.ArrayList;

public class Gui extends Application {
    private JSONObject config;
    private int maxIterations = 1000;
    private ArrayList<City> availableCities;
    private Configuration configuration;

    private TextArea results;
    private ComboBox crossOver;
    private Spinner<Double> crossOverRatio;
    private ComboBox mutation;
    private Spinner<Double> mutationRatio;
    private Spinner<Integer> iterations;
    private ComboBox selection;
    private Button start;


    private Tour result = new Tour();

    private ObservableList<String> crossovers = FXCollections.observableArrayList("1PX", "2PX", "AX", "HX", "IX", "KPX", "SX", "UNX");
    private ObservableList<String> mutations = FXCollections.observableArrayList("DM", "EM", "INSM", "INVM", "SM");
    private ObservableList<String> selections = FXCollections.observableArrayList("PRWS", "RBRWS", "RWS", "TS");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent parent = loader.load();

        VBox dragAndDrop = createDragAndDropLabel();

        StackPane root = new StackPane();
        // load scene
        root.getChildren().setAll(parent);
        root.getChildren().add(dragAndDrop);

        Scene scene = new Scene(root, 820.0, 484.0);
        startupHSQLDB();
        loadData();
        init(scene);
        shutdownHSQLDB();
        primaryStage.setResizable(false);
        primaryStage.setTitle("TSP");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void init(Scene root) {
        configuration = Configuration.instance;

        //Value initialisation
        crossOver = (ComboBox) root.lookup("#Crossover");
        crossOverRatio = (Spinner) root.lookup("#CrossoverRatio");
        mutation = (ComboBox) root.lookup("#Mutation");
        mutationRatio = (Spinner) root.lookup("#MutationRatio");
        iterations = (Spinner) root.lookup("#Iterations");
        selection = (ComboBox) root.lookup("#Selection");
        start = (Button) root.lookup("#Start");
        results = (TextArea) root.lookup("#result");

        initComponents();
    }

    public void print() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < result.getSize(); i++) {
            str.append(result.getCity(i).getId());
            str.append(" -> ");
        }

        results.setText(str.toString());
    }


    public void execute() {

    }

    public void initComponents() {
        crossOver.setItems(crossovers);
        mutation.setItems(mutations);
        selection.setItems(selections);

        crossOver.setValue(configuration.crossoverType);
        mutation.setValue(configuration.mutationType);
        selection.setValue(configuration.selectionType);

        SpinnerValueFactory<Double> crossoverFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.5, 0.8, 0.5);
        SpinnerValueFactory<Double> mutationFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.001, 0.005, 0.001);
        SpinnerValueFactory<Integer> iterationsFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 10000);

        ((SpinnerValueFactory.DoubleSpinnerValueFactory) crossoverFactory).setAmountToStepBy(0.1);
        ((SpinnerValueFactory.DoubleSpinnerValueFactory) mutationFactory).setAmountToStepBy(0.001);

        crossOverRatio.setValueFactory(crossoverFactory);
        mutationRatio.setValueFactory(mutationFactory);
        iterations.setValueFactory(iterationsFactory);

        results.setWrapText(true);

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("You clicked: " + ((Button) event.getSource()).getId());
                execute();
            }
        });
    }

    public void startupHSQLDB() {
        HSQLDBManager.instance.startup();
        HSQLDBManager.instance.init();
    }

    public void shutdownHSQLDB() {
        HSQLDBManager.instance.shutdown();
    }

    public void loadData() {
        InstanceReader instanceReader = new InstanceReader(Configuration.instance.dataFilePath);
        instanceReader.open();
        TSPLIBReader tspLibReader = new TSPLIBReader(instanceReader);

        availableCities = tspLibReader.getCities();

        instanceReader.close();

        System.out.println();
    }

    public VBox createDragAndDropLabel() {
        Label temp = new Label("Drag ´n Drop JSON here");
        temp.setTranslateY(70);
        temp.setTranslateX(450);
        temp.setTextFill(Color.web("#FFFFFF"));

        Label dropped = new Label("");

        VBox dragTarget = new VBox();
        dragTarget.getChildren().addAll(temp, dropped);
        dragTarget.setOnDragOver(event -> {
            if (event.getGestureSource() != dragTarget
                    && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        dragTarget.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                dropped.setText(db.getFiles().toString());
                try {
                    File file = new File(db.getFiles().toString());

                    JSONObject jsonObject = getJSONfile(db.getFiles().toString());
                    config = jsonObject;
                } catch (IOException e) {
                    System.out.println(e);
                }

                success = true;
            }
            event.setDropCompleted(success);

            event.consume();
        });

        return dragTarget;
    }

    private JSONObject getJSONfile(String fileName) throws IOException {

        String newFileName = fileName.substring(1, fileName.length() - 1);
        BufferedReader br = new BufferedReader(new FileReader(newFileName));
        String s = "";

        while ((s = br.readLine()) != null)
            System.out.println(s);

        JSONParser parser = new JSONParser();
        try {
            Object object = parser
                    .parse(new FileReader(newFileName));

            //convert Object to JSONObject
            JSONObject jsonObject = (JSONObject) object;

            // Reading the JSON file
            String mutation = (String) jsonObject.get("mutation");
            String crossover = (String) jsonObject.get("crossover");
            double crossoverRatio = (double) jsonObject.get("crossoverRatio");
            String selection = (String) jsonObject.get("selection");
            double mutationRatio = (double) jsonObject.get("mutationRatio");
            String name = (String) jsonObject.get("name");
            long maximumNumberOfIterations = (long) jsonObject.get("maximumNumberOfIterations");

            return jsonObject;
        } catch (Exception fe) {
            fe.printStackTrace();
        }
        return null;
    }
}