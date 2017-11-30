import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ComboBox;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.sql.*;
import java.util.ArrayList;

public class scene_login {

	public static String email;
	public static String password;
	public static String usertype;

	/**
	 * @param window The Primary Stage of the Frontend GUI
	 * @return The Scene for Main Login Window
	 */
	public static Scene getScene(Stage window)
	{
        Session sx = Session.getInstance();
		GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setVgap(10);
        grid.setHgap(10);

        Label emailLabel = new Label("Email:");
        grid.add(emailLabel, 2, 0);
        TextField nameInput = new TextField();
        nameInput.setPromptText("enter your email");
        grid.add(nameInput, 3, 0);

        Label typeLabel = new Label("User Type:");
        grid.add(typeLabel, 2, 1);
        ComboBox<String> userType = new ComboBox<>();
        userType.getItems().addAll("Student","Faculty","Admin");
        userType.setPromptText("enter user type");
        grid.add(userType, 3, 1);

        Label passLabel = new Label("Password:");
        grid.add(passLabel, 2, 2);
        PasswordField passInput = new PasswordField();
        passInput.setPromptText("enter your password");
        grid.add(passInput, 3, 2);

        //Login
        Button loginButton = new Button("Log In");
        grid.add(loginButton, 3, 3);
        loginButton.setOnAction(e -> {
        	email= nameInput.getText();
        	password = passInput.getText();
        	usertype = userType.getValue();
            ArrayList<String> r = Database.authenticate(email, password, usertype);
            if(r == null) {
                scene_alertbox.display("Incorrect details", "Incorrect username/password", "try again");
                // System.out.println();
            }
            else {
                sx.email = r.get(0);
                sx.fname = r.get(1);
                sx.lname = r.get(2);
                sx.roll = r.get(3);
                sx.usertype = r.get(4);

                if(usertype.equals("Student"))
                    window.setScene(scene_StudentLogin.getScene(window));

                else if(usertype.equals("Admin"))
                        window.setScene(scene_AdminLogin.getScene(window));

                else if(usertype.equals("Faculty"))
                        window.setScene(scene_FacultyLogin.getScene(window));

            }

        });


        Button signupButton = new Button("Sign Up");
        grid.add(signupButton, 3, 4);
        signupButton.setOnAction( e -> window.setScene(signup_scene.getScene(window)));

        BorderPane pane = new BorderPane();
        pane.setCenter(grid);
        pane.setPadding(new Insets(20, 20, 20, 20));
        Text title = new Text("Classroom Management System : Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        pane.setTop(title);
        BorderPane.setAlignment(title,Pos.TOP_CENTER);

        return new Scene(pane, 550, 430);


	}
}
