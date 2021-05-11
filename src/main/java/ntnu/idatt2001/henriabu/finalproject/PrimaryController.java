package ntnu.idatt2001.henriabu.finalproject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidPostalCodeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidPostalOfficeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.NoSuchPostalCodeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.NoSuchPostalOfficeException;

public class PrimaryController {
    @FXML private TableView<PostalCode> tableView;
    @FXML private TableColumn<PostalCode, String> postalCodeColoumn;
    @FXML private TableColumn<PostalCode, String> postOfficeColoumn;
    @FXML private TableColumn<PostalCode, String> communeCodeColoumn;
    @FXML private TableColumn<PostalCode, String> communeNameColoumn;
    @FXML private TableColumn<PostalCode, String> categoryColoumn;

    @FXML private TextField searchByPCTextField;
    @FXML private TextField searchByPOTextField;

    private PostalCodeRegister postalCodeRegister = new PostalCodeRegister();


    public void initialize() throws IOException, InvalidPostalCodeException, InvalidPostalOfficeException {
        setCells();
        readFromFile();
        tableView.setItems((FXCollections.observableList(getPostalCodes())));
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

    private void readFromFile() throws IOException, InvalidPostalCodeException, InvalidPostalOfficeException {
        for (PostalCode p: FileHandler.readFromFile()){
            postalCodeRegister.addPostalCode(p);
        }
    }

    @FXML
    public void searchByPostalCode() {
        try{
        String code = searchByPCTextField.getText();
        setTableViewValue(postalCodeRegister.searchByPostalCode(code));
        searchByPOTextField.clear();}
        catch (InvalidPostalCodeException e){
            GUIFactory.createError("Invalid postal code", "Postal codes cannot contain letters " +
                    "and are 4 digits long");
        }

    }

    @FXML
    public void searchByPostalOffice(){
        try{
        String postalOffice = searchByPOTextField.getText();
        setTableViewValue(postalCodeRegister.searchByPostOffice(postalOffice));
        searchByPCTextField.clear();
        tableView.refresh();}
        catch (InvalidPostalOfficeException e){
            GUIFactory.createError("Invalid postal office", "Postal office can only contain " +
                    "letters, white spaces\nand dashes");
        }
    }
    @FXML
    public void viewAll(){
        setTableViewValue(getPostalCodes());
        searchByPCTextField.clear();
        searchByPOTextField.clear();
    }

    public void viewAbout(){
        GUIFactory.aboutAlert();
    }

    private void setTableViewValue(List<PostalCode> list){
        tableView.setItems(FXCollections.observableList(list));
    }

    public void exitProgram(){
        Platform.exit();
    }

}
