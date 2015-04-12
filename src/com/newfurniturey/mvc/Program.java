package com.newfurniturey.mvc;

import com.newfurniturey.mvc.app.App;

public class Program {

	public static void main(String[] args) {
		try {
			if (App.bootstrap()) {
				// @todo do stuff!
			}
		} catch (ClassNotFoundException e) {
			System.err.println("app error: " + e.getMessage());
		}
	}
	
}
