package hotelResevationSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Reservation object holds data on a specific reservation made and 
 * @author Carl Shefcik, Christian Castro
 *
 */
public class Reservation {

	private String reservationID;
	private LocalDate startDate;
	private LocalDate endDate;
	private String userID;
	private String roomNumber;
	
	public Reservation(String reservationID, String startDate, String endDate, String userID, String roomNumber) {
		this.reservationID = reservationID;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
			this.startDate = LocalDate.parse(startDate, formatter);
			this.endDate = LocalDate.parse(endDate, formatter);
		} catch (Exception e) {
//			e.printStackTrace();
		};
		this.userID = userID;
		this.roomNumber = roomNumber;
	}
	
	//getter methods
	public String getReservationID() { return reservationID; }
	public String getUserID() { return userID; }
	public String getRoom() { return roomNumber; }
	public LocalDate getStartDate() {return startDate;}
	public LocalDate getEndDate() {return endDate;}
	
	/**
	 * Constructs string containing details of reservation
	 * @return details reservation info
	 */
	public String resDetails() {
		String details="Reservation ID: "+reservationID+" Guest ID: "+userID+" Room Number: "+roomNumber
				+ " Start Date: "+startDate.toString()+" End Date: "+endDate.toString();
		return details;
	}
	
}
