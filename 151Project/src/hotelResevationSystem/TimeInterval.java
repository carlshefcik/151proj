import java.time.*;

//TimeInterval class that represents an interval of time, suitable for events 
//(such as a meeting on a given date from 10:00 - 11:00). 
//Provide a method to check whether two intervals overlap.
/**
 * TimeInterval class to describe a time interval
 * @author Elizabeth Wong
 *@version 1.0 9/12/2018
 */
public class TimeInterval {

	private LocalTime start;
	private LocalTime end;
	
	public TimeInterval(LocalTime start, LocalTime end)
	{
		this.start = start;
		this.end = end;
	}
	
	//returns true if entered time interval is conflicting with object
	//Work on algorithm
	public boolean conflict(TimeInterval t)
	{
		if(t.start.isAfter(start) )
		{
			if(t.start.isBefore(end))
			{
				return true;
			}
		}
		
		else if(t.start.equals(start))
		{
			return true;
		}
		
		else if(t.end.isAfter(start))
		{
			if(t.end.isBefore(end))
			{
				return true;
			}
		}
		
		else if(t.start.isBefore(start))
		{
			if(t.end.isAfter(start))
			{
				return true;
			}
		}
		return false;
	}
	
	public void printTime()
	{
		System.out.print(start + " - " + end);
	}
}
