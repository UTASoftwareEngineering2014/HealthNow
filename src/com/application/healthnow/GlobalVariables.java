package com.application.healthnow;

import android.app.Application;

public class GlobalVariables extends Application {

	public static String userName;
	private static GlobalVariables singleton;

	public static GlobalVariables getInstance() {
		return singleton;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		singleton = this;
	}

	public String GetUserName() {
		return userName;
	}

	public void SetUserName(String userName) {
		this.userName = userName;
	}

}
