package tema;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class StatisticsPanel1 extends JPanel{
	
	private ActionListener actionListener;
	private ActionListener actionListener2;
	private GridBagLayout gridBag=new GridBagLayout();
	private GridBagConstraints gbc=new GridBagConstraints();
	private Gestiune g=Gestiune.getInstance();
	private JButton button2,button3,button4,button5;
	
	public StatisticsPanel1(ActionListener p,ActionListener p2) {
		Color color=new Color(183,181,223);
		this.setBackground(color);
		actionListener=p;
		actionListener2=p2;
		this.setLayout(gridBag);
		gbc.insets=new Insets(5,5,5,5);
		
		ImageIcon statisticsImage=new ImageIcon("rsz_1statistics.png");
		JLabel statisticsMenu=new JLabel(" Statistics Menu ",JLabel.CENTER);
		statisticsMenu.setFont(new Font(" Arial ", Font.BOLD ,24));
		statisticsMenu.setBackground(Color.BLACK);
		statisticsMenu.setForeground(Color.BLACK);
		statisticsMenu.setIcon(statisticsImage);
		JButton button1=new JButton("Back");
		button1.addActionListener(p);
		button2=new JButton("Total cu taxe");
		button3=new JButton("Total pe tari");
		button4=new JButton("Total pe categorii");
		button5=new JButton("Factura maxima");
		button2.addActionListener(p2);
		button3.addActionListener(p2);
		button4.addActionListener(p2);
		button5.addActionListener(p2);
		
		gbc.gridwidth=5;
		gbc.gridheight=1;
		gbc.gridx=0;
		gbc.gridy=0;
		add(statisticsMenu,gbc);
		
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.gridx=0;
		gbc.gridy=1;
		add(button2,gbc);
		gbc.gridx=1;
		gbc.gridy=1;
		add(button3,gbc);
		gbc.gridx=2;
		gbc.gridy=1;
		add(button4,gbc);
		gbc.gridx=3;
		gbc.gridy=1;
		add(button5,gbc);
		gbc.gridx=4;
		gbc.gridy=1;
		add(button1,gbc);
	}
}
