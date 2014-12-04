package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import model.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import controller.RatingSystem;

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
	private Tab tab6 = new Tab();
	@FXML
	private Tab tab7 = new Tab();

	
	@FXML
	private static RatingSystem r = new RatingSystem();


	@FXML
	private ChoiceBox<String> genrePreference = new ChoiceBox<String>();

	@FXML
	private ChoiceBox<String> genreChange = new ChoiceBox<String>();

	@FXML
	private PasswordField passwordChange = new PasswordField();

	@FXML
	private PasswordField confirmPasswordChange = new PasswordField();

	@FXML
	private TextField firstNameChange = new TextField();

	@FXML
	private ImageView filmImage = new ImageView();

	@FXML
	private ImageView filmChoiceImage = new ImageView();

	@FXML
	private TextField secondNameChange = new TextField();

	@FXML
	private TextField usernameChange = new TextField();

	@FXML
	private ComboBox<String> titleSearchTerm = new ComboBox<String>();

	@FXML
	private ComboBox<String> genreSearchTerm = new ComboBox<String>();

	@FXML
	private ListView<String> searchResults = new ListView<String>();

	@FXML
	private ListView<String> filmsByTitle = new ListView<String>();

	@FXML
	private ListView<String> filmsByYear = new ListView<String>();

	@FXML
	private ListView<String> ratedFilms = new ListView<String>();

	@FXML
	private ListView<String> recommendations = new ListView<String>();

	@FXML
	private TextField yearChange = new TextField();

	private final ObservableList<String> list = FXCollections.observableArrayList(
			"Drama","Action","Comedy","Family","Horror","Crime","Biography","Adventure"
			);

	private final ObservableList<String> ratingsList = FXCollections.observableArrayList(
			"5","3","1","0","-3","-5"
			);

	private ObservableList<String> ratedFilmsByTitle = FXCollections.observableArrayList();

	private ObservableList<String> filmsByTitleList = FXCollections.observableArrayList();

	private ObservableList<String> filmsByYearList = FXCollections.observableArrayList();

	private ObservableList<String> recommendedFilms = FXCollections.observableArrayList();

	@FXML
	private ChoiceBox<String> rateFilm = new ChoiceBox<String>();

	@FXML
	private ChoiceBox<String> reRatingBox = new ChoiceBox<String>();

	@FXML
	private ChoiceBox<String> filmChoiceRating = new ChoiceBox<String>();

	@FXML
	private ChoiceBox<String> recommendedRating = new ChoiceBox<String>();

	@FXML
	private Label incorrectUser = new Label();

	@FXML
	private Label noSignUp = new Label();

	@FXML
	private Label noSearchTerm = new Label();

	@FXML
	private Label noResults = new Label();

	@FXML
	private Label recommendedTitle = new Label();

	@FXML
	private Label numericalRating = new Label();

	@FXML
	private Pane searchPane = new Pane();

	@FXML
	private Pane recommendedPane = new Pane();

	@FXML
	private Label successfulRating = new Label();

	@FXML
	private Label incorrectDetails = new Label();

	@FXML
	private Label filmTitleLabel = new Label();

	@FXML
	private Label filmChoiceTitle = new Label();

	@FXML
	private Label userId = new Label();

	@FXML
	private Label successfulRecRating = new Label();

	@FXML
	private Label selectedFilmMessage = new Label();

	private SingleSelectionModel<Tab> selectionModel;

	@FXML
	private Pane filmPanel1 = new Pane();

	@FXML
	private Label filmTitle1 = new Label();

	@FXML
	private ImageView image1 = new ImageView();	

	@FXML
	private Pane filmPanel2 = new Pane();

	@FXML
	private Label filmTitle2 = new Label();

	@FXML
	private ImageView image2 = new ImageView();

	@FXML
	private Pane filmPanel3 = new Pane();

	@FXML
	private Label filmTitle3 = new Label();

	@FXML
	private ImageView image3 = new ImageView();

	@FXML
	private Pane filmPanel4 = new Pane();

	@FXML
	private Label filmTitle4 = new Label();

	@FXML
	private ImageView image4 = new ImageView();

	@FXML
	private Pane filmPanel5 = new Pane();

	@FXML
	private Label filmTitle5 = new Label();

	@FXML
	private ImageView image5 = new ImageView();

	@FXML
	private Pane filmPanel6 = new Pane();

	@FXML
	private Label filmTitle6 = new Label();

	@FXML
	private ImageView image6 = new ImageView();

	@FXML
	private ImageView recommendedImage = new ImageView();

	@FXML 
	private Pane ratingPane = new Pane();

	@FXML
	private Label successfulReRating = new Label();

	@FXML
	private Label ratingLabel = new Label();

	@FXML
	private ImageView ratingImage = new ImageView();

	@FXML
	private ImageView mainImage = new ImageView();

	@FXML
	private ImageView bannerImage = new ImageView();

	@FXML
	private Button logOutButton = new Button();

	private int loggedInIndex;

	public SystemController(int loggedInIndex) 
	{
		this.loggedInIndex = loggedInIndex;
	}

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
	private void expandChoiceRatings(MouseEvent event)
	{
		filmChoiceRating.show();
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
		tab5.setDisable(false);
		if(!titleSearchTerm.getValue().isEmpty())
		{
			for(int i = 0;i<r.getFilms().size();i++)
			{
				if(r.getFilms().get(i).getTitle().toLowerCase().contains(titleSearchTerm.getValue().toLowerCase()))
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
			selectionModel.select(4);
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
		searchPane.setVisible(true);
		successfulRating.setText("");
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
	private void showSelectedFilm(MouseEvent event)
	{
		String filmTitle;
		if(filmsByTitle.getSelectionModel().getSelectedItem() != null)
		{
			filmTitle = filmsByTitle.getSelectionModel().getSelectedItem();
		}
		else
		{
			filmTitle = filmsByYear.getSelectionModel().getSelectedItem().substring(0, filmsByYear.getSelectionModel().getSelectedItem().length()-7);
		}
		selectedFilmMessage.setText("");
		if(event.getClickCount()>1)
		{
			tab6.setDisable(false);
			selectionModel.select(5);
			filmChoiceRating.setItems(ratingsList);
			Film film = null;
			filmChoiceTitle.setText(filmTitle);
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
				filmChoiceImage.setImage(image);
			}
			else
			{
				filmChoiceImage.setImage(image);
			}
		}
	}

	@FXML
	private void rateSelectedFilm(MouseEvent event)
	{
		Film film = findFilm(filmChoiceTitle.getText());
		if(film!=null)
		{
			if(filmChoiceRating.getValue()!=null)
			{
				try {
					r.newRating(r.getLoggedIn(), film, Integer.valueOf(filmChoiceRating.getValue()));
					selectedFilmMessage.setText("Rating Successful!");
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
			{
				selectedFilmMessage.setText("Please choose a value!");
			}
		}
	}

	@FXML
	private void searchByGenre(MouseEvent event)
	{
		ObservableList<String> foundFilms = FXCollections.observableArrayList();
		tab5.setDisable(false);
		if(!genreSearchTerm.getValue().toLowerCase().isEmpty())
		{
			for(int i = 0;i<r.getFilms().size();i++)
			{
				if(r.getFilms().get(i).getGenre().toLowerCase().contains(genreSearchTerm.getValue().toLowerCase()))
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
			selectionModel.select(4);
			searchResults.setItems(foundFilms);
		}
		else if (r.getFilms().isEmpty())
		{
			noResults.setText("It appears there are currently no films in the database. Why not add some?");
		}
	}

	private Film findFilm(String filmName)
	{
		Film film = null;
		if(!filmName.equals(null))
		{
			for(int i = 0;i<r.getFilms().size();i++)
			{
				if(r.getFilms().get(i).getTitle().equals(filmName))
				{
					film = r.getFilms().get(i);
					break;
				}
			}
		}
		return film;
	}

	@FXML
	private void rateFilm(MouseEvent event)
	{
		Film film = findFilm(filmTitleLabel.getText());
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
				successfulRating.setText("Please choose a value!");
			}
		}
	}

	@FXML
	private void sortAllFilms()
	{
		addFilmsByTitle();
		addFilmsByYear();
	}

	private void addFilmsByTitle()
	{
		if(filmsByTitleList.isEmpty())
		{
			for(int i = 0;i<r.getSortedByTitle().size();i++)
			{
				filmsByTitleList.add(r.getSortedByTitle().get(i).getTitle());
			}
			filmsByTitle.setItems(filmsByTitleList);
		}
		else
		{
			filmsByTitleList.clear();
			for(int i = 0;i<r.getSortedByTitle().size();i++)
			{
				filmsByTitleList.add(r.getSortedByTitle().get(i).getTitle());
			}
			filmsByTitle.setItems(filmsByTitleList);
		}
	}

	private void addFilmsByYear()
	{
		if(filmsByYearList.isEmpty())
		{
			for(int i = 0;i<r.getSortedByYear().size();i++)
			{
				filmsByYearList.add(r.getSortedByYear().get(i).getTitle() + " (" + Integer.toString(r.getSortedByYear().get(i).getYear()) + ")");
			}
			filmsByYear.setItems(filmsByYearList);
		}
		else
		{
			filmsByYearList.clear();
			for(int i = 0;i<r.getSortedByYear().size();i++)
			{
				filmsByYearList.add(r.getSortedByYear().get(i).getTitle() + " (" + Integer.toString(r.getSortedByYear().get(i).getYear()) + ")");
			}
			filmsByYear.setItems(filmsByYearList);
		}
	}

	@FXML
	private void getRecommendations()
	{	
		recommendations.setItems(null);
		recommendedFilms.clear();
		for(int i = 0; i<r.getBetterRecommendedFilms().size();i++)
		{
			recommendedFilms.add(r.getBetterRecommendedFilms().get(i).getTitle());
		}
		recommendations.setItems(recommendedFilms);
	}

	@FXML
	private void selectRecommendedFilm(MouseEvent event)
	{
		recommendedPane.setVisible(true);
		String filmTitle = recommendations.getSelectionModel().getSelectedItem();
		Film film = findFilm(filmTitle);
		recommendedTitle.setText(filmTitle);;

		Image image = new Image("file:"+film.getFilmImage());
		if(image.getHeight()==0)
		{
			image = new Image("file:src/images/no_image_available.jpg");
			recommendedImage.setImage(image);
		}
		else
		{
			recommendedImage.setImage(image);
		}
	}

	@FXML
	private void updateRatings()
	{

		ratedFilms.setItems(null);
		ratedFilmsByTitle.clear();
		for(int i = 0; i<r.getFilms().size();i++)
		{
			if(r.getLoggedIn().getRatings().get(i)!=null)
			{
				ratedFilmsByTitle.add(r.getLoggedIn().getRatings().get(i).getFilm().getTitle());
			}
		}
		ratedFilms.setItems(ratedFilmsByTitle);
	}

	@FXML
	private void rateRecommendedFilm(MouseEvent event)
	{
		Film film = findFilm(recommendedTitle.getText());
		if(film!=null)
		{
			if(recommendedRating.getValue()!=null)
			{
				try {
					r.newRating(r.getLoggedIn(), film, Integer.valueOf(recommendedRating.getValue()));
					successfulRecRating.setText("Rating Successful!");
					ratedFilmsByTitle.add(film.getTitle());
					recommendedFilms.remove(film);
					getRecommendations();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
			{
				successfulRecRating.setText("Please choose a value!");
			}
		}
	}

	@FXML
	private void selectRating(MouseEvent event)
	{
		ratingPane.setVisible(true);
		successfulReRating.setText("");
		String filmTitle = ratedFilms.getSelectionModel().getSelectedItem();
		ratingLabel.setText(filmTitle);
		Film film = findFilm(filmTitle);
		int filmInt = r.getFilms().indexOf(film);
		Image image = new Image("file:"+film.getFilmImage());
		if(image.getHeight()==0)
		{
			image = new Image("file:src/images/no_image_available.jpg");
			ratingImage.setImage(image);
		}
		else
		{
			ratingImage.setImage(image);
		}
		try{
			numericalRating.setText(Integer.toString(r.getLoggedIn().getRatings().get(filmInt).getRating()));
		}
		catch(Exception e){
		}
	}

	@FXML
	private void editRating(MouseEvent event)
	{
		Film film = findFilm(ratingLabel.getText());
		if(film!=null)
		{
			if(reRatingBox.getValue()!=null)
			{
				try {
					r.newRating(r.getLoggedIn(), film, Integer.valueOf(reRatingBox.getValue()));
					successfulReRating.setText("Rating Successful!");
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
			{
				successfulReRating.setText("Please choose a value!");
			}
		}
	}

	@FXML
	private void swapTab2(MouseEvent event)
	{
		selectionModel.select(1);
	}

	@FXML
	private void swapTab3(MouseEvent event)
	{
		selectionModel.select(2);
	}

	@FXML
	private void swapTab4(MouseEvent event)
	{
		selectionModel.select(3);
	}

	@FXML
	private void swapTab7(MouseEvent event)
	{
		selectionModel.select(6);
	}

	private void tearDown()
	{
		r.getMembers().clear();
		r.getFilms().clear();
		r.getBetterRecommendedFilms().clear();
		r.getListOfSimilarMembers().clear();
		r.getRatings().clear();
		r.getLoggedIn().getRatings().clear();
		r.getSortedByTitle().clear();
		r.getSortedByYear().clear();
		r.getRecommendedFilms().clear();
	}

	@FXML
	private void logOut(MouseEvent event) throws IOException
	{
		r.saveFilms();
		r.saveMembers();
		tearDown();

		((Node)(event.getSource())).getScene().getWindow().hide();


		FXMLLoader loader = new FXMLLoader(
				getClass().getResource(
						"Login.fxml"
						)
				);


		Stage mainStage = new Stage();
		mainStage.setTitle("Log In or Sign Up");
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		mainStage.setScene(scene);
		mainStage.show();
	}

	@FXML
	private void deleteAccount(MouseEvent event) throws IOException
	{
		int noOfMembers = r.getMembers().size();
		DeleteAccountController d = new DeleteAccountController(r.getMembers().indexOf(r.getLoggedIn()));
		
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource(
						"DeleteAccount.fxml"
						)
				);
		loader.setController(d);
		Stage mainStage = new Stage();
		mainStage.setTitle("Delete Account");
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		mainStage.setScene(scene);
		mainStage.show();
		r.loadMembers();
		int newNoOfMembers = r.getMembers().size();
		if(newNoOfMembers<noOfMembers)
		{
			logOut(event);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		genrePreference.setItems(list);
		genrePreference.setValue(list.get(0));
		genreChange.setItems(list);
		rateFilm.setItems(ratingsList);
		recommendedRating.setItems(ratingsList);
		selectionModel = tabPane.getSelectionModel();
		tab6.setDisable(true);
		tab5.setDisable(true);
		r.loadFilms();
		r.loadMembers();
		r.setLoggedIn(r.getMembers().get(loggedInIndex));
		userId.setText("Welcome, " + r.getLoggedIn().getFirstName() + ".");
		searchPane.setVisible(false);
		recommendedPane.setVisible(false);
		r.getSimilarMembers();
		r.getFilmRecommendations();
		r.getBetterRecommendations();
		r.sortByTitle();
		r.sortByYear();
		titleSearchTerm.setItems(filmsByTitleList);
		genreSearchTerm.setItems(list);
		new AutoCompleteComboBoxListener<>(titleSearchTerm);
		new AutoCompleteComboBoxListener<>(genreSearchTerm);

		ratingPane.setVisible(false);
		for(int i = 0; i<r.getFilms().size();i++)
		{
			if(r.getLoggedIn().getRatings().get(i)!=null)
			{
				ratedFilmsByTitle.add(r.getLoggedIn().getRatings().get(i).getFilm().getTitle());
			}
		}
		ratedFilms.setItems(ratedFilmsByTitle);
		reRatingBox.setItems(ratingsList);

		Image image = new Image("file:src/images/movies.gif");
		mainImage.setImage(image);

		Image image2 = new Image("file:src/images/movie-banner.jpg");
		bannerImage.setImage(image2);
	}
}
