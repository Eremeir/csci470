/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 6           Summer 2023 *
 *                                                        *
 *  Class Name: Mile Redeemer Controller                  *
 *                                                        *
 *  Developer: Matt Borek                                 *
 *                                                        *
 *  Purpose: Calculate eligible destinations for an       *
 *           airline mileage points redemption app        *
 *           in a friendly graphical user interface.      *
 *                                                        *
 **********************************************************/
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;

//JavaFX Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;

public class MileRedeemerController
{
    //List of months for use in spinner and discount periods
    private final List<String> monthNames = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

    //FXML element fxids
    @FXML
    private ListView<Destination> destinationListView;

    @FXML
    private Button loadDestinations; 

    @FXML
    private TextField loadedFileName;

    @FXML
    private TextField discountPeriodField; 

    @FXML
    private TextField milesField; 

    @FXML
    private TextField superMilesField;  

    @FXML
    private TextField upgradeCostField; 

    @FXML
    private TextField userMilesField;

    @FXML
    private Spinner<Integer> monthSpinner;

    @FXML
    private Button redeemMiles;

    @FXML
    private ListView<String> responseListView;

    @FXML
    private TextField remainingField;

    @FXML //Event handler for load destination file button
    void loadButtonPressed(ActionEvent event)
    {
        loadFile();
    }

    @FXML //Event handler for redeem miles button
    void redeemButtonPressed(ActionEvent event)
    {
        String userMilesText = userMilesField.getText();
        if (userMilesText.isEmpty()) //Verify that input was provided
        {
            responseListView.getItems().clear();
            responseListView.getItems().add("*** Please enter the number of accumulated Frequent Flyer Miles ***");
            System.err.println("Error: empty input");
            remainingField.setText(""); //Reset remaining miles from previous command
            return;
        }

        int miles; 
        try
        {
            miles = Integer.parseInt(userMilesText); //Test that input can be interpreted as a usable number
        }
        catch (NumberFormatException e)
        {
            responseListView.getItems().clear();
            responseListView.getItems().add("*** Invalid input provided for accumulated Frequent Flyer Miles ***");
            System.err.println("Error: invalid input");
            remainingField.setText(""); //Reset remaining miles from previous command
            return;
        }

        if (mileRedeemer == null || mileRedeemer.getAlphaDest() == null || mileRedeemer.getAlphaDest().length == 0) //Test that destination file was valid and loaded destinations
        {
            responseListView.getItems().clear();
            responseListView.getItems().add("*** No available destinations, please load destinations file ***");
            System.err.println("Error: no destination loaded");
            remainingField.setText(""); //Reset remaining miles from previous command
            return;
        }

        String[] tickets = mileRedeemer.redeemMiles(miles, monthSpinner.getValue() + 1); //Redeem earned miles for specified month

        if (tickets.length == 0) //If no tickets were available
        {
            responseListView.getItems().clear();
            responseListView.getItems().add("*** Your client has not accumulated enough Frequent Flyer Miles ***");
        }
        else //Construct the list of tickets
        {
            ObservableList<String> observeTickets = FXCollections.observableArrayList(tickets);
            responseListView.setItems(observeTickets);
        }

        remainingField.setText(mileRedeemer.getRemainingMiles() + " Miles"); //Adjust remaining miles field
    }

    @FXML //Event handler for clicking a destination in the list
    void selectDest(MouseEvent event)
    {
        Destination selectedDest = destinationListView.getSelectionModel().getSelectedItem();
        if (selectedDest != null) //Adjust fields with data from selected destination once list item selected
        {
            milesField.setText(Integer.toString(selectedDest.getRegMiles()));  
            superMilesField.setText(Integer.toString(selectedDest.getSuperMiles()));
            upgradeCostField.setText(Integer.toString(selectedDest.getFirstClassUpgrade()));
            discountPeriodField.setText(monthNames.get(selectedDest.getStartMonth() - 1) + " - " + monthNames.get(selectedDest.getEndMonth() - 1));
        }
    }

    private MileRedeemer mileRedeemer; //Ensure the controller has a MileRedeemer object to use

    //Load selected destination file and send data to mile redeemer
    private void loadFile()
    {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select Destinations File");
        File file = fc.showOpenDialog(loadDestinations.getScene().getWindow());

        //Clear all fields for next load
        milesField.clear();
        superMilesField.clear();
        upgradeCostField.clear();
        discountPeriodField.clear();

        if (file != null)
        {
            try
            {
                Scanner fscnr = new Scanner(file);
                mileRedeemer = new MileRedeemer();
                mileRedeemer.readDestinations(fscnr);
                fscnr.close();
               
                //Construct list of destinations for list selector
                ObservableList<Destination> observeDestinations = FXCollections.observableArrayList(mileRedeemer.getAlphaDest());
                destinationListView.setItems(observeDestinations);

                loadedFileName.setText(file.getName()); //update filename field
            } 
            catch (IOException e) //file could not be read
            {
                destinationListView.getItems().clear();
                responseListView.getItems().clear();
                responseListView.getItems().add("*** Error reading file " + e.getMessage() + " ***");
                System.err.print("Error: invalid file" + e.getMessage());
                remainingField.setText("");
                return;
            }
        }
    }

    //Initialize controller class and setup spinner and list selector elements
    public void initialize()
    {
        //Setup destination list selector to display cities
        destinationListView.setCellFactory(listView -> new ListCell<>()
        {
            @Override
            protected void updateItem(Destination destination, boolean empty)
            {
                super.updateItem(destination, empty);
                if (empty || destination == null)
                {
                    setText(null);
                }
                else
                {
                    setText(destination.getCity());
                }
            }
        });

        //Setup month spinner to accept input and format it with month names instead of numbers
        SpinnerValueFactory<Integer> monthValueSet = new SpinnerValueFactory.ListSpinnerValueFactory<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        monthSpinner.setValueFactory(monthValueSet);
        monthSpinner.getEditor().setText(monthNames.get(0));

        monthValueSet.setConverter(new StringConverter<>() //convert methods between month names and backend integer representation
        {
            @Override
            public String toString(Integer i)
            {
                return monthNames.get(i);
            }

            @Override
            public Integer fromString(String monthName)
            {
                return monthNames.indexOf(monthName);
            }
        }); 
    }
}
