package hotelResevationSystem;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public abstract class ViewContent extends JPanel implements ChangeListener{
	HotelReservationSystem hrs;
	
	public ViewContent(HotelReservationSystem hrs) {
		super();
		this.hrs = hrs;
	}
	
	/**
	 * Changes the current card of the CardLayout to the specified ViewContent in the HRV
	 * @param s the name of the ViewContent to change too
	 */
	public void changeView(String s) {
		hrs.changeView(s);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		revalidate();
		repaint();
		hrs.sop("Revalidated");
	}
}
