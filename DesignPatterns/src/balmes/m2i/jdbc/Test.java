package balmes.m2i.jdbc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;

public class Test {
	static final String url = "jdbc:mysql://localhost:3306/DP_Formation_Simon?serverTimezone=UTC";
	static final String user = "root";
	static final String password = "";

	static final Connection cnn = SingleConnection.getInstance(url, user, password);

	public static void main(String[] args) {

		Personne p1 = new Personne(1, "Abelard", "Simon", 54.7f, 1.75f, Genre.MASCULIN, 1);
//		System.out.println(p1);

		// Testing retrieve
		DAOPersonne personneDAO = new DAOPersonne();
		System.out.println("retrieve(1):" + personneDAO.retrieve(1));

		// Testing update
		Personne p2 = new Personne(1, "Balmes", "Antoine", 54.7f, 1.75f, Genre.MASCULIN, 1);
		System.out.println(personneDAO.update(p2));
		System.out.println("\nretrieve(1) après édition:" + personneDAO.retrieve(1));

		// Testing delete
		System.out.println("\ndelete(1): " + personneDAO.delete(1));
		System.out.println("Etat de la table Personne après suppression:");
		personneDAO.printTable();

		// Testing create
		System.out.println("\ncreate(): " + personneDAO.create(p1));
		System.out.println("Etat de la table Personne après création:");
		personneDAO.printTable();

		// Testing retrieveAll
		System.out.println("\nretrieveAll() : ");
		System.out.println(personneDAO.retrieveAll());

		// Testing getNextValidID
		Personne p3 = new Personne(personneDAO.getNextValidId(), "Crawford", "Cindy", 35.7f, 1.50f, Genre.FEMININ, 1);
		Personne p4 = new Personne(personneDAO.getNextValidId(), "Dalton", "Joe", 40f, 1.12f, Genre.MASCULIN, 10);
		personneDAO.create(p3);

		// ----------------------------------------------------------
		// Test de societe
		System.out.println("----------------------------------------------------------");
		System.out.println("Test de SocieteDAO");

		// Testing societe retrieve
		DAOSociete societeDAO = new DAOSociete();
		System.out.println("\nretrieve(1):" + societeDAO.retrieve(1));

		// Testing update
		// - Change ChiffreDAffaire to a random value
		System.out.println("Etat de la table Personne avant édition:");
		personneDAO.printTable();
		Societe s2 = new Societe(1, "BNP Paribas", new Random().nextInt(), "Banque et assurance",
				new ArrayList<Personne>());
		// - add p1, p2, p4 but not p3
		s2.addEmploye(p1);
		s2.addEmploye(p2);
		s2.addEmploye(p4);

		// - Update
		System.out.println("update(s2) : " + societeDAO.update(s2));

		System.out.println("Etat de la table Societe apres edition:");
		societeDAO.printTable();
		System.out.println("Etat de la table Personne apres edition:");
		personneDAO.printTable();

		// Testing create
		int newId = societeDAO.getNextValidId();
		Societe s1 = new Societe(newId, "Microsoft", 1000000000f, "Computer hardware and software",
				new ArrayList<Personne>());
		// add 10 new employees
		for (int i = 0; i < 10; i++) {
			Personne p = new Personne(personneDAO.getNextValidId(), "MicroEmployee", "No." + i, 68f, 1.45f,
					Genre.FEMININ, newId);
			s1.addEmploye(p);
		}

		System.out.println("\ncreate(): " + societeDAO.create(s1));
		System.out.println("Etat de la table Societe après création:");
		societeDAO.printTable();
		System.out.println("Etat de la table Personne après création:");
		personneDAO.printTable();

		// Testing retrieveAll
		System.out.println("\nretrieveAll() : ");
		System.out.println(societeDAO.retrieveAll());

		// Testing cascading delete
		System.out.println("\ndelete(newID=" + newId + "): " + societeDAO.delete(newId));
		System.out.println("Etat de la table Societe après suppression:");
		societeDAO.printTable();
		System.out.println("Etat de la table Personne après suppression:");
		personneDAO.printTable();
	}

}
