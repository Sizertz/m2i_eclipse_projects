package balmes.m2i.ecole.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
	/**
	 * Adds an object to the db
	 * 
	 * @param obj
	 * @return the result of the sql query. 1 if creation succeeded. 0 if not. -1 if
	 *         exception was thrown.
	 */
	public int create(T obj);

	/**
	 * Finds and returns the object with a certain ID
	 * 
	 * @param id
	 * @return the object with ID id or null if doesn't exist
	 */
	public T retrieve(int id);

	/**
	 * Returns all elements of type T in the database in a List
	 * 
	 * @return all elements of type T in the database in a List
	 */
	public List<T> retrieveAll();

	/**
	 * Deletes the entry with ID id
	 * 
	 * @param id
	 * @return the result of the sql query. 1 if creation succeeded. 0 if not. -1 if
	 *         exception was thrown.
	 */
	public int delete(int id);

	/**
	 * Overrides all attributes of the entry with ID obj.id
	 * 
	 * @param obj
	 * @return the result of the sql query. 1 if creation succeeded. 0 if not. -1 if
	 *         exception was thrown.
	 */
	public int update(T obj);

	/**
	 * Returns the next valid ID for inserting into the "Personne" table
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int getNextValidId();

	/**
	 * Prints the table "Personne"
	 * 
	 * @throws SQLException
	 */
	public void printTable();

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

}
