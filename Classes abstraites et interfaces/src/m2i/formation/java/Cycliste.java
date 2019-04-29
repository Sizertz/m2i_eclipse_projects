package m2i.formation.java;

public class Cycliste extends Personne implements IMouvement {
	protected String equipe;

	@Override
	public String toString() {
		return "Cycliste [velo=" + equipe + "]";
	}

	public Cycliste(String nom, String prenom, String velo) {
		super(nom, prenom);
		this.equipe = velo;
	}

	public String getVelo() {
		return equipe;
	}

	public void setVelo(String velo) {
		this.equipe = velo;
	}

	@Override
	public void freiner() {
		System.out.println(this.getPrenom() + " freine son vélo.");
	}

	@Override
	public void accelerer() {
		System.out.println(this.getPrenom() + " pédale plus vite");

	}

	@Override
	public float prime() {
		return (float) Math.random();
	}

}
