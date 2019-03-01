package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class Gui extends Application {

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
        primaryStage.setResizable(false);
        primaryStage.setTitle("Drag Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public VBox createDragAndDropLabel() {
        Label temp = new Label("Drag ´n Drop JSON here");
        temp.setTranslateY(70);
        temp.setTranslateX(450);
        temp.setTextFill(Color.web("#FFFFFF"));

        Label dropped = new Label("");

        VBox dragTarget = new VBox();
        dragTarget.getChildren().addAll(temp, dropped);
        dragTarget.setOnDragOver(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != dragTarget
                        && event.getDragboard().hasFiles()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });

        dragTarget.setOnDragDropped(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    //dropped.setText(db.getFiles().toString());
                    // file path: db.getFiles().toString());
                    // todo: do something with dropped file
                    // open JSON fILE

                    try {
                        File file = new File(db.getFiles().toString());

                        JSONObject jsonObject = getJSONfile(db.getFiles().toString());

                    } catch (IOException e) {
                        System.out.println(e);
                    }

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
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
            Double crossoverRatio = (Double) jsonObject.get("crossoverRatio");
            String selection = (String) jsonObject.get("selection");
            Double mutationRatio = (Double) jsonObject.get("mutationRatio");
            String name = (String) jsonObject.get("name");
            long maximumNumberOfIterations = (long) jsonObject.get("maximumNumberOfIterations");

            return jsonObject;
        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}