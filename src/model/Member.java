package model;

import java.util.HashMap;


public class Member 
{
	private String firstName;
	private String secondName;
	private String accountName;
	private String password;
	private boolean loggedIn;
	private HashMap<Integer, Rating> ratings;
	
	public Member(String firstName, String secondName, String accountName, String password)
	{
		this.firstName = firstName;
		this.secondName = secondName;
		this.accountName = accountName;
		this.password = password;
		this.ratings = new HashMap<Integer, Rating>();
	}

	public HashMap<Integer, Rating> getRatings() 
	{
		return ratings;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getSecondName() 
	{
		return secondName;
	}

	public void setSecondName(String secondName) 
	{
		this.secondName = secondName;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getAccountName() 
	{
		return accountName;
	}

	public void setAccountName(String accountName) 
	{
		this.accountName = accountName;
	}
	
	public boolean passwordCheck(String password)
	{
		String lowerCasePass = password.toLowerCase();
		String lowerCaseCheck = this.password.toLowerCase();
		
		if(lowerCasePass.equals(lowerCaseCheck))
		{
				return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isLoggedIn() 
	{
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) 
	{
		this.loggedIn = loggedIn;
	}
}
