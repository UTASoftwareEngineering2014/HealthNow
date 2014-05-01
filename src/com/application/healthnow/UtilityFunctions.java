package com.application.healthnow;

import android.app.Activity;
import android.content.SharedPreferences;

public class UtilityFunctions extends Activity {
	
	private static final String PREFS_NAME = "MyPrefsFile";

	public int[] returnDinnerHistory()
	{
		SharedPreferences settings = this.getSharedPreferences(PREFS_NAME, 0);
		String dayhist=settings.getString("dinnerhistory"+GlobalVariables.userName, "");
		int days[]=null;
		
		if(!(dayhist.equals("")))
		{	
			String dayarray[]=dayhist.split(" ");
			int size=dayarray.length;
			days=new int[size];
			
			for(int i=0;i<size;i++)
			{
				days[i]=Integer.parseInt((dayarray[i].toString()));
			}
		}
		
		return days;

	}
}
