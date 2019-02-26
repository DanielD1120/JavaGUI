package tema;

public interface IMagazin {
	public abstract double getTotalFaraTaxe();
	public abstract double getTotalCuTaxe();
	public abstract double getTotalCuTaxeScutite();
	public abstract double getTotalTaraFaraTaxe(String t);
	public abstract double getTotalTaraCuTaxe(String t);
	public abstract double getTotalTaraCuTaxeScutetite(String t);
	public abstract double calculScutiriTaxe();
}
