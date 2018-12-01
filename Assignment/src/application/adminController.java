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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class adminController implements Initializable {

    //textFeilds to be used to capture user input
    @FXML
    private TextField Username, Password, uId, pname, desc, cost, pId, category, delChoice,updateChoice,updateChoice1,upName,updesc,upcost,upId,upCategory,upUsername,upPassword;
    //TextAreas for displaying information to the user
    @FXML
    private TextArea pros;
    @FXML
    private TextArea lookieUser;

    //lists to store the users and products that admin has created
    private List<user> users = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();


    //FUNCTIONS

    @FXML //changes the information in the product if the name is in the arraylist
    private void updateProducts()throws Exception{
        for (Iterator<Product> p = products.iterator(); p.hasNext();) {
            if (updateChoice.getText().equals(p.next().getName())) {
                p.next().setName(upName.getText());
                p.next().setCost(Double.parseDouble(upcost.getText()));
                p.next().setDesc(updesc.getText());
                p.next().setId(Integer.parseInt(upId.getText()));
                p.next().setCategory(upCategory.getText());
                saveP();
            }
        }
    }

    @FXML//deletes products if the name is in the arraylist
    private void deleteProducts ()throws Exception{
        for (Iterator<Product> p = products.iterator(); p.hasNext();) {
            if (delChoice.getText().equals(p.next().getName())) {
                p.remove();
                saveP();
            }
        }
    }

    @FXML//deletes user if the name is in the arraylist
    private void deleteUser() throws Exception{
        for (Iterator<user> p = users.iterator(); p.hasNext();) {
            if (delChoice.getText().equals(p.next().getUname())) {
                p.remove();
                saveP();
            }
        }
    }

    @FXML//changes the information in the user if the name is in the arraylist
    private void updateUser() throws Exception{
        for (Iterator<user> p = users.iterator(); p.hasNext();) {
            if (updateChoice1.getText().equals(p.next().getUname())) {
                p.next().setUname(upUsername.getText());
                p.next().setPword(upPassword.getText());
                p.next().setuId(Integer.parseInt(upId.getText()));
                saveU();
            }
        }
    }

    @FXML// returns to the previous scene
    private void handleBckBtn(ActionEvent event) throws Exception {
        Parent loginScreenParent = FXMLLoader.load(getClass().getResource("../fxmlPages/home.fxml"));
        Scene loginScreen = new Scene(loginScreenParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(loginScreen);
        stage.show();
    }

    @FXML// takes user input and adds a new user object to the array list
    private void handleUCreateBtn() throws Exception {
        String uname = Username.getText();
        String pword = Password.getText();
        int id = Integer.parseInt(uId.getText());
        users.add(new user(uname, pword, id, false));
        saveU();
        Username.clear();
        Password.clear();
        uId.clear();
    }

    @FXML// takes user input and adds a new product object to the array list
    private void handlePCreateBtn() throws Exception {
        add(new Product(pname.getText(), desc.getText(), category.getText(), Double.parseDouble(cost.getText()), Integer.parseInt(pId.getText())));
        saveP();
        pname.clear();
        desc.clear();
        category.clear();
        cost.clear();
        pId.clear();

    }

    //UTILITY METHODS///
    //lists product
    private String listProducts() {
        if (products.size() > 0) {
            String listOfProducts = "";
            for (int i = 0; i < products.size(); i++) {
                listOfProducts += i + ": " + products.get(i) + "\n";
            }
            return listOfProducts;
        } else {
            return "";
        }
    }
    //lists users
    private String listUsers() {
        if (users.size() > 0) {
            String listOfUsers = "";
            for (int i = 0; i < users.size(); i++) {
                listOfUsers += i + ": " + users.get(i) + "\n";
            }
            return listOfUsers;
        } else {
            return "";
        }
    }
    //adds products to the arraylist
    private void add(Product newItem) {
        products.add(newItem);
    }

    //saves arraylist to the products xml
    public static void saveP() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("products.xml"));
        out.writeObject(products);
        out.close();
    }
    //savez arraylist to teh user xml file
    private void saveU() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("user.xml"));
        out.writeObject(users);
        out.close();
    }

    //loads the products.xml
    private void loadp() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("products.xml"));
        products = (ArrayList<Product>) is.readObject();
        is.close();
    }
    //loads users.xml
    private void loadu() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("user.xml"));
        users = (ArrayList<user>) is.readObject();
        is.close();
    }

    @FXML
    //loads products.xml and users.xml,clears TextAreas and displays the new information in them
    public void display() throws Exception {
        loadp();
        loadu();
        pros.clear();
        lookieUser.clear();
        pros.setText(listProducts());
        lookieUser.setText(listUsers());
    }

    //on creation of the scene the display method is called
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            display();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}