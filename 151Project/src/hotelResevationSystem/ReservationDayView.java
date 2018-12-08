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
	
	public ReservationDayView(HotelReservationSystem hrs) {
		super(hrs);
		setLayout(new GridLayout(1,1));
		date=LocalDate.now();
		calendar=calendar();
		resDisplay=new JTextArea();
		text=new JPanel();
		text.add(resDisplay);
		
		
		setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption));
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        add(calendar);
        add(text);
	}
	
	/**
	 * Constructs container that displays Month and year of calendar along with Buttons to move to different month
	 * @return	JPanel
	 */
	public JPanel calHeader() {
		JPanel calHeader =new JPanel(true);
        calHeader.setLayout(new FlowLayout());
        calHeader.setBackground(Color.WHITE);
        calHeader.setPreferredSize(new Dimension(this.getWidth(),20));
        
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMMM YYY");
        JLabel month = new JLabel(formatter.format(date));
        month.setForeground(SystemColor.activeCaption);
        
        JButton backButton = new JButton("<<");
        JButton nextButton = new JButton(">>");
        
        backButton.addActionListener(e -> {
        	date=date.minusMonths(1);
        	this.remove(calendar);
        	this.remove(text);
        	month.setText(formatter.format(date));
        	calendar=calendar();
        	this.add(calendar);
        	this.add(text);
        	this.revalidate();
        	this.repaint();
        });
        
        nextButton.addActionListener(e -> {
        	date=date.plusMonths(1);
        	remove(calendar);
        	remove(text);
        	month.setText(formatter.format(date));
        	calendar=calendar();
        	add(calendar);
        	add(text);
        	revalidate();
        	repaint();
        });
        
        calHeader.setPreferredSize(new Dimension(10,60));;
        calHeader.add(backButton, BorderLayout.WEST);
        calHeader.add(month, BorderLayout.CENTER);
        calHeader.add(nextButton, BorderLayout.EAST);
        return calHeader;
	}
	
	/**
	 * Method that constructs GUI of gregorian calendar 
	 * @return JPanel of calendar
	 */
	public JPanel calendar() {
		String[] days= {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
		JPanel calendar=new JPanel(true);
		calendar.setLayout(new BoxLayout(calendar,BoxLayout.Y_AXIS));
		calendar.add(calHeader());
		
		JPanel calBody=new JPanel(true);
		calBody.setPreferredSize(new Dimension((int)this.getBounds().getWidth(), this.getHeight()));
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
					resDate=LocalDate.of(date.getYear(), date.getMonth(), Integer.parseInt(dayLabel.getText()));
					String res="";
					try{
						for(Reservation r:hrs.getReservations(resDate)) {
							res=res+r.resDetails()+"\n";
						}
						if(res.equals(""))
							res="No reservations made for this day.";
					}
					catch(Exception ex){
						res="No reservations made for this day.";
					}
					remove(calendar);
					remove(text);
					resDisplay.setText(res);
					add(calendar);
					add(text);
		        	revalidate();
		        	repaint();
				}
			});
			
			dayPanel.add(dayLabel);
			calBody.add(dayPanel);
		}
		calendar.add(calBody);
		return calendar;
	}
}
