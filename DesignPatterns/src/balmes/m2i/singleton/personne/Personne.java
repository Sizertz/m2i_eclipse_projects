package balmes.m2i.singleton.personne;

public class Personne {
	private String prenom;
	private String nom;

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Personne(String prenom, String nom) {
		super();
		this.prenom = prenom;
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Personne [prenom=" + prenom + ", nom=" + nom + ", hashcode=" + Integer.toHexString(super.hashCode())
				+ "]";
	}

}
