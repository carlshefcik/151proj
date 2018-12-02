package hotelResevationSystem;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		GuestLogin guest = new GuestLogin(hrs);

		Container contentPane = this.getContentPane();
		cardLayout = new CardLayout();

		//this section should add every ViewContent to the contentPane
		contentPane.setLayout(cardLayout);
		contentPane.add(mainMenu, "MainMenu");
		contentPane.add(guest, "Guest");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setSize(600,400);
		setVisible(true);
		
		//currently just switches between views
		while (true) {
		    try {
		        Thread.sleep(1000);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    //Switches to the guest view 
		    //TO-DO add event listener to button that will transition to this
		    cardLayout.show(contentPane, "Guest");
		}
	}
	
	/**
	 * Changes the current card of the CardLayout to the specified ViewContent in the HRV 
	 * @param s the name of the ViewContent to change too
	 */
	public void changeView(String s) {
		cardLayout.show(this.getContentPane(), s);
	}

}
