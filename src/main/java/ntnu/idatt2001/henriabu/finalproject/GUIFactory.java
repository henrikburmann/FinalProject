package ntnu.idatt2001.henriabu.finalproject;

import javafx.scene.control.Alert;

/**
 * A GUI factory that isn't really a GUI factory since fxml is used for most of the application.
 * The alert boxes being used is created here to minimize the amount of code in PrimaryController
 */
public class GUIFactory {

    /**
     * Method to create error alerts
     * @param headerText What the error is
     * @param reason    Why it is an error
     */
    public static void createError(String headerText, String reason){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(reason);
        alert.showAndWait();
    }

    /**
     * Method for the about box when clicking help -> about
     */
    public static void aboutAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Title");
        alert.setHeaderText("Postal Code Register\nv 1.0-SNAPSHOT");
        alert.setContentText("An application created by Henrik Burmann");
        alert.showAndWait();
    }
}
