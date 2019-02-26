package tema;

public class Produs {
	private String denumire;
	private String categorie;
	private String taraOrigine;
	private double pret;
	
	public Produs(String d,String c,String t,double p) {
		denumire=d;
		categorie=c;
		taraOrigine=t;
		pret=p;
	}
	
	public void setDenumire(String d) {
		denumire=d;
	}
	
	public String getDenumire() {
		return denumire;
	}
	
	public void setCategorie(String c) {
		categorie=c;
	}
	
	public String getCategorie() {
		return categorie;
	}
	
	public void setTaraOrigine(String t) {
		taraOrigine=t;
	}
	
	public String getTaraOrigine() {
		return taraOrigine;
	}
	
	public void setPret(double p) {
		pret=p;
	}
	
	public double getPret() {
		return pret;
	}
	
	public String toString() {
		return denumire+ " " + categorie + " " + taraOrigine + " " + pret;
	}
}
