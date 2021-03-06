package com.application.healthnow.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
	static final String DATABASE_NAME = "HealthNow.db";
	static final int DATABASE_VERSION = 2;
	
	public static final String TABLE_LOGIN = "LOGIN";
	public static final String TABLE_MEDICATION = "MEDICATION";
	public static final String TABLE_VITALSIGNS = "VITALSIGNS";
	public static final String TABLE_DOCTORS = "DOCTORS";
	
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
    
    //VitalSigns table columns
    public static final String VITALSIGNS_COLUMN_ID = "ID";
    public static final String VITALSIGNS_COLUMN_BLOOD_PRESSURE = "BLOOD_PRESSURE";
    public static final String VITALSIGNS_COLUMN_HEART_RATE = "HEART_RATE";
    public static final String VITALSIGNS_COLUMN_GLUCOSE = "GLUCOSE";
    public static final String VITALSIGNS_COLUMN_CHOLESTEROL = "CHOLESTEROL";
    public static final String VITALSIGNS_COLUMN_DATE = "DATE";
    
    //Medication table columns
    public static final String MEDICATION_COLUMN_ID = "ID";
    public static final String MEDICATION_COLUMN_MEDICATION_NAME = "MEDICATION_NAME";
    public static final String MEDICATION_COLUMN_INTENT = "INTENT";
    
    //Doctors table columns
    public static final String DOCTORS_COLUMN_ID = "ID";
    public static final String DOCTORS_COLUMN_DOCTORS_NAME = "DOCTOR_NAME";
    public static final String DOCTORS_COLUMN_DOCTORS_PHONE = "DOCTOR_PHONE";
    public static final String DOCTORS_COLUMN_DOCTORS_EMAIL = "DOCTOR_EMAIL";
    public static final String DOCTORS_COLUMN_DOCTORS_OFFICE = "DOCTOR_OFFICE";
    public static final String DOCTORS_COLUMN_LOGINID = "LOGIN_ID";
    

    public static String[] LOGIN_ALLCOLUMNS= {LOGIN_COLUMN_ID,LOGIN_COLUMN_USERNAME,LOGIN_COLUMN_PASSWORD,LOGIN_COLUMN_EMAIL,
    											INFO_COLUMN_FIRSTNAME,INFO_COLUMN_LASTNAME,INFO_COLUMN_WEIGHT,INFO_COLUMN_HEIGHT,INFO_COLUMN_PIN};
    
    public static String[] VITALSIGNS_ALLCOLUMNS= {VITALSIGNS_COLUMN_ID,VITALSIGNS_COLUMN_BLOOD_PRESSURE,VITALSIGNS_COLUMN_HEART_RATE,VITALSIGNS_COLUMN_GLUCOSE,VITALSIGNS_COLUMN_CHOLESTEROL,VITALSIGNS_COLUMN_DATE};
    
    public static String[] MEDICATION_ALLCOLUMNS= {MEDICATION_COLUMN_ID,MEDICATION_COLUMN_MEDICATION_NAME, MEDICATION_COLUMN_INTENT};
    
    public static String[] DOCTORS_ALLCOLUMNS= {DOCTORS_COLUMN_ID,DOCTORS_COLUMN_DOCTORS_NAME, DOCTORS_COLUMN_DOCTORS_PHONE, DOCTORS_COLUMN_DOCTORS_EMAIL,DOCTORS_COLUMN_DOCTORS_OFFICE, DOCTORS_COLUMN_LOGINID};
	
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
    
    static final String CREATE_TABLE_VITALSIGNS = " create table " 
    		+ TABLE_VITALSIGNS + "("
    		+ VITALSIGNS_COLUMN_ID + " integer primary key autoincrement, " 
    		+ VITALSIGNS_COLUMN_BLOOD_PRESSURE + " numeric default 1, " 
			+ VITALSIGNS_COLUMN_HEART_RATE + " numeric default 1, "
			+ VITALSIGNS_COLUMN_GLUCOSE + " numeric default 1, " 
			+ VITALSIGNS_COLUMN_CHOLESTEROL + " numeric default 1, "
    		+ VITALSIGNS_COLUMN_DATE + " date );";
    
    static final String CREATE_TABLE_MEDICATION = " create table " 
    		+ TABLE_MEDICATION + "("
    		+ MEDICATION_COLUMN_ID + " integer primary key autoincrement, " 
    		+ MEDICATION_COLUMN_MEDICATION_NAME + " text, "
    		+ MEDICATION_COLUMN_INTENT + " integer );";
    
    static final String CREATE_TABLE_DOCTORS = " create table " 
    		+ TABLE_DOCTORS + "("
    		+ DOCTORS_COLUMN_ID + " integer primary key autoincrement, " 
    		+ DOCTORS_COLUMN_DOCTORS_NAME + " text, " 
			+ DOCTORS_COLUMN_DOCTORS_PHONE + " text, "
			+ DOCTORS_COLUMN_DOCTORS_EMAIL + " text, " 
			+ DOCTORS_COLUMN_DOCTORS_OFFICE + " text, "
    		+ DOCTORS_COLUMN_LOGINID + " integer);";
	
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
