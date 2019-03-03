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
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
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
import selection.RouletteWheelSelection;
import selection.TournamentSelection;

import java.io.*;
import java.util.ArrayList;

public class Gui extends Application {
    private JSONObject config;
    private double crossoverRatio = 0.5;
    private double mutationRatio = 0.001;
    private int maxIterations = 1000;
    private TextArea results;
    private ArrayList<City> availableCities;
    private Configuration configuration;

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
        primaryStage.setResizable(false);
        primaryStage.setTitle("TSP");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void init(Scene root) {
        configuration = Configuration.instance;
        AlternatingEdgesCrossover alternatingEdgesCrossover = new AlternatingEdgesCrossover();
        DisplacementMutation displacementMutation = new DisplacementMutation();
        TournamentSelection tournamentSelection = new TournamentSelection();
        Tour base = new Tour();
        Population pop = new Population();
        base.setCities(availableCities);

        for (int i = 0; i < maxIterations; i++) {
            double crossover = configuration.randomGenerator.nextDouble(0.5, 0.8);
            double mutation = configuration.randomGenerator.nextDouble(0.001, 0.005);

            if (mutation <= mutationRatio) {
                displacementMutation.doMutation(base);
            }

            if (crossover <= crossoverRatio) {
                Tour mix = new Tour();
                mix.setCities(base.getCities());
                pop.setTourList(alternatingEdgesCrossover.doCrossover(base,mix));
                base.setCities(tournamentSelection.doSelection(pop).get(0).getCities());
            }
        }

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < base.getSize(); i++) {
            str.append(base.getCity(i));
            str.append(" -> ");
        }

        results = (TextArea) root.lookup("#result");
        results.setText(str.toString());
    }

    public void startupHSQLDB() {
        HSQLDBManager.instance.startup();
        HSQLDBManager.instance.init();
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

        String newFileName = fileName.substring(1,fileName.length()-1);
        BufferedReader br = new BufferedReader(new FileReader(newFileName));
        String s = "";

        while ((s = br.readLine()) != null)
            System.out.println(s);

        JSONParser parser = new JSONParser();
        try
        {
            Object object = parser
                    .parse(new FileReader(newFileName));

            //convert Object to JSONObject
            JSONObject jsonObject = (JSONObject)object;

            // Reading the JSON file
            String mutation = (String) jsonObject.get("mutation");
            String crossover = (String) jsonObject.get("crossover");
            double crossoverRatio = (double) jsonObject.get("crossoverRatio");
            String selection = (String) jsonObject.get("selection");
            double mutationRatio = (double) jsonObject.get("mutationRatio");
            String name = (String) jsonObject.get("name");
            long maximumNumberOfIterations = (long) jsonObject.get("maximumNumberOfIterations");

            return jsonObject;
        } catch(Exception fe)
        {
            fe.printStackTrace();
        }
        return null;
    }
}