package hotelResevationSystem;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * HotelReservationViewer is the View of the MVC
 * @author Carl Shefcik
 *
 */
public class HotelReservationViewer extends JFrame {
	private HotelReservationSystem hrs;
	
	public HotelReservationViewer(HotelReservationSystem hrs) {
		this.hrs = hrs;
		setLocation(0,200);
		setLayout(new BorderLayout());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
