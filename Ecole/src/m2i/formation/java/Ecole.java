package m2i.formation.java;

import java.util.ArrayList;

public class Ecole {
	private String nom;
	private ArrayList<Classe> classes;

	public Ecole(String nom) {
		this.nom = nom;
		this.classes = new ArrayList<>();
	}

	// Getters & Setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Classe> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<Classe> classes) {
		this.classes = classes;
	}
	
	/**
	 * Ajouter une classe
	 * @param c Classe à ajouter
	 */
	public void addClasse(Classe c) {
		this.classes.add(c);
	}
	
	
	// Affichage
	@Override
	public String toString() {
		return "Ecole [nom=" + nom + ", classes=" + classes + "]";
	}

}
