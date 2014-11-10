package model;

public class Rating 
{
	private int rating;
	private Film film;
	private Member member;
	
	public Rating(int rating, Film film, Member member)
	{
		this.rating = rating;
		this.film = film;
		this.member = member;
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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) 
	{
		this.member = member;
	}

}
