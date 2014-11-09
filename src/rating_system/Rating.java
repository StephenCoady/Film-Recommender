package rating_system;

public class Rating 
{
	private int rating;
	private Film film;
	private Member accountName;
	
	public Rating(int rating, Film film, Member accountName)
	{
		this.rating = rating;
		this.film = film;
		this.accountName = accountName;
	}
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Member getAccountName() {
		return accountName;
	}

	public void setAccountName(Member accountName) 
	{
		this.accountName = accountName;
	}

}
