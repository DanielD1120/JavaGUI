package tema;

public class ProdusComandat {
	private Produs produs;
	private Double taxa;
	private int cantitate;
	
	public ProdusComandat(Produs p,Double t,int c) {
		produs=p;
		taxa=t;
		cantitate=c;
	}
	
	public String toString() {
		return produs + " " +  taxa + " " + cantitate;
	}
	
	public void setProdus (Produs p) {
		produs=p;
	}
	
	public Produs getProdus() {
		return produs;
	}
	
	public void setTaxa(Double t) {
		taxa=t;
	}
	
	public Double getTaxa() {
		return taxa;
	}
	
	public void setCantitate(int c) {
		cantitate=c;
	}
	
	public int getCantitate() {
		return cantitate;
	}
}
