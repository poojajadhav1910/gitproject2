package com.example.phpdb;

import android.content.Context;
import android.content.SharedPreferences;

public class Myshare {
	private static SharedPreferences pref = null;
	private static Context context;

	private Myshare() {
	}

	public static void initializeContext(Context applicationContext) {
		context = applicationContext;
	}

	public static SharedPreferences getInstance() {
		if (null == pref) {
			pref = context.getSharedPreferences("aa", 1);
			if(!pref.contains("count")){
			SharedPreferences.Editor editor = pref.edit();
			editor.putInt("count",0 );
			editor.commit();
			}

		}
		return pref;
	}
}
