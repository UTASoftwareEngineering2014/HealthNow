package com.application.healthnow;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.application.healthnow.database.DoctorDataBaseAdapter;
import com.application.healthnow.database.LoginDataBaseAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

public class DoctorsActivity extends Activity {

	Context mContext = this;
	AlertDialog.Builder builder;
	AlertDialog alertDialog;
	EditText editName, editPhone, editEmail, editOffice;
	LoginDataBaseAdapter LG_DB;
	static DoctorDataBaseAdapter DC_DB;
	List<String> groupList;
	List<String> doctors;
	List<String> doctorsInfo;
	List<String> childList;
	Map<String, List<String>> doctorCollection;
	ExpandableListView expListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctors);
		this.invalidateOptionsMenu();

		LG_DB = new LoginDataBaseAdapter(this);
		DC_DB = new DoctorDataBaseAdapter(this);

		createGroupList();

		expListView = (ExpandableListView) findViewById(R.id.expandableListView1);
		final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
				this, groupList, doctorCollection);
		expListView.setAdapter(expListAdapter);

		expListView.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				final String selected = (String) expListAdapter.getChild(
						groupPosition, childPosition);
				Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
						.show();

				return true;
			}
		});
	}

	private void AddDoctor() {
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
						editName = (EditText) layout
								.findViewById(R.id.add_doctor_text_name);
						editPhone = (EditText) layout
								.findViewById(R.id.add_doctor_text_phone);
						editEmail = (EditText) layout
								.findViewById(R.id.add_doctor_text_email);
						editOffice = (EditText) layout
								.findViewById(R.id.add_doctor_text_office);

						int userId = LG_DB.GetUserId();
						String name = editName.getText().toString();
						String phone = editPhone.getText().toString();
						String email = editEmail.getText().toString();
						String office = editOffice.getText().toString();

						DC_DB.InsertDoctor(name, phone, email, office, userId);

						refresh();	
						
						if (name.equals("")) {
							Toast.makeText(getApplicationContext(),
									"PLEASE FILL NAME FEILD",
									Toast.LENGTH_LONG).show();
							return;
						}
						if (phone.equals("")) {
							Toast.makeText(getApplicationContext(),
									"PLEASE FILL PHONE FEILD",
									Toast.LENGTH_LONG).show();
							return;
						}
						if (email.equals("")) {
							Toast.makeText(getApplicationContext(),
									"PLEASE FILL EMAIL FEILD",
									Toast.LENGTH_LONG).show();
							return;
						}
						if (office.equals("")) {
							Toast.makeText(getApplicationContext(),
									"PLEASE FILL OFFICE FEILD",
									Toast.LENGTH_LONG).show();
							return;
						}
						else
						{
							DC_DB.InsertDoctor(name, phone, email, office, userId);
							
							refresh();
						}
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

	private void createGroupList() {
		groupList = new ArrayList<String>();
		doctors = new ArrayList<String>();

		doctors = DC_DB.GetAllDoctorsNames();
		doctorCollection = new LinkedHashMap<String, List<String>>();

		for (int i = 0; i < doctors.size(); i++) {
			childList = new ArrayList<String>();
			groupList.add(doctors.get(i).toString());
			doctorsInfo = DC_DB.GetAllDoctorsInfo(doctors.get(i).toString());
			for (String info : doctorsInfo) {
				childList.add(info);
			}

			doctorCollection.put(doctors.get(i).toString(), childList);
		}
	}

	private void setGroupIndicatorToRight() {
		/* Get the screen width */
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;

		expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
				- getDipsFromPixel(5));
	}

	public void refresh() {
		createGroupList();

		expListView = (ExpandableListView) findViewById(R.id.expandableListView1);
		final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
				this, groupList, doctorCollection);
		expListView.setAdapter(expListAdapter);
	}

	// Convert pixel to dip
	public int getDipsFromPixel(float pixels) {
		// Get the screen's density scale
		final float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);
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

		if (selected == settings) {
			Intent intentStart = new Intent(getApplicationContext(),
					SettingsActivity.class);
			startActivity(intentStart);
			return true;
		}
		if (selected == addDoctor) {
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
