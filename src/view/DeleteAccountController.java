package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.RatingSystem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class DeleteAccountController implements Initializable
{	
	@FXML
	private ImageView sadFace = new ImageView();
	private RatingSystem r = new RatingSystem();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		r.loadMembers();
		Image image = new Image("file:src/images/sadFace.gif");
		sadFace.setImage(image);
	}
	
	@FXML
	private void deleteAccountNo(MouseEvent event)
	{
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	@FXML
	private void deleteAccountYes(MouseEvent event) throws IOException
	{
		System.exit(0);
	}
}
