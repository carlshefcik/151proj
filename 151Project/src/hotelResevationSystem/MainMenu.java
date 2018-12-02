package hotelResevationSystem;

import java.awt.FlowLayout;

import javax.swing.JTextField;

public class MainMenu extends ViewContent {

	public MainMenu(HotelReservationSystem hrs) {
		super(hrs);
		setLayout(new FlowLayout());
		JTextField tf = new JTextField();
		tf.setText("Main Menu");
		add(tf);
		// TODO Auto-generated constructor stub
	}

}
