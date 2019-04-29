package balmes.m2i.jdbc;

public class Personne {
	private int _ID_Personne;
	private String _Nom;
	private String _Prenom;
	private float _Poids;
	private float _Taille;
	private Genre _Sexe;

	// Constructor
	public Personne(int _ID_Personne, String _Nom, String _Prenom, float _Poids, float _Taille, Genre _Sexe) {
		super();
		this._ID_Personne = _ID_Personne;
		this._Nom = _Nom;
		this._Prenom = _Prenom;
		this._Poids = _Poids;
		this._Taille = _Taille;
		this._Sexe = _Sexe;
	}

	// Getters and setters
	public int get_ID_Personne() {
		return _ID_Personne;
	}

	public void set_ID_Personne(int _ID_Personne) {
		this._ID_Personne = _ID_Personne;
	}

	public String get_Nom() {
		return _Nom;
	}

	public void set_Nom(String _Nom) {
		this._Nom = _Nom;
	}

	public String get_Prenom() {
		return _Prenom;
	}

	public void set_Prenom(String _Prenom) {
		this._Prenom = _Prenom;
	}

	public float get_Poids() {
		return _Poids;
	}

	public void set_Poids(float _Poids) {
		this._Poids = _Poids;
	}

	public float get_Taille() {
		return _Taille;
	}

	public void set_Taille(float _Taille) {
		this._Taille = _Taille;
	}

	public Genre get_Sexe() {
		return _Sexe;
	}

	public void set_Sexe(Genre _Sexe) {
		this._Sexe = _Sexe;
	}

	// IMC-related methods
	public float iMC() {
		return this._Poids / (this._Taille * this._Taille);
	}

	public float poidsMin() {
		return 19 / (this._Taille * this._Taille);
	}

	public float poidsMax() {
		return 25 / (this._Taille * this._Taille);
	}

	public float poidsIdeal() {
		if (this._Sexe == Genre.MASCULIN)
			return 22 / (this._Taille * this._Taille);
		else
			return 21 / (this._Taille * this._Taille);
	}

	public String diagnostic() {
		float imc = this.iMC();
		if (imc < 17) {
			return "Anorexique";
		}
		if (imc < 19) {
			return "Maigre";
		}
		if (imc < 25) {
			return "Super Forme!";
		}
		if (imc < 30) {
			return "Quelques kilos en trop";
		}
		if (imc < 40) {
			return "Obèse";
		}
		return "Obèse morbide";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ID=");
		builder.append(_ID_Personne);
		builder.append(" | Nom=");
		builder.append(_Nom);
		builder.append(" | Prenom=");
		builder.append(_Prenom);
		builder.append(" | Poids=");
		builder.append(_Poids);
		builder.append(" | Taille=");
		builder.append(_Taille);
		builder.append(" | Sexe=");
		builder.append(_Sexe);
		builder.append(" | IMC=");
		builder.append(iMC());
		builder.append(" | poids min=");
		builder.append(poidsMin());
		builder.append(" | poids max=");
		builder.append(poidsMax());
		builder.append(" | poids ideal=");
		builder.append(poidsIdeal());
		builder.append(" | diagnostic=");
		builder.append(diagnostic());
		builder.append("");
		return builder.toString();
	}
	
	
}
