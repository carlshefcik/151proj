/**
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
*/
package hotelResevationSystem;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

public class ViewReservations extends ViewContent {

	private User currentUser;
	
	public ViewReservations(HotelReservationSystem hrs) {
		super(hrs);
		currentUser = hrs.getCurrentUser();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JTextField tf = new JTextField();
		tf.setText("View Reservations");
		add(tf);

		hrs.sop("Repainted");
		if(currentUser == null)
		{
			JLabel error = new JLabel(" Sorry, error ");
			add(error);
		}
		else
		{
			JLabel yay = new JLabel(" Cool ");
			add(yay);
		}
		
		JButton menuButton = new JButton("Guest Menu");
		add(menuButton);
		
		
		menuButton.addActionListener(e ->{
			changeView("Guest Menu");
		});
		
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		currentUser = hrs.getCurrentUser();
		hrs.sop("Current User: " + currentUser.getUsername());
		super.stateChanged(e);
	}

}
