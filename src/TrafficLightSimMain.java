import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * @Author Danny Falero
 * Traffic Light Simulator
 * This system allows a user to simulate a working traffic light obtaining visual feedback using
 * the JavaFX framework.
 * Extends Application as a requirement of JAVAFX
 */
public class TrafficLightSimMain extends Application {

    //Best personal viewing minimum recommended size is 200
    public static final int sizing = 300;

    //Not Required by the program but used by older IDEs to launch the program.
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start method gets called and is required to be Overridden as part of extending Application
     *
     * @param primaryStage the primary stage to be shown (primary window)
     * @throws Exception throws exception in the case of error.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Set window title, for cosmetic purposes
        primaryStage.setTitle("Traffic Light Simulator");

        //Label just for cosmetic purposes.
        Label programName = new Label("Traffic Light Simulator");
        //Set font and size for cosmetic purposes
        programName.setFont(new Font("Arial", 24));

        //Create a TrafficLight object with the set height according to the constant sizing
        TrafficLight trafficLight = new TrafficLight(sizing);

        try {
            //Prepare a trafficLightController object passing a trafficLight initiated object to the constructor
            TrafficLightController trafficLightController = new TrafficLightController(trafficLight);

            //Prepare a new borderPane (initiate)
            BorderPane borderPane = new BorderPane();

            //Set the top of the borderPane to the label containing the text for the programs descriptive name , positioned in center
            borderPane.setTop(programName);
            borderPane.setAlignment(programName, Pos.CENTER);

            //Set the center of the borderPane to the trafficLight VBox subclass object and position it in the center
            borderPane.setCenter(trafficLight);
            borderPane.setAlignment(trafficLight, Pos.CENTER);

            //Set the bottom of the borderPane to the trafficLightController panel (HBox subclass) and position it in center
            borderPane.setBottom(trafficLightController);
            borderPane.setAlignment(trafficLightController, Pos.CENTER);

            //Set the root container and the initial sizing of the scene to the constant "sizing" times estimated eye judgement of 1.3 for width and height.
            Scene scene = new Scene(borderPane, sizing * 1.3, sizing * 1.3);

            //Bind the height of the borderPane to the edge of the scene (so it will grow and shrink as the user resizes the window)
            borderPane.prefHeightProperty().bind(scene.heightProperty());
            //Bind the width of the borderPane to the edge of the scene (so it will grow and shrink as the user resizes the window)
            borderPane.prefWidthProperty().bind(scene.widthProperty());

            //Set the scene which the primaryStage will show. (window will show)
            primaryStage.setScene(scene);

                /*
                    Set the minimum size for the window based on the preferred sizes set for the borderPane container.
                    This makes sure that the borderPane will always be visible to the user, no matter how small they try
                    to make the window.
                */
            primaryStage.setMinWidth(borderPane.getPrefWidth());
            primaryStage.setMinHeight(borderPane.getPrefHeight());

            //Show the window once all scene, containers and nodes have been added
            primaryStage.show();
        } catch (NullPointerException ex) {
            //Catch error and show alert if the traffic light was not initiated before passing to controller. Stop execution and quit gracefully.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Traffic Light was not drawn in the correct order." +
                    "\nClick ok for the program to exit.");
            alert.showAndWait();
            System.exit(0);
        }
    }

}