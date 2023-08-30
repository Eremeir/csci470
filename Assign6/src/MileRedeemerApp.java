/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 6           Summer 2023 *
 *                                                        *
 *  Developer: Matt Borek                                 *
 *                                                        *
 *  Due Date: August 10th, 2023                           *
 *                                                        *
 *  Purpose: Calculate eligible destinations for an       *
 *           airline mileage points redemption app        *
 *           in a friendly graphical user interface.      *
 *                                                        *
 **********************************************************/
import java.io.IOException;

//JavaFX Imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MileRedeemerApp extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("MileRedeemer.fxml")); //load elements from FXML source file

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Mile Redeemer"); //set application window title
        stage.show();
    }

    //Main subroutine to open application window
    public static void main(String[] args) throws IOException 
    {
        //create MileRedeemer object and start it
        launch(args);
    }
}
