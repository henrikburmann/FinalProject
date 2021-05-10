package ntnu.idatt2001.henriabu.finalproject;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        PostalCodeClient.setRoot("primary");
    }
}