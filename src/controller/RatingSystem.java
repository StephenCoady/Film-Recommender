package controller;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import edu.princeton.cs.introcs.Picture;
import edu.princeton.cs.introcs.StdOut;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import model.Film;
import model.Member;
import model.Rating;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import au.com.bytecode.opencsv.CSVReader;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;


@SuppressWarnings("unused")
public class RatingSystem 
{
	private ArrayList<Member> members = new ArrayList<Member>();
	private ArrayList<Film> films = new ArrayList<Film>();
	private HashMap<Integer, Double> ratings = new HashMap<Integer, Double>();
	private Member loggedIn;
	private String loadMembersLocation = "src/files/members.json";
	private String loadFilmsLocation = "src/files/films.json";
	private String loadBackupMembersLocation = "src/backup/members.json";
	private String loadBackupFilmsLocation = "src/backup/films.json";

	public static void main(String[] args) throws IOException
	{
		RatingSystem system = new RatingSystem();
		system.run();
	}

	private void run() throws IOException
	{
		double timeStart = System.currentTimeMillis();
		loadFilms();
		loadMembers();
		logIn("Ben","pass");
		saveFilms();
		saveMembers();
		double timeStop = System.currentTimeMillis();
		StdOut.println("Total running time: " + ((timeStop - timeStart)/1000) + " seconds");
	}

	public void loadMembers()
	{
		JSONParser parser = new JSONParser();
		try {

			Object obj = parser.parse(new FileReader(loadMembersLocation));
			JSONObject jsonObject = (JSONObject) obj;
			for (int i = 0; i < jsonObject.size(); i++)
			{
				ArrayList<?> newArray = (ArrayList<?>) jsonObject.get(Integer.toString(i));
				String obj2  = (String) newArray.get(0); // username
				String obj3  = (String) newArray.get(1); // firstname
				String obj4  = (String) newArray.get(2); // secondname
				String obj5  = (String) newArray.get(3); // password

				Member newMember = new Member(obj3, obj4, obj2, obj5);
				members.add(newMember);

				//deals with the member's ratings
				Object keyArray = newArray.get(4);
				JSONObject jsonObject2 = (JSONObject) keyArray;
				for(int j = 0; j<films.size();j++)
				{
					if(jsonObject2.get(Integer.toString(j))!=null)
					{
						String string = jsonObject2.get(Integer.toString(j)).toString();
						int rating = Integer.parseInt(string);
						InitialNewRating(members.indexOf(newMember), films.get(j), rating);
					}
				}
			}
		} catch (FileNotFoundException e) {
			this.loadMembersLocation = "src/backup/members.json";
			this.loadBackupMembersLocation = "src/files/members.json";
			loadMembers();
		} catch (IOException e) {
			this.loadMembersLocation = "src/backup/members.json";
			this.loadBackupMembersLocation = "src/files/members.json";
			loadMembers();
		} catch (ParseException e) {
			this.loadMembersLocation = "src/backup/members.json";
			this.loadBackupMembersLocation = "src/files/members.json";
			loadMembers();
		}
		backupMembers();
	}

	@SuppressWarnings("unchecked")
	public void saveMembers() throws IOException
	{
		JSONObject obj = new JSONObject();

		for(int i = 0; i <members.size(); i++)
		{
			Member member = members.get(i);
			JSONArray newMember = new JSONArray();
			newMember.add(member.getAccountName());
			newMember.add(member.getFirstName());
			newMember.add(member.getSecondName());
			newMember.add(member.getPassword());

			Map <Integer, Integer> hm = new HashMap<Integer, Integer>();
			for (int j = 0; j < films.size(); j++)
			{
				if(member.getRatings().containsKey(j))
				{
					int rate = member.getRatings().get(j).getRating();
					hm.put(j, rate);
				}
			}
			newMember.add(hm);
			obj.put(i, newMember);
		}

		FileWriter file = new FileWriter("src/files/members.json");
		try {
			file.write(obj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			file.flush();
			file.close();
		}
	}

	public void loadFilms()
	{
		JSONParser parser = new JSONParser();
		try {

			Object obj = parser.parse(new FileReader(loadFilmsLocation));
			JSONObject jsonObject = (JSONObject) obj;
			for (int i = 0; i < jsonObject.size(); i++)
			{
				ArrayList<?> newArray = (ArrayList<?>) jsonObject.get(Integer.toString(i));
				Object obj2  = newArray.get(0);
				String ID2 = obj2.toString();
				int ID = Integer.parseInt(ID2);
				String title = (String) newArray.get(1);
				String year = (String) newArray.get(2);
				String genre = (String) newArray.get(3);
				String imageLocation = (String) newArray.get(4);
				if(imageLocation.equals("src/images/no_image_available.jpg"))
				{
					imageLocation = getFilmImage(title);
					StdOut.println(i);
				}

				Film film = new Film(ID, title, year, genre);
				film.setFilmImage(imageLocation);
				films.add(film);
			}
		} catch (FileNotFoundException e) {
			this.loadFilmsLocation = "src/backup/films.json";
			this.loadBackupFilmsLocation = "src/files/films.json";
			loadFilms();
		} catch (IOException e) {
			this.loadFilmsLocation = "src/backup/films.json";
			this.loadBackupFilmsLocation = "src/files/films.json";
			loadFilms();
		} catch (ParseException e) {
			this.loadFilmsLocation = "src/backup/films.json";
			this.loadBackupFilmsLocation = "src/files/films.json";
			loadFilms();
		}
		backUpFilms();
	}

	@SuppressWarnings("unchecked")
	public void saveFilms() throws IOException
	{
		JSONObject obj = new JSONObject();

		for(int i = 0; i <films.size(); i++)
		{
			JSONArray newFilm = new JSONArray();
			newFilm.add(films.get(i).getID());
			newFilm.add(films.get(i).getTitle());
			newFilm.add(films.get(i).getYear());
			newFilm.add(films.get(i).getGenre());
			newFilm.add(films.get(i).getFilmImage());

			obj.put(films.get(i).getID(), newFilm);
		}
		FileWriter file = new FileWriter("src/files/films.json");
		try {
			file.write(obj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			file.flush();
			file.close();
		}
	}

	public void newMember(String firstName, String secondName, String accountName, String password) throws IOException
	{
		Member newMember = new Member(firstName, secondName, accountName, password);
		members.add(newMember);
		saveMembers();
	}

	public void newFilm(String name, String year, String genre) throws IOException
	{
		Film newFilm = new Film(films.size(), name, year, genre);
		films.add(newFilm);
		saveFilms();
	}

	public void InitialNewRating(int userID, Film film, int rating) throws IOException
	{
		Member member = members.get(userID);
		int ID = film.getID();
		if(!member.getRatings().containsKey(ID))
		{
			film.addRating(rating);
			ratings.put(ID, film.getSumOfRatings());
			Rating newRating = new Rating(rating, film, member);
			member.getRatings().put(ID, newRating);
		}
		/*
		 * if the member has already rated this film, their previous rating 
		 * will not be reflected in that films total ratings
		 */
		else
		{
			film.subtractRating(member.getRatings().get(ID).getRating());
			film.addRating(rating);
			ratings.put(ID, film.getSumOfRatings());
			Rating newRating = new Rating(rating, film, member);
			member.getRatings().put(ID, newRating);
		}
	}

	public void newRating(int userID, Film film, int rating) throws IOException
	{
		Member member = members.get(userID);
		int ID = film.getID();
		if(!member.getRatings().containsKey(ID))
		{
			film.addRating(rating);
			ratings.put(ID, film.getSumOfRatings());
			Rating newRating = new Rating(rating, film, member);
			member.getRatings().put(ID, newRating);
		}
		//if the member has already rated this film, their previous rating 
		//will not be reflected in that films total ratings
		else
		{
			film.subtractRating(member.getRatings().get(ID).getRating());
			film.addRating(rating);
			ratings.put(ID, film.getSumOfRatings());
			Rating newRating = new Rating(rating, film, member);
			member.getRatings().put(ID, newRating);
		}
		saveMembers();
	}

	public void logIn(String userName, String password)
	{
		for(int i = 0; i<members.size();i++)
		{
			if (members.get(i).getAccountName().equals(userName) 
					&& members.get(i).getPassword().equals(password) )
			{
				loggedIn = members.get(i);
				members.get(i).setLoggedIn(true);
				break;
			}
		}
	}
	
	public void saveImage(String urlString, String filmName)
	{
		Image image = null;
		try {
		    URL url = new URL(urlString);
		    image = ImageIO.read(url);
		    BufferedImage image2 = (BufferedImage) image;
		    File newFile = new File("src/images/"+filmName);
		    ImageIO.write(image2, "gif", newFile);
		} catch (IOException e) {
		}
	}
	
	public String getFilmImage(String filmName) throws IOException
	{
		String search = filmName.replaceAll("\\s+","");
		URL googleSearch = new URL("http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + search);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(googleSearch.openStream()));

		String line;
		line = in.readLine();
		in.close();
		

		Object obj=JSONValue.parse(line);
		JSONObject object = (JSONObject)obj;
		
		JSONObject newObj = (JSONObject) object.get("responseData");
		JSONArray newerObj = (JSONArray) newObj.get("results");
		JSONObject evenNewerObj = (JSONObject) newerObj.get(0);
		String imageString = (String) evenNewerObj.get("unescapedUrl");
		
		return imageString;
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, Integer> sortFilmsByRatings()
	{
		HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
		HashMap<Integer, Rating> userRatings = loggedIn.getRatings();
		for(int i = 0; i<films.size();i++)
		{
			if(userRatings.get(i)!=null)
				hmap.put(i, userRatings.get(i).getRating());
		}
		Map<Integer, Integer> map = sortByValues(hmap); 
		return map;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static HashMap sortByValues(HashMap map) { 
		List list = new LinkedList(map.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		} 
		return sortedHashMap;
	}

	public void randomiseRatings()
	{
		Random rand = new Random();
		ratings.clear();
		int[] array;
		array = new int[5];
		array[0] = -5;
		array[1] = -3;
		array[2] = 1;
		array[3] = 3;
		array[4] = 5;
		for(Member member: members)
		{
			member.getRatings().clear();
			for(int i = 0; i < 20; i++)
			{
				int randomKey = rand.nextInt(films.size());
				int random = rand.nextInt(5);
				int randomRating = array[random];
				Rating rating = new Rating(randomRating, films.get(randomKey), member);
				member.getRatings().put(randomKey, rating);
				Film film = films.get(randomKey);
				film.addRating(randomRating);
				ratings.put(film.getID(), film.getSumOfRatings());
			}
		}

	}

	public void backUpFilms()
	{
		File source = new File(loadFilmsLocation);
		File dest = new File(loadBackupFilmsLocation);
		try {
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void backupMembers()
	{
		File membersSource = new File(loadMembersLocation);
		File membersDest = new File(loadBackupMembersLocation);
		try {
			FileUtils.copyFile(membersSource, membersDest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void deleteAllRatings()
	{
		for(Member member: members)
		{
			ratings.clear();
			member.getRatings().clear();
		}
	}

	//purely for setting up members from csv file, should only be used once
	public void setUpMembers() throws IOException
	{
		CSVReader reader = new CSVReader(new FileReader("src/files/membersSetup.csv"));
		try {
			String [] nextLine;
			while ((nextLine = reader.readNext()) != null) 
			{
				// nextLine[] is an array of values from the line
				String username = nextLine[0];
				String firstName = nextLine[1];
				String secondName = nextLine[2];
				String password = nextLine[3];
				Member member = new Member(firstName, secondName, username, password);
				members.add(member);
			}
		}
		finally {
			reader.close();
		}
	}
}
