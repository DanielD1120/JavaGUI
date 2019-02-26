package tema;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class FirstPanel extends JPanel {
	private ActionListener actionListener;
	private GridBagLayout gridBag=new GridBagLayout();
	private GridBagConstraints gbc=new GridBagConstraints();
	JButton button1,button2,button3;
	
	public FirstPanel(ActionListener p) {
		Color color=new Color(183,181,223);
		this.setBackground(color);
		actionListener=p;
		this.setLayout(gridBag);
		gbc.insets=new Insets(5,5,5,5);
		
		JLabel products=new JLabel(" ContaPRO ",JLabel.CENTER);
		products.setFont(new Font(" Arial ", Font.BOLD ,24));
		products.setBackground(Color.BLACK);
		products.setForeground(Color.BLACK);
		
		gbc.gridwidth=4;
		gbc.gridheight=1;
		gbc.gridx=0;
		gbc.gridy=0;
		add(products,gbc);
		
		gbc.gridwidth=1;
		gbc.gridheight=1;
		button1=new JButton("Incarcare");
		button2=new JButton("Produse");
		button3=new JButton("Statistici");
		button1.addActionListener(actionListener);
		button2.addActionListener(actionListener);
		button3.addActionListener(actionListener);
		button2.setEnabled(false);
		button3.setEnabled(false);
		gbc.gridx=0;
		gbc.gridy=1;
		add(button1,gbc);
		gbc.gridx=1;
		gbc.gridy=1;
		add(button2,gbc);
		gbc.gridx=2;
		gbc.gridy=1;
		add(button3,gbc);	
		
		
		ImageIcon accountantImage=new ImageIcon("rsz_accountant.png");
		JLabel accountantLabel=new JLabel(accountantImage);
		gbc.gridwidth=3;
		gbc.gridheight=1;
		gbc.gridx=0;
		gbc.gridy=2;
		add(accountantLabel,gbc);	
	}
}
