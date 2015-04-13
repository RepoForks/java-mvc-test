package com.newfurniturey.mvc.app;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	 * Gets the name of the table for the current model.
	 *
	 * @return String The current model's table name
	 */
	abstract public String getTableName();
	
	/**
	 * Gets the name of the primary key field for the current model.
	 *
	 * @return String The current model's primary key field
	 */
	abstract public String getPrimaryKey();
	
	/**
	 * Creates a new Model instance from data found in the database.
	 *
	 * @param ResultSet item	Database result to populate the instance.
	 * @return Model			Instance of the item created.
	 */
	abstract protected Model _newFromResultSet(ResultSet item) throws SQLException;
	
	/**
	 * Returns a full recordset of all items from the current table.
	 *
	 * @return List<Model> A list with instances of every item found.
	 */
	public List<Model> findAll() throws InvalidDatabaseException {
		if (this._connection == null) {
			throw new InvalidDatabaseException();
		}
		
		List<Model> rows = new ArrayList<Model>();
		
		try {
			Statement statement = this._connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM " + this.getTableName());
			while (results.next()) {
				rows.add(this._newFromResultSet(results));
			}
			return rows;
		} catch (SQLException e) {
			System.err.println("select error: " + e.getMessage());
			return rows;
		}
	}
	
	/**
	 * Finds a single record from the database with the given id.
	 *
	 * @param int id The record's primary key.
	 * @return model An instance of the item found.
	 */
	public Model findById(int id) throws InvalidDatabaseException {
		if (this._connection == null) {
			throw new InvalidDatabaseException();
		}
		
		try {
			String query = String.format("SELECT * FROM %s WHERE %s = ?",
				this.getTableName(),
				this.getPrimaryKey()
			);
			PreparedStatement statement = this._connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return this._newFromResultSet(result);
			}
		} catch (SQLException e) {
			System.err.println("select error: " + e.getMessage());
		}
		return null;
	}
	
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
