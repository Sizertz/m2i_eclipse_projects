package balmes.m2i.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOSociete implements IDAO<Societe> {
	private static final String url = "jdbc:mysql://localhost:3306/DP_Formation_Simon?serverTimezone=UTC";
	private static final String user = "root";
	private static final String pwd = "";
	private static Connection _cnn = SingleConnection.getInstance(url, user, pwd);

	@Override
	public int create(Societe obj) {
		// make sure that obj has a valid ID by switching it with getNextValidId if
		// necessary
		int minId = getNextValidId();
		obj.set_ID_Societe(Math.max(minId, obj.get_ID_Societe()));

		String query = "INSERT INTO Societe VALUES (?,?,?,?)";
		try {
			// Insert Societe entry
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, obj.get_ID_Societe());
			ps.setString(2, obj.get_Nom());
			ps.setFloat(3, obj.get_ChiffreDAffaire());
			ps.setString(4, obj.get_Activite());

			int result = ps.executeUpdate();

			// Insert all Employe's from obj.lstEmployes
			DAOPersonne personneDAO = new DAOPersonne();
			System.out.println("NB employes de " + obj.get_Nom() + " " + obj.getLstEmployes().size());
			for (Personne p : obj.getLstEmployes()) {
				personneDAO.create(p);
			}

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Societe retrieve(int id) {
		// Retrieve Societe entry given an ID
		String query = "SELECT * FROM Societe WHERE ID_Societe = ?";
		ResultSet rs = null;
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				List<Personne> lstEmployes = retrieveAllEmployes(id); // retrieval of a company's employees is handled
																		// in
																		// separate method
				return new Societe(id, rs.getString("Nom"), rs.getFloat("ChiffreDAffaire"), rs.getString("Activite"),
						lstEmployes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns a List<Personne> of all of the employees in the database that belong
	 * to the company with ID idSociete
	 * 
	 * @param idSociete - the ID of the company
	 * @return a List<Personne> of all of the employees in the database that belong
	 *         to the company with ID idSociete
	 */
	private static List<Personne> retrieveAllEmployes(int idSociete) {
		String query = "SELECT * FROM Personne WHERE ID_Societe = ?";
		ResultSet rs = null;
		List<Personne> result = new ArrayList<>();

		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, idSociete);
			rs = ps.executeQuery();
			while (rs.next()) {
				Genre sexe = rs.getString("Sexe").equals("MASCULIN") ? Genre.MASCULIN : Genre.FEMININ;
				Personne p = new Personne(rs.getInt("ID_Personne"), rs.getString("Nom"), rs.getString("Prenom"),
						rs.getFloat("Poids"), rs.getFloat("Taille"), sexe, idSociete);
				result.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Deletes all employees in Personne table belonging to company with ID
	 * idSociete
	 * 
	 * @param idSociete
	 * @return number of entries that were edited || -1 if exception was thrown
	 */
	public static int deleteAllEmployes(int idSociete) {
		String query = "DELETE FROM Personne WHERE ID_Societe = ?";
		int result = -1;

		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, idSociete);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Societe> retrieveAll() {
		String query = "SELECT * FROM Societe";
		ResultSet rs = null;
		List<Societe> result = new ArrayList<>();

		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				List<Personne> lstEmployes = retrieveAllEmployes(rs.getInt("ID_Societe")); // retrieve the employees
				result.add(new Societe(rs.getInt("ID_Societe"), rs.getString("Nom"), rs.getFloat("ChiffreDAffaire"),
						rs.getString("Activite"), lstEmployes));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int delete(int id) {
		// start by deleting employees (manual ON DELETE CASCADE)
		deleteAllEmployes(id);

		// delete Societe entry
		String query = "DELETE FROM Societe WHERE ID_Societe = ?";
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(Societe obj) {
		// if obj hasn't been saved before, create it
		if (this.retrieve(obj.get_ID_Societe()) == null) {
			return this.create(obj);
		}

		// update the employees by deleting and recreating them all
		// start by deleting employees
		deleteAllEmployes(obj.get_ID_Societe());
		// re-create all employees
		DAOPersonne daop = new DAOPersonne();
		for (Personne p : obj.getLstEmployes()) {
			daop.create(p);
		}

		// update the Societe entry.
		String query = "UPDATE Societe SET Nom = ?, ChiffreDAffaire = ?, Activite = ? WHERE ID_Societe = ?";
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setString(1, obj.get_Nom());
			ps.setFloat(2, obj.get_ChiffreDAffaire());
			ps.setString(3, obj.get_Activite());
			ps.setInt(4, obj.get_ID_Societe());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int getNextValidId() {
		String sql = "SELECT MAX(ID_Societe) FROM Societe";
		try {
			PreparedStatement st = _cnn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public void printTable() {
		String sql = "SELECT * FROM Societe";
		PreparedStatement st;
		try {
			st = _cnn.prepareStatement(sql);
			IDAO.printResultSet(st.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
