package com.newfurniturey.mvc.app;

import com.newfurniturey.mvc.app.models.Show;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class Controller implements ActionListener {
	
	public Controller() {
		Connection connection = null;
		try {
			connection = App.getConnection();
			
			System.out.println("=== findAll ===");
			List<Model> shows = (new Show(connection)).findAll();
			for (Model item : shows) {
				Show show = (Show)item;
				System.out.println("[show] " + show.getName());
			}
			
			System.out.println("\n\n=== findById ===");
			Model show = (new Show(connection)).findById(3);
			System.out.println("[show] " + ((Show)show).getName());
			
		} catch (InvalidDatabaseException e) {
			System.out.println("invalid database =[");
		} catch (SQLException e) {
			System.out.println("sql error: " + e.getMessage());
		} finally {
			App.close();
		}
	}
	
	
	/**
	 * Invoked when an action is performed
	 *
	 * @param ActionEvent e Event details.
	 */
	abstract public void actionPerformed(ActionEvent e);
	
}
