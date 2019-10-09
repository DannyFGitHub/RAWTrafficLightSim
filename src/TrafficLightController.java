import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 * TrafficLightController is a VBox container with radiobuttons in a radiobutton toggle group, that controls
 * a given TrafficLight object given when constructed.
 */
public class TrafficLightController extends HBox {

    private TrafficLight trafficLight;

    //final as once it is created it shouldn't be changed.
    private final ToggleGroup radioButtonGroup = new ToggleGroup();

    private RadioButton redLightRadio;
    private RadioButton yellowLightRadio;
    private RadioButton greenLightRadio;

    /**
     * Creates a TrafficLightController object to control a traffic light based on user input.
     *
     * @param trafficLight Trafficlight object required to be manipulated by this controller
     */
    public TrafficLightController(TrafficLight trafficLight) throws NullPointerException {

        //if traffic light controller is created without the traffic light being created first, then show error. Order is important
        if (trafficLight == null) {
            throw new NullPointerException("TrafficLight object given has not been initiated. Please initiate the TrafficLight object first.");
        }

        this.trafficLight = trafficLight;

        redLightRadio = new RadioButton("Red");
        redLightRadio.setToggleGroup(radioButtonGroup);

        yellowLightRadio = new RadioButton("Yellow");
        yellowLightRadio.setToggleGroup(radioButtonGroup);

        greenLightRadio = new RadioButton("Green");
        greenLightRadio.setToggleGroup(radioButtonGroup);

        //Lambda Expression to set up a ChangeListener on the radioButtonGroup Toggle in order to trigger method call on changes.
        radioButtonGroup.selectedToggleProperty().addListener((observableValue, oldToggle, newToggle) -> {
            //If the selected item is not null (double checking that there is truly a selection.
            if (radioButtonGroup.getSelectedToggle() != null) {
                //Convert the selected radiobutton label to a all caps string in order to match it to a Lights Enum value.
                String selectedColor = ((RadioButton) newToggle).getText().toUpperCase();
                //Set the selectedColor to a Lights Enum and call the TrafficLight object's method setLight to set the light passing enum value of the desired light.
                trafficLight.setLight(LightStates.valueOf(selectedColor));
            }
        });

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(20);
        this.getChildren().addAll(redLightRadio, yellowLightRadio, greenLightRadio);
        this.setAlignment(Pos.CENTER);
    }
}
