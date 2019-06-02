package com.ceiti.botezatu;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCPostgreSQLConnect {

//  Database credentials
	private String db_url = "jdbc:postgresql://127.0.0.1:5432/bank";
	private String user = "banker";
	private String pass = "test";

	public String getDb_url() {
		return db_url;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public void setDb_url(String db_url) {
		this.db_url = db_url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Connection getDBConnection() {
		return getDBConnection(getDb_url(), getUser(), getPass());
	}

	public Connection getDBConnection(String db_url, String user, String pass) {
		System.out.println("Testing connection to PostgreSQL JDBC");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
			e.printStackTrace();
		}
		System.out.println("PostgreSQL JDBC Driver successfully connected");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(db_url, user, pass);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("You successfully connected to database now");
		} else {
			System.out.println("Failed to make connection to database");
		}
		return connection;
	}

	public void insert(Connection connection, String sqlQuery) {

		Statement statement = null;
		int rows = 0;
		try {

			statement = connection.createStatement();
			rows = statement.executeUpdate(sqlQuery);
			statement.close();
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Was inserted " + rows + " records successfully.");
	}

	public void select(Connection connection, String sqlQuery) {
		Statement statement = null;
		ResultSet resultSet = null;
		try {

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);
			while (resultSet.next()) {
				System.out.print(String.format("%3s | %-18s | %-12s | %-18s%n", resultSet.getString(1),
						resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
			}
			statement.close();
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
