package com.application.healthnow.medication;

import java.util.Calendar;

import com.application.healthnow.R;
import com.application.healthnow.database.LoginDataBaseAdapter;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MedicationActivity extends Activity{
	
	Button addMedication, removeMedication, saveAlarm;
	ToggleButton sunToggle,monToggle,tueToggle,wedToggle,thuToggle,friToggle,satToggle;
	TimePicker timePicker;
	LoginDataBaseAdapter DB;
	AlarmManager alarmMgr;
	PendingIntent alarmIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_medication);
		
		alarmMgr = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
		Intent intent = new Intent(this, AlarmSystemActivity.class);
		alarmIntent = PendingIntent.getActivity(this, 12345, intent, 0);

		// Set the alarm to start at 8:30 a.m.
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 34);

		alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() , alarmIntent);

		
		addMedication = (Button) findViewById(R.id.medication_button_addmedication);
		removeMedication = (Button) findViewById(R.id.medication_button_removemedication);
		
		addMedication.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {	
				setContentView(R.layout.fragment_medication_add);
				timePicker = (TimePicker) findViewById(R.id.timePicker1);				
				saveAlarm = (Button) findViewById(R.id.medication_button_alarm_save);
				sunToggle = (ToggleButton) findViewById(R.id.toggle_sunday);
				monToggle = (ToggleButton) findViewById(R.id.toggle_monday);
				tueToggle = (ToggleButton) findViewById(R.id.toggle_tuesday);
				wedToggle = (ToggleButton) findViewById(R.id.toggle_wednesday);
				thuToggle = (ToggleButton) findViewById(R.id.toggle_thursday);
				friToggle = (ToggleButton) findViewById(R.id.toggle_friday);
				satToggle = (ToggleButton) findViewById(R.id.toggle_saturday);
				
				saveAlarm.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {	
						Time time = new Time();
						
						timePicker.clearFocus();
						time.hour = timePicker.getCurrentHour();
						time.minute = timePicker.getCurrentMinute();
						Integer hour = time.hour;
						Integer minute = time.minute;
						
						sunToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						        if (isChecked) {
						        	Toast.makeText(getApplicationContext(), "Sunday", Toast.LENGTH_LONG).show();
						        } else {
						            // The toggle is disabled
						        }
						    }
						});
						monToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						        if (isChecked) {
						            // The toggle is enabled
						        } else {
						            // The toggle is disabled
						        }
						    }
						});
						tueToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						        if (isChecked) {
						            // The toggle is enabled
						        } else {
						            // The toggle is disabled
						        }
						    }
						});
						wedToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						        if (isChecked) {
						            // The toggle is enabled
						        } else {
						            // The toggle is disabled
						        }
						    }
						});
						thuToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						        if (isChecked) {
						            // The toggle is enabled
						        } else {
						            // The toggle is disabled
						        }
						    }
						});
						friToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						        if (isChecked) {
						            // The toggle is enabled
						        } else {
						            // The toggle is disabled
						        }
						    }
						});
						satToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						        if (isChecked) {
						        	Toast.makeText(getApplicationContext(), "Saturday", Toast.LENGTH_LONG).show();
						        } else {
						            // The toggle is disabled
						        }
						    }
						});
						String ntime = hour + " " + minute; 
						
						Toast.makeText(getApplicationContext(), ntime, Toast.LENGTH_LONG).show();
					}					
				});											
			}
		});
		
		removeMedication.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {	
				setContentView(R.layout.fragment_medication);
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Close The Database
		if(DB.db != null)
            DB.db.close();;
	}
	
}
