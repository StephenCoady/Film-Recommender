package rating_system;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Film 
{
	private int ID;
	private String title;
	private String year;
	private String genre;
	private BufferedImage filmImage;
	
	public Film(int ID, String title, String year, String genre)
	{
		this.ID = ID;
		this.title = title;
		this.year = year;
		this.genre = genre;
		try {
			this.setFilmImage(selectInitialImage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public BufferedImage selectInitialImage() throws IOException
	{
		BufferedImage firstImage = ImageIO.read(new File("src/images/no_image_available.jpg"));
		return firstImage;
	}

	public BufferedImage getFilmImage() 
	{
		return filmImage;
	}

	public void setFilmImage(BufferedImage filmImage) 
	{
		this.filmImage = filmImage;
	}
	
}
