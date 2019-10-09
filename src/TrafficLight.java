import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * TrafficLight object extends StackPane in order to obtain the functionality that StackPane container provides.
 * Traffic Light requires StackPane functionality in order to draw items onto top of each other and then allow it to be manipulated by other classes like a StackPane
 */
public class TrafficLight extends StackPane {

    private Rectangle box;
    private Circle redLight;
    private Circle yellowLight;
    private Circle greenLight;

    /**
     * Creates a TrafficLight based on the given measurement of height
     *
     * @param height integer corresponding to the size of the height of the traffic light
     */
    public TrafficLight(int height) {

        //Set width based on height divided by 2 to get a rectangle measurement for width
        int width = height / 2;

        //Initiate Values (colors for cosmetic purposes too).
        box = new Rectangle();
        box.setStroke(Color.BLACK);
        box.setFill(Color.GREY);

        redLight = new Circle();
        redLight.setStroke(Color.BLACK);
        redLight.setFill(Color.WHITE);

        yellowLight = new Circle();
        yellowLight.setStroke(Color.BLACK);
        yellowLight.setFill(Color.WHITE);

        greenLight = new Circle();
        greenLight.setStroke(Color.BLACK);
        greenLight.setFill(Color.WHITE);

        /**
         * Set circle radius to 15 percent of height of traffic light box so
         * it is always about (eye judgement) proportionate to the traffic light width and height given.
         */
        redLight.setRadius((height / 100) * 15);
        yellowLight.setRadius((height / 100) * 15);
        greenLight.setRadius((height / 100) * 15);
        /**
         * Prepare box size based on given paramters for constructor
         */
        box.setWidth(width);
        box.setHeight(height);
        /**
         * Create VBox to vertically stack the circles with some padding in between
         */
        VBox lights = new VBox(redLight, yellowLight, greenLight);
        /**
         * Create padding between the circles to simulate the space between the lights in real life.
         * Use percentage of 1 percent of the height as guidance of how much space there should be.
         */
        lights.setPadding(new Insets((height / 100) * 1, (height / 100) * 1, (height / 100) * 1, (height / 100) * 1));
        /**
         * Use 2 percent as spacing guidance of the height size.
         */
        lights.setSpacing((height / 100) * 2);
        /**
         * Position the lights in the center of the VBox node
         */
        lights.setAlignment(Pos.CENTER);

        /**
         * Add the lights and the box to the Trafficlight.
         */
        this.getChildren().addAll(box, lights);

    }

    /**
     * SetLight method sets the color of the circle fills based on the Light Enum value passed as argument
     * This is done so that the colors may be translated to different colors than red yellow or green, as other colors may
     * may look better on screen than the stock RED, YELLOW, GREEN.
     *
     * @param light
     */
    public void setLight(LightStates light) {
        switch (light) {
            case RED:
                //Set the lights so that only the red one is colored. (Opted to use Orange red color based on eye judgement)
                redLight.setFill(Color.ORANGERED);
                yellowLight.setFill(Color.WHITE);
                greenLight.setFill(Color.WHITE);
                break;
            case YELLOW:
                //Set the lights so that only the yellow one is colored. (Opted to use Orange color based on eye judgement)
                redLight.setFill(Color.WHITE);
                yellowLight.setFill(Color.ORANGE);
                greenLight.setFill(Color.WHITE);
                break;
            case GREEN:
                //Set the lights so that only the green one is colored. (Opted to use Lime Green color based on eye judgement)
                redLight.setFill(Color.WHITE);
                yellowLight.setFill(Color.WHITE);
                greenLight.setFill(Color.LIMEGREEN);
                break;
        }
    }

}

/**
 * LightStates enum used to store the three possible states of the traffic light.
 * Declared outside the class to be available publicly and statically.
 */
enum LightStates {
    RED,
    YELLOW,
    GREEN
}