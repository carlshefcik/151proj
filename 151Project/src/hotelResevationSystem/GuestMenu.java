package hotelResevationSystem;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class GuestMenu extends ViewContent{

	public GuestMenu(HotelReservationSystem hrs) {
		super(hrs);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JTextField tf = new JTextField();
		tf.setText("Guest Menu");
		add(tf);
				
		JButton makeReservationButton = new JButton("Make Reservation");
		JButton viewReservationButton = new JButton("View Reservation");
		JButton logoutButton = new JButton ("Log out");
		
		add(makeReservationButton);
		add(viewReservationButton);
		add(logoutButton);
		
		
		makeReservationButton.addActionListener(e ->{
			changeView("Make Reservation");
		});
		
		viewReservationButton.addActionListener(e ->{
			changeView("View Reservations");
		});
		
		logoutButton.addActionListener(e -> {
			changeView("Main Menu");
		});
		// TODO Auto-generated constructor stub
	}

}
