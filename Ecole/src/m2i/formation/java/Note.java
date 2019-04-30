package m2i.formation.java;

import java.time.LocalDate;

public class Note {
	private Matiere matiere;
	private float valeur;
	private int ID_Note;
	private int ID_Eleve;
	private LocalDate date;

	@Override
	public String toString() {
		return "Note [matiere=" + matiere + ", valeur=" + valeur + ", ID_Note=" + ID_Note + ", ID_Eleve=" + ID_Eleve
				+ ", date=" + date + "]";
	}

	public Note(int iD_Note, float valeur, Matiere matiere, int iD_Eleve, LocalDate date) {
		super();
		this.matiere = matiere;
		this.valeur = valeur;
		ID_Note = iD_Note;
		ID_Eleve = iD_Eleve;
		this.date = date;
	}

	// Getters & Setters
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getID_Note() {
		return ID_Note;
	}

	public void setID_Note(int iD_Note) {
		ID_Note = iD_Note;
	}

	public int getID_Eleve() {
		return ID_Eleve;
	}

	public void setID_Eleve(int iD_Eleve) {
		ID_Eleve = iD_Eleve;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public float getValeur() {
		return valeur;
	}

	public void setValeur(float valeur) {
		this.valeur = valeur;
	}

}
