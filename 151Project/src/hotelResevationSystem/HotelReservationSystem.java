package hotelResevationSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class HotelReservationSystem {
	
//	private Room[] luxoryRooms;
//	private Room[] economicRooms;
	private ArrayList<User> accounts;
	
	private HashMap<String, User> users;
	private HashMap<String, Reservation> reservations;
	
	public HotelReservationSystem() {
		users = new HashMap<>();
		reservations = new HashMap<>();
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
				sop(line);
				String[] userInfo = line.split(",");
				//creates a user checking if they are a Guest user or not
				User tempUser = new User(userInfo[0],userInfo[1],userInfo[3],userInfo[4] == "Guest");
				
				//puts the user in users map for userID -> User object
				users.put(userInfo[0], tempUser);
				
				//puts the id's of all the reservations into the user object
				if(userInfo.length > 4) {
					for(int i=4; i < userInfo.length; i++) {
						//adds the reservationID's the user has to the user object
						tempUser.addReservation(userInfo[i]);
					}
				}
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
