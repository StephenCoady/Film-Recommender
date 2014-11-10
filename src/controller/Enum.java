package controller;

import java.util.HashMap;
import java.util.Map;

public enum Enum 
{
	TERRIBLE(-5), DIDNT_LIKE_IT(-3), HAVENT_SEEN_IT(0), OK(1), LIKED_IT(3), REALLY_LIKED_IT(5);

	private static Map<Integer, Enum> map;
	private int value;

	private Enum(int value)
	{
		this.value = value;
	}
	
	static
	{
		map  = new HashMap<>();
		map.put(5, REALLY_LIKED_IT);
		map.put(3, LIKED_IT);
		map.put(1, OK);
		map.put(0, HAVENT_SEEN_IT);
		map.put(-3, DIDNT_LIKE_IT);
		map.put(-5, TERRIBLE);
	}

	public int getValue() 
	{
		return value;
	}
	
	public static Enum getRating(int key)
	{
		return map.get(key);
	}
}
