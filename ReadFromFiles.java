package tema;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class ReadFromFiles{
	File f1;
	File f2;
	File f3;
	ArrayList<Produs> produse;
	ArrayList<Magazin> magazine;
	TreeMap<String,TreeMap<String,Double>> taxe;
	Vector<String> countries;
	
	public ReadFromFiles(File fisi1,File fisi2,File fisi3) {
		f1=fisi1;
		f2=fisi2;
		f3=fisi3;
		produse=readProducts();
		taxe=readTaxes();
		magazine = readMagazine();
	}
	
	private ArrayList<Produs> readProducts() {
		ArrayList<Produs> p=new ArrayList<>();
		File f=f1;
		Scanner in=null;
		try {
			Vector<String> v=new Vector<>();
			in=new Scanner(f);
			String line1=in.nextLine();
			StringTokenizer stk1=new StringTokenizer(line1);
			stk1.nextToken();
			stk1.nextToken();
			while (stk1.hasMoreTokens()) {
				v.add(stk1.nextToken());
			}
			countries=v;
			while(in.hasNextLine()) {
				String line=in.nextLine();
				StringTokenizer stk=new StringTokenizer(line);
				String denumire=stk.nextToken();
				String categorie=stk.nextToken();
				int i=0;
				while (stk.hasMoreTokens()) {
					String nt=stk.nextToken();
					Double h=Double.parseDouble(nt);
					if (h==0) {
						i++;
						continue;
					}
					Produs aux=new Produs(denumire,categorie,v.get(i),h);
					p.add(aux);
					i++;
				}
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			in.close();
		}
		return p;
	}
	
	private int getType(String s) {
		if (s.equals("MiniMarket"))
			return 1;
		if (s.equals("MediumMarket"))
			return 2;
		if (s.equals("HyperMarket"))
			return 3;
		return 0;
	}
	
	private ProdusComandat makeProdusComandat(String line) {
		StringTokenizer stk=new StringTokenizer(line);
		String nume=stk.nextToken();
		String tara=stk.nextToken();
		int cantitate=Integer.parseInt(stk.nextToken());
		Produs cautat=null;
		for (int i=0;i<produse.size();i++) {
			Produs aux=produse.get(i);
			if ((aux.getDenumire().equals(nume)) && (aux.getTaraOrigine().equals(tara))){
				cautat=aux;
				break;
			}
		}
		TreeMap<String,Double> k=taxe.get(tara);
		Double taxa=k.get(cautat.getCategorie());
		return new ProdusComandat(cautat,taxa,cantitate);
	}
	
	private Factura makeFactura(Scanner in,String name) {
		Vector <ProdusComandat> pc=new Vector<>();
		in.nextLine();
		while (in.hasNextLine()) {
			String line=in.nextLine();
			if (line.isEmpty())
				break;
			ProdusComandat p =  makeProdusComandat(line);
			pc.add(p);
		}
		return new Factura(name,pc);
	}
	
	private Vector<Factura> getFacturi(Scanner in){
		Vector<Factura> facturi=new Vector<>();
		while (in.hasNextLine()) {
			String line=in.nextLine();
			if (line.contains("Magazin"))
				break;
			if (line.isEmpty())
				continue;
			if (line.contains("Factura")) {
				Factura p = makeFactura(in,line);
				facturi.add(p);
			}
		}
		return facturi;
	}
	
	private ArrayList<Magazin> readMagazine(){
		ArrayList<Magazin> m=new ArrayList<>();
		File f=f2;
		Scanner in=null;
		Scanner in2=null;
		try {
			in=new Scanner(f);
			String line=in.nextLine();
			while(true) {
				if (!line.contains("Magazin"))
					continue;
				StringTokenizer stk=new StringTokenizer(line,":");
				stk.nextToken();
				int p = getType(stk.nextToken());
				String name= stk.nextToken();
				in.nextLine();
				Vector<Factura> aux=new Vector<>();
				String line2="";
				while (in.hasNextLine()) {
					line2=in.nextLine();
					if (line2.contains("Magazin"))
						break;
					if (line2.isEmpty())
						continue;
					if (line2.contains("Factura")) {
						Factura h = makeFactura(in,line2);
						aux.add(h);
					}
				}
				line=line2;
				if (p==1)
					m.add(new MiniMarket(name,aux));
				else if (p==2)
					m.add(new MediumMarket(name,aux));
				else
					m.add(new HyperMarket(name,aux));
				if (!in.hasNextLine())
					break;
			}

		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			in.close();
		}
		return m;
	}
	
	private TreeMap<String,TreeMap<String,Double>> readTaxes(){
		TreeMap<String,TreeMap<String,Double>> aux=new TreeMap<>();
		File f=f3;
		Scanner in=null;
		try {
			Vector<String> v=new Vector<>();
			in=new Scanner(f);
			String line1=in.nextLine();
			StringTokenizer stk1=new StringTokenizer(line1);
			stk1.nextToken();
			while (stk1.hasMoreTokens()) {
				v.add(stk1.nextToken());
			}
			
			while(in.hasNextLine()) {
				String line=in.nextLine();
				StringTokenizer stk=new StringTokenizer(line);
				String categorie=stk.nextToken();
				for (int i=0;i<v.size();i++) {
					if (!aux.containsKey(v.get(i))) {
						TreeMap<String,Double> k=new TreeMap<>();
						k.put(categorie, Double.parseDouble(stk.nextToken()));
						aux.put(v.get(i), k);
					}
					else {
						TreeMap<String,Double> k=aux.get(v.get(i));
						k.put(categorie, Double.parseDouble(stk.nextToken()));
						aux.put(v.get(i), k);
					}
				}
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			in.close();
		}
		return aux;
	}
}
