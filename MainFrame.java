package tema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame{
	
	private JButton button1,button2;
	JPanel contentPanel=new JPanel();
	private CardLayout cardLayout = new CardLayout();
	private ActionListener actionListener=new Actiuni(contentPanel,cardLayout,this);
	JPanel panel1,panel2,panel3,panel4,loginPanel;
	
	public MainFrame(){
		super("ContaPRO");
		setSize(1000,700);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension dim =tk.getScreenSize();
		int xPos=(dim.width/2) - (getWidth()/2);
		int yPos=(dim.height/2) - (getHeight()/2);
		setLocation(xPos,yPos);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		panel1=new FirstPanel(actionListener);
		panel2=new SecondPanel(actionListener);
		loginPanel=new LoginPanel(contentPanel,cardLayout);
		
		contentPanel.setLayout(cardLayout);
		contentPanel.add(loginPanel,"login");
		contentPanel.add(panel1,"panel1");
		contentPanel.add(panel2, "panel2");
		this.setContentPane(contentPanel);
		cardLayout.show(contentPanel, "login");
		this.setVisible(true);
	}


	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		MainFrame x=new MainFrame();
	}

}
