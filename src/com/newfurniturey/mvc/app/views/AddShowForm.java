package com.newfurniturey.mvc.app.views;

import com.newfurniturey.mvc.app.View;
import com.newfurniturey.mvc.app.models.table.ShowTable;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class AddShowForm extends View {
	
	protected ShowTable _dataTable;
	protected JButton _saveButton;
	
	public AddShowForm() {
		super();
		_title = "Add Show";
		
		// create the buttons now so we can attach listeners to it
		this._saveButton = new JButton("Save");
	}
	
	@Override
	public JFrame render() {
		JFrame frame = super.render();
        frame.removeWindowListener(this._closeListener);
        this._closeListener = new SaveListener();
		frame.addWindowListener(_closeListener);
		
		// create the panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		frame.getContentPane().add(panel);
        
        JPanel fields = this._createFieldsPanel();
        panel.add(fields, BorderLayout.NORTH);
		
		JPanel savePanel = this._createSavePanel();
		panel.add(savePanel, BorderLayout.SOUTH);
		
		return frame;
	}
	
	@Override
	public void update(Observable observable, Object object) {
		// @todo implement
	}
	
	public void addSaveListener(ActionListener listener) {
		_saveButton.addActionListener(listener);
	}
	
	protected JPanel _createFieldsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        
        // Name field
        JLabel nameLabel = new JLabel("Name:");
            constraints.gridwidth = GridBagConstraints.RELATIVE;
            constraints.fill = GridBagConstraints.NONE;
            constraints.weightx = 0.0;
            panel.add(nameLabel, constraints);
        
        JTextField nameField = new JTextField();
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1.0;
            panel.add(nameField, constraints);
		
		return panel;
	}
	
	protected JPanel _createSavePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
        panel.add(this._saveButton, BorderLayout.EAST);
		return panel;
	}
	
	protected static class SaveListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
		}
	}
}
