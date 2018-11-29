package hotelResevationSystem;

import java.util.ArrayList;
import java.util.HashSet;

public class User {
	
	private String userID;
	private String password;
	private String username;
	private boolean guestUser;
	private ArrayList<String> reservationIDs;
	
	public User(String userID, String password, String username, boolean guestUser) {
		this.userID = userID;
		this.password = password;
		this.username = username;
		this.guestUser = guestUser;
		reservationIDs = new ArrayList<String>();
	}
	
	public ArrayList<String> getReservations() {
		return reservationIDs;
	}
	
	public boolean correctPassword(String aPassword) {
		if(password.equals(aPassword))
			return true;
		return false;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void addReservation(String reservationNumber) {
		reservationIDs.add(reservationNumber);
	}
	
	public int hashCode() {
		HashSet<Object> temp = new HashSet<Object>() {{
			add(userID);
			add(password);
			add(username);
			add(guestUser);
		}};
		return temp.hashCode();
	}
	
}
