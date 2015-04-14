package com.newfurniturey.mvc.app.views;

import com.newfurniturey.mvc.app.View;
import java.awt.BorderLayout;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShowsList extends View {
	
	public ShowsList() {
		super();
		this._defaultWidth = 800;
		this._defaultHeight = 600;
		_title = "Shows";
	}
	
	@Override
	public JFrame render() {
		JFrame frame = super.render();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		frame.getContentPane().add(panel);
		
		JScrollPane dataPane = this._createDataTable();
		panel.add(dataPane, BorderLayout.CENTER);
		
		return frame;
	}
	
	@Override
	public void update(Observable observable, Object object) {
		// @todo implement
	}
	
	protected JScrollPane _createDataTable() {
		String columns[] = {"", "Name", "Rating", "Description"};
		String data[][] = {
			{ "", "Test", "5.0", "This is a test entry" },
			{ "", "Second Test", "3.6", "This is a test entry" }
		};
		
		JTable table = new JTable(data, columns);
		return new JScrollPane(table);
	}
}
