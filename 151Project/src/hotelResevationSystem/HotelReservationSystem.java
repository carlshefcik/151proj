package hotelResevationSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * HotelReservationSystem is the Model for the MVC
 * @author Carl Sheficik, Christian Castro
 *
 */
public class HotelReservationSystem {
	// static instantiation for the total rooms available
	private static final String[] lUXORY_ROOMS = {"1","2","3","4","5","6","7","8","9","10"};
	private static final String[] ECONOMIC_ROOMS = {"11","12","13","14","15","16","17","18","19","20"};
	
	private User currentUser;
	
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
	 * Gets reservations relative to given date
	 * @param d date
	 * @return array list of reservations
	 */
	public ArrayList<Reservation> getReservations(LocalDate d) {
		ArrayList<Reservation> res=new ArrayList<Reservation>();
		for(String key:reservations.keySet()) {
			if((d.isAfter(reservations.get(key).getStartDate())&&d.isBefore(reservations.get(key).getEndDate()))
					||d.isEqual(reservations.get(key).getStartDate())||d.isEqual(reservations.get(key).getEndDate()))
				res.add(reservations.get(key));
		}
		return res;
	}
	
	/**
	 * Attaches the view to the Model to cycle through the ViewContent's correctly
	 * @param hrv the viewer to be attached
	 */
	public void attachView(HotelReservationViewer hrv) {
		this.hrv = hrv;
	}
	
	/**
	 * Attaches ChangeListeners to model
	 */
	public void attachChangeListener(ChangeListener c) {
		listeners.add(c);
	}

	/**
	 * Updates listeners of change in Model
	 */
	public void updateUser(User user) {
		currentUser = user;
		updateViews();
	}
	
	/**
	 * Updates all the views with new content
	 */
	public void updateViews() {
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}
	}
	
	//getter methods
	public User getCurrentUser() { return currentUser; }
	public HashMap<String, Reservation> getReservations(){ return reservations; }

	/** Logs the user in if the user and Id match
	 * @param manager user status (manager or guest)
	 * @param userID the id of the user
	 * @param password the attempted user password
	 * @return true if logged in, false if wrong password
	 */
	public boolean userLogin(Boolean manager, String userID, String password) {
		// maybe throw two different types of exceptions, one for no user and one for wrong password
		try {
			if(!manager&&users.get(userID).correctPassword(password)) {
				//sets the current user and returns true
				updateUser(users.get(userID));
				return true;
			}
			else if(manager&&managers.get(userID).correctPassword(password)) {
				updateUser(users.get(managers.get(userID)));
				return true;
			}
			return false;
		}
		catch(NullPointerException e) {
			return false;
		}
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
			FileReader fr = new FileReader(new File("reservations.txt"));
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
			FileReader fr = new FileReader(new File("users.txt"));
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
