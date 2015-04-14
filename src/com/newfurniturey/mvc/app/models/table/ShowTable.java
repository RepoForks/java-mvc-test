package com.newfurniturey.mvc.app.models.table;

import com.newfurniturey.mvc.app.Model;
import com.newfurniturey.mvc.app.models.Show;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ShowTable extends AbstractTableModel {
	private static final long serialVersionUID = 3094633615447716224L;
	
	private String[] columnNames = { "", "Name", "Rating", "Description" };
	private List<Model> data;

	public ShowTable(List<Model> shows) {
		data = shows;
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		if (row >= data.size()) return null;
		
		return this._getColumnData((Show)data.get(row), col);
	}

	public Class<?> getColumnClass(int column) {
		return getValueAt(0, column).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	protected Object _getColumnData(Show show, int column) {
		switch (column) {
			case 0: return show.getImage();
			case 1: return show.getName();
			case 2: return show.getRating();
			case 3: return show.getDescription();
			default:
				return null;
		}
	}
}