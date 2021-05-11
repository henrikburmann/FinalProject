package ntnu.idatt2001.henriabu.finalproject;

import javafx.scene.control.Alert;

public class GUIFactory {


    public static void createError(String headerText, String reason){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(reason);
        alert.showAndWait();
    }

    public static void aboutAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Title");
        alert.setHeaderText("Postal Code Register\nv 1.0-SNAPSHOT");
        alert.setContentText("An application created by Henrik Burmann");
        alert.showAndWait();
    }
}
