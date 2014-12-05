package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.RatingSystem;


/**
 * test class for the rating system
 * @author Stephen
 *
 */
public class RatingSystemTester 
{

	RatingSystem r = new RatingSystem();

	@Before
	public void setUp()
	{
		r.setLoadFilmsLocation("src/files/films.json");
		r.setLoadMembersLocation("src/files/Members.json");
		r.setSaveFilmsLocation("src/files/testerFilms.json");
		r.setSaveMembersLocation("src/files/testerMembers.json");
		r.setLoadBackupFilmsLocation("src/backup/testerFilmsBackup.json");
		r.setLoadBackupMembersLocation("src/backup/testerMembersBackup.json");
	}

	public void loadUp() throws Exception 
	{
		r.loadFilms();
		r.loadMembers();
	}

	@After
	public void tearDown()
	{
		
	}
	
	@Test
	public void testLoadMembers() throws IOException 
	{
		assertTrue(r.getMembers().isEmpty());
		r.loadMembers();
		assertTrue(!r.getMembers().isEmpty());
	}

	@Test
	public void testLoadFilms() throws IOException 
	{
		assertTrue(r.getFilms().isEmpty());
		r.loadFilms();
		assertTrue(!r.getFilms().isEmpty());
	}

	@Test
	public void testSaveMembers() throws Exception
	{
		loadUp();
		assertTrue(!r.getMembers().isEmpty());
		int size = r.getMembers().size();
		r.saveMembers();
		for(int i = 0; i<r.getMembers().size(); i++)
		{
			r.getMembers().removeAll(r.getMembers());
		}
		assertTrue(r.getMembers().isEmpty());
		loadUp();
		int sizeAfter = r.getMembers().size();
		assertTrue(!r.getMembers().isEmpty());
		assertTrue(size==sizeAfter);
	}

	@Test
	public void testSaveFilms() throws Exception
	{
		loadUp();
		assertTrue(!r.getFilms().isEmpty());
		int size = r.getFilms().size();
		r.saveFilms();
		for(int i = 0; i<r.getFilms().size(); i++)
		{
			r.getFilms().removeAll(r.getFilms());
		}
		assertTrue(r.getFilms().isEmpty());
		loadUp();
		int sizeAfter = r.getFilms().size();
		assertTrue(!r.getFilms().isEmpty());
		assertTrue(size==sizeAfter);
	}

	@Test
	public void testAddMember() throws Exception
	{
		assertTrue(r.getMembers().isEmpty());
		r.newMember("Joe", "Bloggs", "Blogsy", "pass", "Comedy", 1980);
		assertTrue(!r.getMembers().isEmpty());
		assertTrue(r.getMembers().size()==1);
		loadUp();
		int size = r.getMembers().size();
		r.newMember("Joe", "Bloggs", "Blogsy", "pass", "Comedy", 1980);
		int newSize = r.getMembers().size();
		assertTrue(newSize ==(size+1));
		loadUp();
	}

	@Test
	public void testNewRating() throws Exception
	{
		loadUp();
		r.deleteAllRatings();
		assertTrue(r.getRatings().isEmpty());
		r.logIn("Ben", "pass");
		r.newRating(r.getLoggedIn(), r.getFilms().get(0), 5);
		assertTrue(!r.getRatings().isEmpty());
		assertTrue(r.getRatings().size()==1);

		//the following shows the same film cannot be rated twice by the same member
		r.newRating(r.getLoggedIn(), r.getFilms().get(0), 5);
		assertTrue(r.getRatings().size()==1);
	}

	@Test
	public void testLogIn() throws Exception
	{
		loadUp();
		assertTrue(r.getLoggedIn()==null);
		r.logIn("Ben", "pass");
		assertTrue(r.getLoggedIn().getAccountName().equals("Ben"));
		r.logIn("Francois", "pass");
		assertTrue(r.getLoggedIn().getAccountName().equals("Francois"));
	}

	@Test
	public void testSimilarMembers() throws Exception
	{
		loadUp();
		r.deleteAllRatings();
		r.logIn("Ben","pass");
		r.newRating(r.getLoggedIn(), r.getFilms().get(0), 5);
		r.logIn("Francois", "pass");
		r.newRating(r.getLoggedIn(), r.getFilms().get(0), 5);
		r.getSimilarMembers();
		assertTrue(r.getListOfSimilarMembers().size()==1);
		r.logIn("Moose", "pass");
		r.newRating(r.getLoggedIn(), r.getFilms().get(0), 5);
		r.getSimilarMembers();
		assertTrue(r.getListOfSimilarMembers().size()==2);
	}

	@Test
	public void testFilmRecommendations() throws Exception
	{
		loadUp();
		r.deleteAllRatings();

		r.logIn("Ben","pass");
		r.newRating(r.getLoggedIn(), r.getFilms().get(0), 5);

		r.logIn("Francois", "pass");
		r.newRating(r.getLoggedIn(), r.getFilms().get(0), 5);
		r.newRating(r.getLoggedIn(), r.getFilms().get(1), 5);

		r.logIn("Ben","pass");
		r.getSimilarMembers();
		r.getFilmRecommendations();
		assertTrue(r.getRecommendedFilms().size()==1);

		r.logIn("Moose", "pass");
		r.newRating(r.getLoggedIn(), r.getFilms().get(0), 5);
		r.newRating(r.getLoggedIn(), r.getFilms().get(2), 5);

		r.logIn("Ben","pass");
		r.getSimilarMembers();
		r.getFilmRecommendations();
		assertTrue(r.getRecommendedFilms().size()==2);
	}

	@Test
	public void testBetterRecommendations() throws Exception
	{
		loadUp();
		r.deleteAllRatings();

		r.logIn("Ben","pass");
		r.getLoggedIn().setGenrePreference("Action");
		r.newRating(r.getLoggedIn(), r.getFilms().get(0), 5);

		r.logIn("Francois", "pass");
		r.newRating(r.getLoggedIn(), r.getFilms().get(0), 5);
		r.newRating(r.getLoggedIn(), r.getFilms().get(1), 5);

		r.logIn("Ben","pass");
		r.getSimilarMembers();
		r.getFilmRecommendations();
		r.getBetterRecommendations();
		assertTrue(r.getBetterRecommendedFilms().size()==1);

		r.logIn("Francois", "pass");
		r.newRating(r.getLoggedIn(), r.getFilms().get(4), 5);

		r.logIn("Ben","pass");
		assertTrue(r.getBetterRecommendedFilms().size()==1);
	}

	@Test
	public void testSortByTitle() throws Exception
	{
		loadUp();
		r.sortByTitle();
		boolean sorted = true;        
		for (int i = 1; i < r.getFilms().size(); i++) 
		{
			if (r.getFilms().get(i-1).getTitle().compareTo(r.getFilms().get(i).getTitle()) > 0)
			{
				sorted = false;
			}
		}
		assertTrue(sorted);
	}
	
	@Test
	public void testSortByYear() throws Exception
	{
		loadUp();
		r.sortByYear();
		boolean sorted = true;        
		for (int i = 1; i < r.getFilms().size(); i++) 
		{
			if ((r.getFilms().get(i-1).getYear() - (r.getFilms().get(i).getYear())) < 0)
			{
				sorted = false;
			}
		}
		assertTrue(sorted);
	}
}
