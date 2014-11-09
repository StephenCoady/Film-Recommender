package rating_system;

public class Enum 
{
	public enum rate 
	{
	    TERRIBLE(-5), DIDNT_LIKE_IT(-3), HAVENT_SEEN_IT(0), OK(1), LIKED_IT(3), REALLY_LIKED_IT(5);
	   
	    private final int value;

	    private rate(int value) 
	    {
	        this.value = value;
	    }

	    public int getValue() 
	    {
	        return value;
	    }
	}

}
