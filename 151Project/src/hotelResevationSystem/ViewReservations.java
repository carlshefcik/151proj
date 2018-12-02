package hotelResevationSystem;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ViewReservations extends ViewContent {

	public ViewReservations(HotelReservationSystem hrs) {
		super(hrs);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JTextField tf = new JTextField();
		tf.setText("View Reservations");
		add(tf);
		
		JButton menuButton = new JButton("Guest Menu");
		add(menuButton);
		
		
		menuButton.addActionListener(e ->{
			changeView("Guest Menu");
		});
		// TODO Auto-generated constructor stub
	}

}
