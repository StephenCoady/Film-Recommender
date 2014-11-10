package controller;
 
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.princeton.cs.introcs.Picture;
import edu.princeton.cs.introcs.StdOut;

import javax.imageio.ImageIO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import model.Film;
import model.Member;
import model.Rating;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import au.com.bytecode.opencsv.CSVReader;

import com.google.gson.Gson;


@SuppressWarnings("unused")
public class RatingSystem 
{
	ArrayList<Member> members = new ArrayList<Member>();
	ArrayList<Film> films = new ArrayList<Film>();
	HashMap<Integer, Integer> ratings = new HashMap<Integer, Integer>();

	public static void main(String[] args) throws IOException
	{
		RatingSystem system = new RatingSystem();
		system.run();
	}

	private void run() throws IOException
	{
		loadFilms();
		loadMembers();
		saveFilms();
		saveMembers();
	}

	public void loadMembers()
	{
		JSONParser parser = new JSONParser();

		try {
			
			Object obj = parser.parse(new FileReader("src/files/members.json"));
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
						newRating(members.indexOf(newMember), films.get(j), rating);
					}
				}

			}
			StdOut.println("Number of members loaded: " + members.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
			StdOut.println("Number of members saved: " + obj.size());
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

			Object obj = parser.parse(new FileReader("src/files/films.json"));
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

				Film film = new Film(ID, title, year, genre);
				film.setFilmImage(imageLocation);
				films.add(film);
			}
			StdOut.println("Number of films loaded: " + films.size());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
			StdOut.println("Number of films saved: " + obj.size());
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			file.flush();
			file.close();
		}
	}

	public void newMember(String firstName, String secondName, String accountName, String password, String passwordAgain)
	{
		if(password.equals(passwordAgain))
		{
			Member newMember = new Member(firstName, secondName, accountName, password);
			members.add(newMember);
		}
		else
		{
			// TODO add in method if password check fails
		}
	}

	public void newFilm(String name, String year, String genre)
	{
		Film newFilm = new Film(films.size(), name, year, genre);
		films.add(newFilm);
	}

	public void newRating(int userID, Film film, int rating)
	{
		int ID = film.getID();
		ratings.put(ID, rating);

		Member member = members.get(userID);
		Rating newRating = new Rating(rating, film, member);
		member.getRatings().put(ID, newRating);
	}
	
	public void calculateRatings(int ID, int rating)
	{
		
	}

	//ratings hashmap is excluding duplicates, no good for what i want it for
	public void randomiseRatings()
	{
		Random rand = new Random();
		ratings.clear();
		int[] array;
		array = new int[6];
		array[0] = -5;
		array[1] = -3;
		array[2] = -1;
		array[3] = 1;
		array[4] = 3;
		array[5] = 5;
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
				ratings.put(randomKey, randomRating);
			}
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
