package hotelResevationSystem;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.*;

public class ReservationDayView extends ViewContent {
	private LocalDate date;
	private JPanel body;
	
	public ReservationDayView(HotelReservationSystem hrs) {
		super(hrs);
		setLayout(new GridLayout(2,1));
		date=LocalDate.now();
		body=calBody();
		
		setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption));
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        add(calHeader());
        add(body); //change listener?
	}
	
	public JPanel calHeader() {
        //calHeader.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption));
		JPanel calHeader =new JPanel(true);
        calHeader.setLayout(new FlowLayout());
        calHeader.setBackground(Color.WHITE);
        
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMMM YYY");
        JLabel month = new JLabel(formatter.format(date));
        month.setForeground(SystemColor.activeCaption);
        
        JButton backButton = new JButton("<");
        JButton nextButton = new JButton(">");
        
        backButton.addActionListener(e -> {
        	date=date.minusMonths(1);
        	this.remove(body);
        	month.setText(formatter.format(date));
        	body=calBody();
        	this.add(body);
        	this.revalidate();
        	this.repaint();
        });
        
        nextButton.addActionListener(e -> {
        	date=date.plusMonths(1);
        	this.remove(body);
        	month.setText(formatter.format(date));
        	body=calBody();
        	this.add(body);
        	this.revalidate();
        	this.repaint();
        });
        
        calHeader.setPreferredSize(new Dimension(10,60));;
        calHeader.add(backButton, BorderLayout.WEST);
        calHeader.add(month, BorderLayout.CENTER);
        calHeader.add(nextButton, BorderLayout.EAST);
        return calHeader;
	}
	
	public JPanel calBody() {
		String[] days= {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
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
		else 
			day=0;
		
		for(int i=1;i<=date.lengthOfMonth();i++) {
			JPanel dayPanel=new JPanel();
			dayPanel.setPreferredSize(new Dimension(81,30));
			JLabel dayLabel=new JLabel(Integer.toString(i));
			dayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			dayPanel.addMouseListener(new MouseAdapter() {
				public void MousePressed(MouseEvent e) {	//Click on panel display reservations for day
					
				}
				
			});
			dayPanel.add(dayLabel);
			calBody.add(dayPanel);
		}
		
		return calBody;
	}
	
	public void refresh() {
		this.repaint();
	}
}