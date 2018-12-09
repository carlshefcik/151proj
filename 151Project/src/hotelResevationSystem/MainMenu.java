package hotelResevationSystem;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class MainMenu extends ViewContent {

	public MainMenu(HotelReservationSystem hrs) {
		super(hrs);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JTextField tf = new JTextField();
		tf.setText("Main Menu");
		
		JButton newGuestButton = new JButton("Sign up");
		JButton guestButton = new JButton("Guest Login");
		JButton managerButton = new JButton("Manager Login");
		
		
		add(tf);
		
		add(guestButton);
		add(managerButton);
		
		
		guestButton.addActionListener(e ->{
			changeView("Guest Login");
		});
		
		newGuestButton.addActionListener(e ->{
			changeView("New Guest");
		});
		
		
		managerButton.addActionListener(e -> {
			changeView("Manager Login");
		});
		
		// TODO Auto-generated constructor stub
	}

}
