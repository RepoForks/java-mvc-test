package com.newfurniturey.mvc.app.controllers;

import com.newfurniturey.mvc.app.App;
import com.newfurniturey.mvc.app.Controller;
import com.newfurniturey.mvc.app.InvalidDatabaseException;
import com.newfurniturey.mvc.app.Model;
import com.newfurniturey.mvc.app.models.Show;
import com.newfurniturey.mvc.app.models.Show.ShowBuilder;
import com.newfurniturey.mvc.app.views.AddShowForm;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.lang.NumberFormatException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddShow extends Controller {
    
    private AddShowForm _addShowView = null;
	
	public AddShow() {
        this._render();
	}
    
    public void display() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                _render();
            }
        });
    }
	
    public void actionPerformed(ActionEvent e) {
        String name = this._addShowView.getName();
        String imageUrl = this._addShowView.getImageUrl();
        String description = this._addShowView.getDescription();
        double rating = 0.0;
        try {
            rating = Double.parseDouble(this._addShowView.getRating());
        } catch (NumberFormatException ne) {
            // suppress; though, we should display an error or something!
        }
        
        System.out.printf("Adding:\n\tName: %s\n\tRating: %f\n\tImage: %s\n\tDesc: %s\n", name, rating, imageUrl, description);
        
        Connection conn = null;
        try {
            conn = App.getConnection();
        } catch (SQLException se) {
            System.err.println("sql error: " + se.getMessage());
            return;
        }
		Model show = (new Show(conn)).new ShowBuilder(conn)
            .name(name)
            .imageUrl(imageUrl)
            .rating(rating)
            .description(description)
            .build();
        
        if (show.save()) {
            App.refreshList();
        }
        
        this._addShowView.close();
    }
    
	private void _render() {
        if (this._addShowView != null) return;
        
		this._addShowView = new AddShowForm();
		this._addShowView.addSaveListener(this);
		this._addShowView.render().setVisible(true);
	}
}
