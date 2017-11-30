import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.*;
import java.util.ArrayList;
import javafx.geometry.*;
/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 */
public class scene_viewRoomDetails {

    /**
     * @param roomName The Name of the Room whose Bokoing Details are to be found
     * The Function generates a Window Displaying the Booking Details of the Room
     */
    public static void display(String roomName) {
        Stage window = new Stage();
        Session sx = Session.getInstance();
        ArrayList<roomdetail> timeslots = sx.roommap.get(roomName);
        // sx.test();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(roomName + " | Details");
        window.setMinWidth(800);
        window.setMinHeight(500);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(0,20,20,20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.TOP_CENTER);

     //    Label lprim = new Label("Primary Bookings");
    	// lprim.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    	// grid.add(lprim, 0, 0);
        Label l = new Label("Course");
    	l.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    	grid.add(l, 0, 0);
    	Label l1 = new Label("Day");
    	l1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    	grid.add(l1, 1, 0);
    	Label l2 = new Label("From");
    	l2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    	grid.add(l2, 2, 0);
    	Label l3 = new Label("To");
    	l3.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    	grid.add(l3, 3, 0);

        int i;
        for(i=0;i<timeslots.size();i++)
        {
            grid.add(new Label(timeslots.get(i).mycourse.name), 0, i+1);
        	grid.add(new Label(timeslots.get(i).mytime.day), 1, i+1);
            grid.add(new Label(timeslots.get(i).mytime.from), 2, i+1);
            grid.add(new Label(timeslots.get(i).mytime.to), 3, i+1);
        }
        i++;
     //    Label lextra = new Label("Extra Bookings");
     //    lextra.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    	// grid.add(lextra, 0, i);
     //    Label f = new Label("Purpose");
    	// f.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    	// grid.add(f, 1, i);
    	// Label f1 = new Label("Day");
    	// f1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    	// grid.add(f1, 2, i);
    	// Label f2 = new Label("From");
    	// f2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    	// grid.add(f2, 3, i);
    	// Label f3 = new Label("To");
    	// f3.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    	// grid.add(f3, 4, i);
    	// Label f4 = new Label("Date");
    	// f4.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    	// grid.add(f4, 5, i);
    	// i++;
    	// ArrayList<request> extra = sx.extramap.get(roomName);
     //    if(extra != null)
     //    {
     //        for(int j=i;j<extra.size();j++)
     //        {
     //            grid.add(new Label(extra.get(j).purpose), 1, i+1);
     //            grid.add(new Label(extra.get(j).day), 2, i+1);
     //            grid.add(new Label(extra.get(j).from), 3, i+1);
     //            grid.add(new Label(extra.get(j).to), 4, i+1);
     //            grid.add(new Label(extra.get(j).date + " " + extra.get(j).month + " " + extra.get(j).year), 5, i+1);
     //            i++;
     //        }
     //    }


        Button closeButton = new Button("Close");

        closeButton.setOnAction(e -> {
        	window.close();
        	});

        VBox layout = new VBox(10);
        layout.getChildren().addAll(grid, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
