/**
 * Student number: 20079431
 * @author  Conor Askins
 * @version 1.0
 * @since   2018-11-11
 */
package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;


public class homeController {
   @FXML//brings you to the login page
   private void handleLoginButtonAction(ActionEvent event) throws IOException{
       Parent loginScreenParent = FXMLLoader.load(getClass().getResource("../fxmlPages/login.fxml"));
       Scene loginScreen = new Scene(loginScreenParent);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.setScene(loginScreen);
       stage.show();
   }

    @FXML// brings you to the registration page
    private void handleRegButtonAction(ActionEvent event) throws IOException{
        Parent regScreenParent = FXMLLoader.load(getClass().getResource("../fxmlPages/registration.fxml"));
        Scene regScreen = new Scene(regScreenParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(regScreen);
        stage.show();
    }
}