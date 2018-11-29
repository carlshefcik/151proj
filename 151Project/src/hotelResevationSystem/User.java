package hotelResevationSystem;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * User method creates a user to be used with the HotelReservationSystem holding data about reservations
 * @author Carl Shefcik
 *
 */
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
	
	//Getter Methods
	public ArrayList<String> getReservations() { return reservationIDs; }
	public String getUsername() { return username; }
	
	/**
	 * Checks to see of the password is correct
	 * @param aPassword a String representing the password to be checked
	 * @return true if the correct password, false otherwise
	 */
	public boolean correctPassword(String aPassword) {
		if(password.equals(aPassword))
			return true;
		return false;
	}

	/**
	 * Adds a reservation to the User object
	 * @param reservationID the reservation ID as a string
	 */
	public void addReservation(String reservationID) {
		reservationIDs.add(reservationID);
	}
	
	//Overridden HashCode method excluding the reservations as those can change
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
