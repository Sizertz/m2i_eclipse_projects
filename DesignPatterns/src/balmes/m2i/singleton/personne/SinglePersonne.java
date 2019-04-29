package balmes.m2i.singleton.personne;

public class SinglePersonne {
	private static Personne _instance = null;

	private SinglePersonne() {
	}

	public static Personne getPersonne(String prenom, String nom) {
		if (_instance == null)
			_instance = new Personne(prenom, nom);
		return _instance;
	}

}
