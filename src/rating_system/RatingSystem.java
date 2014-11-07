package rating_system;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import edu.princeton.cs.introcs.Picture;
import edu.princeton.cs.introcs.StdOut;

import javax.imageio.ImageIO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;


@SuppressWarnings("unused")
public class RatingSystem 
{
	ArrayList<Member> members = new ArrayList<Member>();
	ArrayList<Film> films = new ArrayList<Film>();

	public static void main(String[] args) throws IOException
	{
		RatingSystem system = new RatingSystem();
		system.run();
	}

	private void run() throws IOException
	{
		loadFilms();
		saveFilms();
		for(int i = 0; i<films.size();i++)
		{
			StdOut.println(films.get(i).getID() + ": " + films.get(i).getTitle());
		}
	}
	
	public void loadMembers()
	{
		
	}
	
	public void saveMembers()
	{
		
	}

	public void loadFilms()
	{
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("src/files/films.json"));
			JSONObject jsonObject = (JSONObject) obj;
			for (int i = 1; i < jsonObject.size()+1; i++)
			{
				ArrayList<?> newArray = (ArrayList<?>) jsonObject.get(Integer.toString(i));
				Object obj2  = newArray.get(0);
				String ID2 = obj2.toString();
				int ID = Integer.parseInt(ID2);
				String title = (String) newArray.get(1);
				String year = (String) newArray.get(2);
				String genre = (String) newArray.get(3);
				Film film = new Film(ID, title, year, genre);
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

	public void newFilm(String name, String year, String genre) throws IOException
	{
		Film newFilm = new Film(films.size()+1, name, year, genre);
		films.add(newFilm);
	}
}
