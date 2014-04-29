package com.application.healthnow.database;

import java.util.ArrayList;
import com.application.healthnow.GlobalVariables;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class LoginDataBaseAdapter {
	public SQLiteDatabase db;
	DBAdapter instance;

	public LoginDataBaseAdapter(Context _context) {
		instance = new DBAdapter(_context);
		instance.open();
		db = instance.getDatabaseInstance();
	}

	// Start of Personal Methods
	public void insertEntry(String userName, String password, String email) {
		ContentValues newValues = new ContentValues();
		// Assign values for each row.
		newValues.put(DBAdapter.LOGIN_COLUMN_USERNAME, userName);
		newValues.put(DBAdapter.LOGIN_COLUMN_PASSWORD, password);
		newValues.put(DBAdapter.LOGIN_COLUMN_EMAIL, email);

		// Insert the row into your table
		// test
		long status = db.insert(DBAdapter.TABLE_LOGIN, null, newValues);

		if (status == -1) {
			Toast.makeText(instance.context, "Not Inserted FirstLogin",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(instance.context, "Success", Toast.LENGTH_LONG)
					.show();
		}
	}

	public void updateEmail(String email) {

		final String userName = GlobalVariables.userName;
		Cursor cursor = db.query(DBAdapter.TABLE_LOGIN, null, " USERNAME=?",
				new String[] { userName }, null, null, null);
		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
		}
		cursor.moveToFirst();

		ContentValues updateValues = new ContentValues();
		updateValues.put(DBAdapter.LOGIN_COLUMN_EMAIL, email);

		// Update Information
		String where = " USERNAME=?";
		long status = db.update(DBAdapter.TABLE_LOGIN, updateValues, where,
				new String[] { userName });

		if (status == -1) {
			Toast.makeText(instance.context, "Email not updated",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(instance.context, "Email Saved Successfully",
					Toast.LENGTH_LONG).show();
		}
		cursor.close();
	}

	public ArrayList<String> GetAllMain() {
		ArrayList<String> mylist = new ArrayList<String>();
		String userName = GlobalVariables.userName;
		String where = " USERNAME=?";
		Cursor c = db.query(DBAdapter.TABLE_LOGIN, null, where,
				new String[] { userName }, null, null, null);
		if (c.getCount() < 1) // UserName Not Exist
		{
			c.close();
		}
		c.moveToFirst();

		while (c.isAfterLast() == false) {
			String firstName = c.getString(c
					.getColumnIndex(DBAdapter.INFO_COLUMN_FIRSTNAME));
			String lastName = c.getString(c
					.getColumnIndex(DBAdapter.INFO_COLUMN_LASTNAME));
			String weight = c.getString(c
					.getColumnIndex(DBAdapter.INFO_COLUMN_WEIGHT));
			String height = c.getString(c
					.getColumnIndex(DBAdapter.INFO_COLUMN_HEIGHT));
			String email = c.getString(c
					.getColumnIndex(DBAdapter.LOGIN_COLUMN_EMAIL));

			if (firstName == null) {
				mylist.add("NAME: ");
			} else {
				if (!firstName.equals("")) {
					if (lastName == null) {
						lastName = "";
					}
					mylist.add("NAME: " + firstName + " " + lastName);
				}
			}
			if (weight == null) {
				mylist.add("WEIGHT: ");
			} else {
				if (!weight.equals("")) {
					mylist.add("WEIGHT: " + weight);
				}
			}
			if (height == null) {
				mylist.add("HEIGHT: ");
			} else {
				if (!height.equals("")) {
					mylist.add("HEIGHT: " + height);
				}
			}
			if (email == null) {
				mylist.add("EMAIL \n: ");
			} else {
				if (!email.equals("")) {
					mylist.add("EMAIL \n" + email);
				}
			}

			c.moveToNext();
		}
		return mylist;
	}

	public String GetWeight() {
		String userName = GlobalVariables.userName;
		String where = " USERNAME=?";
		Cursor c = db.query(DBAdapter.TABLE_LOGIN, null, where,
				new String[] { userName }, null, null, null);
		if (c.getCount() < 1) // UserName Not Exist
		{
			c.close();
		}
		c.moveToFirst();

		String weight = c.getString(c
				.getColumnIndex(DBAdapter.INFO_COLUMN_WEIGHT));

		return weight;
	}

	public String GetHeight() {
		String userName = GlobalVariables.userName;
		String where = " USERNAME=?";
		Cursor c = db.query(DBAdapter.TABLE_LOGIN, null, where,
				new String[] { userName }, null, null, null);
		if (c.getCount() < 1) // UserName Not Exist
		{
			c.close();
		}
		c.moveToFirst();

		String height = c.getString(c
				.getColumnIndex(DBAdapter.INFO_COLUMN_HEIGHT));

		return height;
	}

	public void updatePin(String pin) {
		final String userName = GlobalVariables.userName;
		Cursor cursor = db.query(DBAdapter.TABLE_LOGIN, null, " USERNAME=?",
				new String[] { userName }, null, null, null);
		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
		}
		cursor.moveToFirst();

		ContentValues updateValues = new ContentValues();
		updateValues.put(DBAdapter.INFO_COLUMN_PIN, pin);

		// Update Information
		String where = " USERNAME=?";
		long status = db.update(DBAdapter.TABLE_LOGIN, updateValues, where,
				new String[] { userName });

		if (status == -1) {
			Toast.makeText(instance.context, "Pin not updated",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(instance.context, "Pin Saved Successfully",
					Toast.LENGTH_LONG).show();
		}

		cursor.close();
	}

	public void updateSettingsInfo(String first, String last, String weight,
			String height) {
		ContentValues updateValues = new ContentValues();
		String userName = GlobalVariables.userName;
		String where = " USERNAME=?";
		boolean update = false;

		if (!first.equals("")) {
			updateValues.put(DBAdapter.INFO_COLUMN_FIRSTNAME, first);
			update = true;
		}
		if (!last.equals("")) {
			updateValues.put(DBAdapter.INFO_COLUMN_LASTNAME, last);
			update = true;
		}
		if (!weight.equals("")) {
			updateValues.put(DBAdapter.INFO_COLUMN_WEIGHT, weight);
			update = true;
		}
		if (!height.equals("")) {
			updateValues.put(DBAdapter.INFO_COLUMN_HEIGHT, height);
			update = true;
		}
		if (update) {
			long status = db.update(DBAdapter.TABLE_LOGIN, updateValues, where,
					new String[] { userName });

			if (status == -1) {
				Toast.makeText(instance.context, "Personal Info not inserted",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(instance.context,
						"Personal Info Saved Successfully", Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	public void InsertFirstLogin(String userName, String first, String last,
			String weight, String height) {

		Cursor cursor = db.query(DBAdapter.TABLE_LOGIN, null, " USERNAME=?",
				new String[] { userName }, null, null, null);
		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
			Toast.makeText(instance.context, "first login fail",
					Toast.LENGTH_LONG).show();
		}
		cursor.moveToFirst();

		ContentValues updateValues = new ContentValues();
		updateValues.put(DBAdapter.INFO_COLUMN_FIRSTNAME, first);
		updateValues.put(DBAdapter.INFO_COLUMN_LASTNAME, last);
		updateValues.put(DBAdapter.INFO_COLUMN_WEIGHT, weight);
		updateValues.put(DBAdapter.INFO_COLUMN_HEIGHT, height);
		updateValues.put(DBAdapter.INFO_COLUMN_FIRSTLOGIN, 0);

		// Update Information
		String where = " USERNAME=?";
		db.update(DBAdapter.TABLE_LOGIN, updateValues, where,
				new String[] { userName });

		cursor.close();

		long status = db.insert(DBAdapter.TABLE_LOGIN, null, updateValues);

		if (status == -1) {
			Toast.makeText(instance.context, "Personal Info not inserted",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(instance.context,
					"Personal Info Saved Successfully", Toast.LENGTH_LONG)
					.show();
		}
	}

	public void insertPin(String pin, String userName) {
		Cursor cursor = db.query(DBAdapter.TABLE_LOGIN, null, " USERNAME=?",
				new String[] { userName }, null, null, null);
		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
		}

		ContentValues newValue = new ContentValues();
		newValue.put(DBAdapter.INFO_COLUMN_PIN, pin);
		String where = "USERNAME= '" + userName + "'";
		db.update(DBAdapter.TABLE_LOGIN, newValue, where, null);
	}

	public boolean IsFirstLogin(String userName) {
		Boolean firstLogin;

		Cursor cursor = db.query(DBAdapter.TABLE_LOGIN, null, " USERNAME=?",
				new String[] { userName }, null, null, null);
		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
			Toast.makeText(instance.context, "first login fail",
					Toast.LENGTH_LONG).show();
		}
		cursor.moveToFirst();

		Integer check = cursor.getInt(cursor
				.getColumnIndex(DBAdapter.INFO_COLUMN_FIRSTLOGIN));
		cursor.close();

		if (check == 1) {
			firstLogin = true;
		} else {
			firstLogin = false;
		}

		return firstLogin;
	}

	public void DismissFirstLogin(String userName) {
		Cursor cursor = db.query(DBAdapter.TABLE_LOGIN, null, " USERNAME=?",
				new String[] { userName }, null, null, null);
		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
			Toast.makeText(instance.context, "first login fail",
					Toast.LENGTH_LONG).show();
		}
		cursor.moveToFirst();

		ContentValues updatedValues = new ContentValues();
		updatedValues.put(DBAdapter.INFO_COLUMN_FIRSTLOGIN, 0);

		String where = " USERNAME=?";
		db.update(DBAdapter.TABLE_LOGIN, updatedValues, where,
				new String[] { userName });

		cursor.close();
	}

	public int deleteEntry(String UserName) {
		String where = "USERNAME=?";
		int numberOFEntriesDeleted = db.delete("TABLE", where,
				new String[] { UserName });
		return numberOFEntriesDeleted;
	}

	public String GetSinlgeEntry(String userName) {
		Cursor cursor = db.query(DBAdapter.TABLE_LOGIN, null, " USERNAME=?",
				new String[] { userName }, null, null, null);
		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
			return "NOT EXIST";
		}
		cursor.moveToFirst();
		String password = cursor.getString(cursor
				.getColumnIndex(DBAdapter.LOGIN_COLUMN_PASSWORD));
		cursor.close();
		return password;
	}

	public void updateEntry(String userName, String password) {
		// Define the updated row content.
		ContentValues updatedValues = new ContentValues();
		// Assign values for each row.
		updatedValues.put("USERNAME", userName);
		updatedValues.put("COLUMN_PASSWORD", password);

		String where = "USERNAME = ?";
		db.update("TABLE", updatedValues, where, new String[] { userName });
	}

	public String GetPin(String userName) {
		Cursor cursor = db.query(DBAdapter.TABLE_LOGIN, null, " USERNAME=?",
				new String[] { userName }, null, null, null);
		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
		}
		cursor.moveToFirst();
		String pin = cursor.getString(cursor
				.getColumnIndex(DBAdapter.INFO_COLUMN_PIN));
		cursor.close();

		return pin;
	}

	public Cursor GetLoginIds() {
		String where = null;
		Cursor c = db
				.query(true, DBAdapter.TABLE_LOGIN, DBAdapter.LOGIN_ALLCOLUMNS,
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	public int GetUserId() {
		final String userName = GlobalVariables.userName;

		String where = " USERNAME=?";
		Cursor cursor = db.query(DBAdapter.TABLE_LOGIN, null, where,
				new String[] { userName }, null, null, null);

		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
		}
		cursor.moveToFirst();

		int uniqueId = cursor.getInt(cursor.getColumnIndex(DBAdapter.LOGIN_COLUMN_ID));

		return uniqueId;
	}
}
