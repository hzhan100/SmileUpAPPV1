package com.smileup;

import java.util.HashMap;

import android.app.Application;

public class MyApplication extends Application {

	private static HashMap<String, Object> userInfo = new HashMap<String, Object>();

	public static HashMap<String, Object> getUserInfo() {
		return userInfo;
	}

	
	
}
