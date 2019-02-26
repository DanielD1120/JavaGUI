package tema;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ActiuniStatistici implements ActionListener{
	
	private JPanel contentPanel;
	private CardLayout cardLayout;
	
	public ActiuniStatistici (JPanel p,CardLayout k) {
		contentPanel=p;
		cardLayout=k;
	}
	

	public void actionPerformed(ActionEvent e) {
		JButton button= (JButton) e.getSource();
		
		if (button.getText().equals("Total cu taxe")) {
			cardLayout.show(contentPanel, "panel2");
		}
		
		if (button.getText().equals("Total pe tari")) {
			cardLayout.show(contentPanel, "panel3");
		}
		
		if (button.getText().equals("Total pe categorii")) {
			cardLayout.show(contentPanel, "panel4");
		}
		
		if (button.getText().equals("Factura maxima")) {
			cardLayout.show(contentPanel, "panel5");
		}	
		
		if (button.getText().equals("Back")) {
			cardLayout.show(contentPanel, "panel1");
		}	
	}
}
