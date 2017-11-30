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

/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 */
public class scene_searchCourse {
	/**
	 * @param window The Primary Stage of the Frontend GUI
	 * @return The Scene for Searching a Course
	 */
	public static Scene getScene(Stage window, ArrayList<course> mycourses)
	{

		BorderPane pane = new BorderPane();
                Session sx = Session.getInstance();
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

        Button backButton1 = new Button("Back");
        backButton1.setAlignment(Pos.TOP_LEFT);
        backButton1.setOnAction(e -> window.setScene(scene_StudentLogin.getScene(window)));

        lBox.getChildren().addAll(name,roll,backButton1,backButton);
        lBox.setAlignment(Pos.TOP_LEFT);
        pane.setTop(vBox);
        pane.setLeft(lBox);

        TableView<course> table = new TableView<>();
        table.setMaxHeight(400);
        table.setMaxWidth(800);

        //Code column
        TableColumn<course, String> codeColumn = new TableColumn<>("Code");
        codeColumn.setMinWidth(200);
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

        //Name column
        TableColumn<course, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Type column
        TableColumn<course, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(200);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setSortType(TableColumn.SortType.ASCENDING);
        //Instructor column
        TableColumn<course, String> instructorColumn = new TableColumn<>("Instructor");
        instructorColumn.setMinWidth(200);
        instructorColumn.setCellValueFactory(new PropertyValueFactory<>("instructor"));

        //Credit column
        TableColumn<course, Integer> creditColumn = new TableColumn<>("Credits");
        creditColumn.setMinWidth(200);
        creditColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
                ObservableList<course> courseSelected, allcourses;
                allcourses = table.getItems();
                courseSelected = table.getSelectionModel().getSelectedItems();
                course tmp = table.getSelectionModel().getSelectedItem();
                int status = Database.addCourse(sx.email, tmp.name);
                if(status == -2)
                        scene_alertbox.display("Course error", "Course clashes with current courses", "try again");
                else if(status == -1)
                        scene_alertbox.display("Course error", "Max. allowed courses exceeded", "try again");
                else if(status > 0) {
                        scene_alertbox.display("Course", "Course added", "Close");
                        courseSelected.forEach(allcourses::remove);
                }
        });

        table.setItems(getcourse(mycourses));
        table.getColumns().addAll(codeColumn,nameColumn,typeColumn,instructorColumn,creditColumn);
        table.getSortOrder().addAll(typeColumn,nameColumn);
//        HashMap<String, course> coursemap = csvreader.getCourseMap();
//        for(HashMap.Entry<String, course> entry:coursemap.entrySet()){
//        	table.getItems().add(entry.getValue());
//        }

        Button backButton11 = new Button("Done");
        backButton11.setAlignment(Pos.TOP_LEFT);
        backButton11.setOnAction(e -> window.setScene(scene_StudentLogin.getScene(window)));

        Button viewButton = new Button("View Details");
        viewButton.setOnAction(e -> {
        	course temp = table.getSelectionModel().getSelectedItem();
        	scene_courseView.display(temp);});

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(backButton11,addButton, viewButton);
        hBox.setAlignment(Pos.TOP_CENTER);

        VBox tablebox = new VBox();
        tablebox.setPadding(new Insets(20,10,10,10));
        tablebox.getChildren().addAll(table, hBox);
        tablebox.setAlignment(Pos.TOP_CENTER);

        pane.setCenter(tablebox);

        return new Scene(pane,1000,600);
	}

	public static ObservableList<course> getcourse(ArrayList<course> mycourses){
        ObservableList<course> courses = FXCollections.observableArrayList();
        for(int i=0;i<mycourses.size();i++)
        	courses.add(mycourses.get(i));
        return courses;
	}
}

