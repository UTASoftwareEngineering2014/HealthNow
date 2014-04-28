package com.application.healthnow.database;

import com.application.healthnow.GlobalVariables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MedicationDataBaseAdapter {
	public SQLiteDatabase db;
	DBAdapter instance;

	public MedicationDataBaseAdapter(Context _context) {
		instance = new DBAdapter(_context);
		instance.open();
		db = instance.getDatabaseInstance();
	}
	
	public void InsertVitalSigns(int medicationName) {
		
		ContentValues newValues = new ContentValues();
		// Assign values for each row.
		newValues.put(DBAdapter.MEDICATION_COLUMN_MEDICATION_NAME, medicationName);

		long status = db.insert(DBAdapter.TABLE_MEDIACTION, null, newValues);

		if (status == -1) {
			Toast.makeText(instance.context, "Not Inserted medication",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(instance.context, "Success", Toast.LENGTH_LONG).show();
		}
	}
	
	public void DeleteMedication(String medicationName)
	{
			String where = "USERNAME=?";
			int numberOFEntriesDeleted = db.delete(DBAdapter.TABLE_MEDIACTION, where,
					new String[] { medicationName });	
	}
}