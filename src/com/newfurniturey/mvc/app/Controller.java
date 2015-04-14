package com.newfurniturey.mvc.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {
	
	/**
	 * Invoked when an action is performed
	 *
	 * @param ActionEvent e Event details.
	 */
	abstract public void actionPerformed(ActionEvent e);
	
}
