package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;




import model.Member;
import model.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import controller.RatingSystem;
import edu.princeton.cs.introcs.StdOut;

public class SystemController implements Initializable
{	
	@FXML
	private TabPane tabPane = new TabPane();
	@FXML
	private Tab tab1 = new Tab();
	@FXML
	private Tab tab2 = new Tab();
	@FXML
	private Tab tab3 = new Tab();
	@FXML
	private Tab tab4 = new Tab();
	@FXML
	private Tab tab5 = new Tab();

	@FXML
	private static RatingSystem r = new RatingSystem();

	@FXML
	private TextField userName = new TextField();

	@FXML
	private PasswordField passwordEntry = new PasswordField();

	@FXML
	private TextField firstName = new TextField();

	@FXML
	private TextField secondName = new TextField();

	@FXML
	private TextField desiredUserName = new TextField();

	@FXML
	private PasswordField desiredPassword = new PasswordField();

	@FXML
	private PasswordField reEnteredPassword = new PasswordField();

	@FXML
	private ChoiceBox<String> genrePreference = new ChoiceBox<String>();

	@FXML
	private ChoiceBox<String> genreChange = new ChoiceBox<String>();

	@FXML
	private TextField yearPreference = new TextField();

	@FXML
	private PasswordField passwordChange = new PasswordField();

	@FXML
	private PasswordField confirmPasswordChange = new PasswordField();

	@FXML
	private TextField firstNameChange = new TextField();

	@FXML
	private ImageView filmImage = new ImageView();

	@FXML
	private TextField secondNameChange = new TextField();

	@FXML
	private TextField usernameChange = new TextField();

	@FXML
	private TextField titleSearchTerm = new TextField();

	@FXML
	private TextField genreSearchTerm = new TextField();

	@FXML
	private ListView<String> searchResults = new ListView<String>();

	@FXML
	private TextField yearChange = new TextField();

	final ObservableList<String> list = FXCollections.observableArrayList(
			"Drama","Action","Comedy","Family","Horror","Crime","Biography","Adventure"
			);

	final ObservableList<String> ratingsList = FXCollections.observableArrayList(
			"5","3","1","0","-3","-5"
			);

	@FXML
	private ChoiceBox<String> rateFilm = new ChoiceBox<String>();

	@FXML
	private Label incorrectUser = new Label();

	@FXML
	private Label noSignUp = new Label();

	@FXML
	private Label noSearchTerm = new Label();

	@FXML
	private Label noResults = new Label();

	@FXML
	private Label successfulRating = new Label();

	@FXML
	private Label incorrectDetails = new Label();

	@FXML
	private Label filmTitleLabel = new Label();

	private SingleSelectionModel<Tab> selectionModel;



	@FXML
	private void expandGenres(MouseEvent event)
	{
		genrePreference.show();
	}

	@FXML
	private void expandRatings(MouseEvent event)
	{
		rateFilm.show();
	}

	@FXML
	private void signIn(MouseEvent event) throws IOException 
	{
		Member member = null;
		for(int i = 0; i<r.getMembers().size();i++)
		{
			if(r.getMembers().get(i).getAccountName().equals(userName.getText()))
			{
				member = r.getMembers().get(i);
				break;
			}
		}
		if(member!= null && member.getPassword().equals(passwordEntry.getText()))
		{
			r.logIn(userName.getText(), passwordEntry.getText());
			((Node)(event.getSource())).getScene().getWindow().hide();
			startUp();
		}
		else
		{
			incorrectUser.setText("Incorrect username or password. Try again.");
		}
	}

	@FXML
	private void signUp(MouseEvent event) throws IOException 
	{
		if(desiredPassword.getText().equals(reEnteredPassword.getText()) && Integer.valueOf(yearPreference.getText()) instanceof Integer)
		{
			r.newMember(firstName.getText(), secondName.getText(), desiredUserName.getText(), desiredPassword.getText(), genrePreference.getValue(), Integer.valueOf(yearPreference.getText()));
			((Node)(event.getSource())).getScene().getWindow().hide();
			startUp();
		}
		else if (firstName.getText().equals(null) 
				|| secondName.getText().equals(null) 
				|| desiredUserName.getText().equals(null)
				|| desiredPassword.getText().equals(null) 
				|| !(Integer.valueOf(yearChange.getText()) instanceof Integer))
		{
			noSignUp.setText("Please complete all fields.");
		}
		else if(!desiredPassword.getText().equals(reEnteredPassword.getText()))
		{
			noSignUp.setText("Passwords do not match. Try again.");
		}
	}

	private void startUp() throws IOException
	{
		r.loadFilms();
		r.sortByTitle();
		r.sortByYear();
		r.getListOfSimilarMembers();
		r.getFilmRecommendations();
		r.getBetterRecommendations();
		Stage mainStage = new Stage();
		mainStage.setTitle("Film Recommender");
		Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
		Scene scene = new Scene(root);
		mainStage.setScene(scene);
		mainStage.show();
	}

	@FXML
	private void changeDetails(MouseEvent event)
	{
		if(passwordChange.getText().equals(confirmPasswordChange.getText()) && firstNameChange.getText()!=null 
				&& !secondNameChange.getText().equals(null) 
				&& !usernameChange.getText().equals(null) 
				&& !passwordChange.getText().equals(null) 
				&& !yearChange.getText().equals(null))
		{
			r.getLoggedIn().setFirstName(firstNameChange.getText());
			r.getLoggedIn().setSecondName(secondNameChange.getText());
			r.getLoggedIn().setAccountName(usernameChange.getText());
			r.getLoggedIn().setPassword(passwordChange.getText());
			r.getLoggedIn().setYearPreference(Integer.valueOf(yearChange.getText()));
			r.getLoggedIn().setGenrePreference(genreChange.getValue());
			StdOut.println(r.getLoggedIn().toString());
		}
		else if (firstNameChange.getText().equals(null)
				|| secondNameChange.getText().equals(null)
				|| usernameChange.getText().equals(null)
				|| passwordChange.getText().equals(null)
				|| yearChange.getText().equals(null))
		{
			incorrectDetails.setText("Please complete all fields.");
		}
		else if(!passwordChange.getText().equals(confirmPasswordChange.getText()))
		{
			incorrectDetails.setText("Passwords do not match. Try again.");
		}
	}

	@FXML
	private void resetFields(MouseEvent event)
	{
		firstNameChange.clear();
		secondNameChange.clear();
		usernameChange.clear();
		passwordChange.clear();
		confirmPasswordChange.clear();
		yearChange.clear();
	}

	@FXML
	private void searchByTitle(MouseEvent event)
	{
		ObservableList<String> foundFilms = FXCollections.observableArrayList();
		if(!titleSearchTerm.getText().toLowerCase().isEmpty())
		{
			for(int i = 0;i<r.getFilms().size();i++)
			{
				if(r.getFilms().get(i).getTitle().contains(titleSearchTerm.getText().toLowerCase()))
				{
					foundFilms.add(r.getFilms().get(i).getTitle());
				}
			}
		}
		else
		{
			noSearchTerm.setText("Please enter a search term to search for.");
		}

		if(!foundFilms.isEmpty())
		{
			selectionModel.select(3);
			searchResults.setItems(foundFilms);
		}
		else if (r.getFilms().isEmpty())
		{
			noResults.setText("It appears there are currently no films in the database. Why not add some?");
		}
	}

	@FXML
	private void selectFilm(MouseEvent event)
	{
		successfulRating.setText(" ");
		Film film = null;
		String filmTitle = searchResults.getSelectionModel().getSelectedItem();
		filmTitleLabel.setText(filmTitle);
		for(int i = 0;i<r.getFilms().size();i++)
		{
			if(r.getFilms().get(i).getTitle().equals(filmTitle))
			{
				film = r.getFilms().get(i);
				break;
			}
		}
		Image image = new Image("file:"+film.getFilmImage());
		if(image.getHeight()==0)
		{
			image = new Image("file:src/images/no_image_available.jpg");
			filmImage.setImage(image);
		}
		else
		{
			filmImage.setImage(image);
		}
	}

	@FXML
	private void searchByGenre(MouseEvent event)
	{
		ObservableList<String> foundFilms = FXCollections.observableArrayList();
		if(!genreSearchTerm.getText().toLowerCase().isEmpty())
		{
			for(int i = 0;i<r.getFilms().size();i++)
			{
				if(r.getFilms().get(i).getGenre().contains(genreSearchTerm.getText().toLowerCase()))
				{
					foundFilms.add(r.getFilms().get(i).getTitle());
				}
			}
		}
		else
		{
			noSearchTerm.setText("Please enter a search term to search for.");
		}

		if(!foundFilms.isEmpty())
		{
			selectionModel.select(3);
			searchResults.setItems(foundFilms);
		}
		else if (r.getFilms().isEmpty())
		{
			noResults.setText("It appears there are currently no films in the database. Why not add some?");
		}
	}

	@FXML
	private void rateFilm(MouseEvent event)
	{
		Film film = null;
		if(!filmTitleLabel.equals(null))
		{
			for(int i = 0;i<r.getFilms().size();i++)
			{
				if(r.getFilms().get(i).getTitle().equals(filmTitleLabel.getText()))
				{
					film = r.getFilms().get(i);
					break;
				}
			}
		}
		if(film!=null)
		{
			if(rateFilm.getValue()!=null)
			{
				try {
					r.newRating(r.getLoggedIn(), film, Integer.valueOf(rateFilm.getValue()));
					successfulRating.setText("Rating Successful!");
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
			{
				successfulRating.setText("Please Choose a value!");
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		genrePreference.setItems(list);
		genrePreference.setValue(list.get(0));
		genreChange.setItems(list);
		rateFilm.setItems(ratingsList);
		selectionModel = tabPane.getSelectionModel();
		r.loadMembers();
		StdOut.println(r.getFilms().size());
	}
}
