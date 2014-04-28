package com.application.healthnow.database;

import java.sql.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class VSDataBaseAdapter {
	public SQLiteDatabase db;
	DBAdapter instance;

	public VSDataBaseAdapter(Context _context) {
		instance = new DBAdapter(_context);
		instance.open();
		db = instance.getDatabaseInstance();
	}
	
	public void InsertVitalSigns(int bloodPressure, int heartRate, int glucose, int cholesterol) {
		
		 //TODO 
		//create variable (today) from datetime and insert today into VITALSIGNS_COLUMN_DATE
		
		ContentValues newValues = new ContentValues();
		// Assign values for each row.
		newValues.put(DBAdapter.VITALSIGNS_COLUMN_BLOOD_PRESSURE, bloodPressure);
		newValues.put(DBAdapter.VITALSIGNS_COLUMN_HEART_RATE, heartRate);
		newValues.put(DBAdapter.VITALSIGNS_COLUMN_GLUCOSE, glucose);
		newValues.put(DBAdapter.VITALSIGNS_COLUMN_CHOLESTEROL, cholesterol);
		//newValues.put(DBAdapter.VITALSIGNS_COLUMN_DATE, today);

		// Insert the row into your table
		// test
		long status = db.insert(DBAdapter.TABLE_VITALSIGNS, null, newValues);

		if (status == -1) {
			Toast.makeText(instance.context, "Not Inserted vitalsigns",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(instance.context, "Success", Toast.LENGTH_LONG)
					.show();
		}
	}
	
	public String[] GetAllVitalSigns() {
		String where = null;
		Cursor cursor = db
				.query(true, DBAdapter.TABLE_LOGIN, DBAdapter.LOGIN_ALLCOLUMNS,
						where, null, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}

		//TODO write code to move through cursor and retrieve these values
		

		String bloodPressure = cursor.getString(cursor.getColumnIndex(DBAdapter.VITALSIGNS_COLUMN_BLOOD_PRESSURE));
		String hearRate = cursor.getString(cursor.getColumnIndex(DBAdapter.VITALSIGNS_COLUMN_HEART_RATE));
		String glucose = cursor.getString(cursor.getColumnIndex(DBAdapter.VITALSIGNS_COLUMN_GLUCOSE));
		String cholesterol = cursor.getString(cursor.getColumnIndex(DBAdapter.VITALSIGNS_COLUMN_CHOLESTEROL));
		cursor.close();
		
		String[] vitals = {bloodPressure, hearRate, glucose, cholesterol};
		
		//

		return vitals;
	}
	
	
	
	
	
	
	


}
