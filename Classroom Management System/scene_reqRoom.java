import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 */
public class scene_reqRoom {
	/**
	 * @param window The Primary Stage of the Frontend GUI
	 * @return The Scene for Requesting a Room
	 */
	public static Scene getScene(Stage window)
	{
        Session sx = Session.getInstance();

		//SIDEPANES
		BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(20, 20, 20, 20));

        //pane.setCenter(grid);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        Text title = new Text("Classroom Management System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Button backButton = new Button("Back");
        backButton.setAlignment(Pos.TOP_LEFT);
        backButton.setOnAction(e -> window.setScene(scene_StudentLogin.getScene(window)));

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

        // Button backButton = new Button("Logout");
        // backButton.setOnAction(e -> window.setScene(scene_login.getScene(window)));

        lBox.getChildren().addAll(name,roll, backButton);
        lBox.setAlignment(Pos.TOP_LEFT);
        pane.setTop(vBox);
        pane.setLeft(lBox);


		//--------------SIDEPANES DONE

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(0, 30, 30, 30));
        grid.setVgap(10);
        grid.setHgap(10);

        ColumnConstraints cc = new ColumnConstraints();
  	  	cc.setPercentWidth(30);
  	  	grid.getColumnConstraints().add(cc);

  	  	ColumnConstraints cc1 = new ColumnConstraints();
  	  	cc1.setPercentWidth(70);
  	  	grid.getColumnConstraints().add(cc1);

        Text reqtitle = new Text("Request for Room");
	    reqtitle.setFont(Font.font( Font.getDefault().getName(), FontWeight.MEDIUM, Font.getDefault().getSize()+1));
        grid.add(reqtitle, 0, 0);

        Label date1 = new Label("Date:");
        ComboBox<String> date = new ComboBox<>();
        date.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31");
        date.setPromptText("date");

        Label month1 = new Label("Month:");
        ComboBox<String> month = new ComboBox<>();
        month.getItems().addAll("January","Februrary","March","April","May","June","July","August","September","October","November","December");
        month.setPromptText("month");

        Label year1 = new Label("Year:");
        ComboBox<Integer> year = new ComboBox<>();
        year.getItems().addAll(2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022,2023,2024,2025);
        year.setPromptText("year");

        Label day1 = new Label("Day:");
        ComboBox<String> day = new ComboBox<>();
        day.getItems().addAll("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");
        day.setPromptText("day");

        grid.add(date1, 0, 1);
        grid.add(date, 1, 1);
        grid.add(month1, 0, 2);
        grid.add(month, 1, 2);
        grid.add(year1, 0, 3);
        grid.add(year, 1, 3);
        grid.add(day1, 0, 4);
        grid.add(day, 1, 4);
        Label roomname = new Label("Room Name:");
        TextField roomInput = new TextField();
        roomInput.setPrefWidth(70);
        roomInput.setPromptText("enter room name");
        grid.add(roomname, 0, 5);
        grid.add(roomInput, 1, 5);

        Label from = new Label("From Time:");
        TextField fromInput = new TextField();
        fromInput.setPrefWidth(70);
        fromInput.setPromptText("HH:MM (24 Hr)");
        grid.add(from, 0, 6);
        grid.add(fromInput, 1, 6);

        Label to = new Label("To Time:");
        TextField toInput = new TextField();
        toInput.setPrefWidth(70);
        toInput.setPromptText("HH:MM (24 Hr)");
        grid.add(to, 0, 7);
        grid.add(toInput, 1, 7);

        Label purpose = new Label("Purpose:");
        TextField purposeInput = new TextField();
        purposeInput.setMinWidth(150);
        purposeInput.setMinHeight(70);
        purposeInput.setPromptText("mention purpose of the booking");
        purposeInput.setAlignment(Pos.TOP_LEFT);
        grid.add(purpose, 0, 8);
        grid.add(purposeInput, 1, 8);

        Button loginButton = new Button("Send Request");
        loginButton.setMaxWidth(150);
        loginButton.setAlignment(Pos.TOP_CENTER);
        grid.add(loginButton, 1, 10);
        loginButton.setOnAction(e -> {
        	request temp = new request(sx.email, sx.usertype, date.getValue(), month.getValue(), year.getValue()+"", day.getValue(), fromInput.getText(), toInput.getText(), purposeInput.getText(), roomInput.getText());
        	int status = Database.addReq(temp);
            if(status > 0)
            {
                scene_alertbox.display("Request", "Request sent", "Exit");
                window.setScene(scene_reqRoom.getScene(window));
            }

            else
                scene_alertbox.display("Request", "Request Error", "Retry");
        });

	    pane.setCenter(grid);
	    return new Scene(pane,800,550);
	    }
}
