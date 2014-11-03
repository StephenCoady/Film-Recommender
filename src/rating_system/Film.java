package rating_system;

public class Film 
{
	private int ID;
	private String title;
	private String year;
	private String genre;
	
	public Film(int ID, String title, String year, String genre)
	{
		this.ID = ID;
		this.title = title;
		this.year = year;
		this.genre = genre;
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

	
}
