package tema;

import java.util.Vector;

public abstract class Magazin implements IMagazin,Comparable<Magazin>{

	String nume;
	Vector<Factura> v;
	String type;
	
	
	public Magazin(String t) {
		super();
	}
	
	public Magazin(String n, Vector<Factura> k,String t) {
		nume=n;
		v=k;
		type=t;
	}
	
	public String toString() {
		return nume + "\n" + v + "\n";
	}

	public double getTotalFaraTaxe() {
		double s=0;
		for (int i=0;i<v.size();i++) {
			s+= v.get(i).getTotalFaraTaxe();
		}
		return s;
	}

	
	public double getTotalCuTaxe() {
		double s=0;
		for (int i=0;i<v.size();i++) {
			s+= v.get(i).getTotalCuTaxe();
		}
		return s;
	}

	public double getTotalCuTaxeScutite() {
		if( calculScutiriTaxe()==-1)
			return getTotalCuTaxe();
		else
			return getTotalCuTaxe()*calculScutiriTaxe();
	}

	public double getTotalTaraFaraTaxe(String t) {
		double s=0;
		for (int i=0;i<v.size();i++) {
			s+= v.get(i).getTotalTaraFaraTaxe(t);
		}
		return s;
	}

	public double getTotalTaraCuTaxe(String t) {
		double s=0;
		for (int i=0;i<v.size();i++) {
			s+= v.get(i).getTotalTaraCuTaxe(t);
		}
		return s;
	}

	public double getTotalTaraCuTaxeScutetite(String t) {
		if( calculScutiriTaxe()==-1)
			return getTotalTaraCuTaxe(t);
		else
			return getTotalTaraCuTaxe(t)*calculScutiriTaxe();
	}
	
	public double getTotalCategorieCuTaxe(String t) {
		double s=0;
		for (int i=0;i<v.size();i++) {
			s+= v.get(i).getTotalCategorieCuTaxe(t);
		}
		return s;
	}
	
	public double getTotalCategorieFaraTaxe(String t) {
		double s=0;
		for (int i=0;i<v.size();i++) {
			s+= v.get(i).getTotalCategorieFaraTaxe(t);
		}
		return s;
	}
	
	public double getTotalCategorieCuTaxeScutetite(String t) {
		if( calculScutiriTaxe()==-1)
			return getTotalCategorieCuTaxe(t);
		else
			return getTotalCategorieCuTaxe(t)*calculScutiriTaxe();
	}
	
	public Factura getFacturaMaxima() {
		Factura aux=null;
		double max=0;
		for(int i=0;i<v.size();i++) {
			if (v.get(i).getTotalFaraTaxe()>max) {
				aux=v.get(i);
				max=v.get(i).getTotalFaraTaxe();
			}
		}
		return aux;
	}
	
	public int compareTo (Magazin o) {
		Double s=getTotalFaraTaxe();
		Double k=o.getTotalFaraTaxe();
		return s.compareTo(k);
	}
}
