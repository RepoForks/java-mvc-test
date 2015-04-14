package com.newfurniturey.mvc.app.controllers;

import com.newfurniturey.mvc.app.App;
import com.newfurniturey.mvc.app.Controller;
import com.newfurniturey.mvc.app.InvalidDatabaseException;
import com.newfurniturey.mvc.app.Model;
import com.newfurniturey.mvc.app.models.Show;
import com.newfurniturey.mvc.app.views.AddShowForm;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
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
		// @todo implement
    }
    
	private void _render() {
        if (this._addShowView != null) return;
        
		this._addShowView = new AddShowForm();
		this._addShowView.addSaveListener(this);
		this._addShowView.render().setVisible(true);
	}
}
