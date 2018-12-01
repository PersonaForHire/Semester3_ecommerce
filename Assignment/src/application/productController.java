/**
 * Student number: 20079431
 * @author  Conor Askins
 * @version 1.0
 * @since   2018-11-11
 */
package application;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.*;

public class productController implements Initializable {

   private List<Product> items = new ArrayList<>();
   private List<Product> cart = new ArrayList<>();

    @FXML
    private TextArea productList,cartList,errBox;
    @FXML
    private TextField addToCart,removeCart;

    @FXML// adds the product to the cart
    public void addToCartBtn (){

        for (Product p: items) {//enhanced for loop:assigns u to users
            try {
                if (addToCart.getText().equals(p.getName())) {
                   cart.add(p);
                   cartList.setText(listCart());
                } else {
                    addToCart.clear();
                }
            } catch (NoSuchElementException e) {
                errBox.clear();
                errBox.setText("Please enter an exciting product");
            }

        }
    }

    @FXML//removes the product from cart
    public void removeFromCart()throws Exception{
        for (Iterator<Product> p = cart.iterator(); p.hasNext();) {
            if (removeCart.getText().equals(p.next().getName())) {
                p.remove();
                adminController.saveP();
                cartList.setText(listCart());
            }
        }
    }

    //loads products.xml
    private void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("products.xml"));
        items = (ArrayList<Product>) is.readObject();
        is.close();
    }

    //Utility methods

    private String listProducts() {
        if (items.size() > 0) {
            String listOfProducts = "";
            for (int i = 0; i < items.size(); i++) {
                listOfProducts += i + ": " + items.get(i) + "\n";
            }
            return listOfProducts;
        } else {
            return "";
        }
    }
    private String listCart(){
        if (cart.size() > 0) {
            String listOfContents = "";
            for (int i = 0; i < cart.size(); i++) {
                listOfContents += i + ": " + cart.get(i) + "\n";
            }
            return listOfContents;
        } else {
            return "";
        }
    }

    @Override// loads products.xml, displays the information into the TextAreas
    public void initialize(URL location, ResourceBundle resources) {
        try {
            load();
            productList.setText(listProducts());
            cartList.setText(listCart());
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
