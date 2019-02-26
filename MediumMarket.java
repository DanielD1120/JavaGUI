package tema;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class MediumMarket extends Magazin {
	
	public MediumMarket(String n,Vector <Factura> f) {
		super(n,f,"MediumMarket");
	}
	
	public double calculScutiriTaxe() {
		TreeMap<String,Double> t=new TreeMap<String,Double>();
		double half=getTotalCuTaxe()/2;
		for (int i=0;i<v.size();i++) {
			Factura k=v.get(i);
			for (int j=0;j<k.v.size();j++) {
				ProdusComandat aux=k.v.get(j);
				Produs p=aux.getProdus();
				if (t.containsKey(p.getCategorie())){
					Double stara=t.get(p.getCategorie());
					double taxamul = aux.getTaxa().doubleValue()*0.01+1;
					stara+=aux.getCantitate()*taxamul*p.getPret();
					t.put(p.getCategorie(), stara);
				}
				else {
					Double stara=0.0;
					double taxamul = aux.getTaxa().doubleValue()*0.01+1;
					stara+=aux.getCantitate()*taxamul*p.getPret();
					t.put(p.getCategorie(), stara);
				}
			}
		}
		for(Map.Entry<String,Double> entry : t.entrySet()) {
			  Double value = entry.getValue();
			  if  (value.compareTo(half) > 0) {
				  return 0.95;
			  }  
		}
		return -1;
	}
}
