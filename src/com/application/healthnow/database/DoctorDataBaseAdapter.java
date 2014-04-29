package com.application.healthnow.database;

import com.application.healthnow.GlobalVariables;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DoctorDataBaseAdapter {

	public SQLiteDatabase db;
	DBAdapter instance;
	LoginDataBaseAdapter lgDB;

	public DoctorDataBaseAdapter(Context _context) {
		instance = new DBAdapter(_context);
		instance.open();
		db = instance.getDatabaseInstance();
	}
	
	public void InsertDoctor(String name, String phone, String email, String office, int userId) 
	{		
		
		ContentValues newValues = new ContentValues();
		// Assign values for each row.
		newValues.put(DBAdapter.DOCTORS_COLUMN_DOCTORS_NAME, name);
		newValues.put(DBAdapter.DOCTORS_COLUMN_DOCTORS_PHONE, phone);
		newValues.put(DBAdapter.DOCTORS_COLUMN_DOCTORS_EMAIL, email);
		newValues.put(DBAdapter.DOCTORS_COLUMN_DOCTORS_OFFICE, office);
		newValues.put(DBAdapter.DOCTORS_COLUMN_LOGINID, userId);

		long status = db.insert(DBAdapter.TABLE_DOCTORS, null, newValues);

		if (status == -1) {
			Toast.makeText(instance.context, "Not Inserted doctors",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(instance.context, "Success", Toast.LENGTH_LONG).show();
		}
	}
	
}
