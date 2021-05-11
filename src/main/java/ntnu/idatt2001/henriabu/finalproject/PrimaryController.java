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
import ntnu.idatt2001.henriabu.finalproject.exceptions.NoSuchPostalCodeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.NoSuchPostalOfficeException;

public class PrimaryController {
    @FXML private TableView<PostalCode> tableView;
    @FXML private TableColumn<PostalCode, String> postalCodeColoumn;
    @FXML private TableColumn<PostalCode, String> postOfficeColoumn;
    @FXML private TableColumn<PostalCode, String> communeCodeColoumn;
    @FXML private TableColumn<PostalCode, String> communeNameColoumn;
    @FXML private TableColumn<PostalCode, String> categoryColoumn;

    @FXML private Button searchByPCButton;
    @FXML private Button searchByPOButton;
    @FXML private TextField searchByPCTextField;
    @FXML private TextField searchByPOTextField;

    private PostalCodeRegister postalCodeRegister = new PostalCodeRegister();


    public void initialize() throws IOException {
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

    private void readFromFile() throws IOException {
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
        catch (NoSuchPostalCodeException e){
            GUIFactory.createError("", "There is no post office with this postal code");
        }
        catch (IllegalArgumentException e){
            GUIFactory.createError("", "No postal code is longer than 4 digits");
        }
        tableView.refresh();
    }

    @FXML
    public void searchByPostalOffice() throws NoSuchPostalOfficeException {
        String postalOffice = searchByPOTextField.getText();
        setTableViewValue(postalCodeRegister.searchByPostOffice(postalOffice));
        searchByPCTextField.clear();
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
