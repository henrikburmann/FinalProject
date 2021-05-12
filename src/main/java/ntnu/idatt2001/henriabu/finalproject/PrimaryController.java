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

    //The textFields used to search
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
     * Help method that uses the help method setCategoryButtonOnAction() to set the buttons on action
     */
    private void categoryButtonSetup(){
        setCategoryButtonsOnAction(categoryBButton, "B", "Both street adresses and post boxes");
        setCategoryButtonsOnAction(categoryFButton, "F", "Multiple areas of use");
        setCategoryButtonsOnAction(categoryGButton, "G", "Street addresses (and place addresses),\n meaning green " +
                "mail boxes");
        setCategoryButtonsOnAction(categoryPButton, "P", "PostBoxes");
        setCategoryButtonsOnAction(categorySButton, "S", "Service postal code (these aren't used for post addresses)");
        //Since the "allButton" isn't using the searchByCategory() method we code it's onAction here.
        categoryAllButton.setTooltip(new Tooltip("Show all"));
        categoryAllButton.setOnAction(e -> {viewAll();
            searchByPCTextField.clear();
            searchByTownTextField.clear();
        });
    }

    /**
     * Help method to set the different category buttons on action, as well as setting their tooltip.
     * Uses the searchByCategory method from postPlaceRegister to require the list.
     * Also clears the textFields as to not confuse the user
     * @param button The button that is being set on action
     * @param category The category belonging to the button
     * @param toolTip  The information about the category
     */
    private void setCategoryButtonsOnAction(Button button, String category, String toolTip){
        button.setOnAction(e -> {setTableViewValue(postPlaceRegister.searchByCategory(category));
            searchByPCTextField.clear();
            searchByTownTextField.clear();
        });
        button.setTooltip(new Tooltip(toolTip));
    }

    /**
     * Help method to set the property value for the table coloumns
     */
    private void setCells(){
        postalCodeColoumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        townColoumn.setCellValueFactory(new PropertyValueFactory<>("town"));
        communeCodeColoumn.setCellValueFactory(new PropertyValueFactory<>("communeCode"));
        communeNameColoumn.setCellValueFactory(new PropertyValueFactory<>("communeName"));
        categoryColoumn.setCellValueFactory(new PropertyValueFactory<>("category"));
    }

    /**
     * Help method to get the register of the the postPlaces.
     * Don't feel it is necessary to sort the objects since the user has the option to sort in tableView and in the file
      the postal codes are listed ascending order.
     * The exception is of course when searching for towns, but that is handled in PostPlaceRegister
     * @return
     */
    private ArrayList<PostPlace> getPostalCodes(){
        return postPlaceRegister.getRegister();
    }

    /**
     * Help method that uses that static readFromFile method in FileHandler and adds all the objects to the
     * postPlaceRegister
     * @throws IOException
     * @throws InvalidPostalCodeException
     * @throws InvalidTownException
     */
    private void readFromFile() throws IOException, InvalidPostalCodeException, InvalidTownException {
        for (PostPlace p: FileHandler.readFromFile()){
            postPlaceRegister.addPostPlace(p);
        }
    }

    /**
     * Method to search by postal codes. Uses the searchByPostalCode() method from postPlaceRegister
     * The method is called every time a key is typed in searchByPCTextField and tableView therefore updates as the
     * user types.
     */
    @FXML
    public void searchByPostalCode() {
        //Sets string code equal to what is in searchByPCTextField
        String code = searchByPCTextField.getText();
        try{
            //Uses the helpMethod setTableValue to set the tableViewValue to the list from the searchByPostalCode method
        setTableViewValue(postPlaceRegister.searchByPostalCode(code));
        searchByTownTextField.clear();}//Clears the other textField to not confuse the user
        catch (InvalidPostalCodeException e){
            //If an invalid postalCode is passed an error box shows
            GUIFactory.createError("Invalid postal code", "Postal codes cannot contain letters " +
                    "and are 4 digits long");
            //Since InvalidPostalCodeException only is thrown when an invalid character is typed we remove the last
            // character from the textField after the error box is shown
            searchByPCTextField.deleteText(code.length()-1, code.length());
        }

    }

    /**
     * Method to search by town. Uses the searchByTown() method from postPlaceRegister
     * The method is called every time a key is typed in searchByTownTextField and tableView therefore updates as the
     * user types.
     */
    @FXML
    public void searchByTown(){
        //Sets string town equal to what is in searchByTownTextField
        String town = searchByTownTextField.getText();
        try{
        //Uses the helpMethod setTableValue to set the tableViewValue to the list from the searchByTown method
        setTableViewValue(postPlaceRegister.searchByTown(town));
        searchByPCTextField.clear();//Clears the other textField to not confuse the user
        tableView.refresh();}
        catch (InvalidTownException e){
            //If an invalid town is passed an error box shows
            GUIFactory.createError("Invalid town",
                    "Town can only contain " +
                    "letters, white spaces\nand dashes");
            //Since InvalidTownException only is thrown when an invalid character is typed we remove the last
            // character from the textField after the error box is shown
            searchByTownTextField.deleteText(town.length()-1, town.length());
        }
    }

    /**
     * Method to view all postPlaces. Clears the textFields to not confuse the user.
     */
    @FXML
    public void viewAll(){
        setTableViewValue(getPostalCodes());
        searchByPCTextField.clear();
        searchByTownTextField.clear();
    }

    /**
     * The method that calls the static method aboutAlert()
     */
    public void viewAbout(){
        GUIFactory.aboutAlert();
    }

    /**
     * Help method to set the tableView value with a list. Since tableView demands an observableList the list is cast
     * to an observableList
     * @param list The list that is being set as the the tableView value
     */
    private void setTableViewValue(List<PostPlace> list){
        tableView.setItems(FXCollections.observableList(list));
    }

    /**
     * Method to close the application
     */
    public void exitProgram(){
        Platform.exit();
    }

}
