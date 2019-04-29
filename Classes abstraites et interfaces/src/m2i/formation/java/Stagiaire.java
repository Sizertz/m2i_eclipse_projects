package m2i.formation.java;

public class Stagiaire extends Personne {

	private String diplome;

	public Stagiaire(String nom, String prenom, String diplome) {
		super(nom, prenom);
		this.diplome = diplome;
	}

	@Override
	public float prime() {
		// TODO Auto-generated method stub
		return 0;
	}

}
