package tema;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListPanel extends JPanel implements ActionListener{
	
	File f;
	private Vector<String> productNames;
	private JLabel  productName,category,country,price;
	private JPanel details,buttons,adding;
	private JButton show,add,delete,edit,show2;
	private DefaultListModel<String> model;
	private JList list;
	private JScrollPane scpane;
	private JTextField countryField,productField,searchField;
	private Vector<String> v=Gestiune.getInstance().getCountries();
	
	
	public ListPanel(Vector<String> p,File aux) {
		productNames = p;
		f=aux;
		this.setLayout(new BorderLayout(2,2));
		this.setBorder(new TitledBorder("Products"));
		
		details=new JPanel(new GridLayout(0,1,1,1));
		details.setBorder(new TitledBorder("Details"));
		productName = new JLabel("Product: ");
		category = new JLabel("Category: ");
		country = new JLabel("Country: ");
		price = new JLabel("Price: ");
		details.add(productName);
        details.add(category);
        details.add(country);
        details.add(price);
        
        adding = new JPanel(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        adding.setBorder(new TitledBorder("AddElement"));
        JLabel productFormat=new JLabel("Product:");
        productField=new JTextField(10);
        JLabel format=new JLabel("     Format: Produs Categorie Price1 Price2 ..      ");
        String tari="     Countries:";
        for (int i=0;i<v.size();i++) {
        	tari += " " + v.get(i);
        }
        JLabel countries=new JLabel(tari);
        JButton addButton=new JButton("Add");
        addButton.addActionListener(this);
        JButton searchButton=new JButton("Search");
        searchButton.addActionListener(this);
        JLabel searchLabel=new JLabel("Search:");
        searchField=new JTextField(10);
        
        
        gbc.weightx=0.1;
        gbc.weighty=0.1;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor=GridBagConstraints.LINE_END;
        adding.add(productFormat,gbc);
        
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.gridx=1;
        gbc.gridy=0;
        adding.add(productField,gbc);
        
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=1;
        adding.add(format,gbc);
        
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=2;
        adding.add(countries,gbc);
        
        
        gbc.weighty=5;
        gbc.anchor=GridBagConstraints.FIRST_LINE_START;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=3;
        adding.add(addButton,gbc);
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.anchor=GridBagConstraints.LINE_END;
        adding.add(searchLabel,gbc);
        
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.gridx=1;
        gbc.gridy=4;
        adding.add(searchField,gbc);
        
        gbc.anchor=GridBagConstraints.FIRST_LINE_START;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=5;
        adding.add(searchButton,gbc);
		
		buttons = new JPanel(new GridLayout(1,0,1,1));
		show=new JButton("Display Alphabetically");
		show2=new JButton("Display By Country =>");
		countryField=new JTextField(5);
		delete=new JButton("Delete");
		edit=new JButton("Edit");
		delete.addActionListener(this);
		show.addActionListener(this);
		show2.addActionListener(this);
		edit.addActionListener(this);
		buttons.add(show);
		buttons.add(show2);
		buttons.add(countryField);
		buttons.add(delete);
		buttons.add(edit);
		
		model=new DefaultListModel<String>();
		for(int i=0;i<this.productNames.size();i++) {
			model.addElement(productNames.get(i));
		}
		list=new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setEnabled(true);
        list.addListSelectionListener(
                new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent e) {
						if (list.getSelectedValue()==null)
							return;
						String line = list.getSelectedValue().toString();
						StringTokenizer stk=new StringTokenizer(line);
						productName.setText("Product: " + stk.nextToken());
						category.setText("Category: " + stk.nextToken());
						country.setText("Country: " + stk.nextToken());
						price.setText("Price: " + stk.nextToken());
					}
                   
                });
        scpane=new JScrollPane(list);
        Dimension d=list.getPreferredSize();
        d.width=d.width+50;
        scpane.setPreferredSize(d);
        add(details,BorderLayout.CENTER);
        add(scpane,BorderLayout.WEST);
        add(buttons,BorderLayout.SOUTH);
        add(adding,BorderLayout.EAST);    
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton button=(JButton) e.getSource();
		
		if (button.getText().equals("Delete")) {
			int selectedIndex = list.getSelectedIndex();
			String lineSelected=(String)list.getSelectedValue();
			list.clearSelection();
			list.setSelectedIndex(-1);
			
			int lineNumber=0;
			boolean found=false;
			if (selectedIndex != -1) {
			    model.remove(selectedIndex);
				StringTokenizer stk=new StringTokenizer(lineSelected);
				String denumire=stk.nextToken();
				String categorie=stk.nextToken();
				String line="";
				Scanner in= null;
				try {
					in=new Scanner(f);
					while (in.hasNextLine()) {
						line=in.nextLine();
						if (line.contains(denumire)) {
							found=true;
							break;
						}
						lineNumber++;
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				finally {
					if (in!=null) {
						in.close();
					}
				}
				
				if (found) {
					try {
						List<String> lines= Files.readAllLines(f.toPath());
						lines.remove(lineNumber);
						Files.write(f.toPath(), lines);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
		if (button.getText().equals("Search")) {
			String product=searchField.getText();
			Vector <String> aux=new Vector<String>();
			for (int j=0;j<model.getSize();j++) {
				if (model.getElementAt(j).contains(product)) {
					aux.add(model.getElementAt(j));
				}
			}
			model.removeAllElements();
			for(int i=0;i<aux.size();i++) {
				model.addElement(aux.get(i));
			}
		}
		
		if (button.getText().equals("Display Alphabetically")) {
			Vector <String> aux=new Vector<String>();
			for (int j=0;j<model.getSize();j++) {
				aux.add(model.getElementAt(j));
			}
			Collections.sort(aux);
			model.removeAllElements();
			for(int i=0;i<aux.size();i++) {
				model.addElement(aux.get(i));
			}
		}
		
		if (button.getText().equals("Display By Country =>")) {
			String country=countryField.getText();
			Vector <String> aux=new Vector<String>();
			for (int j=0;j<model.getSize();j++) {
				String element=model.getElementAt(j);
				if (element.contains(country))
					aux.add(element);
			}
			model.removeAllElements();
			for(int i=0;i<aux.size();i++) {
				model.addElement(aux.get(i));
			}
		}
		
		if (button.getText().equals("Add")) {
			String produs=productField.getText();
			ThirdPanel tp=(ThirdPanel) this.getParent();
			StringTokenizer stk=new StringTokenizer(produs);
			String denumire=stk.nextToken();
			String categorie=stk.nextToken();
			if (!containsProduct(denumire)) {
				int i=0;
				while (stk.hasMoreTokens()) {
					String nt=stk.nextToken();
					if (Double.parseDouble(nt)==0) {
						i++;
						continue;
					}
					String s= denumire + " " + categorie + " " + v.get(i) + " " + nt;
					model.addElement(s);
					Produs aux=new Produs(denumire,categorie,v.get(i),Double.parseDouble(nt));
					tp.produse.add(aux);
					i++;
				}
				FileWriter fw=null;
				PrintWriter out=null;
				try {
					fw=new FileWriter(f,true);
					out=new PrintWriter(fw);
					out.println();
					out.print(produs);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				finally {
					try {
						fw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Produsul exista deja!");
			}
		}
		
		if (button.getText().equals("Edit")) {
			int lineNumber=0;
			if (list.getSelectedIndex()!=-1) {
				
				String lineSelected=(String)list.getSelectedValue();
				StringTokenizer stk=new StringTokenizer(lineSelected);
				String denumire=stk.nextToken();
				String categorie=stk.nextToken();
				String line="";
				Scanner in= null;
				
				try {
					in=new Scanner(f);
					while (in.hasNextLine()) {
						line=in.nextLine();
						if (line.contains(denumire))
							break;
						lineNumber++;
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				finally {
					if (in!=null) {
						in.close();
					}
				}
				String countries="";
				for(int i=0;i<v.size();i++) {
					countries += " " + v.get(i);
				}
				String replaceline=JOptionPane.showInputDialog(null,"Initial value: " + line + "\nCountries:"+ countries + "\nPlace your edit: (Name Category Price1 Price2 ...)","EditProduct",3);
				if (!(replaceline==null)) {
					try {
						List<String> lines= Files.readAllLines(f.toPath());
						lines.set(lineNumber, replaceline);
						Files.write(f.toPath(), lines);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					updateModel(replaceline,line);
				}
			}
		}
	}
	
	public void updateModel(String replaceline,String initialline) {
		StringTokenizer stk1=new StringTokenizer(initialline);
		String denumire1=stk1.nextToken();
		StringTokenizer stk=new StringTokenizer(replaceline);
		String denumire=stk.nextToken();
		String categorie=stk.nextToken();
		Vector <String> aux=new Vector<String>();
		
		for (int j=0;j<model.getSize();j++) {
			String element=model.getElementAt(j);
			if (!element.contains(denumire1)) {
				aux.add(element);
			}
		}
		int i=0;
		while (stk.hasMoreTokens()) {
			String nt=stk.nextToken();
			if (Double.parseDouble(nt)==0)
				continue;
			String s= denumire + " " + categorie + " " + v.get(i) + " " + nt;
			aux.add(s);
			i++;
		}
		
		model.removeAllElements();
		for(int j=0;j<aux.size();j++) {
			model.addElement(aux.get(j));
		}
		
	}
	
	public boolean containsProduct(String productName) {
		for (int i=0;i<model.size();i++) {
			String element=model.get(i);
			if (element.contains(productName))
				return true;
		}
		return false;
	}
	
}