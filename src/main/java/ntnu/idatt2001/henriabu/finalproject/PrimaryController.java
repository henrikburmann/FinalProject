package ntnu.idatt2001.henriabu.finalproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {
    @FXML private TableView<PostalCode> tableView;
    @FXML private TableColumn<PostalCode, String> postalCodeColoumn;
    @FXML private TableColumn<PostalCode, String> postOfficeColoumn;
    @FXML private TableColumn<PostalCode, String> communeCodeColoumn;
    @FXML private TableColumn<PostalCode, String> communeNameColoumn;
    @FXML private TableColumn<PostalCode, String> categoryColoumn;

    private PostalCodeRegister postalCodeRegister = new PostalCodeRegister();


    public void initialize() throws IOException {
        setCells();
        PostalCode p = new PostalCode("2040", "Kløfta", "3033", "Ullensaker",
                "G");
        postalCodeRegister.addPostalCode(p);
        readFromFile();
        tableView.setItems((FXCollections.observableList(getPostalCodes())));
        System.out.println(p.getPostalCode());
    }
    private void setCells(){
        postalCodeColoumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        postOfficeColoumn.setCellValueFactory(new PropertyValueFactory<>("postalOffice"));
        communeCodeColoumn.setCellValueFactory(new PropertyValueFactory<>("communeCode"));
        communeNameColoumn.setCellValueFactory(new PropertyValueFactory<>("communeName"));
        categoryColoumn.setCellValueFactory(new PropertyValueFactory<>("category"));
    }

    private ArrayList<PostalCode> getPostalCodes(){
        return postalCodeRegister.getRegister();
    }

    private void readFromFile() throws IOException {
        for (PostalCode p: FileHandler.readFromFile()){
            postalCodeRegister.addPostalCode(p);
        }
    }
    @FXML
    public void searchByPostalCode(){
        setTableViewValue(postalCodeRegister.searchByPostalCode("2040"));
    }

    private void setTableViewValue(List<PostalCode> list){
        tableView.setItems(FXCollections.observableList(list));
    }
}
