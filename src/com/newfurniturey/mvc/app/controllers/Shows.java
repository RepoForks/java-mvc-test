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
	
	private List<Model> _shows;
	
	public Shows() {
		Connection connection = null;
		try {
			connection = App.getConnection();
			_shows = (new Show(connection)).findAll();
			
			// debug output
			System.out.println("=== findAll ===");
			for (Model item : _shows) {
				Show show = (Show)item;
				System.out.println("[show] " + show.getName());
			}
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					_render();
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
		System.out.println("[shows] Action performed!");
	}
	
	private void _render() {
		ShowsList view = new ShowsList();
		view.addSearchActionListener(this);
		ShowTable showTable = new ShowTable(this._shows);
		view.setShowList(showTable);
		view.render().setVisible(true);
	}
}
