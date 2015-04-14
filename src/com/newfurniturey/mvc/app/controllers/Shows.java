package com.newfurniturey.mvc.app.controllers;

import com.newfurniturey.mvc.app.App;
import com.newfurniturey.mvc.app.Controller;
import com.newfurniturey.mvc.app.InvalidDatabaseException;
import com.newfurniturey.mvc.app.Model;
import com.newfurniturey.mvc.app.models.Show;
import com.newfurniturey.mvc.app.models.table.ShowTable;
import com.newfurniturey.mvc.app.View;
import com.newfurniturey.mvc.app.views.ShowsList;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Shows extends Controller {
	
	public Shows() {
		Connection connection = null;
		try {
			connection = App.getConnection();
			
			System.out.println("=== findAll ===");
			final List<Model> shows = (new Show(connection)).findAll();
			for (Model item : shows) {
				Show show = (Show)item;
				System.out.println("[show] " + show.getName());
			}
			
			System.out.println("\n\n=== findById ===");
			Model show = (new Show(connection)).findById(3);
			System.out.println("[show] " + ((Show)show).getName());
			
			// -------------
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					ShowsList view = new ShowsList();
					ShowTable showTable = new ShowTable(shows);
					view.setShowList(showTable);
					view.render().setVisible(true);
				}
			});
			
		} catch (InvalidDatabaseException e) {
			System.out.println("invalid database =[");
		} catch (SQLException e) {
			System.out.println("sql error: " + e.getMessage());
		} finally {
			App.close();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// @todo implement listener
	}
	
}
