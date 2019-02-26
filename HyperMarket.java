package tema;

import java.util.Vector;

public class HyperMarket extends Magazin {
	
	public HyperMarket(String n,Vector <Factura> f) {
		super(n,f,"HyperMarket");
	}
	
	public double calculScutiriTaxe() {
		Double tenpercent=getTotalCuTaxe()/10;
		for(int i=0;i<v.size();i++) {
			Factura k=v.get(i);
			Double p=k.getTotalCuTaxe();
			if (p.compareTo(tenpercent)>0) {
				return 0.99;
			}
		}
		return -1;
	}
	
}
