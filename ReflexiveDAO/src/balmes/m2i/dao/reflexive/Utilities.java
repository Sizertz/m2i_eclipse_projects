package balmes.m2i.dao.reflexive;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Utilities {

	/**
	 * Returns an array containing all Fields of the specified class
	 * 
	 * @param fullQualifiedName - of the class whose fields we want
	 * @return an array containing all Fields of the specified class
	 */
	public static Field[] fieldArray(String fullQualifiedName) {
		try {
			Class<?> myClass = Class.forName(fullQualifiedName);
			return myClass.getDeclaredFields();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Generates SQL queries for CRUD operations given a class name. <br>
	 * Expects that the database uses the same names for columns as the class
	 * fields. <br>
	 * Expects that all fields have a corresponding column in the database.
	 * 
	 * @param fullQualifiedName - of the class for whom we want to generate the
	 *                          queries
	 * @return a Map of SQL queries as Strings for each of the CRUD operations,
	 *         indexed by : "create", "retrieve", "retrieveAll", "delete", "update"
	 */
	public static Map<String, String> sqlQueryMap(String fullQualifiedName) {
		Map<String, String> result = new HashMap<>();
		Class<?> myClass;
		try {
			myClass = Class.forName(fullQualifiedName);
			String simpleName = myClass.getSimpleName();
			Field[] fArray = fieldArray(fullQualifiedName);

			// create
			// build the query by successive concatenations
			String createQuery = "INSERT INTO ";
			createQuery += simpleName;
			createQuery += " VALUES (";
			for (int i = 0; i < fArray.length; i++) {
				// add a "?" for each field
				createQuery += "?";
				// add a ", " for each field except the last one
				if (i != fArray.length - 1) {
					createQuery += ", ";
				}
			}
			createQuery += ")";
			// store the query in our result map
			result.put("create", createQuery);

			// retrieve
			// build the query with in-line concatenations
			String retrieveQuery = "SELECT * FROM " + simpleName + " WHERE ID_" + simpleName + " = ?";
			// store the query in our result map
			result.put("retrieve", retrieveQuery);

			// retrieveAll
			String retrieveAllQuery = "SELECT * FROM " + simpleName;
			result.put("retrieveAll", retrieveAllQuery);

			// delete
			String deleteQuery = "DELETE * FROM " + simpleName + " WHERE ID_" + simpleName + " = ?";
			result.put("delete", deleteQuery);

			// update
			String updateQuery = "UPDATE " + simpleName + " SET ";
			for (int i = 0; i < fArray.length; i++) {
				// don't add ID_Class attribute
				if (!fArray[i].getName().equals("ID_" + simpleName)) {
					// add "fieldName = ?" for each field
					updateQuery += fArray[i].getName() + " = ?";
					// add ", " for each field except the last one
					if (i < fArray.length - 1)
						updateQuery += ", ";
				}
			}
			updateQuery += " WHERE ID_" + simpleName + " = ?";
			result.put("update", updateQuery);

			// return the map of our queries
			return result;

		} catch (ClassNotFoundException e) {
			System.out.println("CLASS NOT FOUND : " + e.getMessage());
		}

		// if an exception was raised, just return null
		return null;
	}
}
