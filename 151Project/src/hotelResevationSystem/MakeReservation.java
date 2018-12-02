package hotelResevationSystem;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class MakeReservation extends ViewContent{

	public MakeReservation(HotelReservationSystem hrs) {
		super(hrs);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JTextField tf = new JTextField();
		tf.setText("Make Reservations");
		add(tf);
		
		JButton menuButton = new JButton("Guest Menu");
		add(menuButton);
		
		
		menuButton.addActionListener(e ->{
			changeView("Guest Menu");
		});
		// TODO Auto-generated constructor stub
	}

}
