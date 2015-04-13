package com.newfurniturey.mvc;

import com.newfurniturey.mvc.app.App;

public class Program {

	public static void main(String[] args) {
		try {
			if (App.bootstrap()) {
				(new App()).run();
			}
		} catch (ClassNotFoundException e) {
			System.err.println("app error: " + e.getMessage());
		}
	}
	
}
