package hotelResevationSystem;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ManagerMenu extends ViewContent {

	public ManagerMenu(HotelReservationSystem hrs) {
		super(hrs);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JTextField tf = new JTextField();
		tf.setText("Manager Menu");
		add(tf);
		
		JButton logoutButton = new JButton("Log out");
		JButton dayViewButton = new JButton("Day View");
		add(dayViewButton);
		add(logoutButton);
		
		
		logoutButton.addActionListener(e ->{
			changeView("Main Menu");
		});
		
		dayViewButton.addActionListener(e ->{
			changeView("View Reservations");
		});
		
		// TODO Auto-generated constructor stub
	}

}

