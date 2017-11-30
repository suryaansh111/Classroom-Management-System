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
public class scene_viewrequest {
	/**
	 * @param window The Primary Stage of the Frontend GUI
	 * @return The Scene for Viewing of Request by an Admin
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
        backButton1.setOnAction(e -> {if(sx.usertype.equals("Student"))
                        window.setScene(scene_StudentLogin.getScene(window));
                else if(sx.usertype.equals("Admin"))
                        window.setScene(scene_AdminLogin.getScene(window));
                else if(sx.usertype.equals("Faculty"))
                        window.setScene(scene_FacultyLogin.getScene(window));});

        lBox.getChildren().addAll(name,roll,backButton1);//,backButton);
        lBox.setAlignment(Pos.TOP_LEFT);
        pane.setTop(vBox);
        pane.setLeft(lBox);

        TableView<request> table = new TableView<>();
        table.setMaxHeight(400);
        table.setMaxWidth(800);

        //Type column
        TableColumn<request, String> typeColumn = new TableColumn<>("User Type");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("senderType"));

        //Room Name column
        TableColumn<request, String> roomColumn = new TableColumn<>("Room");
        roomColumn.setMinWidth(60);
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomName"));

        //date column
        TableColumn<request, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(60);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        //month column
        TableColumn<request, String> monthColumn = new TableColumn<>("Month");
        monthColumn.setMinWidth(60);
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));

        //year column
        TableColumn<request, String> yearColumn = new TableColumn<>("Year");
        yearColumn.setMinWidth(60);
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        //From column
        TableColumn<request, String> fromColumn = new TableColumn<>("From");
        fromColumn.setMinWidth(50);
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));

        //To column
        TableColumn<request, String> toColumn = new TableColumn<>("To");
        toColumn.setMinWidth(50);
        toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));

        // Purpose Column
        TableColumn<request, String> purposeColumn = new TableColumn<>("Pupose of Booking");
        purposeColumn.setMinWidth(200);
        purposeColumn.setCellValueFactory(new PropertyValueFactory<>("purpose"));

        Button delButton = new Button("Delete Request");
        delButton.setOnAction(e -> {
            ObservableList<request> courseSelected, allcourses;
            allcourses = table.getItems();
            courseSelected = table.getSelectionModel().getSelectedItems();
            request r = table.getSelectionModel().getSelectedItem();
            Database.deleteReq(r);
            courseSelected.forEach(allcourses::remove);
        });

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
               ObservableList<request> courseSelected, allcourses;

               allcourses = table.getItems();
               courseSelected = table.getSelectionModel().getSelectedItems();
               request r = table.getSelectionModel().getSelectedItem();
               request temp  = sx.check(r);
               // System.out.println(temp);

               if(temp==null) {
                   courseSelected.forEach(allcourses::remove);
                   scene_alertbox.display("Request", "Unsuccessfull request addition", "Close");
                   Database.deleteReq(r);
                   // generate unsuccessfull booked alert box
                   // delete from database and arraylist

               }
               else {
                    courseSelected.forEach(allcourses::remove);
                    scene_alertbox.display("Request", "Successfull request addition Room no: " + temp.getRoomName(), "Close");
                    Database.addRoom(temp);
                    if(sx.extramap.containsKey(temp.getRoomName()))
                    {
                        sx.extramap.get(temp.getRoomName()).add(temp);
                    }
                    else
                    {
                        ArrayList<request> tempVal = new ArrayList<request>();
                        tempVal.add(temp);
                        sx.extramap.put(temp.getRoomName(), tempVal);
                    }
                    Database.deleteReq(r);
                   // generate successful alert box
                   // delete req from database
                   // add to extraroom hashmap
                }
       });


        table.setItems(getrequest());
        table.getColumns().addAll(typeColumn,roomColumn,dateColumn,monthColumn,yearColumn,fromColumn,toColumn,purposeColumn);
        //table.getSortOrder().addAll(typeColumn,roomColumn,dateColumn,monthColumn,yearColumn,fromColumn,toColumn,purposeColumn);
//        HashMap<String, course> coursemap = csvreader.getCourseMap();
//        for(HashMap.Entry<String, course> entry:coursemap.entrySet()){
//        	table.getItems().add(entry.getValue());
//        }

        Button backButton11 = new Button("Check");
        backButton11.setOnAction(e -> {
               request r = table.getSelectionModel().getSelectedItem();
               request temp  = sx.check(r);
               if(temp==null) {
                    // scene_alertbox.display("Check", "Room not Available!", "Close");
                    scene_alertbox.display("Check", "Room not Available!", "Close");
                    //System.out.println("check");
               }
               else {
                    scene_alertbox.display("Check", "Room Available: " + temp.getRoomName(), "Close");
                    //System.out.println("room avial");
                    //popup of temp.roomname();
               }
        });


        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(addButton, delButton,backButton11);
        hBox.setAlignment(Pos.TOP_CENTER);

        VBox tablebox = new VBox();
        tablebox.setPadding(new Insets(20,10,10,10));
        tablebox.getChildren().addAll(table, hBox);
        tablebox.setAlignment(Pos.TOP_CENTER);

        pane.setCenter(tablebox);

        return new Scene(pane,1000,600);
	}

	public static ObservableList<request> getrequest(){
        Session sx = Session.getInstance();
        ObservableList<request> myrequests = FXCollections.observableArrayList();
        for(int i=0;i<sx.requestlist.size();i++)
        {
        	myrequests.add(sx.requestlist.get(i));
        }
        return myrequests;
	}
}

