package com.application.healthnow.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
	static final String DATABASE_NAME = "HealthNow.db";
	static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_LOGIN = "LOGIN";
	public static final String TABLE_MEDIACTION = "MEDICATION";
	
	//Login table columns
    public static final String LOGIN_COLUMN_ID = "ID";
    public static final String LOGIN_COLUMN_USERNAME = "USERNAME";
    public static final String LOGIN_COLUMN_PASSWORD = "PASSWORD";
    public static final String LOGIN_COLUMN_EMAIL = "EMAIL";
    public static final String INFO_COLUMN_FIRSTNAME = "FIRSTNAME";
    public static final String INFO_COLUMN_LASTNAME = "LASTNAME";
    public static final String INFO_COLUMN_WEIGHT = "WEIGHT";
    public static final String INFO_COLUMN_HEIGHT = "HEIGHT";
    public static final String INFO_COLUMN_PIN = "PIN";
    public static final String INFO_COLUMN_FIRSTLOGIN = "FIRST_LOGIN";

    public static String[] LOGIN_ALLCOLUMNS= {LOGIN_COLUMN_ID,LOGIN_COLUMN_USERNAME,LOGIN_COLUMN_PASSWORD,LOGIN_COLUMN_EMAIL,
    											INFO_COLUMN_FIRSTNAME,INFO_COLUMN_LASTNAME,INFO_COLUMN_WEIGHT,INFO_COLUMN_HEIGHT,INFO_COLUMN_PIN};
	
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String CREATE_TABLE_LOGIN = " create table " 
    		+ TABLE_LOGIN + "("
    		+ LOGIN_COLUMN_ID + " integer primary key autoincrement, " 
    		+ INFO_COLUMN_FIRSTNAME + " text, " 
			+ INFO_COLUMN_LASTNAME + " text, "
			+ INFO_COLUMN_WEIGHT + " text, " 
			+ INFO_COLUMN_HEIGHT + " text, "
			+ INFO_COLUMN_FIRSTLOGIN + " numeric default 1, "
			+ INFO_COLUMN_PIN + " text, " 
    		+ LOGIN_COLUMN_USERNAME + " text, "
    		+ LOGIN_COLUMN_EMAIL + " text, "
    		+ LOGIN_COLUMN_PASSWORD + " text);";
	
	// Variable to hold the database instance
	public SQLiteDatabase db;
	// Context of the application using the database.
	public final Context context;
	// Database open/upgrade helper
	private DataBaseHelper dbHelper;

	public DBAdapter(Context _context) {
		context = _context;
		dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
				DATABASE_VERSION);
	}

	public DBAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		db.close();
	}

	public SQLiteDatabase getDatabaseInstance() {
		
		return db;
	}
}
