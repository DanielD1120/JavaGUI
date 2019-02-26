package tema;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SecondPanel extends JPanel implements ActionListener{
	
	private ActionListener actionListener;
	private GridBagLayout gridBag=new GridBagLayout();
	private GridBagConstraints gbc=new GridBagConstraints();
	private JFileChooser fc=new JFileChooser();
	File f1,f2,f3;
	JLabel labelProducts,labelFacturi,labelTaxe;
	private JButton button1,button2,button3,button4,button5;
	
	public SecondPanel(ActionListener p) {
		Color color=new Color(183,181,223);
		this.setBackground(color);
		actionListener=p;
		this.setLayout(gridBag);
		gbc.insets=new Insets(5,5,5,5);
		
		button1=new JButton("Back");
		button2=new JButton("PrintOutput");
		button3=new JButton("Load produse.txt");
		button4=new JButton("Load facturi.txt");
		button5=new JButton("Load taxe.txt");
		button1.addActionListener(actionListener);
		button2.addActionListener(actionListener);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button2.setEnabled(false);
		labelProducts= new JLabel("Produse: ");
		labelFacturi= new JLabel("Facturi: ");
		labelTaxe= new JLabel("Taxe: ");
		labelProducts.setFont(new Font(" Arial ", Font.BOLD ,13));
		labelFacturi.setFont(new Font(" Arial ", Font.BOLD ,13));
		labelTaxe.setFont(new Font(" Arial ", Font.BOLD ,13));
		
		ImageIcon uploadImage=new ImageIcon("rsz_upload.png");
		JLabel loading=new JLabel(" Upload Files ",JLabel.CENTER);
		loading.setFont(new Font(" Arial ", Font.BOLD ,24));
		loading.setBackground(Color.BLACK);
		loading.setForeground(Color.BLACK);
		loading.setIcon(uploadImage);
		gbc.gridwidth=5;
		gbc.gridheight=1;
		gbc.gridx=0;
		gbc.gridy=0;
		add(loading,gbc);
		
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.gridx=0;
		gbc.gridy=1;
		add(button5,gbc);
		gbc.gridx=1;
		gbc.gridy=1;
		add(button4,gbc);
		gbc.gridx=2;
		gbc.gridy=1;
		add(button3,gbc);
		gbc.gridx=3;
		gbc.gridy=1;
		add(button2,gbc);
		gbc.gridx=4;
		gbc.gridy=1;
		add(button1,gbc);
		gbc.anchor=GridBagConstraints.LINE_START;
		gbc.gridwidth=4;
		gbc.gridheight=1;
		gbc.gridx=0;
		gbc.gridy=2;
		add(labelProducts,gbc);
		gbc.gridx=0;
		gbc.gridy=3;
		add(labelFacturi,gbc);
		gbc.gridx=0;
		gbc.gridy=4;
		add(labelTaxe,gbc);
	}

	public void actionPerformed(ActionEvent e) {
		JButton button=(JButton) e.getSource();
		if (button.getText().equals("Load produse.txt")) {
			fc.showOpenDialog(this);
			File selectedFile=fc.getSelectedFile();
			f1=selectedFile;
			labelProducts.setText("Produse: " + f1.getPath());
			if (checkAllLoaded()) {
				button2.setEnabled(true);
			}
		}
		if (button.getText().equals("Load facturi.txt")) {
			fc.showOpenDialog(this);
			File selectedFile=fc.getSelectedFile();
			f2=selectedFile;
			labelFacturi.setText("Facturi: " + f2.getPath());
			if (checkAllLoaded()) {
				button2.setEnabled(true);
			}
		}
		if (button.getText().equals("Load taxe.txt")) {
			fc.showOpenDialog(this);
			File selectedFile=fc.getSelectedFile();
			f3=selectedFile;
			labelTaxe.setText("Taxe: " + f3.getPath());
			if (checkAllLoaded()) {
				button2.setEnabled(true);
			}
		}
	}
	
	public boolean checkAllLoaded() {
		if ((f1!=null) && (f2!=null) && (f3!=null)) {
			return true;
		}
		return false;
	}
}
