import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.*;
import java.io.*;

/// CHANGE DETIALS TO SESSION DETAILS
/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 */
public class scene_viewRoom {
	/**
	 * @param window The Primary Stage of the Frontend GUI
	 * @return The Scene for Viewing the Details of the Room
	 */
	public static Scene getScene(Stage window)
	{
                Session sx = Session.getInstance();
		BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(20, 20, 20, 20));

        //pane.setCenter(grid);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        Text title = new Text("Classroom Management System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text welc = new Text(sx.usertype+" Login");
        welc.setFont(Font.font( Font.getDefault().getName(), FontWeight.BOLD, Font.getDefault().getSize()));

        vBox.getChildren().addAll(title,welc);

        VBox lBox = new VBox();
        lBox.setSpacing(10);
        lBox.setAlignment(Pos.CENTER);
        Text name = new Text(sx.fname);
        name.setFont(Font.font( Font.getDefault().getName(), FontWeight.MEDIUM, Font.getDefault().getSize()+1));

        Text roll = new Text(sx.roll);
        roll.setFont(Font.font( Font.getDefault().getName(), FontWeight.NORMAL, Font.getDefault().getSize()));

        // Button backButton = new Button("Logout");
        // backButton.setOnAction(e -> window.setScene(scene_login.getScene(window)));

        Button backButton1 = new Button("Back");
        backButton1.setAlignment(Pos.TOP_LEFT);
        backButton1.setOnAction(e -> {

                if(sx.usertype.equals("Student"))
                        window.setScene(scene_StudentLogin.getScene(window));
                else if(sx.usertype.equals("Admin"))
                        window.setScene(scene_AdminLogin.getScene(window));
                else if(sx.usertype.equals("Faculty"))
                        window.setScene(scene_FacultyLogin.getScene(window));
        });

        lBox.getChildren().addAll(name,roll,backButton1);//,backButton);
        lBox.setAlignment(Pos.TOP_LEFT);
        pane.setTop(vBox);
        pane.setLeft(lBox);

        TableView<timeslot> table = new TableView<>();
        table.setMaxHeight(300);
        table.setMaxWidth(500);

        //Room column
        TableColumn<timeslot, String> roomColumn = new TableColumn<>("Room Name");
        roomColumn.setMinWidth(500);
        roomColumn.setStyle("-fx-alignment: CENTER;");
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("venue"));
        roomColumn.setSortType(TableColumn.SortType.ASCENDING);


//        Button addButton = new Button("Add");
//        addButton.setOnAction(e -> {
//        ObservableList<course> courseSelected, allcourses;
//        allcourses = table.getItems();
//        courseSelected = table.getSelectionModel().getSelectedItems();
//        courseSelected.forEach(allcourses::remove);});

        table.setItems(getrooms());
        table.getColumns().addAll(roomColumn);
        table.getSortOrder().add(roomColumn);
//        HashMap<String, course> coursemap = csvreader.getCourseMap();
//        for(HashMap.Entry<String, course> entry:coursemap.entrySet()){
//        	table.getItems().add(entry.getValue());
//        }

        Button backButton11 = new Button("Done");
        backButton11.setAlignment(Pos.TOP_LEFT);
        backButton11.setOnAction(e ->{

                if(sx.usertype.equals("Student"))
                        window.setScene(scene_StudentLogin.getScene(window));
                else if(sx.usertype.equals("Admin"))
                        window.setScene(scene_AdminLogin.getScene(window));
                else if(sx.usertype.equals("Faculty"))
                        window.setScene(scene_FacultyLogin.getScene(window));
        });

        Button viewButton = new Button("View Details");
        viewButton.setOnAction(e -> {
        	timeslot temp = table.getSelectionModel().getSelectedItem();
        	scene_viewRoomDetails.display(temp.venue);});

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(viewButton,backButton11 );
        hBox.setAlignment(Pos.TOP_CENTER);

        VBox tablebox = new VBox();
        tablebox.setPadding(new Insets(20,10,10,10));
        tablebox.getChildren().addAll(table, hBox);
        tablebox.setAlignment(Pos.TOP_CENTER);

        pane.setCenter(tablebox);

        return new Scene(pane,1000,600);
	}

	public static ObservableList<timeslot> getrooms(){
        ObservableList<timeslot> rooms = FXCollections.observableArrayList();
        Session sx = Session.getInstance();
        HashMap<String, ArrayList<roomdetail>> roommap = sx.roommap;
        for(HashMap.Entry<String, ArrayList<roomdetail>> entry:roommap.entrySet()){
        	rooms.add(entry.getValue().get(0).mytime);
        }
        return rooms;
	}
}
