package view;

import controller.RatingSystem;

import java.util.Iterator;
import java.util.Map;

public class Gui 
{
	@SuppressWarnings("rawtypes")
	public void sortFilms()
	{
		RatingSystem ratingSystem = new RatingSystem();
		Map map = ratingSystem.sortFilmsByRatings();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}
	}
}