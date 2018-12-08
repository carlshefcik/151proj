package hotelResevationSystem;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * TimeInterval class uses the java.time packages to create time interval objects that can be compared to each other
 * @author Carl Shefcik
 *
 */
public class TimeInterval implements Comparable<TimeInterval>{
	
	private LocalDate start;
	private LocalDate end;
	
	/**
	 * TimeInterval ctor that takes in two LocalDates and sets them
	 * @param start The start of the time interval
	 * @param end The end of the time interval
	 */
	TimeInterval(LocalDate start, LocalDate end){
		this.start = start;
		this.end = end;
	}
	
	//getters and setters
	public void setStartTime(LocalDate start) { this.start = start; }
	public void setEndTime(LocalDate end) { this.end = end; }
	public LocalDate getStartTime() { return start; }
	public LocalDate getEndTime() { return end; }
	
	/**
	 * Compares two time intervals to see if they overlap
	 * @param ti TimeInterval object to be compared too
	 * @return true if time intervals overlap, false otherwise
	 */
	public boolean overlap(TimeInterval ti) {
		if (this.start.compareTo(ti.start) <= 0) {
			if(this.end.compareTo(ti.start) > 0 ) {
				return true;
			} else if(this.end.compareTo(ti.end) >= 0) {
				return true;
			}
		} else if (this.start.compareTo(ti.end) < 0) {
			if(this.end.compareTo(ti.end) >= 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks to see if the time interval is over 60 days
	 * @return true if over 60 days, false otherwise
	 */
	public boolean over60() {
		return false;
	}
	
	/**
	 * Compares start times of TimeIntervals using the LocalTime compareTo() method
	 * @return An int from a compare to called on the start times of the time intervals. If 0 then it returns comparison of end times
	 */
	@Override
	public int compareTo(TimeInterval o) {
		if(this.start.compareTo(o.start) == 0)
			return this.end.compareTo(o.end);
		return this.start.compareTo(o.start);
	}

	/**
	 * toString returns string form of the TimeInterval instance
	 * @return A string with the start time and end time
	 */
	public String toString() {
		return start.toString()+" - "+end.toString();
	}
}
