package com.newfurniturey.mvc.app;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Model {
	/**
	 * The database connection to use.
	 */
	protected Connection _connection = null;
	
	/**
	 * Initializes the Model instance.
	 */
	public Model() {
		// allow an empty construction, but nothing to do here =]
	}
	
	/**
	 * Initializes the Model instance.
	 *
	 * @param Connection conn The database connection to use.
	 */
	public Model(Connection conn) {
		this._connection = conn;
	}

	/**
	 * Initializes the model's database schema, if it doesn't already exist.
	 */
	abstract public void bootstrap() throws InvalidDatabaseException;
	
	/**
	 * Checks if a table exists in the current database or not.
	 *
	 * @param String tableName	Name of the table to check for.
	 * @return boolean			true if the table exists; otherwise false
	 */
	protected boolean _tableExists(String tableName) throws InvalidDatabaseException {
		if (this._connection == null) {
			throw new InvalidDatabaseException();
		}
		
		try {
			DatabaseMetaData meta = this._connection.getMetaData();
			ResultSet rs = meta.getTables(null, null, "%", null);
			while (rs.next()) {
				if (rs.getString(3).equals(tableName)) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.err.println("Failed to pull metadata from db: " + e.getMessage());
			return false;
		}
		
		return false;
	}
}
