package m2i.formation.java;

import java.util.List;

public class Eleve extends Personne implements Moyennable {
	private List<Note> notes;
	private List<Matiere> matieres;
	private int ID_Eleve;
	private int ID_Classe;

	public Eleve(String nom, String prenom, List<Note> notes, List<Matiere> matieres, int iD_Eleve, int iD_Classe) {
		super(nom, prenom);
		this.notes = notes;
		this.matieres = matieres;
		ID_Eleve = iD_Eleve;
		ID_Classe = iD_Classe;
	}

	public int getID_Classe() {
		return ID_Classe;
	}

	public void setID_Classe(int iD_Classe) {
		ID_Classe = iD_Classe;
	}

	public int getID_Eleve() {
		return ID_Eleve;
	}

	public void setID_Eleve(int iD_Eleve) {
		ID_Eleve = iD_Eleve;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}

	/**
	 * Ajouter une Matiere
	 * 
	 * @param m
	 */
	public void addMatiere(Matiere m) {
		this.matieres.add(m);
	}

	/**
	 * Ajouter une Note
	 * 
	 * @param n
	 */
	public void addNote(Note n) {
		n.setID_Eleve(this.ID_Eleve);
		this.notes.add(n);
	}

	@Override
	public float moyenne() {
		float result = 0;
		for (Note note : notes) {
			result = result + note.getValeur();
		}

		result = result / notes.size();
		// TODO Auto-generated method stub
		return result;
	}

	public float moyenne(NoteFilter filter) {
		float result = 0;
		int compteur = 0;
		for (Note note : notes) {
			if (filter.filter(note)) {
				result += note.getValeur();
				compteur++;
			}
		}
		if (compteur != 0)
			result = result / compteur;
		else
			return -1;
		return result;
	}

	@Override
	public float moyenneParMatiere(Matiere m) {
		// TODO Auto-generated method stub
		float result = 0;
		int compteur = 0;
		for (Note note : notes) {
			if (note.getMatiere() == m) {
				result += note.getValeur();
				compteur++;
			}
		}
		if (compteur != 0)
			result = result / compteur;
		else
			return -1;
		return result;
	}

	@Override
	public String toString() {
		return "Eleve [notes=" + notes + ", matieres=" + matieres + ", ID_Eleve=" + ID_Eleve + ", ID_Classe="
				+ ID_Classe + "]";
	}

}
