package hotelResevationSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.event.ChangeListener;

/**
 * HotelReservationSystem is the Model for the MVC
 * @author Carl Sheficik
 *
 */
public class HotelReservationSystem {
	// static instantiation for the total rooms available
	private static final String[] lUXORY_ROOMS = {"1","2","3","4","5","6","7","8","9","10"};
	private static final String[] ECONOMIC_ROOMS = {"11","12","13","14","15","16","17","18","19","20"};
	
	//reference to the view
	HotelReservationViewer hrv;
	
	//stores user objects and gets the user by the userID
	private HashMap<String, User> users;
	//stores managers objects and gets the manager by the userID
	private HashMap<String, Manager> managers;
	//stores the reservation objects and is gotten by the reservationID
	private HashMap<String, Reservation> reservations;
	
	//holds the ChangeListeners to be attached and updated later
	private ArrayList<ChangeListener> listeners;
	
	public HotelReservationSystem() {
		users = new HashMap<>();
		managers = new HashMap<>();
		reservations = new HashMap<>();
		listeners =  new ArrayList<ChangeListener>();
		
		loadReservations();
		loadUsers();
	}
	
	/**
	 * Attaches the view to the Model to cycle through the ViewContent's correctly
	 * @param hrv the viewer to be attached
	 */
	public void attachView(HotelReservationViewer hrv) {
		this.hrv = hrv;
	}
	
	/**
	 * Changes the current card of the CardLayout to the specified ViewContent in the HRV 
	 * @param s the name of the ViewContent to change too
	 */
	public void changeView(String s) {
		hrv.changeView(s);
	}
	
	/**
	 * Deletes all the old data and replaces it with the new data
	 * should be done whenever anyone logs out to refresh user/reservation info
	 * assuming the files have been written too during execution
	 */
	public void reload() {
		users = new HashMap<>();
		managers = new HashMap<>();
		reservations = new HashMap<>();
		listeners =  new ArrayList<ChangeListener>();
		
		loadReservations();
		loadUsers();
	}
	
	/**
	 * Loads all the reservations from the reservations.txt and puts them in the reservations HashMap
	 */
	public void loadReservations() {
		sop("Loading Reservations: ");
		try {
			FileReader fr = new FileReader(new File("data/reservations.txt"));
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			while((line = br.readLine()) != null) {
				sop(line);
				String[] resInfo = line.split(",");
				//put into instance variable and store in map
				Reservation tempRes = new Reservation(resInfo[0],resInfo[1],resInfo[2],resInfo[3],resInfo[4]);
				reservations.put(resInfo[0], tempRes);
			}
			br.close();
			fr.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		sop(reservations);
		sop("");
	}
	
	/**
	 * Loads all the users from the users.txt and puts them in their respective HashMaps
	 */
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
				if(userInfo[3].equals("Guest")) {
					User tempUser = new User(userInfo[0],userInfo[1],userInfo[2]);
					
					//puts the user in users map for userID -> User object
					users.put(userInfo[0], tempUser);
					
					//puts the id's of all the reservations into the user object
					if(userInfo.length > 4) {
						for(int i=4; i < userInfo.length; i++) {
							//adds the reservationID's the user has to the user object
							tempUser.addReservation(userInfo[i]);
						}
					}
				} else if (userInfo[3].equals("Manager")) {
					Manager tempManager = new Manager(userInfo[0],userInfo[1],userInfo[2]);
					
					//puts the Manager in managers map for userID -> Manager object
					managers.put(userInfo[0], tempManager);
					//managers wont have reservations during this demo implementaion
				}
			}
			br.close();
			fr.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		sop(users);
		sop(managers);
	}
	
	//helper method to not type System.out.println so much
	public void sop(Object o) {
		System.out.println(o);
	}
}
