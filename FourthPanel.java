package tema;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class FourthPanel extends JPanel{
	
	JPanel panel1,panel2,panel3,panel4,panel5;
	private CardLayout cardLayout = new CardLayout();
	private ActionListener actionListener2=new ActiuniStatistici(this,cardLayout);
	
	public FourthPanel(ActionListener p) {
		setLayout(cardLayout);
		panel1=new StatisticsPanel1(p,actionListener2);
		add(panel1,"panel1");
		panel2=new StatisticsPanel2(actionListener2);
		add(panel2,"panel2");
		panel3=new StatisticsPanel3(actionListener2);
		add(panel3,"panel3");
		panel4=new StatisticsPanel4(actionListener2);
		add(panel4,"panel4");
		panel5=new StatisticsPanel5(actionListener2);
		add(panel5,"panel5");
		cardLayout.show(this, "panel1");
	}
}
