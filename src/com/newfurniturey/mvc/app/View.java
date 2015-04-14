package com.newfurniturey.mvc.app;

import java.util.Observable;
import java.util.Observer;

public abstract class View implements Observer {
	
    public void update(Observable observable, Object object) {
		// empty override
	}
	
}
