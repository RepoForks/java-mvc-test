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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Shows extends Controller {
	
	private List<Model> _shows;
	private ShowsList _showsListView;
	private ShowTable _showTable;
	
	public Shows() {
		Connection connection = null;
		try {
			connection = App.getConnection();
			_shows = (new Show(connection)).findAll();
			
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
		final String query = this._showsListView.getSearchQuery().toLowerCase();
		Predicate<Model> criteria = s ->
            ((Show)s).getName().toLowerCase().contains(query)
            || ((Show)s).getDescription().toLowerCase().contains(query);

		this._showTable.setShows(
            this._shows.stream().filter(criteria).collect(Collectors.toList())
        );
	}
	
	private void _render() {
		this._showsListView = new ShowsList();
		this._showsListView.addSearchActionListener(this);
		this._showTable = new ShowTable(this._shows);
		this._showsListView.setShowList(this._showTable);
		this._showsListView.render().setVisible(true);
	}
}
