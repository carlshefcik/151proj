
package hotelResevationSystem;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.Graphics;


public class ViewReservations extends ViewContent {

	private User currentUser;
	private JTextArea message;
	
	public ViewReservations(HotelReservationSystem hrs) {
		super(hrs);
		currentUser = hrs.getCurrentUser();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JLabel tf = new JLabel("View Reservations");
		add(tf);

		if(currentUser == null)
		{
			message = new JTextArea(" Sorry, error ");
			add(message);
		}
		else
		{
			message = new JTextArea(" Cool ");
			add(message);
		}
		
		JButton menuButton = new JButton("Guest Menu");
		add(menuButton);
		
		
		menuButton.addActionListener(e ->{
			changeView("Guest Menu");
		});
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		currentUser = hrs.getCurrentUser();
		repaint();
		super.stateChanged(e);
	}
	
	public void paint(Graphics g)
	{
		revalidate();
		if(currentUser !=null)
		{
			message.setText("Hello " + currentUser.getUsername() + "\n \nReservations are below: \n\n" + currentUser.getReservations());
		}
		super.paintComponents(g);
	}

}
