package m2i.formation.java;

public class Principale {

	public static void main(String[] args) {
		Personne p1;
		// p1 = new Personne("Audiard", "Alice"); ne compile pas parce que la classe est
		// abstraite.
		p1 = new Stagiaire("Audiard", "Alice", "Bac S");
		System.out.println(p1);
		IMouvement i = new Cycliste("Bu", "Bob", "Orange");
		System.out.println(i instanceof IMouvement);
		i.accelerer();
		i = new Camion(null, null);
		i.freiner();
	}

}
