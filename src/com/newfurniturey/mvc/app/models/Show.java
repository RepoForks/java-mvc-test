package com.newfurniturey.mvc.app.models;

import com.newfurniturey.mvc.app.Model;
import com.newfurniturey.mvc.app.InvalidDatabaseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Show extends Model {
	
	protected String _tableName = "shows";
	
	public Show(Connection conn) {
		super(conn);
	}
	
	public void bootstrap() throws InvalidDatabaseException {
		if (this._tableExists(this._tableName)) {
			// table already exists, nothing to do =]
			return;
		}
		
		try {
			Statement statement = this._connection.createStatement();
			statement.executeUpdate("CREATE TABLE shows (id integer PRIMARY KEY, name string, image_url string, rating real, description text, added_on integer);");
		} catch (SQLException e) {
			System.err.println("Failed to create table: " + this._tableName);
		}
	}
	
}
