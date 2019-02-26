package tema;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class PrintOutput {
	
	public PrintOutput(File f1,File f2,File f3) throws FileNotFoundException, UnsupportedEncodingException {
		Gestiune p=Gestiune.getInstance();
		ReadFromFiles ro=new ReadFromFiles(f1,f2,f3);
		p.setAll(ro);
		ArrayList<Magazin> magazine=p.getMagazine();
		TreeMap<String,TreeMap<String,Double>> taxe=p.getTaxe();
		Collections.sort(magazine);
		PrintWriter writer= new PrintWriter("out.txt","UTF-8");
		writer.println("MiniMarket");
		for (Magazin k:magazine) {
			if (k.type.equals("MiniMarket")) {
				printMagazin(k,writer,taxe);
			}
		}
		writer.println("MediumMarket");
		for (Magazin k:magazine) {
			if (k.type.equals("MediumMarket")) {
				printMagazin(k,writer,taxe);
			}
		}
		writer.println("HyperMarket");
		for (Magazin k:magazine) {
			if (k.type.equals("HyperMarket")) {
				printMagazin(k,writer,taxe);
			}
		}
		writer.close();
	}
	
	public void printMagazin(Magazin k,PrintWriter writer,TreeMap<String,TreeMap<String,Double>> taxe) {
		DecimalFormat df =new DecimalFormat("#.#####");
		writer.println(k.nume);
		writer.println();
		writer.println("Total " + df.format(k.getTotalFaraTaxe()) + " " + df.format(k.getTotalCuTaxe()) + " " + df.format(k.getTotalCuTaxeScutite()));
		writer.println();
		
		writer.println("Tara");
		for (Map.Entry<String, TreeMap<String,Double>> entry : taxe.entrySet()) {
		    String key = entry.getKey();
		    if (k.getTotalTaraFaraTaxe(key)==0.0) {
		    	writer.println(key + " " + 0);
		    	continue;
		    }
		    writer.println(key + " " + df.format(k.getTotalTaraFaraTaxe(key)) +" " + df.format(k.getTotalTaraCuTaxe(key)) + " " + df.format(k.getTotalTaraCuTaxeScutetite(key)));
		}
		writer.println();
		
		Vector<Factura> v=k.v;
		Collections.sort(v);
		for (int i=0;i<v.size();i++) {
			Factura x=v.get(i);
			writer.println(x);
			writer.println();
			writer.println("Tara");
			for (Map.Entry<String, TreeMap<String,Double>> entry : taxe.entrySet()) {
			    String key = entry.getKey();
			    if (k.getTotalTaraFaraTaxe(key)==0.0) {
			    	writer.println(key + " " + 0);
			    	continue;
			    }
			    writer.println(key + " " + df.format(x.getTotalTaraFaraTaxe(key)) +" " + df.format(x.getTotalTaraCuTaxe(key)));
			}
			writer.println();
		}
	}	
}
