package com.application.healthnow.database;

import java.util.ArrayList;

import com.application.healthnow.GlobalVariables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

	public void InsertDoctor(String name, String phone, String email,
			String office, int userId) {

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
			Toast.makeText(instance.context, "Success", Toast.LENGTH_LONG)
					.show();
		}
	}
	
	public void DeleteDoctor(String doctorName)
	{
			String where = " DOCTOR_NAME=?";
			int numberOFEntriesDeleted = db.delete(DBAdapter.TABLE_DOCTORS, where,
					new String[] { doctorName });
			
			Toast.makeText(instance.context, doctorName + " Removed", Toast.LENGTH_LONG).show();
	}

	public ArrayList<String> GetAllDoctorsNames() {
		String where = null;

		ArrayList<String> doctors = new ArrayList<String>();

		Cursor c = db.query(true, DBAdapter.TABLE_DOCTORS,
				DBAdapter.DOCTORS_ALLCOLUMNS, where, null, null, null, null,
				null);
		if (c != null) {
			c.moveToFirst();
		}

		while (c.isAfterLast() == false) {
			doctors.add(c.getString(c.getColumnIndex(DBAdapter.DOCTORS_COLUMN_DOCTORS_NAME)));
			
			c.moveToNext();
		}

		return doctors;
	}
	
	public ArrayList<String> GetAllDoctorsInfo(String doctorName) {
		String where = " DOCTOR_NAME=?";

		ArrayList<String> doctorsInfo = new ArrayList<String>();
		
		Cursor c = db.query(DBAdapter.TABLE_DOCTORS, null, where,
				new String[] { doctorName }, null, null, null);
		
		if (c != null) {
			c.moveToFirst();
		}

		while (c.isAfterLast() == false) {
			doctorsInfo.add(c.getString(c.getColumnIndex(DBAdapter.DOCTORS_COLUMN_DOCTORS_EMAIL)));
			doctorsInfo.add(c.getString(c.getColumnIndex(DBAdapter.DOCTORS_COLUMN_DOCTORS_PHONE)));
			doctorsInfo.add(c.getString(c.getColumnIndex(DBAdapter.DOCTORS_COLUMN_DOCTORS_OFFICE)));
			
			c.moveToNext();
		}

		return doctorsInfo;

	}

}
