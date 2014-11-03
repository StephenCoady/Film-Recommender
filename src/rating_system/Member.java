package rating_system;

public class Member 
{
	private String firstName;
	private String secondName;
	private String accountName;
	private String password;
	
	public Member(String firstName, String secondName, String accountName, String password)
	{
		this.firstName = firstName;
		this.secondName = secondName;
		this.accountName = accountName;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
