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
		hrs.attachChangeListener(mainMenu);
		GuestLogin guestLogin = new GuestLogin(hrs);
		hrs.attachChangeListener(guestLogin);
		GuestMenu guestMenu = new GuestMenu(hrs);
		hrs.attachChangeListener(guestLogin);
		ManagerMenu managerMenu = new ManagerMenu(hrs);
		hrs.attachChangeListener(managerMenu);

		MakeReservation makeReservation = new MakeReservation(hrs);
		hrs.attachChangeListener(makeReservation);

		ViewReservations viewReservations = new ViewReservations(hrs);
		hrs.attachChangeListener(viewReservations);

		ManagerLogin managerLogin = new ManagerLogin(hrs);
		hrs.attachChangeListener(managerLogin);

		ReservationDayView dayView = new ReservationDayView(hrs);
		hrs.attachChangeListener(dayView);


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
