import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ComboBox;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 * The Login Class is the main Running Class of the Classroom Management System
 * Application Opens with the Login Page
 */
public class login extends Application{

	Stage window;
	Scene scene, signupScene;
	public static void main(String[] args) {
        Session s = Session.getInstance();
        launch(args);
	}

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Classroom Management System");
        scene = scene_login.getScene(window);
        window.setScene(scene);
        window.show();

    }

}
