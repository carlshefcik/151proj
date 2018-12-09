package hotelResevationSystem;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

/**
 * View that displays the day by day view of reservations in the system
 * @author Christian Castro
 *
 */
public class ReservationDayView extends ViewContent {
	private LocalDate date;
	private JPanel calendar;
	private JPanel text;
	private JTextArea resDisplay;
	private LocalDate resDate;
	private JPanel rooms;
	private JPanel roomText;
	
	public ReservationDayView(HotelReservationSystem hrs) {
		super(hrs);
		setLayout(new GridLayout(2,2));
		date=LocalDate.now();
		calendar=calendar();
		resDisplay=new JTextArea();
		text=new JPanel();
		text.add(resDisplay);
		rooms=new JPanel();
		rooms=viewByRoom();
		roomText=new JPanel();
        add(calendar);
        add(text);
        add(rooms);
        add(roomText);
	}
	
	/**
	 * Constructs container that displays Month and year of calendar along with Buttons to move to different month
	 * @return	JPanel
	 */
	public JPanel calHeader() {
		JPanel calHeader =new JPanel(true);
		calHeader.setMaximumSize(new Dimension(1200,10));
        calHeader.setLayout(new FlowLayout());

		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMMM YYY");
        JLabel month = new JLabel(formatter.format(date));
        
        JButton backButton = new JButton("<<");
        JButton nextButton = new JButton(">>");
        
        backButton.addActionListener(e -> {
        	date=date.minusMonths(1);
        	remove(calendar);
        	remove(text);
        	remove(rooms);
        	remove(roomText);
        	month.setText(formatter.format(date));
        	calendar=calendar();
        	add(calendar);
        	add(text);
        	add(rooms);
        	add(roomText);
        	this.revalidate();
        	this.repaint();
        });
        
        nextButton.addActionListener(e -> {
        	date=date.plusMonths(1);
        	
        	remove(calendar);
        	remove(text);
        	remove(rooms);
        	remove(roomText);
        	month.setText(formatter.format(date));
        	calendar=calendar();
        	add(calendar);
        	add(text);
        	add(rooms);
        	add(roomText);
        	revalidate();
        	repaint();
        });

        calHeader.add(backButton);
        calHeader.add(month);
        calHeader.add(nextButton);
        calHeader.revalidate();
        calHeader.repaint();
        return calHeader;
	}
	
	/**
	 * Method that constructs GUI of calendar 
	 * @return JPanel of calendar
	 */
	public JPanel calendar() {
		String[] days= {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
		JPanel calendar=new JPanel(true);
		calendar.setLayout(new BoxLayout(calendar,BoxLayout.Y_AXIS));
		JLabel label=new JLabel("Reservations by Day");
		label.setAlignmentX(CENTER_ALIGNMENT);
		JPanel header=calHeader();
		calendar.add(label);
		calendar.add(header);
		
		JPanel calBody=new JPanel(true);
		calBody.setPreferredSize(new Dimension((int)this.getBounds().getWidth(), this.getHeight()-80));
		FlowLayout layout=new FlowLayout(FlowLayout.LEFT);
		layout.setHgap(0);
		layout.setVgap(0);
		calBody.setLayout(layout);
		
		//Header for days of the week
		for(int i=0;i<days.length;i++) {
			JPanel dayPanel = new JPanel();
			dayPanel.setPreferredSize(new Dimension(81,25));
			JLabel dayLabel = new JLabel(days[i]);
			dayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			dayPanel.add(dayLabel);
			calBody.add(dayPanel);
		}
		
		//JPanel for days in the month
		LocalDate first=LocalDate.of(date.getYear(), date.getMonth(), 1);
		int day=first.getDayOfWeek().getValue();
		if(day!=7){
			for(int i=0;i!=day;i++) {
				JPanel dayPanel=new JPanel();
				dayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				dayPanel.setPreferredSize(new Dimension(81,30));
				calBody.add(dayPanel);
			}
		}
		
		for(int i=1;i<=date.lengthOfMonth();i++) {
			JPanel dayPanel=new JPanel();
			dayPanel.setPreferredSize(new Dimension(81,30));
			dayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			JLabel dayLabel=new JLabel(Integer.toString(i));
			
			dayPanel.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {	//Click on panel display reservations for day
					resDisplay.setText("");
					resDate=LocalDate.of(date.getYear(), date.getMonth(), Integer.parseInt(dayLabel.getText()));
					String res="Rooms Reserved:";
					try{
						for(Reservation r:hrs.getReservations(resDate)) {
							res=res+"\n"+r.resDetails();
						}
						if(res.equals("Rooms Reserved:"))
							res="No rooms reserved for this day.";
						else
							res=res+"\n\nRooms Available:\n"+hrs.getAvailableRooms(resDate);
					}
					catch(Exception ex){
						res="No rooms reserved for this day.";
					}
					remove(calendar);
		        	remove(text);
		        	remove(rooms);
		        	remove(roomText);
					resDisplay.setText(res);
					add(calendar);
		        	add(text);
		        	add(rooms);
		        	add(roomText);
		        	revalidate();
		        	repaint();
				}
			});
			
			dayPanel.add(dayLabel);
			calBody.add(dayPanel);
		}
		calendar.setBorder(BorderFactory.createLineBorder(Color.black));
		calendar.add(calBody);
		calendar.revalidate();
		calendar.repaint();
		return calendar;
	}
	
	/**
	 * Display of rooms available in the hotel and details for each room
	 * @return JPanel
	 */
	public JPanel viewByRoom() {
		rooms.setLayout(new BoxLayout(rooms,BoxLayout.Y_AXIS));
		JTextArea details=new JTextArea();
		JLabel header=new JLabel("Reservations By Room");
		header.setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel luxory=new JPanel();
		luxory.setAlignmentX(LEFT_ALIGNMENT);
		JLabel label1=new JLabel("Luxory Rooms: ");
		luxory.add(label1);
		for(String s:hrs.getLuxoryRooms()) {
			JButton roomButton = new JButton(s);
			roomButton.setPreferredSize(new Dimension(50,40));
			roomButton.addActionListener(e -> {
				remove(calendar);
	        	remove(text);
	        	remove(rooms);
	        	remove(roomText);
				details.setText(hrs.getResByRoom(s));
				roomText.add(details);
				add(calendar);
	        	add(text);
	        	add(rooms);
	        	add(roomText);
				revalidate();
				repaint();;
			});
			luxory.add(roomButton);
		}
		
		JPanel economic=new JPanel();
		economic.setAlignmentX(LEFT_ALIGNMENT);
		JLabel label2=new JLabel("Economic Rooms: ");
		economic.add(label2);
		for(String s:hrs.getEconomicRooms()) {
			JButton roomButton = new JButton();
			roomButton.setPreferredSize(new Dimension(50,40));
			roomButton.setText(s);
			roomButton.addActionListener(e -> {
				remove(calendar);
	        	remove(text);
	        	remove(rooms);
	        	remove(roomText);
				details.setText(hrs.getResByRoom(s));
				roomText.add(details);
				add(calendar);
	        	add(text);
	        	add(rooms);
	        	add(roomText);
				revalidate();
				repaint();
			});
			economic.add(roomButton);
		}
		rooms.setBorder(BorderFactory.createLineBorder(Color.black));
		rooms.add(header);
		rooms.add(luxory);
		rooms.add(economic);
		
		JButton backButton = new JButton("back");
		backButton.addActionListener(e ->{
			details.setText("");
			resDisplay.setText("");
			hrs.changeView("Manager Menu");
		});
		rooms.add(backButton);
		return rooms;
	}
}
