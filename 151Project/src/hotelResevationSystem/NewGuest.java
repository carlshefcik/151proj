package hotelResevationSystem;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewGuest  extends ViewContent {

	public NewGuest(HotelReservationSystem hrs)
	{
		super(hrs);
		setAlignmentX(CENTER_ALIGNMENT);
		this.setLayout(new FlowLayout());
		JLabel tf = new JLabel("Welcome new guest");
		add(tf);
		
		JPanel textPane=new JPanel();
		textPane.setLayout(new BoxLayout(textPane,BoxLayout.Y_AXIS));
		JLabel unLabel=new JLabel("Enter a Username:");
		JTextField uID=new JTextField("",10);
		uID.setSize(this.getWidth()/5,this.getHeight()/4);
		JLabel pwLabel=new JLabel("Enter a Password:");
		JPasswordField pw=new JPasswordField("",10);
		textPane.add(unLabel);
		textPane.add(uID);
		textPane.add(pwLabel);
		textPane.add(pw);
	
		JPanel buttonPane=new JPanel();
		JButton signUpButton = new JButton("Sign up");
		JButton backButton = new JButton("Back");
		buttonPane.add(signUpButton);
		buttonPane.add(backButton);
		
		add(textPane);
		add(buttonPane);
		
		signUpButton.addActionListener(e ->{
			String ID = hrs.newUser(uID.getText(), String.valueOf(pw.getPassword()));
			uID.setText("");
			pw.setText("");
			changeView("Guest Menu");
			
			JOptionPane.showMessageDialog(null, "Your ID is " + ID);
			
		});
		
		backButton.addActionListener(e ->{
			changeView("Main Menu");
		});
		// TODO Auto-generated constructor stub
	}

}
