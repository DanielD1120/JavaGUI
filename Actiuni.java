package tema;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Actiuni implements ActionListener {
	
	private JPanel contentPanel;
	private CardLayout cardLayout;
	private MainFrame mainF;
	
	public Actiuni (JPanel p,CardLayout k,MainFrame mf) {
		contentPanel=p;
		cardLayout=k;
		mainF=mf;
	}
	

	public void actionPerformed(ActionEvent e) {
		JButton button= (JButton) e.getSource();
		
		if (button.getText().equals("Incarcare")) {
			cardLayout.show(contentPanel, "panel2");
		}
			
		if (button.getText().equals("Statistici")) {
			cardLayout.show(contentPanel, "panel4");
		}
		
		if (button.getText().equals("PrintOutput")) {
			try {
				SecondPanel aux=(SecondPanel)mainF.panel2;
				new PrintOutput(aux.f1,aux.f2,aux.f3);
			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			FirstPanel p=(FirstPanel)mainF.panel1;
			p.button2.setEnabled(true);
			p.button3.setEnabled(true);
			mainF.panel3=new ThirdPanel(this,mainF);
			mainF.contentPanel.add(mainF.panel3,"panel3");
			mainF.panel4=new FourthPanel(this);
			mainF.contentPanel.add(mainF.panel4,"panel4");
		}
		
		if (button.getText().equals("Produse")) {
			cardLayout.show(contentPanel, "panel3");
		}
		
		if (button.getText().equals("Back")) {
			cardLayout.show(contentPanel, "panel1");
		}	
	}

}
