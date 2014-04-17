package com.application.healthnow.monitoring;

import java.util.Calendar;

import com.application.healthnow.R;
import com.application.healthnow.R.layout;
import com.application.healthnow.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class AlarmActivity extends Activity {

	private DatePicker datePicker;
	private TimePicker timePicker;
	private Button btnSetAlarm;
	private TextView alarmInfo;
	private int hour;
	private int minute;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
		
//		timePicker = (TimePicker) findViewById(R.id.tp_medicine_alarm_setter);
//		btnSetAlarm = (Button) findViewById(R.id.btn_setAlarm);
		
		final Calendar calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		
		timePicker.setCurrentHour(hour);
		timePicker.setCurrentMinute(minute);
		btnSetAlarm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm, menu);
		return true;
	}

}
