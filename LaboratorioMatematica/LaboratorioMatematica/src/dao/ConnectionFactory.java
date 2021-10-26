package dao;

import java.sql.*;

/***
 * Classe responsavel por criar as conexoes que serao usadas para comunicacao do
 * sistema com o banco de dados
 * 
 * @author thiago
 *
 */
public class ConnectionFactory {

	public Connection getConnection() {

		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/labmat?serverTimezone=UTC", "root", "33411118");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
