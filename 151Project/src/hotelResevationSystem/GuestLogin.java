package hotelResevationSystem;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.*;


public class GuestLogin extends ViewContent {

	public GuestLogin(HotelReservationSystem hrs) {
		super(hrs);
		setAlignmentX(CENTER_ALIGNMENT);
		this.setLayout(new FlowLayout());
		JTextArea tf = new JTextArea();
		
		JPanel textPane=new JPanel();
		textPane.setLayout(new BoxLayout(textPane,BoxLayout.Y_AXIS));
		JLabel unLabel=new JLabel("UserID:");
		JTextField uID=new JTextField("",10);
		uID.setSize(this.getWidth()/5,this.getHeight()/4);
		JLabel pwLabel=new JLabel("Password:");
		JPasswordField pw=new JPasswordField("",10);
		textPane.add(unLabel);
		textPane.add(uID);
		textPane.add(pwLabel);
		textPane.add(pw);
	
		JPanel buttonPane=new JPanel();
		JButton loginButton = new JButton("Login");
		JButton backButton = new JButton("Back");
		buttonPane.add(loginButton);
		buttonPane.add(backButton);
		
		add(textPane);
		add(buttonPane);
		
		loginButton.addActionListener(e ->{
			if(hrs.userLogin(false, uID.getText(), String.valueOf(pw.getPassword()))) {
				uID.setText("");
				pw.setText("");
				changeView("Guest Menu");
			}
			else {
				JOptionPane.showMessageDialog(null, "Wrong UserID or password!", "Error", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		backButton.addActionListener(e ->{
			changeView("Main Menu");
		});
		// TODO Auto-generated constructor stub
	}

}
