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
public class scene_alertbox {
	
    /**
     * @param title Title for the PopUp
     * @param message Message to be Displayed
     * @param button_message Button Name Display
     * 
     * 
     */
    public static void display(String title, String message, String button_message) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(200);
        
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button(button_message);
        
        closeButton.setOnAction(e -> {
        	window.close();
        	//w.setScene(scene_login.getScene(w));
        	});

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
