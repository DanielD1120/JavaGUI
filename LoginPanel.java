package tema;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel implements ActionListener {
	
	private GridBagLayout gridBag=new GridBagLayout();
	private GridBagConstraints gbc=new GridBagConstraints();
	JButton button1,button2,button3;
	private JPanel contentPanel;
	private CardLayout cardLayout;
	private JTextField userField;
	private JPasswordField passwordField;
	private TreeMap <String,String> us;
	private File f=new File("users.txt");
	private JTextField newuser=new JTextField();
	private JTextField newpassword=new JPasswordField();
	Object [] message= {"New Username: ",newuser,"Password: ",newpassword};
	
	private void putUserPass() {
		us=new TreeMap<>();
		String line;
		Scanner in= null;
		try {
			in = new Scanner(f);
			while (in.hasNextLine()) {
				line=in.nextLine();
				StringTokenizer stk=new StringTokenizer(line);
				String user=stk.nextToken();
				String parola=stk.nextToken();
				us.put(user, parola);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (in!=null) {
				in.close();
			}
		}
	}
	
	public LoginPanel(JPanel cp,CardLayout k) {
		Color color=new Color(183,181,223);
		this.setBackground(color);
		contentPanel=cp;
		cardLayout=k;
		this.setLayout(gridBag);
		gbc.insets=new Insets(5,5,5,5);
		
		putUserPass();
		
		JLabel userLabel=new JLabel("Username:");
		userField=new JTextField(10);
		JLabel passwordLabel=new JLabel("Password:");
		passwordField=new JPasswordField(10);
		
		ImageIcon loginImage=new ImageIcon("rsz_login.png");
		JLabel imageLabel=new JLabel(loginImage);
		
		gbc.gridwidth=2;
		gbc.gridheight=1;
		gbc.gridx=0;
		gbc.gridy=0;
		add(imageLabel,gbc);
		
		gbc.anchor=GridBagConstraints.LINE_END;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.gridx=0;
		gbc.gridy=1;
		add(userLabel,gbc);
		
		gbc.anchor=GridBagConstraints.LINE_START;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.gridx=1;
		gbc.gridy=1;
		add(userField,gbc);
		
		gbc.anchor=GridBagConstraints.LINE_END;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.gridx=0;
		gbc.gridy=2;
		add(passwordLabel,gbc);
		
		gbc.anchor=GridBagConstraints.LINE_START;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.gridx=1;
		gbc.gridy=2;
		add(passwordField,gbc);
		
		button1=new JButton("Login");
		button2=new JButton("Reset");
		button3=new JButton("New Account");
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.LINE_END;
		gbc.gridx=0;
		gbc.gridy=3;
		add(button1,gbc);
		gbc.anchor=GridBagConstraints.LINE_START;
		gbc.gridx=1;
		gbc.gridy=3;
		add(button2,gbc);
		gbc.anchor=GridBagConstraints.LINE_END;
		gbc.gridwidth=2;
		gbc.gridheight=1;
		gbc.gridx=0;
		gbc.gridy=4;
		add(button3,gbc);
	}

	public void actionPerformed(ActionEvent e) {
		JButton button= (JButton) e.getSource();
		if (button.getText().equals("Login")) {
			String user=userField.getText();
			String pass=passwordField.getText();
			
			if (!us.containsKey(user)) {
				JOptionPane.showMessageDialog(null,"Invalid username or password!","Unsuccesfull login",JOptionPane.WARNING_MESSAGE);
				userField.setText("");
				passwordField.setText("");
			}
			else {
				String passValid=us.get(user);
				if (!pass.equals(passValid)) {
					JOptionPane.showMessageDialog(null,"Invalid username or password!","Unsuccesfull login",JOptionPane.WARNING_MESSAGE);
					userField.setText("");
					passwordField.setText("");
				}
				else {
					cardLayout.show(contentPanel, "panel1");
					JOptionPane.showMessageDialog(null,"You are loged in as " + user + "!");
				}
			}
		}
		
		if (button.getText().equals("Reset")){
			userField.setText("");
			passwordField.setText("");
		}
		
		if (button.getText().equals("New Account")){
			int option = JOptionPane.showConfirmDialog(null, message, "New Account", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				String user=newuser.getText();
				String pass=newpassword.getText();
				us.put(user, pass);
				
				FileWriter fw=null;
				PrintWriter out=null;
				try {
					fw=new FileWriter(f,true);
					out=new PrintWriter(fw);
					out.println();
					out.print(user + " " + pass);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				finally {
					try {
						fw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			} 
		}
	}
}
