package com.newfurniturey.mvc.app;

import java.sql.Connection;

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
	public void bootstrap();
	
}
