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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class StatisticsPanel5 extends JPanel implements ActionListener {
	
	private ActionListener actionListener;
	private GridBagLayout gridBag=new GridBagLayout();
	private GridBagConstraints gbc=new GridBagConstraints();
	private Gestiune g=Gestiune.getInstance();
	private ArrayList <Magazin> magazine=g.getMagazine();
	
	public StatisticsPanel5(ActionListener p) {
		Color color=new Color(183,181,223);
		this.setBackground(color);
		actionListener=p;
		this.setLayout(gridBag);
		gbc.insets=new Insets(5,5,5,5);
		
		JLabel statisticsMenu=new JLabel(" Option 4 ",JLabel.CENTER);
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
		
		Vector<Factura> facturiMaxime=new Vector<>();
		DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		for (int i=0;i<magazine.size();i++) {
			Magazin aux=magazine.get(i);
			Factura k=aux.getFacturaMaxima();
			k.denumire = k.denumire + "-MG" + i;
			facturiMaxime.add(k);
			dataset.addValue(k.getTotalFaraTaxe(),aux.nume,k.denumire);
		}
		JFreeChart barChart=ChartFactory.createBarChart("Facturi maxime pe magazine","Magazin","Total",dataset,PlotOrientation.VERTICAL,true,true,false);
		ChartPanel chartpanel=new ChartPanel(barChart);
		gbc.gridx=0;
		gbc.gridy=2;
		add(chartpanel,gbc);
		
		Factura maximTotal=getFacturaMaxima(facturiMaxime);
		
		DecimalFormat df =new DecimalFormat("#.#####");
		JLabel infoMag;
		gbc.anchor=GridBagConstraints.LINE_START;
		gbc.gridx=0;
		gbc.gridy=3;
		infoMag=new JLabel("Factura cu suma totala este " +maximTotal.denumire + " cu suma totala: " + maximTotal.getTotalFaraTaxe());
		add(infoMag,gbc);
	}
	
	public Factura getFacturaMaxima(Vector <Factura> f) {
		Factura aux=null;
		double maxim=0;
		for (int i=0;i<f.size();i++) {
			if (f.get(i).getTotalFaraTaxe()>maxim) {
				aux=f.get(i);
				maxim=aux.getTotalFaraTaxe();
			}
		}
		return aux;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
