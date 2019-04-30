package m2i.formation.java;

import java.util.ArrayList;
import java.util.List;

public class Classement {
	protected List<Eleve> classement;
	protected List<Float> moyennes;

	public List<Eleve> getClassement() {
		return classement;
	}

	public void setClassement(List<Eleve> classement) {
		this.classement = classement;
	}

	public List<Float> getMoyennes() {
		return moyennes;
	}

	public void setMoyennes(List<Float> moyennes) {
		this.moyennes = moyennes;
	}

	public Classement(List<Eleve> classement, Moyenator moy) {
		this.classement = classement;
		enregistrerMoyennes(moy);
	}

	public Classement(List<Eleve> classement, NoteFilter<Note> filter) {
		this.classement = classement;
		enregistrerMoyennes((Eleve e)->e.moyenne(filter));
	}

	public void enregistrerMoyennes(Moyenator moy) {
		this.moyennes = new ArrayList<Float>();
		for (Eleve e : classement)
			moyennes.add(moy.calculer(e));

	}

	// affiche un classement à partir d'une liste supposée ordonnée
	public String toString() {
		String str = "Classement\n";
		for (int i = 0; i < classement.size(); i++) {
			str += classement.get(i).getPrenom() + " " + classement.get(i).getPrenom() + " " + this.moyennes.get(i)
					+ "\n";
		}
		return str;
	}
}
