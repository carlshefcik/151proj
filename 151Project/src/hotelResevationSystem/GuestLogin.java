package hotelResevationSystem;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class GuestLogin extends ViewContent {

	public GuestLogin(HotelReservationSystem hrs) {
		super(hrs);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JTextField tf = new JTextField();
		tf.setText("Guest Login");
		
		
		add(tf);
				
		JButton loginButton = new JButton("Login");
		JButton backButton = new JButton("Back");
		
		add(loginButton);
		add(backButton);
		
		
		loginButton.addActionListener(e ->{
			changeView("Guest Menu");
		});
		
		backButton.addActionListener(e ->{
			changeView("Main Menu");
		});
		// TODO Auto-generated constructor stub
	}

}
