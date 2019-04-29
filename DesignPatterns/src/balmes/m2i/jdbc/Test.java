package balmes.m2i.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
	static final String url = "jdbc:mysql://localhost:3306/DP_Formation?serverTimezone=UTC";
	static final String user = "root";
	static final String password = "";

	static final Connection cnn = SingleConnection.getInstance(url, user, password);

	public static void main(String[] args) {

		Personne p1 = new Personne(1, "Abelard", "Simon", 54.7f, 1.75f, Genre.MASCULIN);
//		System.out.println(p1);

		DAOPersonne personneDAO = new DAOPersonne();
		System.out.println("retrieve(1):" + personneDAO.retrieve(1));

		Personne p2 = new Personne(1, "Balmes", "Antoine", 54.7f, 1.75f, Genre.MASCULIN);
		System.out.println(personneDAO.update(p2));
		System.out.println("\nretrieve(1) après édition:" + personneDAO.retrieve(1));

		System.out.println("\ndelete(1): " + personneDAO.delete(1));
		System.out.println("Etat de la BDD après suppression:");
		printTablePersonne();

		System.out.println("\ncreate(): " + personneDAO.create(p1));
		System.out.println("Etat de la BDD après création:");
		printTablePersonne();

		System.out.println("\nretrieveAll() : ");
		System.out.println(personneDAO.retrieveAll());

	}

	/**
	 * Prints the current entry in a result set
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public static void printCurrentEntry(ResultSet rs) throws SQLException {
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.print(rs.getObject(i) + " | ");
		}
		System.out.print("\n");
	}

	/**
	 * Prints an entire result set
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public static void printResultSet(ResultSet rs) throws SQLException {
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.print(rs.getMetaData().getColumnName(i) + " | ");
		}
		System.out.print("\n");
		while (rs.next()) {
			printCurrentEntry(rs);
		}
	}

	/**
	 * Prints the table "Personne"
	 * 
	 * @throws SQLException
	 */
	public static void printTablePersonne() {
		String sql = "SELECT * FROM Personne";
		PreparedStatement st;
		try {
			st = cnn.prepareStatement(sql);
			printResultSet(st.executeQuery());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns the next valid ID for inserting into the "Personne" table
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static synchronized int getNextValidIdPersonne() throws SQLException {
		String sql = "SELECT MAX(ID_Personne) FROM Personne";
		PreparedStatement st = cnn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			return rs.getInt(1) + 1;
		} else {
			throw new SQLException("Query failed:" + st);
		}
	}

}
