package com.newfurniturey.mvc.app.views;

import com.newfurniturey.mvc.app.View;
import com.newfurniturey.mvc.app.models.table.ShowTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class AddShowForm extends View {
	
	protected ShowTable _dataTable;
	protected JButton _saveButton;
    protected JTextField _nameField;
    protected JTextField _ratingField;
    protected JTextField _imageUrlField;
    protected JTextArea _descriptionField;
    protected JFrame _mainWindow;
	
	public AddShowForm() {
		super();
		_title = "Add Show";
		
		// create the buttons now so we can attach listeners to it
		this._saveButton = new JButton("Save");
	}
	
	@Override
	public JFrame render() {
		this._mainWindow = super.render();
        this._mainWindow.removeWindowListener(this._closeListener);
        this._closeListener = new SaveListener();
		this._mainWindow.addWindowListener(_closeListener);
		
		// create the panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		this._mainWindow.getContentPane().add(panel);
        
        JPanel fields = this._createFieldsPanel();
        panel.add(fields, BorderLayout.NORTH);
		
		JPanel savePanel = this._createSavePanel();
		panel.add(savePanel, BorderLayout.SOUTH);
		
		return this._mainWindow;
	}
    
    public void close() {
        this._mainWindow.setVisible(false);
    }
	
	@Override
	public void update(Observable observable, Object object) {
		// @todo implement
	}
	
	public void addSaveListener(ActionListener listener) {
		_saveButton.addActionListener(listener);
	}
    
    public String getName() {
        return this._nameField.getText();
    }
    
    public String getRating() {
        return this._ratingField.getText();
    }
    
    public String getImageUrl() {
        return this._imageUrlField.getText();
    }
    
    public String getDescription() {
        return this._descriptionField.getText();
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
        
        this._nameField = new JTextField();
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1.0;
            panel.add(this._nameField, constraints);
        
        // Rating field
        JLabel ratingLabel = new JLabel("Rating:");
            constraints.gridwidth = GridBagConstraints.RELATIVE;
            constraints.fill = GridBagConstraints.NONE;
            constraints.weightx = 0.0;
            panel.add(ratingLabel, constraints);
        
        this._ratingField = new JTextField();
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1.0;
            panel.add(this._ratingField, constraints);
        
        // Image URL field
        JLabel imageLabel = new JLabel("Image URL:");
            constraints.gridwidth = GridBagConstraints.RELATIVE;
            constraints.fill = GridBagConstraints.NONE;
            constraints.weightx = 0.0;
            panel.add(imageLabel, constraints);
        
        this._imageUrlField = new JTextField();
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1.0;
            panel.add(this._imageUrlField, constraints);
        
        // Description field
        JLabel descriptionLabel = new JLabel("Description:");
            constraints.gridwidth = GridBagConstraints.RELATIVE;
            constraints.fill = GridBagConstraints.NONE;
            constraints.weightx = 0.0;
            panel.add(descriptionLabel, constraints);
        
        this._descriptionField = new JTextArea();
            this._descriptionField.setLineWrap(true);
            this._descriptionField.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(this._descriptionField);
            descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            descriptionScrollPane.setPreferredSize(new Dimension(250, 250));
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1.0;
            panel.add(descriptionScrollPane, constraints);
		
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
