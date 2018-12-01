/**
 * Student number: 20079431
 * @author  Conor Askins
 * @version 1.0
 * @since   2018-11-11
 */
package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class regController {

    @FXML
    private TextField Username;
    @FXML
    private TextField Password;

    private ArrayList<user> users = new ArrayList<>();

    @FXML//brings you to the prevoius page
    private void handleBckBtn(ActionEvent event) throws Exception{
        Parent loginScreenParent = FXMLLoader.load(getClass().getResource("../fxmlPages/home.fxml"));
        Scene loginScreen = new Scene(loginScreenParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(loginScreen);
        stage.show();
    }

    @FXML
    // creates random id and adds the user, saves, and brings you back to the home page
    private void handleRegBtn(ActionEvent event) throws Exception {
        int id = 1 + (int) (Math.random() * ((200 - 1) + 1));

        add(new user(Username.getText(), Password.getText(), id, false));
        save();

        Parent homeScreenParent = FXMLLoader.load(getClass().getResource("../fxmlPages/home.fxml"));
        Scene homeScreen = new Scene(homeScreenParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(homeScreen);
        stage.show();
    }
    //adds users
    public void add(user newUser) {
        users.add(newUser);
    }

    // gotten from a previous project
    //saves arraylist to user.xml
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("user.xml"));
        out.writeObject(users);
        out.close();
    }


}
