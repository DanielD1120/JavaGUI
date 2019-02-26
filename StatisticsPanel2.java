package tema;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class StatisticsPanel2 extends JPanel implements ActionListener {
	
	private ActionListener actionListener;
	private GridBagLayout gridBag=new GridBagLayout();
	private GridBagConstraints gbc=new GridBagConstraints();
	private Gestiune g=Gestiune.getInstance();
	
	public StatisticsPanel2(ActionListener p) {
		Color color=new Color(183,181,223);
		this.setBackground(color);
		actionListener=p;
		this.setLayout(gridBag);
		gbc.insets=new Insets(5,5,5,5);
		
		JLabel statisticsMenu=new JLabel(" Option 1 ",JLabel.CENTER);
		statisticsMenu.setFont(new Font(" Arial ", Font.BOLD ,24));
		statisticsMenu.setBackground(Color.BLACK);
		statisticsMenu.setForeground(Color.BLACK);
		JButton button1=new JButton("Back");
		button1.addActionListener(p);
		gbc.gridwidth=4;
		gbc.gridheight=1;
		gbc.gridx=0;
		gbc.gridy=0;
		add(statisticsMenu,gbc);
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.gridx=0;
		gbc.gridy=1;
		add(button1,gbc);
		
		DefaultPieDataset dataset=new DefaultPieDataset();
		for (int i=0;i<g.getMagazine().size();i++) {
			Magazin k=g.getMagazine().get(i);
			dataset.setValue(k.nume,k.getTotalCuTaxe() );
		}
		JFreeChart chart=ChartFactory.createPieChart("Total cu taxe", dataset,true,true,false);
		ChartPanel chartpanel=new ChartPanel(chart);
		gbc.gridx=0;
		gbc.gridy=2;
		add(chartpanel,gbc);
		
		DecimalFormat df =new DecimalFormat("#.#####");
		Magazin maximMag=getMaximMagazin();
		JLabel infoMag=new JLabel("Magazin cu cele mai mari vanzari: " + maximMag.nume + " " + df.format(maximMag.getTotalFaraTaxe()) + " " + df.format(maximMag.getTotalCuTaxe()) + " " + df.format(maximMag.getTotalCuTaxeScutite()));
		gbc.gridx=0;
		gbc.gridy=3;
		add(infoMag,gbc);
	}
	
	public Magazin getMaximMagazin() {
		ArrayList<Magazin> k =g.getMagazine();
		Magazin maximMag=null;
		double maxim=0;
		for (int i=0;i<k.size();i++) {
			if (k.get(i).getTotalCuTaxe()>maxim) {
				maximMag=k.get(i);
			}
		}
		return maximMag;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}