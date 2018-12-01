
/**
 * This program implements an application that
 * does the following
 * Allows the user to register or login
 * if they
 * they login if the username and password are in the xml file
 *
 * if that user is admin they will be brought to the admin screen
 *  there they can create, delete update and view both products and users
 *
 * if they are an average user then they will be brought to the products screen
 * where they can type in the name of the product they want and add it to there cart.
 *
 * Student number: 20079431
 * @author  Conor Askins
 * @version 1.0
 * @since   2018-11-11
 */
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Main extends Application {
    ImageView imageView =new ImageView();

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxmlPages/home.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
        showImage();
    }

    // code source :
    // https://stackoverflow.com/questions/24260019/javafx-image-not-showing-in-stage
    //displays the image on the home screen

    public void showImage() {
        try {
            Image image = new Image("/images/ed4.png");
            imageView.setImage(image);
            imageView.setCache(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {

        launch(args);
    }

}
