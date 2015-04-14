package com.newfurniturey.mvc.app.views;

import com.newfurniturey.mvc.app.View;
import com.newfurniturey.mvc.app.models.table.ShowTable;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class ShowsList extends View {
	
	protected ShowTable _dataTable;
	
	public ShowsList() {
		super();
		this._defaultWidth = 800;
		this._defaultHeight = 600;
		_title = "Shows";
	}
	
	@Override
	public JFrame render() {
		JFrame frame = super.render();
		
		// create the panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		frame.getContentPane().add(panel);
		
		JPanel searchPanel = this._createSearchPanel();
		panel.add(searchPanel, BorderLayout.NORTH);
		
		// add our data pane
		JScrollPane dataPane = this._createDataTable();
		panel.add(dataPane, BorderLayout.CENTER);
		
		return frame;
	}
	
	@Override
	public void update(Observable observable, Object object) {
		// @todo implement
	}
	
	public void setShowList(ShowTable data) {
		this._dataTable = data;
	}
	
	protected JScrollPane _createDataTable() {
		JTable table = new JTable(_dataTable);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.getColumnModel().getColumn(0).setCellRenderer(new VariableRowHeightRenderer());
		return new JScrollPane(table);
	}
	
	protected JPanel _createSearchPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		// add the search box / button
        JTextField searchField = new JTextField();
        panel.add(searchField, BorderLayout.CENTER);

        JButton searchButton = new JButton("Search");
        panel.add(searchButton, BorderLayout.EAST);
		
		return panel;
	}
	
	class VariableRowHeightRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			table.setRowHeight(row, ((ImageIcon)value).getIconHeight());
			setIcon((ImageIcon)value);
			return this;
		}
	}
}
