package balmes.m2i.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Main {
	static Connection cnn;

	public static void main(String[] args) {
//		String dbmsName = "mysql";
//		String hostAdress = "localhost";
//		String portAdress = "3306";
//		String databaseName = "formation";
//		String url = "jdbc:" + dbmsName + "://" + hostAdress + ":" + portAdress + "/" + databaseName;

		String url = "jdbc:mysql://localhost:3306/formation?serverTimezone=UTC";
		String user = "root";
		String password = "";
		String query = "";

		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			cnn = DriverManager.getConnection(url, user, password);
			st = cnn.createStatement();
			st.execute("UPDATE Personne SET Nom = 'Bob' WHERE Nom = 'foo'");
			
			query = "DELETE FROM Personne WHERE ID_Personne > 200";
			st.executeUpdate(query);

			query = "INSERT INTO Personne (ID_Personne, Nom, Prenom, Telephone, Email, Nb_victoires, Pays, ID_Ecurie)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			ps = cnn.prepareStatement(query);
			ps.setInt(1, getNextValidIdPersonne());
			ps.setObject(2, "Zola");
			ps.setString(3, "Emile");
			ps.setString(4, "786123864");
			ps.setString(5, "ez@roxor.gg");
			ps.setObject(6, null);
			ps.setString(7, "France");
			ps.setInt(8, 2);
			System.out.println(ps);

			//int n = ps.executeUpdate();
			//System.out.println("Number of modified rows " + n); // .executeUpdate() returns the number of updated rows

			printTablePersonne();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void printCurrentEntry(ResultSet rs) throws SQLException {
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.print(rs.getObject(i) + " | ");
		}
		System.out.print("\n");
	}

	public static void printResultSet(ResultSet rs) throws SQLException {

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.print(rs.getMetaData().getColumnName(i) + " | ");
		}
		System.out.print("\n");
		while (rs.next()) {
			printCurrentEntry(rs);
		}
	}

	public static void printTablePersonne() throws SQLException {
		String sql = "SELECT * FROM Personne";
		PreparedStatement st = cnn.prepareStatement(sql);
		printResultSet(st.executeQuery());
	}
	
	public static int getNextValidIdPersonne() throws SQLException {
		String sql = "SELECT MAX(ID_Personne) FROM Personne";
		PreparedStatement st = cnn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			return rs.getInt(1)+1;
		}else {
			throw new SQLException("Query failed:" + st);
		}
	}

}
