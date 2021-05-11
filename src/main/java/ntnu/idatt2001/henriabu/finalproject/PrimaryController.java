package ntnu.idatt2001.henriabu.finalproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidPostalCodeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidTownException;

public class PrimaryController {
    @FXML private TableView<PostPlace> tableView;
    @FXML private TableColumn<PostPlace, String> postalCodeColoumn;
    @FXML private TableColumn<PostPlace, String> townColoumn;
    @FXML private TableColumn<PostPlace, String> communeCodeColoumn;
    @FXML private TableColumn<PostPlace, String> communeNameColoumn;
    @FXML private TableColumn<PostPlace, String> categoryColoumn;

    @FXML private TextField searchByPCTextField;
    @FXML private TextField searchByTownTextField;

    private PostPlaceRegister postPlaceRegister = new PostPlaceRegister();


    public void initialize() throws IOException, InvalidPostalCodeException, InvalidTownException {
        setCells();
        readFromFile();
        tableView.setItems((FXCollections.observableList(getPostalCodes())));
    }
    private void setCells(){
        postalCodeColoumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        townColoumn.setCellValueFactory(new PropertyValueFactory<>("town"));
        communeCodeColoumn.setCellValueFactory(new PropertyValueFactory<>("communeCode"));
        communeNameColoumn.setCellValueFactory(new PropertyValueFactory<>("communeName"));
        categoryColoumn.setCellValueFactory(new PropertyValueFactory<>("category"));
    }

    private ArrayList<PostPlace> getPostalCodes(){
        return postPlaceRegister.getRegister();
    }

    private void readFromFile() throws IOException, InvalidPostalCodeException, InvalidTownException {
        for (PostPlace p: FileHandler.readFromFile()){
            postPlaceRegister.addPostPlace(p);
        }
    }

    @FXML
    public void searchByPostalCode() {
        try{
        String code = searchByPCTextField.getText();
        setTableViewValue(postPlaceRegister.searchByPostalCode(code));
        searchByTownTextField.clear();}
        catch (InvalidPostalCodeException e){
            GUIFactory.createError("Invalid postal code", "Postal codes cannot contain letters " +
                    "and are 4 digits long");
        }

    }

    @FXML
    public void searchByTown(){
        try{
        String postalOffice = searchByTownTextField.getText();
        setTableViewValue(postPlaceRegister.searchByTown(postalOffice));
        searchByPCTextField.clear();
        tableView.refresh();}
        catch (InvalidTownException e){
            GUIFactory.createError("Invalid town", "Town can only contain " +
                    "letters, white spaces\nand dashes");
        }
    }
    @FXML
    public void viewAll(){
        setTableViewValue(getPostalCodes());
        searchByPCTextField.clear();
        searchByTownTextField.clear();
    }

    public void viewAbout(){
        GUIFactory.aboutAlert();
    }

    private void setTableViewValue(List<PostPlace> list){
        tableView.setItems(FXCollections.observableList(list));
    }

    public void exitProgram(){
        Platform.exit();
    }

}
