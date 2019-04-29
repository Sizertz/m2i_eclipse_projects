package balmes.m2i.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Implementation du design-pattern "Singleton" <br>
 * But: une classe n'ayant qu'une seule instance.
 * 
 * 
 * @author Simon Balmes
 *
 */
public class SingleConnection {
	private static Connection _instance = null;

	private SingleConnection() {
	}

	public static Connection getInstance(String url, String user, String pwd) {
		if (_instance == null) {
			synchronized (SingleConnection.class) {
				if (_instance == null) {
					try {
						_instance = DriverManager.getConnection(url, user, pwd);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return _instance;
	}

}
