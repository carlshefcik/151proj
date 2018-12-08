package hotelResevationSystem;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;

/**
 * HotelReservationViewer is the View of the MVC
 * It contains many references to JPanels that it replaces and repaints into the main part
 * @author Carl Shefcik
 *
 */
public class HotelReservationViewer extends JFrame {
	private HotelReservationSystem hrs;
	CardLayout cardLayout;
	
	
	public HotelReservationViewer(HotelReservationSystem hrs) {
		this.hrs = hrs;
		setLocation(0,200);
		MainMenu mainMenu = new MainMenu(hrs);
		GuestLogin guestLogin = new GuestLogin(hrs);
		GuestMenu guestMenu = new GuestMenu(hrs);
		ManagerMenu managerMenu = new ManagerMenu(hrs);
		MakeReservation makeReservation = new MakeReservation(hrs);
		ViewReservations viewReservations = new ViewReservations(hrs);
		ManagerLogin managerLogin = new ManagerLogin(hrs);
		ReservationDayView dayView = new ReservationDayView(hrs);

		Container contentPane = this.getContentPane();
		cardLayout = new CardLayout();

		//this section should add every ViewContent to the contentPane
		contentPane.setLayout(cardLayout);
		contentPane.add(mainMenu, "Main Menu");
		
		contentPane.add(managerLogin, "Manager Login");
		contentPane.add(managerMenu, "Manager Menu");
		contentPane.add(dayView, "Day View");

		contentPane.add(guestLogin, "Guest Login");
		contentPane.add(guestMenu, "Guest Menu");
		contentPane.add(makeReservation, "Make Reservation");
		contentPane.add(viewReservations, "View Reservations");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setSize(600,400);
		setVisible(true);
		
	}
	
	/**
	 * Changes the current card of the CardLayout to the specified ViewContent in the HRV 
	 * @param s the name of the ViewContent to change too
	 */
	public void changeView(String s) {
		cardLayout.show(this.getContentPane(), s);
	}

}
