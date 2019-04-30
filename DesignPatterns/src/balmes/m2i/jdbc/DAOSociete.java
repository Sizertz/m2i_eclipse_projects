package balmes.m2i.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOSociete implements IDAO<Societe> {
	private static final String url = "jdbc:mysql://localhost:3306/DP_Formation?serverTimezone=UTC";
	private static final String user = "root";
	private static final String pwd = "";
	private static Connection _cnn = SingleConnection.getInstance(url, user, pwd);

	@Override
	public int create(Societe obj) {
		String query = "INSERT INTO Societe VALUES (?,?,?,?)";
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, obj.get_ID_Societe());
			ps.setString(2, obj.get_Nom());
			ps.setFloat(3, obj.get_ChiffreDAffaire());
			ps.setString(4, obj.get_Activite());

			DAOPersonne personneDAO = new DAOPersonne();
			for (Personne p : obj.getLstEmployes()) {
				if (personneDAO.retrieve(p.get_ID_Personne()) != null)
					personneDAO.create(p);
			}

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Societe retrieve(int id) {
		String query = "SELECT * FROM Societe WHERE ID_Societe = ?";
		ResultSet rs = null;
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				List<Personne> lstEmployes = retrieveEmployes(id);
				return new Societe(id, rs.getString("Nom"), rs.getFloat("ChiffreDAffaire"), rs.getString("Activite"),
						lstEmployes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Personne> retrieveEmployes(int id) {
		String query = "SELECT * FROM Personne WHERE ID_Societe = ?";
		ResultSet rs = null;
		List<Personne> result = new ArrayList<>();

		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Genre sexe = rs.getString("Sexe").equals("MASCULIN") ? Genre.MASCULIN : Genre.FEMININ;
				Personne p = new Personne(rs.getInt("ID_Personne"), rs.getString("Nom"), rs.getString("Prenom"),
						rs.getFloat("Poids"), rs.getFloat("Taille"), sexe, id);
				result.add(p);
			}
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
				List<Personne> lstEmployes = retrieveEmployes(rs.getInt("ID_Societe"));
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
