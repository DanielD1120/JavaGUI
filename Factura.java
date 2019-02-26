package tema;

import java.text.DecimalFormat;
import java.util.Vector;

public class Factura implements Comparable<Factura>{
	String denumire;
	Vector <ProdusComandat> v;
	
	public Factura(String n,Vector <ProdusComandat> k) {
		denumire=n;
		v=k;
	}
	
	public String toString() {
		DecimalFormat df =new DecimalFormat("#.#####");
		String s=denumire + "\n" + "\n";
		s+= "Total " + df.format(getTotalFaraTaxe()) + " " + df.format(getTotalCuTaxe());
		return s;
	}
	
	public double getTotalFaraTaxe() {
		double s=0;
		for (int i=0;i<v.size();i++) {
			s+=v.get(i).getProdus().getPret()*v.get(i).getCantitate();
		}
		return s;
	}
	
	public double getTotalCuTaxe() {
		double s=0;
		for (int i=0;i<v.size();i++) {
			ProdusComandat c=v.get(i);
			double taxamul = c.getTaxa().doubleValue()*0.01+1;
			s+=c.getProdus().getPret()*taxamul*c.getCantitate();
		}
		return s;
	}
	
	public double getTaxe() {
		double s=0;
		for (int i=0;i<v.size();i++) {
			s+=v.get(i).getTaxa();
		}
		return s;
	}
	
	public double getTotalTaraFaraTaxe(String t) {
		double s=0;
		for (int i=0;i<v.size();i++) {
			ProdusComandat c=v.get(i);
			String tara=v.get(i).getProdus().getTaraOrigine();
			if (tara.equals(t))
				s+=c.getProdus().getPret()*c.getCantitate();

		}
		return s;
	}
	
	public double getTotalTaraCuTaxe(String t) {
		double s=0;
		for (int i=0;i<v.size();i++) {
			ProdusComandat c=v.get(i);
			double taxamul = c.getTaxa().doubleValue()*0.01+1;
			String tara=v.get(i).getProdus().getTaraOrigine();
			if (tara.equals(t))
				s+=c.getProdus().getPret()*c.getCantitate()*taxamul;
		}
		return s;
	}
	
	public double getTaxeTara(String t) {
		double s=0;
		for (int i=0;i<v.size();i++) {
			ProdusComandat c=v.get(i);
			String tara=v.get(i).getProdus().getTaraOrigine();
			if (tara.equals(t))
				s+=c.getTaxa();
		}
		return s;
	}
	
	public double getTotalCategorieFaraTaxe(String t) {
		double s=0;
		for (int i=0;i<v.size();i++) {
			ProdusComandat c=v.get(i);
			String categorie=v.get(i).getProdus().getCategorie();
			if (categorie.equals(t))
				s+=c.getProdus().getPret()*c.getCantitate();

		}
		return s;
	}
	
	public double getTotalCategorieCuTaxe(String t) {
		double s=0;
		for (int i=0;i<v.size();i++) {
			ProdusComandat c=v.get(i);
			double taxamul = c.getTaxa().doubleValue()*0.01+1;
			String categorie=v.get(i).getProdus().getCategorie();
			if (categorie.equals(t))
				s+=c.getProdus().getPret()*c.getCantitate()*taxamul;
		}
		return s;
	}
	
	public int compareTo(Factura o) {
		Double s=this.getTotalCuTaxe();
		Double p=o.getTotalCuTaxe();
		return s.compareTo(p);
	}
}
