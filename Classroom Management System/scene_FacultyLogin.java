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
public class scene_FacultyLogin {
	/**
	 * @param window The Primary Stage of the Frontend GUI
	 * @return The Scene for Main Faculty Login Window
	 */
	public static Scene getScene(Stage window)
	{

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(20, 20, 20, 20));

        //pane.setCenter(grid);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        Text title = new Text("Classroom Management System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text welc = new Text("Faculty Login");
        welc.setFont(Font.font( Font.getDefault().getName(), FontWeight.BOLD, Font.getDefault().getSize()));

        vBox.getChildren().addAll(title,welc);

        VBox lBox = new VBox();
        lBox.setSpacing(10);
        lBox.setAlignment(Pos.CENTER);
        Text name = new Text("Faculty Name");
        name.setFont(Font.font( Font.getDefault().getName(), FontWeight.MEDIUM, Font.getDefault().getSize()+1));

        Button backButton = new Button("Logout");
        backButton.setOnAction(e -> window.setScene(scene_login.getScene(window)));

        lBox.getChildren().addAll(name,backButton);
        lBox.setAlignment(Pos.TOP_LEFT);
        pane.setTop(vBox);
        pane.setLeft(lBox);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(50, 30, 30, 30));
        grid.setVgap(10);
        grid.setHgap(10);

        Label room = new Label("View Booked Rooms/");
        grid.add(room, 0, 2);
        Label room1 = new Label("Room Availability");
        grid.add(room1, 0, 3);
        Button roomButton = new Button("View Rooms");
        grid.add(roomButton, 1, 3);
        roomButton.setOnAction(e -> window.setScene(scene_viewRoom.getScene(window)));

        Label request = new Label("Cancel Room Booking");
        grid.add(request, 0, 5);
        Button reqButton = new Button("Cancel Booking");
        grid.add(reqButton, 1, 5);
        reqButton.setOnAction(e -> window.setScene(scene_cancelbooking.getScene(window)));

        Label book = new Label("Book a Room");
        grid.add(book, 0, 7);
        Button bookButton = new Button("Book");
        grid.add(bookButton, 1, 7);
        bookButton.setOnAction(e -> window.setScene(scene_bookroom.getScene(window)));

        pane.setCenter(grid);

        return new Scene(pane,800,430);
	}
}
