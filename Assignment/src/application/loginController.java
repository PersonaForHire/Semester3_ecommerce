/**
 * Student number: 20079431
 * @author  Conor Askins
 * @version 1.0
 * @since   2018-11-11
 */
package application;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.Parent;


public class loginController implements Initializable{
    @FXML
    private TextField Username,Password;
    private ArrayList<user> users =new ArrayList<>();
    @FXML
    private void handleBckBtn(ActionEvent event) throws Exception{
        Parent loginScreenParent = FXMLLoader.load(getClass().getResource("../fxmlPages/home.fxml"));
        Scene loginScreen = new Scene(loginScreenParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(loginScreen);
        stage.show();
    }

    @FXML// when the login button is clicked the username and password are compared to the ones in the arraylist and is they are there they will be brought to the appropriate page
   private void handleLoginButtonAction(ActionEvent event) throws IOException{

        searchFunction(Username.getText(),Password.getText(),event);
    }
    //the username and password are compared to the ones in the arraylist and is they are there they will be brought to the appropriate page
    private void searchFunction(String username,String password ,ActionEvent event)//refactoring code from an old project rom last year. I gave it to many people last year so others might have it also
    {
        for (user u : users) {//enhanced for loop:assigns u to users
            try {
                if (u.getUname().equals(username) && u.getPword().equals(password)) {
                    if(u.isAdmin()){
                        Parent productsScreenParent = FXMLLoader.load(getClass().getResource("../fxmlPages/admin.fxml"));
                        Scene productScreen = new Scene(productsScreenParent);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(productScreen);
                        stage.show();
                    }else {
                        Parent productsScreenParent = FXMLLoader.load(getClass().getResource("../fxmlPages/product.fxml"));
                        Scene productScreen = new Scene(productsScreenParent);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(productScreen);
                        stage.show();
                    }
                } else {
                    Username.clear();
                    Password.clear();
                }
            } catch (Exception e) {
                System.out.println("Error searching for user: " + e);
            }

        }
    }
    //loads users.xml
    public void load() throws Exception{
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("user.xml"));
        users = (ArrayList<user>) is.readObject();
        is.close();
    }

    @Override //on creation the stage will load users.xml
    public void initialize(URL location, ResourceBundle resources) {
      try {
          load();
      }catch (Exception e){
          System.out.println(e);
      }
    }

}
