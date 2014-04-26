package com.application.healthnow;

import java.util.List;

import com.application.healthnow.database.LoginDataBaseAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SettingsActivity extends Activity{

	public ArrayAdapter<String> adapterList;
	ListView listView;
	Button changePin, changeEmail, saveInfo;
	AlertDialog.Builder builder;
	AlertDialog alertDialog;
	EditText editEmail, editVerifyEmail, editPin, editVerifyPin, editFirst, editLast, editWeight, editHeight; 
	LoginDataBaseAdapter DB;
	Context mContext = this;
		
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		saveInfo = (Button)findViewById(R.id.settings_button_save);
		changePin = (Button) findViewById(R.id.settings_button_pin);
		changeEmail = (Button) findViewById(R.id.settings_button_email);
		editFirst = (EditText)findViewById(R.id.settings_text_firstname);
		editLast = (EditText)findViewById(R.id.settings_text_lastname);
		editWeight = (EditText)findViewById(R.id.settings_text_weight);
		editHeight = (EditText)findViewById(R.id.settings_text_height);		
		
		DB = new LoginDataBaseAdapter(mContext);
		List<String> info = DB.GetAllMain();
		
		adapterList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, info);
		listView = (ListView) findViewById(R.id.settings_listview);
		listView.setAdapter(adapterList);
		

		changePin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
				final View layout = inflater.inflate(R.layout.activity_settings_pin, null);
				builder = new AlertDialog.Builder(mContext);
				builder.setView(layout).setTitle("Change Pin");
				builder.setPositiveButton("Save",
						new DialogInterface.OnClickListener() {
						
							@Override
							public void onClick(DialogInterface dialog, int id) {
								editPin = (EditText)layout.findViewById(R.id.settings_text_pin);
								editVerifyPin = (EditText)layout.findViewById(R.id.settings_text_pinverify);
								String pin = editPin.getText().toString();
								String verifyPin = editVerifyPin.getText().toString();
								
								if(!pin.equals(verifyPin))
								{
									Toast.makeText(getApplicationContext(), "Emails dont match", Toast.LENGTH_LONG).show();
									return;									
								}
								else
								{
									DB.updatePin(pin);
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
		});
		
		
		changeEmail.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
				final View layout = inflater.inflate(R.layout.activity_settings_email, null);
				builder = new AlertDialog.Builder(mContext);
				builder.setView(layout).setTitle("Change Email");
				builder.setPositiveButton("Save",
						new DialogInterface.OnClickListener() {
						
							@Override
							public void onClick(DialogInterface dialog, int id) {
								editEmail = (EditText)layout.findViewById(R.id.settings_text_email);
								editVerifyEmail = (EditText)layout.findViewById(R.id.settings_text_emailverify);
								String email = editEmail.getText().toString();
								String verifyEmail = editVerifyEmail.getText().toString();
								
								if(!email.equals(verifyEmail))
								{
									Toast.makeText(getApplicationContext(), "Emails dont match", Toast.LENGTH_LONG).show();
									return;
								}
								else
								{
									DB.updateEmail(email);
									UpdateView();									;
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
		});
		
		
		saveInfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String first = editFirst.getText().toString();
				String last = editLast.getText().toString();
				String weight = editWeight.getText().toString();
				String height = editHeight.getText().toString();
				DB.updateSettingsInfo(first, last, weight, height);
				UpdateView();
			}
		});
		
	}
	
	public void UpdateView()
	{
		DB = new LoginDataBaseAdapter(mContext);
		List<String> info = DB.GetAllMain();
		
		adapterList = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, info);
		listView = (ListView) findViewById(R.id.settings_listview);
		listView.setAdapter(adapterList);
	}
	
}
