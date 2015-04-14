package com.newfurniturey.mvc.app;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

public abstract class View implements Observer {
	
	protected int _defaultWidth;
	protected int _defaultHeight;
	protected String _title;
	
	public View() {
		this._defaultWidth = 640;
		this._defaultHeight = 480;
		this._title = "View";
	}
	
	public JFrame render() {
		JFrame frame = new JFrame(this._title);
		this._configureFrame(frame);
		return frame;
	}
	
	public void update(Observable observable, Object object) {
		// empty override
	}
	
	private void _configureFrame(JFrame frame) {
		// setup the close listener
		frame.addWindowListener(new CloseListener());	
		
		// size it up
		frame.setSize(this._defaultWidth, this._defaultHeight);
		
		// center it on the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (screenSize.width - this._defaultWidth) / 2;
		int yPos = (screenSize.height - this._defaultHeight) / 2;
		frame.setLocation(xPos, yPos);
	}
	
	protected static class CloseListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
}
