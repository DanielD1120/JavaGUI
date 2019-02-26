package tema;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class Gestiune {
	
	private static Gestiune instance = new Gestiune();
	private ArrayList<Produs> produse;
	private ArrayList<Magazin> magazine;
	private TreeMap<String,TreeMap<String,Double>> taxe;
	private Vector<String> countries;
	
	public static Gestiune getInstance() {
		return instance;
	}
	
	public ArrayList<Produs> getProduse() {
		return produse;
	}
	
	public ArrayList<Magazin> getMagazine() {
		return magazine;
	}
	public Vector<String> getCountries() {
		return countries;
	}
	
	public TreeMap<String,TreeMap<String,Double>> getTaxe() {
		return taxe;
	}
	
	public void setAll(ReadFromFiles ro) {
		produse=ro.produse;
		magazine=ro.magazine;
		taxe=ro.taxe;
		countries=ro.countries;
	}
}
