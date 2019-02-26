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

public class StatisticsPanel4 extends JPanel implements ActionListener {
	
	private ActionListener actionListener;
	private GridBagLayout gridBag=new GridBagLayout();
	private GridBagConstraints gbc=new GridBagConstraints();
	private Gestiune g=Gestiune.getInstance();
	private ArrayList <Magazin> magazine=g.getMagazine();
	private Vector<String> v=this.categories();
	
	public StatisticsPanel4(ActionListener p) {
		Color color=new Color(183,181,223);
		this.setBackground(color);
		actionListener=p;
		this.setLayout(gridBag);
		gbc.insets=new Insets(5,5,5,5);
		
		JLabel statisticsMenu=new JLabel(" Option 3 ",JLabel.CENTER);
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
		
		DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		for (int i=0;i<magazine.size();i++) {
			Magazin aux=magazine.get(i);
			for (int j=0;j<v.size();j++) {
				dataset.addValue(aux.getTotalCategorieCuTaxe(v.get(j)), v.get(j), aux.nume);
			}
		}
		JFreeChart barChart=ChartFactory.createBarChart("Total magazine pe categorii","Magazin","Total",dataset,PlotOrientation.VERTICAL,true,true,false);
		ChartPanel chartpanel=new ChartPanel(barChart);
		gbc.gridx=0;
		gbc.gridy=2;
		add(chartpanel,gbc);
		
		DecimalFormat df =new DecimalFormat("#.#####");
		JLabel infoMag;
		Magazin maximMag;
		gbc.anchor=GridBagConstraints.LINE_START;
		gbc.gridx=0;
		gbc.gridy=3;
		for (int i=0;i<v.size();i++) {
			maximMag=getMaximMagazinCategorie(v.get(i));
			infoMag=new JLabel("Magazin cu cele mai mari vanzari pentru " +v.get(i) +": " + maximMag.nume + "  " + df.format(maximMag.getTotalCategorieFaraTaxe(v.get(i))) + "  " + df.format(maximMag.getTotalCategorieCuTaxe(v.get(i))) + "  " + df.format(maximMag.getTotalCategorieCuTaxeScutetite(v.get(i))));
			add(infoMag,gbc);
			gbc.gridy++;
		}
	}
	
	public Magazin getMaximMagazinCategorie(String category) {
		ArrayList<Magazin> k =g.getMagazine();
		Magazin maximMag=null;
		double maxim=0;
		for (int i=0;i<k.size();i++) {
			if (k.get(i).getTotalCategorieCuTaxe(category)>maxim) {
				maximMag=k.get(i);
				maxim=k.get(i).getTotalCategorieCuTaxe(category);
			}
		}
		return maximMag;
	}
	
	public Vector<String> categories(){
		Vector <String> cat=new Vector<>();
		ArrayList<Produs> aux=g.getProduse();
		for(int i=0;i<aux.size();i++) {
			String categorie=aux.get(i).getCategorie();
			if (!cat.contains(categorie)){
				cat.add(categorie);
			}
		}
		return cat;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
