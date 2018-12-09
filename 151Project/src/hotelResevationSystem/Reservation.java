package hotelResevationSystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	private TimeInterval timeInterval;
	
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
		this.timeInterval = new TimeInterval(this.startDate, this.endDate);
	}
	
	public Reservation(String reservationID, TimeInterval ti, String userID, String roomNumber) {
		this.reservationID = reservationID;
		this.startDate = ti.getStartTime();
		this.endDate = ti.getEndTime();
		this.userID = userID;
		this.roomNumber = roomNumber;
		this.timeInterval = ti;
	}
	
	//getter methods
	public String getReservationID() { return reservationID; }
	public String getUserID() { return userID; }
	public String getRoom() { return roomNumber; }
	public LocalDate getStartDate() {return startDate;}
	public LocalDate getEndDate() {return endDate;}
	
	/**
	 * Checks to see if the dates overlap
	 * @param dates a time interval to check if the dates overlap
	 * @return true iv overlap, false otherwise
	 */
	public boolean dateOverlap(TimeInterval dates) {
		return timeInterval.overlap(dates);
	}
	
	/**
	 * Constructs string containing details of reservation
	 * @return details reservation info
	 */
	public String resDetails() {
		String details="Room Number: "+roomNumber+"\tStart Date: "+startDate.toString()+"  End Date: "+endDate.toString();
		return details;
	}
	public String roomDetails() {
		String details="Guest ID: "+userID+"\tReservation ID: "+reservationID+"\tStart Date: "+startDate.toString()+"  End Date: "+endDate.toString();
		return details;
	}
}
