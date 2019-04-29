package m2i.formation.java;

public class Camion implements IMouvement {

	private String marque;
	private String modele;

	@Override
	public String toString() {
		return "Camion [Marque=" + marque + ", Modele=" + modele + "]";
	}

	public Camion(String marque, String modele) {
		super();
		marque = marque;
		modele = modele;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		modele = modele;
	}

	@Override
	public void freiner() {
		System.out.println("Le camion " + marque + " " + modele + " pile.");
	}

	@Override
	public void accelerer() {
		System.out.println("Le camion " + marque + " " + modele + " appuie sur le champignon.");

	}

}
