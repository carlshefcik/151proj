import java.util.ArrayList;

public class User {
	
	private int userID;
	private String password;
	private String username;
	private ArrayList<Reservation> reservations;
	
	public User(int userID, String password, String username)
	{
		reservations = new ArrayList<Reservation>();
	}
	
	public ArrayList<Reservation> getReservations()
	{
		return reservations;
	}
	
	public boolean correctPassword(String aPassword)
	{
		if(password.equals(aPassword))
		{
			return true;
		}
		return false;
	}
	
	public String getUsername()
	{
		return username;
	}
}
