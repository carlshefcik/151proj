package hotelResevationSystem;

import java.awt.FlowLayout;

import javax.swing.JTextField;

public class GuestLogin extends ViewContent {

	public GuestLogin(HotelReservationSystem hrs) {
		super(hrs);
		setLayout(new FlowLayout());
		JTextField tf = new JTextField();
		tf.setText("Guest Login");
		add(tf);
		// TODO Auto-generated constructor stub
	}

}
