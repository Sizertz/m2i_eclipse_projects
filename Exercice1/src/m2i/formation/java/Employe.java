package m2i.formation.java;

public class Employe {
	private String nom;
	private String prenom;
	private float salaire = 0.0f;
	private byte nEnfants = 0;

	public float primeSalaire() {
		if (salaire <= 3000)
			return salaire * 0.4f;
		if (salaire <= 4000)
			return salaire * 0.2f;
		return salaire * 0.1f;
	}

	public float primeEnfants() {
		if (salaire <= 3000) {
			if (nEnfants > 2)
				return nEnfants * 1000;
			return nEnfants * 500;
		}
		if (salaire <= 4000) {
			if (nEnfants > 3)
				return nEnfants * 500;
			return nEnfants * 300;
		}
		return nEnfants * 100;
	}

	public float primeTotale() {
		return this.primeEnfants() + this.primeSalaire();
	}

	public Employe(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		if (nEnfants == 0)
			return prenom + " " + nom + ", n'a pas d'enfants et gagne " + salaire + "€ par mois";
		return prenom + " " + nom + ", a " + nEnfants + " enfants et gagne " + salaire + "€ par mois";
	}

	public Employe(String nom, String prenom, float salaire, byte nEnfants) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.salaire = salaire;
		this.nEnfants = nEnfants;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public float getSalaire() {
		return salaire;
	}

	public void setSalaire(float salaire) {
		this.salaire = salaire;
	}

	public byte getnEnfants() {
		return nEnfants;
	}

	public void setnEnfants(byte nEnfants) {
		this.nEnfants = nEnfants;
	}

	public void affichePrimes() {
		System.out.println("Prime salaire: " + this.primeSalaire() + "€");
		System.out.println("Prime enfants: " + this.primeEnfants() + "€");
	}
}
