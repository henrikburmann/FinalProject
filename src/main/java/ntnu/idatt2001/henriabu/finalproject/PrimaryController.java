package ntnu.idatt2001.henriabu.finalproject;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {
    @FXML private TableView<PostalCode> tableView;
    @FXML private TableColumn<PostalCode, String> codeColoumn;
    @FXML private TableColumn<PostalCode, String> communeColoumn;

    private PostalCodeRegister postalCodeRegister = new PostalCodeRegister();


    public void initialize(){
        setCells();
        PostalCode p = new PostalCode("2040", "Kløfta");
        postalCodeRegister.addPostalCode(p);
        tableView.setItems(getPostalCodes());
    }
    private void setCells(){
        codeColoumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        communeColoumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private ObservableList<PostalCode> getPostalCodes(){
        return postalCodeRegister.getRegister();
    }
}
