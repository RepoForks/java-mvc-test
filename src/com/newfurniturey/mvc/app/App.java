package com.newfurniturey.mvc.app;

import com.newfurniturey.mvc.app.models.Show;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
	private static String DB_NAME = "anime.sqlite";
	
	public static boolean bootstrap() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
			(new Show(connection)).bootstrap();
			
			return true;
		} catch (InvalidDatabaseException e) {
			System.out.println("invalid database =[");
			return false;
		} catch (SQLException e) {
			System.out.println("sql error: " + e.getMessage());
			return false;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("failed to close the connection: " + e.getMessage());
			}
		}
	}

}
