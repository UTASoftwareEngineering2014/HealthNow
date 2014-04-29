package com.application.healthnow.database;

import java.util.ArrayList;

import com.application.healthnow.GlobalVariables;

import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MedicationDataBaseAdapter 
{
	public SQLiteDatabase db;
	DBAdapter instance;

	public MedicationDataBaseAdapter(Context _context) {
		instance = new DBAdapter(_context);
		instance.open();
		db = instance.getDatabaseInstance();
	}
	
	public void InsertMedication(String medicationName, int intent) 
	{
		
		ContentValues newValues = new ContentValues();
		// Assign values for each row.
		newValues.put(DBAdapter.MEDICATION_COLUMN_MEDICATION_NAME, medicationName);
		newValues.put(DBAdapter.MEDICATION_COLUMN_INTENT, intent
				);

		long status = db.insert(DBAdapter.TABLE_MEDICATION, null, newValues);

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
			int numberOFEntriesDeleted = db.delete(DBAdapter.TABLE_MEDICATION, where,
					new String[] { medicationName });	
	}
	
	public String[] GetAllMedication()
	{
		String where = null;
		String[] medicationNames = null;
		Cursor c = db.query(true, DBAdapter.TABLE_MEDICATION, DBAdapter.MEDICATION_ALLCOLUMNS,
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		int i = 0;
		
		 while(c.isAfterLast() == false)
		 {
			medicationNames[i] = c.getString(c.getColumnIndex(DBAdapter.MEDICATION_COLUMN_MEDICATION_NAME));
			 
			i++;
			c.moveToNext();
		 }
		 
		 return medicationNames;
	
	}
	
	public int GetIntentId(String medicationName)
	{
		int intentId;
		Cursor cursor = db.query(DBAdapter.TABLE_MEDICATION, null, " USERNAME=?",
				new String[] { medicationName }, null, null, null);
		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
		}
		cursor.moveToFirst();
		
		intentId = cursor.getInt(cursor.getColumnIndex(DBAdapter.MEDICATION_COLUMN_INTENT)); 
				
		return intentId;
	}
}
