package hotelResevationSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.util.HashMap;

public class HotelReservationSystem {
	
//	private Room[] luxoryRooms;
//	private Room[] economicRooms;
//	private HashMap<User, Room> accounts;
	
	private HashMap<String, String> users;
	private HashMap<Date, Reservation> reservations;
	
	public HotelReservationSystem() {
		loadReservations();
		loadUsers();
	}

	
	public HotelReservationSystem(int luxory, int economic)
	{
		// should just instantiate the variables and initialize the JFrame
//		luxoryRooms = new Room[luxory];
//		for(int i = 0; i < luxory ; i++)
//		{
//			luxoryRooms[i] = new Luxory();
//		}
		loadReservations();
	}
	
	
	
	public void loadReservations() {
		sop("Loading Reservations: ");
		try {
			FileReader fr = new FileReader(new File("data/reservations.txt"));
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			while((line = br.readLine()) != null) {
				System.out.println(line);
				//put into instance variable
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		sop("");
	}
	
	public void loadUsers() {
		sop("Loading Users: ");
		try {
			FileReader fr = new FileReader(new File("data/users.txt"));
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			while((line = br.readLine()) != null) {
				System.out.println(line);
				//put into instance variable
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sop(Object o) {
		System.out.println(o);
	}

}
