package hotelResevationSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Reservation object holds data on a specific reservation made and 
 * @author Carl Shefcik
 *
 */
public class Reservation {

	private String reservationID;
	//probably replace ; with a time interval class
	private Date startDate;
	private Date endDate;
	private String userID;
	private String roomNumber;
	
	public Reservation(String reservationID, String startDate, String endDate, String userID, String roomNumber) {
		this.reservationID = reservationID;
		try {
			this.startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
			this.endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
		} catch (ParseException e) {
//			e.printStackTrace();
		};
		this.userID = userID;
		this.roomNumber = roomNumber;
	}
	
	//getter methods
	public String getReservationID() { return reservationID; }
	public String getUserID() { return userID; }
	public String getRoom() { return roomNumber; }
	
}
