package hotelResevationSystem;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MakeReservation extends ViewContent{
	private HotelReservationSystem hrs;
	private int data;
	private ChangeListener roomView;
	private TimeInterval ti;
	
	
	public MakeReservation(HotelReservationSystem hrs) {
		super(hrs);
		this.hrs = hrs;
		
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
				ti = new TimeInterval(LocalDate.of(startInt[2], startInt[0], startInt[1]), 
								LocalDate.of(endInt[2], endInt[0], endInt[1]));
					if(!ti.over60()) {
						hrs.sop("Looking for avaliable rooms");
						roomView.stateChanged(new ChangeEvent(this));
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
		
		RoomAvaliabilityView rav = new RoomAvaliabilityView(hrs);
		this.roomView = rav;
		
		add(rav, BorderLayout.CENTER);
	}
	
	//I think this does nothing
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
	}
	
	public void actionPerformed(ActionEvent e) {
	    hrs.sop(e.getActionCommand());
	}
	
	
	public class RoomAvaliabilityView extends ViewContent {
		private JTextField tf;
		
		public RoomAvaliabilityView(HotelReservationSystem hrs) {
			super(hrs);
			
			GroupLayout layout1 = new GroupLayout(this);
			setLayout(layout1);
			
			layout1.setAutoCreateGaps(true);
			layout1.setAutoCreateContainerGaps(true);
			
			JButton c1 = new JButton("Button 1");
			JButton c2 = new JButton("Button 2");
			JButton c3 = new JButton("Button 3");
			JButton c4 = new JButton("Button 4");
			
			layout1.setHorizontalGroup(
			   layout1.createSequentialGroup()
			      .addComponent(c1)
			      .addComponent(c2)
			      .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
			           .addComponent(c3)
			           .addComponent(c4))
			);
			layout1.setVerticalGroup(
			   layout1.createSequentialGroup()
			      .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.BASELINE)
			           .addComponent(c1)
			           .addComponent(c2)
			           .addComponent(c3))
			      .addComponent(c4)
			);
					
			
//			this.add(pane, BorderLayout.CENTER);
//			this.tf = new JTextField();
//	        this.add(tf, BorderLayout.SOUTH);
		}
		
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        
//	        this.tf.setText(""+data);
		}
		
	}
}
