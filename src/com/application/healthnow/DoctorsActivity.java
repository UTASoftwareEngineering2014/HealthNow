package com.application.healthnow;

import com.application.healthnow.database.DoctorDataBaseAdapter;
import com.application.healthnow.database.LoginDataBaseAdapter;
import com.application.healthnow.diet.PreferencesActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorsActivity extends Activity{
	
	Context mContext = this;
	AlertDialog.Builder builder;
	AlertDialog alertDialog;
	EditText editName, editPhone, editEmail, editOffice;
	LoginDataBaseAdapter LG_DB;
	DoctorDataBaseAdapter DC_DB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctors);
		this.invalidateOptionsMenu();		
		
		LG_DB = new LoginDataBaseAdapter(this);
		DC_DB = new DoctorDataBaseAdapter(this);
	}
	
	private void AddDoctor()
	{
		
		
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		final View layout = inflater.inflate(
				R.layout.activity_add_doctor_popup, null);
		builder = new AlertDialog.Builder(mContext);
		builder.setView(layout).setTitle("Add Doctor");
		builder.setPositiveButton("Save",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int id) {
						editName = (EditText) layout.findViewById(R.id.add_doctor_text_name);
						editPhone = (EditText) layout.findViewById(R.id.add_doctor_text_phone);
						editEmail = (EditText) layout.findViewById(R.id.add_doctor_text_email);
						editOffice = (EditText) layout.findViewById(R.id.add_doctor_text_office);
						
						int userId = LG_DB.GetUserId(); 
						String name = editName.getText().toString();
						String phone = editPhone.getText().toString();
						String email = editEmail.getText().toString();
						String office = editOffice.getText().toString();
						
						DC_DB.InsertDoctor(name, phone, email, office, userId);
					}
				});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		alertDialog = builder.create();

		alertDialog.show();
		
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.doctors, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		int selected = item.getItemId();
		final int settings = R.id.action_settings;
		final int addDoctor = R.id.action_add_doctors;

		if(selected == settings)
		{
			Intent intentStart = new Intent(getApplicationContext(),SettingsActivity.class);
			startActivity(intentStart);
			return true;
		}
		if(selected == addDoctor)
		{
			AddDoctor();
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
