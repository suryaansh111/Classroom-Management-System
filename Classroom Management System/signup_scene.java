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
/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 */
public class signup_scene {
	/**
	 * @param window The Primary Stage of the Frontend GUI
	 * @return The Scene for SignUp of ccount by a User
	 */
	public static Scene getScene(Stage window)
	{
			GridPane signupGrid = new GridPane();
			signupGrid.setPadding(new Insets(30, 30, 30, 30));
			signupGrid.setVgap(10);
			signupGrid.setHgap(10);

			Label signupEmailLabel = new Label("Email:");
			signupGrid.add(signupEmailLabel, 2, 0);
			TextField signupNameInput = new TextField();
			signupNameInput.setPromptText("enter your email");
			signupGrid.add(signupNameInput, 3, 0);

			Label signupfName = new Label("First Name:");
			signupGrid.add(signupfName, 2, 1);
			TextField signupfNameInput = new TextField();
			signupfNameInput.setPromptText("enter first name");
			signupGrid.add(signupfNameInput, 3, 1);

			Label signuplName = new Label("Last Name:");
			signupGrid.add(signuplName, 2, 2);
			TextField signuplNameInput = new TextField();
			signuplNameInput.setPromptText("enter last email");
			signupGrid.add(signuplNameInput, 3, 2);


			Label signupRoll = new Label("Roll/Employee No.:");
			signupGrid.add(signupRoll, 2, 3);
			TextField signupRollInput = new TextField();
			signupRollInput.setPromptText("enter roll number");
			signupGrid.add(signupRollInput, 3, 3);

			Label singupTypeLabel = new Label("User Type:");
			signupGrid.add(singupTypeLabel, 2, 4);
			ComboBox<String> signupUserType = new ComboBox<>();
			signupUserType.getItems().addAll("Student","Faculty","Admin");
			signupUserType.setPromptText("enter user type");
			signupGrid.add(signupUserType, 3, 4);

			Label signupPassLabel = new Label("Password:");
			signupGrid.add(signupPassLabel, 2, 5);
			PasswordField signupPassInput = new PasswordField();
			signupPassInput.setPromptText("enter your password");
			signupGrid.add(signupPassInput, 3, 5);

			Label signupPassLabelC = new Label("Confirm Password:");
			signupGrid.add(signupPassLabelC, 2, 6);
			PasswordField signupPassInputC = new PasswordField();
			signupPassInputC.setPromptText("confirm your password");
			signupGrid.add(signupPassInputC, 3, 6);
			//Login
			Button confirmButton = new Button("Create Account");
			signupGrid.add(confirmButton, 3, 7);
			confirmButton.setOnAction(e -> {
				String fname = signupfNameInput.getText(), lname = signuplNameInput.getText(), email = signupNameInput.getText();
				String rno = signupRollInput.getText();
				String password = signupPassInput.getText();
				String password2 = signupPassInputC.getText();
				String type = signupUserType.getValue();

				if(password.equals(password2) && !email.endsWith("@iiitd.ac.in")) {
					int rst = Database.add(email, fname, lname, rno, type, password);
					if(rst > 0)
						scene_alertbox.display("Register", "Registered successfully", "Exit");
					else if(rst < 0)
						scene_alertbox.display("Register", "Email exits", "Retry");
					else
						scene_alertbox.display("Register", "Error", "Retry");
				}
				else {
					scene_alertbox.display("Register", "Incorrect login details", "Retry");
				}
			});

			Button backButton = new Button("Login Page");
			signupGrid.add(backButton, 3, 8);
			backButton.setOnAction(e -> window.setScene(scene_login.getScene(window)));

			BorderPane pane1 = new BorderPane();
			pane1.setCenter(signupGrid);
			pane1.setPadding(new Insets(20, 20, 20, 20));
			Text title1 = new Text("Classroom Management System : Sign-Up");
			title1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			pane1.setTop(title1);
			BorderPane.setAlignment(title1,Pos.TOP_CENTER);

			return new Scene(pane1, 550, 430);
		}
	}

