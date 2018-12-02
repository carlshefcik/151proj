package hotelResevationSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

/**
 * HotelReservationSystem is the Model for the MVC
 * @author Carl Sheficik
 *
 */
public class HotelReservationSystem {
	// static instantiation for the total rooms available
	private static final String[] lUXORY_ROOMS = {"1","2","3","4","5","6","7","8","9","10"};
	private static final String[] ECONOMIC_ROOMS = {"11","12","13","14","15","16","17","18","19","20"};
	
	//stores user objects and gets the user by the userID
	private HashMap<String, User> users;
	//stores managers objects and gets the manager by the userID
	private HashMap<String, Manager> managers;
	//stores the reservation objects and is gotten by the reservationID
	private HashMap<String, Reservation> reservations;
	
	public HotelReservationSystem() {
		users = new HashMap<>();
		reservations = new HashMap<>();
		loadReservations();
		loadUsers();
	}
	
	public void loadReservations() {
		sop("Loading Reservations: ");
		try {
			FileReader fr = new FileReader(new File("data/reservations.txt"));
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			while((line = br.readLine()) != null) {
				sop(line);
				String[] resInfo = line.split(",");
				//put into instance variable
				
				Reservation tempRes = new Reservation(resInfo[0],resInfo[1],resInfo[2],resInfo[3],resInfo[4]);
				
				reservations.put(resInfo[0], tempRes);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		sop(reservations);
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
				if(userInfo[4] == "Guest") {
					User tempUser = new User(userInfo[0],userInfo[1],userInfo[3]);
				
					//puts the user in users map for userID -> User object
					users.put(userInfo[0], tempUser);
					
					//puts the id's of all the reservations into the user object
					if(userInfo.length > 4) {
						for(int i=4; i < userInfo.length; i++) {
							//adds the reservationID's the user has to the user object
							tempUser.addReservation(userInfo[i]);
						}
					}
				} else if (userInfo[4] == "Manager") {
					Manager tempManager = new Manager(userInfo[0],userInfo[1],userInfo[3]);
					
					//puts the Manager in managers map for userID -> Manager object
					managers.put(userInfo[0], tempManager);
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
