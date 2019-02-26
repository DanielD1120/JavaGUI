package tema;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ThirdPanel extends JPanel implements ActionListener{
	
	private ActionListener actionListener;
	private GridBagLayout gridBag=new GridBagLayout();
	private GridBagConstraints gbc=new GridBagConstraints();
	private Gestiune p=Gestiune.getInstance();
	ArrayList<Produs> produse=p.getProduse();
	private JPanel aux=new JPanel(),listPanel;
	private MainFrame mainF;
	
	public Vector<String> getProductNames(ArrayList<Produs> produse){
		Vector<String> p=new Vector<String>();
		for (int i=0;i<produse.size();i++){
			p.add(produse.get(i).toString());
		}
		return p;
	}
	
	void adauga ( Component comp ,int x, int y) {
		gbc . gridx = x;
		gbc . gridy = y;
		add( comp ,gbc);
	}
	
	public ThirdPanel(ActionListener p,MainFrame mf) {
		mainF=mf;
		Color color=new Color(183,181,223);
		this.setBackground(color);
		actionListener=p;
		gbc.weightx=1;
		gbc.weighty=1;
		gbc.insets=new Insets(5,5,5,5);
		this.setLayout(gridBag);
		
		ImageIcon foodImage=new ImageIcon("rsz_1food.png");
		JLabel products=new JLabel(" Products Menu ",JLabel.CENTER);
		products.setFont(new Font(" Arial ", Font.BOLD ,24));
		products.setBackground(Color.BLACK);
		products.setForeground(Color.BLACK);
		products.setIcon(foodImage);
		gbc.gridwidth=4;
		gbc.gridheight=1;
		adauga (products,0,0);
		
		gbc.gridwidth=1;
		gbc.gridheight=1;
		
		
		JButton backButton=new JButton("Back");
		backButton.addActionListener(actionListener);
		adauga(backButton,2,1);
		JButton resetButton=new JButton("Reset");
		resetButton.addActionListener(this);
		adauga(resetButton,1,1);
		
		SecondPanel p2=(SecondPanel) mainF.panel2;
		listPanel=new ListPanel(getProductNames(produse),p2.f1);
		gbc.gridwidth=4;
		gbc.gridheight=1;
		gbc.fill=GridBagConstraints.BOTH;
		adauga(listPanel,0,2);
		
	}

	public void actionPerformed(ActionEvent e) {
		JButton button=(JButton) e.getSource();
		if (button.getText().equals("Reset")) {
			this.remove(listPanel);
			SecondPanel p2=(SecondPanel) mainF.panel2;
			listPanel=new ListPanel(getProductNames(produse),p2.f1);
			gbc.gridwidth=4;
			gbc.gridheight=1;
			gbc.fill=GridBagConstraints.BOTH;
			adauga(listPanel,0,2);
			this.revalidate();
			this.repaint();
		}
	}

}
