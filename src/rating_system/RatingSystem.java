package rating_system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import edu.princeton.cs.introcs.Picture;

import javax.imageio.ImageIO;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class RatingSystem 
{
	ArrayList<Member> members = new ArrayList<Member>();

	public static void main(String[] args) throws IOException 
	{
		Film newFilm = new Film(1, "Interstellar", "2014", "drama");
		File outputfile = new File("src/images/image.jpg");
		ImageIO.write(newFilm.getFilmImage(), "jpg", outputfile);
		Picture pic = new Picture("src/images/image.jpg");
		pic.show();
		
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
}
