package rating_system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class RatingSystem {

	public static void main(String[] args) throws IOException 
	{
		CSVReader reader = new CSVReader(new FileReader("src/files/films_fx.csv"));
		try {
			String [] nextLine;
			while ((nextLine = reader.readNext()) != null) 
			{
				// nextLine[] is an array of values from the line
				System.out.println(nextLine[0] + ": " + nextLine[1] + 
						" " + nextLine[2] + " " + nextLine[3] );
			}
		}
		finally {
			reader.close();
		}
		
		FileWriter writer = new FileWriter("src/files/films_fx.csv", true);
	     // feed in your array (or convert your data to an array)
	    writer.append("\n"); 
			writer.append("test");
			writer.append(",");
			writer.append("test1");
			writer.append(",");
			writer.append("test2");
			writer.append(",");
			writer.append("");
			writer.close();
	}

}
