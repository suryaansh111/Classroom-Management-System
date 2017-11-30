import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
/**
 * @author Suryaansh Mata
 * @author Osaid Rehman
 * 
 */
public class scene_courseView {
	
    /**
     * @param C The Course for which Details are required
     */
    public static void display(course C) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(C.name + " | Details");
        window.setMinWidth(550);
        window.setMinHeight(500);
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(0,20,20,20));
        grid.setVgap(1);
        grid.setHgap(2);        
        grid.setAlignment(Pos.TOP_CENTER);
        
        
        grid.add(new Label("Name"), 0, 0);
        grid.add(new Label("Code"), 0, 1);
        grid.add(new Label("Type"), 0, 2);
        grid.add(new Label("Instructor"), 0, 3);
        grid.add(new Label("Credits"), 0, 4);
        grid.add(new Label("Acronym"), 0, 5);
        grid.add(new Label("Time Slots : Lecture"), 0, 6);
        int i;
        for(i=0;i<C.classTime.size();i++)
        {	
        	grid.add(new Label(C.classTime.get(i).day), 1, i+6);
        	grid.add(new Label(C.classTime.get(i).from +"-"+C.classTime.get(i).to), 2, i+6);
        	grid.add(new Label(C.classTime.get(i).venue), 3, i+6);

        }
        i+=6;
        int j=0;
        if(C.tutTime.size()!=0)
        {
        	grid.add(new Label("Time Slots : Tutorial"), 0, i);
        	for(j=0;j<C.tutTime.size();j++)
        	{
        		grid.add(new Label(C.tutTime.get(j).day), 1, j+i);
            	grid.add(new Label(C.tutTime.get(j).from +"-"+C.tutTime.get(j).to), 2, i+j);
        		grid.add(new Label(C.tutTime.get(j).venue), 3, j+i);

        	}
        }
        j+=i;
        int k=0;
        if(C.labTime.size()!=0)
        {
        	grid.add(new Label("Time Slots : Lab"), 0, j);
        	for(k=0;k<C.labTime.size();k++)
        	{
        		grid.add(new Label(C.labTime.get(k).day), 1, j+k);
            	grid.add(new Label(C.labTime.get(k).from +"-"+C.labTime.get(k).to), 2, k+j);
        		grid.add(new Label(C.labTime.get(k).venue), 3, j+k);

        	}
        }
        k+=j;
        grid.add(new Label(C.name), 1, 0);
        grid.add(new Label (C.code), 1, 1);
        grid.add(new Label (C.type), 1, 2);
        grid.add(new Label (C.instructor), 1, 3);
        grid.add(new Label (C.credits+""), 1, 4);
        grid.add(new Label (C.acronym), 1, 5);

        

        
//        grid.add(new Label("Pre-Conditions"), 0, 6);
//        grid.add(new Label("Post-Conditions"), 0, k);
//        grid.add(new Label(C.post_conditions), 1, k);

        
        
        
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
