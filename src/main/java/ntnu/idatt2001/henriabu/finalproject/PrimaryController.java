package ntnu.idatt2001.henriabu.finalproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
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

    @FXML private MenuButton categoryMenuButton;
    

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
        String code = searchByPCTextField.getText();
        try{
        setTableViewValue(postPlaceRegister.searchByPostalCode(code));
        searchByTownTextField.clear();}
        catch (InvalidPostalCodeException e){
            GUIFactory.createError("Invalid postal code", "Postal codes cannot contain letters " +
                    "and are 4 digits long");
            searchByPCTextField.deleteText(code.length()-1, code.length());
        }

    }

    @FXML
    public void searchByTown(){
        String town = searchByTownTextField.getText();
        try{
        setTableViewValue(postPlaceRegister.searchByTown(town));
        searchByPCTextField.clear();
        tableView.refresh();}
        catch (InvalidTownException e){
            GUIFactory.createError("Invalid town",
                    "Town can only contain " +
                    "letters, white spaces\nand dashes");
            searchByTownTextField.deleteText(town.length()-1, town.length());
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
