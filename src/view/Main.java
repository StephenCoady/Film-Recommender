package view;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	 /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	launch(args);
    }
	
	

@Override
public void start(Stage primaryStage) throws IOException {
		
		primaryStage.setTitle("Log In or Sign Up");
		Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
		primaryStage.show();
	}
	
}