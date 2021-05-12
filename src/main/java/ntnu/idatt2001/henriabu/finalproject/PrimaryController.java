package ntnu.idatt2001.henriabu.finalproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidPostalCodeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidTownException;

/**
 * The primary controller for the client
 */
public class PrimaryController {
    //The nodes used in the tableView
    @FXML private TableView<PostPlace> tableView;
    @FXML private TableColumn<PostPlace, String> postalCodeColoumn;
    @FXML private TableColumn<PostPlace, String> townColoumn;
    @FXML private TableColumn<PostPlace, String> communeCodeColoumn;
    @FXML private TableColumn<PostPlace, String> communeNameColoumn;
    @FXML private TableColumn<PostPlace, String> categoryColoumn;

    //The textField used to search
    @FXML private TextField searchByPCTextField;
    @FXML private TextField searchByTownTextField;

    //The buttons used for choosing a category
    @FXML private Button categoryGButton;
    @FXML private Button categoryFButton;
    @FXML private Button categoryBButton;
    @FXML private Button categoryPButton;
    @FXML private Button categorySButton;
    @FXML private Button categoryAllButton;

    //Creates a new instance of postPlaceRegister
    private PostPlaceRegister postPlaceRegister = new PostPlaceRegister();

    /**
     * The method that is called the when the application is launched.
     * Calls the different help methods.
     * @throws IOException
     * @throws InvalidPostalCodeException
     * @throws InvalidTownException
     */
    public void initialize() throws IOException, InvalidPostalCodeException, InvalidTownException {
        setCells();
        readFromFile();
        tableView.setItems((FXCollections.observableList(getPostalCodes())));
        categoryButtonSetup();
    }

    /**
     * Help method t
     */
    private void categoryButtonSetup(){
        setCategoryButtonsOnAction(categoryBButton, "B", "Both street adresses and post boxes");
        setCategoryButtonsOnAction(categoryFButton, "F", "Multiple areas of use");
        setCategoryButtonsOnAction(categoryGButton, "G", "Street addresses (and place addresses),\n meaning green " +
                "mail boxes");
        setCategoryButtonsOnAction(categoryPButton, "P", "PostBoxes");
        setCategoryButtonsOnAction(categorySButton, "S", "Service postal code (these aren't used for post addresses)");
        categoryAllButton.setOnAction(e -> viewAll());
        categoryAllButton.setTooltip(new Tooltip("Show all"));
        categoryAllButton.setOnAction(e -> {viewAll();
            searchByPCTextField.clear();
            searchByTownTextField.clear();
        });
    }

    /**
     * Help method to set the different category buttons on action, as well as setting their tooltip.
     * Uses the searchByCategory method from postPlaceRegister
     * @param button The button that is being set on action
     * @param category The
     * @param toolTip
     */
    private void setCategoryButtonsOnAction(Button button, String category, String toolTip){
        button.setOnAction(e -> {setTableViewValue(postPlaceRegister.searchByCategory(category));
            searchByPCTextField.clear();
            searchByTownTextField.clear();
        });
        button.setTooltip(new Tooltip(toolTip));
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
        getPostalCodes().sort(Comparator.comparingInt(s -> Integer.parseInt(s.getPostalCode())));
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
