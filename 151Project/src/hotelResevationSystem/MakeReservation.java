package hotelResevationSystem;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MakeReservation extends ViewContent{
	private HotelReservationSystem hrs;
	private RoomAvaliabilityView roomView;
	private TimeInterval ti;
	private HashMap<String, Reservation> reservations;
	private boolean luxory;

	public MakeReservation(HotelReservationSystem hrs) {
		super(hrs);
		this.hrs = hrs;
		luxory = true;
		
		setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		
		JPanel title = new JPanel();
		title.setLayout(new BoxLayout(title, BoxLayout.X_AXIS));
		
		JTextField tf = new JTextField();
		tf.setEditable(false);
		tf.setText("Make Reservations");
		title.add(tf);
		
		JButton menuButton = new JButton("Guest Menu");
		title.add(menuButton);
		
		top.add(title);
		
		
		JPanel findRes = new JPanel();
		findRes.setLayout(new BoxLayout(findRes,BoxLayout.Y_AXIS));
		
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new BoxLayout(datePanel,BoxLayout.X_AXIS));
		
		JPanel date1Panel = new JPanel();
		date1Panel.setLayout(new BoxLayout(date1Panel,BoxLayout.Y_AXIS));
		JLabel date1Label = new JLabel("Check In Date:");
		JTextField date1 = new JTextField();
		date1.setSize(this.getWidth()/5,this.getHeight()/4);
		date1Panel.add(date1Label);
		date1Panel.add(date1);
		
		JPanel date2Panel = new JPanel();
		date2Panel.setLayout(new BoxLayout(date2Panel,BoxLayout.Y_AXIS));
		JLabel date2Label = new JLabel("Check Out Date:");
		JTextField date2 = new JTextField();
		date2.setSize(this.getWidth()/5,this.getHeight()/4);
		date2Panel.add(date2Label);
		date2Panel.add(date2);
		
		datePanel.add(date1Panel);
		datePanel.add(date2Panel);
		findRes.add(datePanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		
		JRadioButton luxoryButton = new JRadioButton("$300");
		luxoryButton.setMnemonic(KeyEvent.VK_B);
		luxoryButton.setActionCommand("luxory");
		luxoryButton.setSelected(true);

	    JRadioButton economyButton = new JRadioButton("$100");
	    economyButton.setMnemonic(KeyEvent.VK_C);
	    economyButton.setActionCommand("economy");
	    
	    ButtonGroup group = new ButtonGroup();
	    group.add(luxoryButton);
	    group.add(economyButton);
	    
	    buttonPanel.add(luxoryButton);
	    buttonPanel.add(economyButton);

		
		JButton findButton = new JButton("Check Avaliability");
		buttonPanel.add(findButton);
		
		findRes.add(buttonPanel);
		top.add(findRes);
		
		JTextField status = new JTextField();
		status.setEditable(false);
		status.setText("Date format is MM/dd/yyyy");
		
		top.add(status);
		
		add(top,  BorderLayout.NORTH);
		
		findButton.addActionListener(e ->{
			//parse the dates and check if it is over 60 days
			String[] start = date1.getText().split("/");
			String[] end = date2.getText().split("/");
			if (start.length == 3 && end.length == 3) {
				int[] startInt = new int[3];
				int[] endInt = new int[3];
				for(int i=0; i<3;i++) {
					startInt[i] = Integer.parseInt(start[i]);
					endInt[i] = Integer.parseInt(end[i]);
				}
				TimeInterval tempTi = new TimeInterval(LocalDate.of(startInt[2], startInt[0], startInt[1]), 
								LocalDate.of(endInt[2], endInt[0], endInt[1]));
					if(!tempTi.over60()) {
						ti = tempTi;
						hrs.sop("Looking for avaliable rooms: " + tempTi.toString());
						roomView.ti = tempTi;
						roomView.roomSearch(tempTi, luxory);
					} else {
						status.setText("Cannot have reservations lasting more than 60 days.");
					}
			} else {
				status.setText("Incorrect date formats. Date format is MM/dd/yyyy");
			}
		});
		
		menuButton.addActionListener(e ->{
			changeView("Guest Menu");
		});
		
		luxoryButton.addActionListener(e -> {
			luxory = true;
			JOptionPane.showMessageDialog(this, "thank you for using java");

		});
		
		economyButton.addActionListener(e -> {
			luxory = false;
		});
		
		RoomAvaliabilityView rav = new RoomAvaliabilityView(hrs);
		this.roomView = rav;
		
		add(rav, BorderLayout.CENTER);
	}
	
	/**
	 * Updates the reservations map
	 */
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        reservations = hrs.getReservations();
	}
	
	/**
	 * Inner class that is rendered in the MakeReservationViewer view
	 * @author Carl Shefcik
	 *
	 */
	public class RoomAvaliabilityView extends ViewContent {
		private JTextField tf;
		private ArrayList<String> rooms;
		public TimeInterval ti;

		public RoomAvaliabilityView(HotelReservationSystem hrs) {
			super(hrs);
			
			setLayout(new BorderLayout());
			
			JPanel pane = new JPanel();
			GroupLayout layout1 = new GroupLayout(pane);
			pane.setLayout(layout1);
			
			layout1.setAutoCreateGaps(true);
			layout1.setAutoCreateContainerGaps(true);
			
			tf = new JTextField("Avaliable Rooms: ", 10);
			tf.setEditable(false);
			tf.setSize(this.getWidth()/4, this.getHeight()/4);
			
			
			JLabel roomLabel = new JLabel("Enter room:");
			JTextField roomNumber = new JTextField("", 10);

			
			JTextField status = new JTextField("No reservation made yet.");
			status.setEditable(false);
	        this.add(status, BorderLayout.SOUTH);
			
			JButton confirmButton = new JButton("Confirm");
			confirmButton.addActionListener(e ->{
				if(rooms != null && !rooms.isEmpty()) {
					if(rooms.contains(roomNumber.getText())) {
						
						hrs.makeReservation(ti, roomNumber.getText());
						status.setText("Reservation complete!");
					}
				} else {
					status.setText("Invalid reservation!");
				}
			});
			
			JButton doneButton = new JButton("Done");
			doneButton.addActionListener(e ->{
				changeView("Guest Menu");
			});

			
			layout1.setHorizontalGroup(
			   layout1.createSequentialGroup()
			      .addComponent(tf)
			      .addComponent(roomLabel)
			      .addComponent(roomNumber)
			      .addComponent(confirmButton)
			      .addComponent(doneButton)
			);
			layout1.setVerticalGroup(
			   layout1.createSequentialGroup()
			      .addComponent(tf)
			      .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    		  .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    				  .addComponent(roomLabel)
				    		  .addComponent(roomNumber))
		    		  .addComponent(confirmButton)
				      .addComponent(doneButton))
			);
					
			
			this.add(pane, BorderLayout.CENTER);
			
		}
		
		public void roomSearch(TimeInterval ti, boolean luxory) {
			//search for avaliability
			//puts all the rooms in a list
			rooms = new ArrayList<>();
			if(luxory) 
				rooms.addAll(Arrays.asList(hrs.LUXORY_ROOMS));
			else 
				rooms.addAll(Arrays.asList(hrs.ECONOMIC_ROOMS));
			
			//removes the rooms that have a time conflict
			for (Reservation r :reservations.values()) 
				if(r.dateOverlap(ti)) 
					rooms.remove(r.getRoom());
			//update the view
			if(rooms.isEmpty()) {
				tf.setText("There are no rooms for your selected stay!");
			} else {
				String results = "Avaliable Rooms: \n";
				for (String s : rooms) 
					results += s + ", ";
				tf.setText(results);
			}
			
		}
		
		
		
		
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        tf.setText("Avaliable Rooms:");
        }
		
	}
}
