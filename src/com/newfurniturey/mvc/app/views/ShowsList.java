package com.newfurniturey.mvc.app.views;

import com.newfurniturey.mvc.app.View;
import java.util.Observable;
import javax.swing.JFrame;

public class ShowsList extends View {
	
	public ShowsList() {
		super();
		this._defaultWidth = 800;
		this._defaultHeight = 600;
		_title = "Shows";
	}
	
	@Override
	public JFrame render() {
		return super.render();
	}
	
	@Override
	public void update(Observable observable, Object object) {
		// @todo implement
	}
	
}
