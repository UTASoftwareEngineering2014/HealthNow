package com.application.healthnow.diet;

import com.application.healthnow.DoctorsActivity;
import com.application.healthnow.R;
import com.application.healthnow.SettingsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.Menu;
import android.view.MenuItem;

public class PreferencesActivity extends PreferenceActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction().replace(android.R.id.content, new DietPreferenceFragment()).commit();
		
		this.invalidateOptionsMenu();
	}
	
	public static class DietPreferenceFragment extends PreferenceFragment
	{
		@Override
		public void onCreate(final Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.pref_daily_calorie_goal);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.preference, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		int selected = item.getItemId();
		final int settings = R.id.action_settings;
		if(selected == settings)
		{
			Intent intentStart = new Intent(getApplicationContext(),SettingsActivity.class);
			startActivity(intentStart);
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	

}