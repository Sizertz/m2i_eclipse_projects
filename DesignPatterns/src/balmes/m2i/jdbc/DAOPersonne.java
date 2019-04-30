package balmes.m2i.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPersonne implements IDAO<Personne> {
	private static final String url = "jdbc:mysql://localhost:3306/DP_Formation?serverTimezone=UTC";
	private static final String user = "root";
	private static final String pwd = "";
	private static Connection _cnn = SingleConnection.getInstance(url, user, pwd);

	@Override
	public int create(Personne obj) {
		String query = "INSERT INTO Personne VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, obj.get_ID_Personne());
			ps.setString(2, obj.get_Nom());
			ps.setString(3, obj.get_Prenom());
			ps.setFloat(4, obj.get_Poids());
			ps.setFloat(5, obj.get_Taille());
			ps.setString(6, obj.get_Sexe().toString());
			ps.setInt(7, obj.get_ID_Societe());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Personne retrieve(int id) {
		String query = "SELECT * FROM Personne WHERE ID_Personne = ?";
		ResultSet rs = null;
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Genre sexe = Genre.valueOf(rs.getString("Sexe")); // rs.getString("Sexe").equals("MASCULIN")?
																	// Genre.MASCULIN : Genre.FEMININ;
				return new Personne(id, rs.getString("Nom"), rs.getString("Prenom"), rs.getFloat("Poids"),
						rs.getFloat("Taille"), sexe, rs.getInt("ID_Societe"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Personne> retrieveAll() {
		List<Personne> res = new ArrayList<>();
		String query = "SELECT * FROM Personne";
		ResultSet rs = null;
		Genre sexe;
		Personne p;
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				sexe = rs.getString("Sexe").equals("MASCULIN") ? Genre.MASCULIN : Genre.FEMININ;
				p = new Personne(rs.getInt("ID_Personne"), rs.getString("Nom"), rs.getString("Prenom"),
						rs.getFloat("Poids"), rs.getFloat("Taille"), sexe, rs.getInt("ID_Societe"));
				res.add(p);
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int delete(int id) {
		String query = "DELETE FROM Personne WHERE ID_Personne = ?";
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
	public int update(Personne obj) {
		String query = "UPDATE Personne SET Nom = ?, Prenom = ?, Poids = ?, Taille = ?, Sexe = ?, ID_Societe = ? WHERE ID_Personne = ?";
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setString(1, obj.get_Nom());
			ps.setString(2, obj.get_Prenom());
			ps.setFloat(3, obj.get_Poids());
			ps.setFloat(4, obj.get_Taille());
			ps.setString(5, obj.get_Sexe().toString());
			ps.setInt(6, obj.get_ID_Societe());
			ps.setInt(7, obj.get_ID_Personne());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void printTable() {
		String sql = "SELECT * FROM Personne";
		PreparedStatement st;
		try {
			st = _cnn.prepareStatement(sql);
			IDAO.printResultSet(st.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized int getNextValidId() {
		String sql = "SELECT MAX(ID_Personne) FROM Personne";
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

}
