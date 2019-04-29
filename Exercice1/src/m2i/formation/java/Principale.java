package m2i.formation.java;

public class Principale {

	public static void main(String[] args) {
		Employe employe1 = new Employe("Durand", "Jacques", 10000f, (byte) 3);
		System.out.println("employe1: " + employe1);
		employe1.affichePrimes();

		Employe employe2 = new Employe("Dupont", "Bob", 0, (byte) 0);
		System.out.println("employe2: " + employe2);
		employe2.affichePrimes();

		Employe employe3 = new Employe("Carnot", "Charles", 2000, (byte) 2);
		System.out.println("employe3: " + employe3);
		employe3.affichePrimes();
	}

}
