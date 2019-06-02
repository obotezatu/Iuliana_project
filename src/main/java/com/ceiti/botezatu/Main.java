package com.ceiti.botezatu;

import java.sql.Connection;

public class Main {

	public static void main(String[] args) {

		JDBCPostgreSQLConnect connection = new JDBCPostgreSQLConnect();
		Connection connect = connection.getDBConnection();

		//connection.insert(connect,
			//	"INSERT INTO clients (ID,NAME,ADRESS,PHONE) VALUES (4, 'Sergiu Brasoveanu','Balti', '+373 444 333 44' );");
		
		connection.select(connect, "SELECT * FROM clients");
	}

}
