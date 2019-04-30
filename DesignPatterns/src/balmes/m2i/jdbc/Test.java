package balmes.m2i.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {
	static final String url = "jdbc:mysql://localhost:3306/DP_Formation?serverTimezone=UTC";
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
		System.out.println("Etat de la BDD après suppression:");
		personneDAO.printTable();

		// Testing create
		System.out.println("\ncreate(): " + personneDAO.create(p1));
		System.out.println("Etat de la BDD après création:");
		personneDAO.printTable();

		// Testing retrieveAll
		System.out.println("\nretrieveAll() : ");
		System.out.println(personneDAO.retrieveAll());

		// Testing getNextValidID
		Personne p3 = new Personne(personneDAO.getNextValidId(), "Abelard", "Simon", 54.7f, 1.75f, Genre.MASCULIN, 1);
		personneDAO.create(p3);

		// ----------------------------------------------------------
		// Test de societe
		System.out.println("----------------------------------------------------------");
		System.out.println("Test de SocieteDAO");

		// Testing societe retrieve
		DAOSociete societeDAO = new DAOSociete();
		System.out.println("\nretrieve(1):" + societeDAO.retrieve(1));

		// Testing update
		Societe s2 = new Societe(1, "BNP Paribas", 54.7f, "Banque et assurance", new ArrayList<Personne>());
		System.out.println(societeDAO.update(s2));
		System.out.println("\nretrieve(1) après édition:" + societeDAO.retrieve(1));

		// Testing delete
		System.out.println("\ndelete(1): " + societeDAO.delete(1));
		System.out.println("Etat de la BDD après suppression:");
		societeDAO.printTable();

		// Testing create
		Societe s1 = new Societe(1, "BNP", 10000000f, "Banque", new ArrayList<Personne>());
		System.out.println("\ncreate(): " + societeDAO.create(s1));
		System.out.println("Etat de la BDD après création:");
		societeDAO.printTable();

		// Testing retrieveAll
		System.out.println("\nretrieveAll() : ");
		System.out.println(societeDAO.retrieveAll());

		// Testing getNextValidID
		Societe s3 = new Societe(societeDAO.getNextValidId(), "M2I Formation", 540000.7f, "Organisme de formation",
				new ArrayList<Personne>());
		societeDAO.create(s3);
	}

}
