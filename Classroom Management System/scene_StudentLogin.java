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
import java.util.*;
import java.util.regex.Pattern;
/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 */
public class scene_StudentLogin {
	/**
	 * @param window The Primary Stage of the Frontend GUI
	 * @return The Scene for Main Student Login Window
	 */
	public static Scene getScene(Stage window) {
        VBox reqBox;
        Session sx = Session.getInstance();
	BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(20, 20, 20, 20));

        //pane.setCenter(grid);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        Text title = new Text("Classroom Management System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text welc = new Text("Student Login");
        welc.setFont(Font.font( Font.getDefault().getName(), FontWeight.BOLD, Font.getDefault().getSize()));

        vBox.getChildren().addAll(title,welc);

        VBox lBox = new VBox();
        lBox.setSpacing(10);
        lBox.setAlignment(Pos.CENTER);
        Text name = new Text(sx.fname);
        name.setFont(Font.font( Font.getDefault().getName(), FontWeight.MEDIUM, Font.getDefault().getSize()+1));

        Text roll = new Text(sx.roll);
        roll.setFont(Font.font( Font.getDefault().getName(), FontWeight.NORMAL, Font.getDefault().getSize()));

        Button backButton = new Button("Logout");
        backButton.setOnAction(e -> window.setScene(scene_login.getScene(window)));

        lBox.getChildren().addAll(name,roll,backButton);
        lBox.setAlignment(Pos.TOP_LEFT);
        pane.setTop(vBox);
        pane.setLeft(lBox);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(90, 30, 30, 30));
        grid.setVgap(10);
        grid.setHgap(10);

        Label search = new Label("Search:");
        grid.add(search, 0, 0);
        TextField searchInput = new TextField();
        searchInput.setPrefWidth(350);
        searchInput.setPromptText("search for courses based on post conditions");
        Button srch = new Button("Search");
        grid.add(srch, 2, 0);
        srch.setOnAction(e -> {
            String hints = searchInput.getText();
            // System.out.println(hints);
            ArrayList<course> prospective = new ArrayList<course>();
            for(HashMap.Entry<String, course> entry:sx.coursemap.entrySet()){
                course c1 = entry.getValue();
                String[] splt = hints.split(Pattern.quote(" "));
                int c = 0;
                for (String x : splt) {
                    if(c1.post_conditions.toLowerCase().contains(x.toLowerCase())) {
                        prospective.add(c1);
                        break;
                    }
                }
            }
            window.setScene(scene_searchCourse.getScene(window, prospective));
            // for (course c : prospective) {
            //     System.out.println(c.name);
            // }
        });
        grid.add(searchInput, 1, 0);

        Label room = new Label("View Booked Rooms/");
        grid.add(room, 0, 2);
        Label room1 = new Label("Room Availability");
        grid.add(room1, 0, 3);
        Button roomButton = new Button("View Rooms");
        grid.add(roomButton, 1, 3);
        roomButton.setOnAction(e -> window.setScene(scene_viewRoom.getScene(window)));

        Label request = new Label("Request for Room");
        grid.add(request, 0, 5);
        Button reqButton = new Button("Request");
        grid.add(reqButton, 1, 5);

        Label tt = new Label("View Personalised");
        grid.add(tt, 0, 7);
        grid.add(new Label("TimeTable"), 0, 8);
        Button ttButton = new Button("View");
        grid.add(ttButton, 1, 8);
        ttButton.setOnAction(e -> window.setScene(scene_timetable.getScene(window)));


        grid.add(new Label("Add Courses"), 0, 10);
        Button ttButton1 = new Button("Add Courses");
        grid.add(ttButton1, 1, 10);
        ttButton1.setOnAction(e -> window.setScene(scene_AddCourse.getScene(window)));

        pane.setCenter(grid);
        reqButton.setOnAction(e -> window.setScene(scene_reqRoom.getScene(window)));

        return new Scene(pane,800,460);
    }
}
