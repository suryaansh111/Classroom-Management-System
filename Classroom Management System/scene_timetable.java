import java.util.ArrayList;
import java.util.regex.Pattern;

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
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 */
public class scene_timetable {
	
	/**
	 * @param window The Primary Stage of the Frontend GUI
	 * @return The Scene for Viewinf the Persinalised Timetable
	 */
	public static Scene getScene(Stage window)
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

        Text note = new Text("Note-: Classes held at Multiple Locations Appear in Bold");
        note.setFont(Font.font( Font.getDefault().getName(), FontWeight.LIGHT, Font.getDefault().getSize()-2));



//        VBox lBox = new VBox();
//        lBox.setSpacing(10);
//        lBox.setAlignment(Pos.CENTER);
//        Text name = new Text("Student Name");
//        name.setFont(Font.font( Font.getDefault().getName(), FontWeight.MEDIUM, Font.getDefault().getSize()+1));
//
//
//
//
//        Text roll = new Text("Student Roll Number");
//        roll.setFont(Font.font( Font.getDefault().getName(), FontWeight.NORMAL, Font.getDefault().getSize()));

        Button backButton = new Button("Back");
        backButton.setAlignment(Pos.TOP_LEFT);
        backButton.setOnAction(e -> window.setScene(scene_StudentLogin.getScene(window)));

        Button LogoutButton = new Button("Logout");
        LogoutButton.setAlignment(Pos.TOP_RIGHT);
        LogoutButton.setOnAction(e -> window.setScene(scene_login.getScene(window)));

        HBox h = new HBox();
        h.setSpacing(10);
        h.setMaxWidth(Double.MAX_VALUE);
        h.getChildren().addAll(backButton);

        vBox.getChildren().addAll(title,welc,note,h);
//        lBox.getChildren().addAll(name,roll,backButton,LogoutButton);
//        lBox.setAlignment(Pos.TOP_LEFT);
        pane.setTop(vBox);
        //pane.setLeft(lBox);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(90, 0, 0, 0));
        grid.setVgap(0);
        grid.setHgap(0);
        grid.setGridLinesVisible(true);

        for (int j = 0; j < 20; ++j) {

        	  ColumnConstraints cc = new ColumnConstraints();
        	  cc.setPercentWidth(100/20);
        	  grid.getColumnConstraints().add(cc);
        	}

        for (int j = 0; j < 6; ++j) {

      	  RowConstraints cc = new RowConstraints();
      	  cc.setPercentHeight(100/6);
      	  grid.getRowConstraints().add(cc);
      	}

        int c = 8;
        int a = 0;
        for(int i=1;i<=19;i++)
        {
        	String str= new String();
        	if(a%2==0)
        	{
        		str="   "+c+":00 -\n "+c+":30";
        		a+=1;
        	}
        	else
        	{
        		str="   "+c+":30 -\n "+(++c)+":00";
        		a+=1;
        	}
        	Label l = new Label(str);
        	l.setTextAlignment(TextAlignment.CENTER);
        	grid.add(l, i, 0);
        }
        String[] str = {"MON","TUE","WED","THUR","FRI"};
        for(int i=1;i<=5;i++)
        {

        	Label l = new Label(str[i-1]);
        	l.setAlignment(Pos.CENTER);
        	grid.add(l, 0, i);
        }

        ArrayList<String> mycourses = Database.myCourse();


        	for(int i=0;i<mycourses.size();i++)
        	{
        		String tt = sx.coursemap.get(mycourses.get(i)).code;
        		ArrayList<timeslot> classTime= sx.coursemap.get(mycourses.get(i)).classTime;
        		ArrayList<timeslot> tutTime = sx.coursemap.get(mycourses.get(i)).tutTime;
        		ArrayList<timeslot> labTime = sx.coursemap.get(mycourses.get(i)).labTime;
        		for(int j=0;j<classTime.size();j++)
        		{
        			int row=0;
        			switch(classTime.get(j).day)
        			{
        			case "Monday": row=1; break;
        			case "Tuesday": row=2; break;
        			case "Wednesday": row=3; break;
        			case "Thursday": row=4; break;
        			case "Friday": row=5; break;

        			}
        			int fromHour = Integer.parseInt(classTime.get(j).from.split(Pattern.quote(":"))[0]);
        			int fromMin = Integer.parseInt(classTime.get(j).from.split(Pattern.quote(":"))[1]);

        			int toHour = Integer.parseInt(classTime.get(j).to.split(Pattern.quote(":"))[0]);
        			int toMin = Integer.parseInt(classTime.get(j).to.split(Pattern.quote(":"))[1]);

        			int len = (((toHour-fromHour)*60)+(toMin-fromMin))/30;

        			int col = 2*(fromHour-8)+1 + (fromMin/30);
//        			System.out.println(tt+" lecture "+row+" "+col+" "+classTime.get(j).from+" "+classTime.get(j).to +" "+ classTime.get(j).day+ " " + len);
        			for(int k=0;k<len;k++)
        				grid.add(new Label("  "+tt), col+k, row);
        		}
        		for(int j=0;j<tutTime.size();j++)
        		{
        			int row=0;
        			switch(tutTime.get(j).day)
        			{
        			case "Monday": row=1; break;
        			case "Tuesday": row=2; break;
        			case "Wednesday": row=3; break;
        			case "Thursday": row=4; break;
        			case "Friday": row=5; break;

        			}
        			int fromHour = Integer.parseInt(tutTime.get(j).from.split(Pattern.quote(":"))[0]);
        			int fromMin = Integer.parseInt(tutTime.get(j).from.split(Pattern.quote(":"))[1]);

        			int toHour = Integer.parseInt(tutTime.get(j).to.split(Pattern.quote(":"))[0]);
        			int toMin = Integer.parseInt(tutTime.get(j).to.split(Pattern.quote(":"))[1]);

        			int len = (((toHour-fromHour)*60)+(toMin-fromMin))/30;

        			int col = 2*(fromHour-8)+1 + (fromMin/30);
//        			System.out.println(tt+" tut "+row+" "+col+" "+tutTime.get(j).from+" "+tutTime.get(j).to +" "+tutTime.get(j).day+ " " + len);

        			for(int k=0;k<len;k++)
        				grid.add(new Label("  "+tt), col+k, row);
        		}
        		for(int j=0;j<labTime.size();j++)
        		{
        			int row=0;
        			switch(labTime.get(j).day)
        			{
        			case "Monday": row=1; break;
        			case "Tuesday": row=2; break;
        			case "Wednesday": row=3; break;
        			case "Thursday": row=4; break;
        			case "Friday": row=5; break;

        			}
        			int fromHour = Integer.parseInt(labTime.get(j).from.split(Pattern.quote(":"))[0]);
        			int fromMin = Integer.parseInt(labTime.get(j).from.split(Pattern.quote(":"))[1]);

        			int toHour = Integer.parseInt(labTime.get(j).to.split(Pattern.quote(":"))[0]);
        			int toMin = Integer.parseInt(labTime.get(j).to.split(Pattern.quote(":"))[1]);

        			int len = (((toHour-fromHour)*60)+(toMin-fromMin))/30;

        			int col = 2*(fromHour-8)+1 + (fromMin/30);
//        			System.out.println(tt+" lab "+row+" "+col+" "+labTime.get(j).from+" "+labTime.get(j).to + " "+ labTime.get(j).day + " " + len);

        			for(int k=0;k<len;k++)
        				grid.add(new Label("  "+tt), col+k, row);
        		}
        	}


        pane.setCenter(grid);

        Scene S = new Scene(pane,1400,600);
        return S;
	}
}
