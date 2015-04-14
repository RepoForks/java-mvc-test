package com.newfurniturey.mvc.app;

import com.newfurniturey.mvc.app.controllers.AddShow;
import com.newfurniturey.mvc.app.controllers.Shows;
import com.newfurniturey.mvc.app.models.Show;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class App {
	public static final String DB_NAME = "anime.sqlite";
	private static Connection _connection = null;
	private static Controller _shows = null;
    private static Controller _addShow = null;
    
	/**
	 * The main entry-point in the MVC Application test.
	 */
	public void run() {
		_shows = new Shows();
        _shows.display();
	}
    
    public static void addNewShow() {
        _addShow = new AddShow();
        _addShow.display();
    }
    
    public static void refreshList() {
        ((Shows)_shows).refresh();
    }
	
	/**
	 * Establishes and returns a database connection.
	 *
	 * @return Connection The established db connection
	 */
	public static Connection getConnection() throws SQLException {
		if (_connection == null) {
			_connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
		}
		return _connection;
	}
	
	/**
	 * Closes any currently-open database connection.
	 */
	public static void close() {
		if (_connection == null) return;
		
		try {
			_connection.close();
		} catch (SQLException e) {
			System.err.println("sql error: " + e.getMessage());
		} finally {
			_connection = null;
		}
	}
	
	/**
	 * Configures our 'MVC Application' for a nice proof-of-concept run.
	 */
	public static boolean bootstrap() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		
		Connection connection = null;
		try {
			connection = getConnection();
			
			// bootstrap our model(s)
			(new Show(connection)).bootstrap();
			
			return true;
		} catch (InvalidDatabaseException e) {
			System.out.println("invalid database =[");
			return false;
		} catch (SQLException e) {
			System.out.println("sql error: " + e.getMessage());
			return false;
		} finally {
			close();
		}
	}

}
