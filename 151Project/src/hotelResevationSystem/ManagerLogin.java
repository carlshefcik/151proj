package hotelResevationSystem;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ManagerLogin extends ViewContent {

	public ManagerLogin(HotelReservationSystem hrs) {
		super(hrs);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JTextField tf = new JTextField();
		tf.setText("Manager Login");
		
		
		add(tf);
				
		JButton loginButton = new JButton("Login");
		JButton backButton = new JButton("Back");
		
		add(loginButton);
		add(backButton);
		
		
		loginButton.addActionListener(e ->{
			changeView("Manager Menu");
		});
		
		backButton.addActionListener(e ->{
			changeView("Main Menu");
		});
		// TODO Auto-generated constructor stub
	}

}
