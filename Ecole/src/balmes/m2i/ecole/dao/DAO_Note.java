package balmes.m2i.ecole.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import m2i.formation.java.Matiere;
import m2i.formation.java.Note;

public class DAO_Note implements IDAO<Note> {
	private static final String url = "jdbc:mysql://localhost:3306/ecoles?serverTimezone=UTC";
	private static final String user = "root";
	private static final String pwd = "";
	private static Connection _cnn = SingleConnection.getInstance(url, user, pwd);

	@Override
	public int create(Note obj) {
		String query = "INSERT INTO Note VALUES (?,?,?,?,?)";
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, obj.getID_Note());
			ps.setFloat(2, obj.getValeur());
			ps.setString(3, obj.getMatiere().name());
			ps.setInt(4, obj.getID_Eleve());
			ps.setObject(5, obj.getDate().plusDays(1)); // watch out: sql Dates remove a day

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Note retrieve(int id) {
		String query = "SELECT * FROM Note WHERE ID_Note = ?";
		ResultSet rs = null;
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Matiere mat = Matiere.valueOf(rs.getString("Matiere"));
				return new Note(id, rs.getFloat("Valeur"), mat, rs.getInt("ID_Eleve"),
						rs.getObject("Date", LocalDate.class));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Note> retrieveAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int id) {
		String query = "DELETE FROM Note WHERE ID_Note = ?";
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
	public int update(Note obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNextValidId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printTable() {
		// TODO Auto-generated method stub

	}

}
