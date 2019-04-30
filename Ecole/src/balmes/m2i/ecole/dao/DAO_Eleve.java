package balmes.m2i.ecole.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import m2i.formation.java.Eleve;
import m2i.formation.java.Matiere;
import m2i.formation.java.Note;

public class DAO_Eleve implements IDAO<Eleve> {
	private static final String url = "jdbc:mysql://localhost:3306/ecoles?serverTimezone=UTC";
	private static final String user = "root";
	private static final String pwd = "";
	private static Connection _cnn = SingleConnection.getInstance(url, user, pwd);

	@Override
	public int create(Eleve obj) {
		String query = "INSERT INTO Eleve VALUES (?,?,?,?)";
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, obj.getID_Eleve());
			ps.setString(2, obj.getNom());
			ps.setString(3, obj.getPrenom());
			ps.setInt(4, obj.getID_Classe());

			int res = ps.executeUpdate();

			DAO_Note daon = new DAO_Note();
			for (Note n : obj.getNotes()) {
				daon.create(n);
			}

			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Eleve retrieve(int id) {
		// Retrieve Societe entry given an ID
		String query = "SELECT * FROM Eleve WHERE ID_Eleve = ?";
		ResultSet rs = null;
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				List<Note> lstNotes = retrieveAllNotes(id); // retrieval of all the students grades
				return new Eleve(rs.getString("Nom"), rs.getString("Prenom"), lstNotes, new ArrayList<Matiere>(), id,
						rs.getInt("ID_Classe"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Note> retrieveAllNotes(int id) {
		String query = "SELECT * FROM Note WHERE ID_Eleve = ?";
		ResultSet rs = null;
		List<Note> result = new ArrayList<>();

		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Matiere mat = Matiere.valueOf(rs.getString("Matiere"));
				result.add(new Note(id, rs.getFloat("Valeur"), mat, rs.getInt("ID_Eleve"),
						rs.getObject("Date", LocalDate.class)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Eleve> retrieveAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int id) {
		// start by deleting notes (manual ON DELETE CASCADE)
		deleteAllNotes(id);

		// delete Note entry
		String query = "DELETE FROM Eleve WHERE ID_Eleve = ?";
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;

	}

	private static int deleteAllNotes(int id) {
		String query = "DELETE FROM Note WHERE ID_Eleve = ?";
		int result = -1;

		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setInt(1, id);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int update(Eleve obj) {
		// if obj hasn't been saved before, create it
		if (this.retrieve(obj.getID_Eleve()) == null) {
			return this.create(obj);
		}

		// update the notes by deleting and recreating them all
		// start by deleting notes
		deleteAllNotes(obj.getID_Eleve());
		// re-create all notes
		DAO_Note daon = new DAO_Note();
		for (Note n : obj.getNotes()) {
			daon.create(n);
		}

		// update the Societe entry.
		String query = "UPDATE Eleve SET Nom = ?, Prenom = ?, ID_Classe = ? WHERE ID_Eleve = ?";
		try {
			PreparedStatement ps = _cnn.prepareStatement(query);
			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPrenom());
			ps.setInt(3, obj.getID_Classe());
			ps.setInt(4, obj.getID_Eleve());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
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
