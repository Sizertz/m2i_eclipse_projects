package m2i.formation.java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Classe implements Moyennable {
	private String id_salle;
	private String nom;

	private List<Eleve> eleves;

	public Classe(String id_salle) {
		this.id_salle = id_salle;
		this.eleves = new ArrayList<>();
	}

	// Getters & Setters
	public String getId_salle() {
		return id_salle;
	}

	public void setId_salle(String id_salle) {
		this.id_salle = id_salle;
	}

	public List<Eleve> getEleves() {
		return eleves;
	}

	public void setEleves(List<Eleve> eleves) {
		this.eleves = eleves;
	}

	/**
	 * Ajouter un Eleve
	 * 
	 * @param e Eleve
	 */
	public void addEleve(Eleve e) {
		this.eleves.add(e);
	}

	// Affichage
	@Override
	public String toString() {
		return "Classe [id_salle=" + id_salle + ", eleves=" + eleves + "]";
	}

	public float moyenne(NoteFilter filter) {
		if (this.eleves.isEmpty()) {
			return -1;
		}
		float result = 0;
		for (Eleve e : this.eleves) {
			result += e.moyenne(filter);
		}
		result /= eleves.size();
		return result;
	}

	/**
	 * Retourne la moyenne générale de la classe
	 */
	@Override
	public float moyenne() {
		if (this.eleves.isEmpty()) {
			return -1;
		}
		float result = 0;
		for (Eleve e : this.eleves) {
			result += e.moyenne();
		}
		result /= eleves.size();
		return result;
	}

	/**
	 * Retourne la moyenne de la classe dans la matière m
	 */
	@Override
	public float moyenneParMatiere(Matiere m) {
		if (this.eleves.isEmpty()) {
			return -1;
		}
		float result = 0;
		for (Eleve e : this.eleves) {
			result += e.moyenneParMatiere(m);
		}
		result /= eleves.size();
		return result;
	}

	public Classement classementFiltre(NoteFilter filter) {
		// trie l'attribut eleves
		eleves.sort((Eleve e1, Eleve e2)->((Float)e1.moyenne(filter)).compareTo(e2.moyenne(filter)));

		// copie la liste
		List<Eleve> result = new ArrayList<>();
		result.addAll(eleves);

		// retourne la liste
		return new Classement(result, (Note note) -> true);
	}

	public Classement classement(Comparator<Eleve> comp) {
		// trie l'attribut eleves
		eleves.sort(comp);

		// copie la liste
		List<Eleve> result = new ArrayList<>();
		result.addAll(eleves);

		// retourne la liste
		return new Classement(result, (Note note) -> true);
	}

	/**
	 * classe les eleves par moyenne générale et renvoit le classement à cet instant
	 * 
	 * @return
	 */
	public Classement classementGeneral() {
		// trie l'attribut eleves
		eleves.sort(new ComparateurGeneral());

		// copie la liste
		List<Eleve> result = new ArrayList<>();
		result.addAll(eleves);

		// retourne la liste
		return new Classement(result, new Moyenator() {

			@Override
			public float calculer(Eleve e) {
				return e.moyenne();
			}

		});
	}

	/**
	 * classe les eleves par moyenne générale et renvoit le classement à cet instant
	 * 
	 * @return
	 */
	public Classement classementParMatiere(Matiere m) {
		// trie l'attribut eleves
		eleves.sort(new Comparator<Eleve>() {

			@Override
			public int compare(Eleve e1, Eleve e2) {
				return ((Float) e1.moyenneParMatiere(m)).compareTo(e2.moyenneParMatiere(m));
			}

		});

		// copie la liste
		List<Eleve> result = new ArrayList<>();
		result.addAll(eleves);

		// retourne la liste
		return new Classement(result, new Moyenator() {

			@Override
			public float calculer(Eleve e) {
				return e.moyenneParMatiere(m);
			}

		});
	}

}
