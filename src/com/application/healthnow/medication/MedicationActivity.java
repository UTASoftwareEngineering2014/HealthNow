package com.application.healthnow.medication;

import java.util.Calendar;

import com.application.healthnow.R;
import com.application.healthnow.database.LoginDataBaseAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MedicationActivity extends Activity
{
	
	PendingIntent pendingIntentsat,pendingIntentsun,pendingIntentmon,pendingIntenttue,pendingIntentwed,pendingIntentthu,pendingIntentfri;
	AlarmManager alarmManagersat,alarmManagersun,alarmManagermon,alarmManagertue,alarmManagerwed,alarmManagerthu,alarmManagerfri;
	//BroadcastReceiver mReceiversat;
	Button addMedication, removeMedication, saveAlarm;
	ToggleButton sunToggle,monToggle,tueToggle,wedToggle,thuToggle,friToggle,satToggle;
	TimePicker timePicker;
	LoginDataBaseAdapter DB;
	AlarmManager alarmMgr;
	PendingIntent alarmIntent;
	Calendar calendar;
	Integer hour;Integer minute;Context context;
	int idmon=2,idtue=3,idwed=4,idthu=5,idfri=6,idsat=7,idsun=1;
	PendingIntent pISender;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_medication);
		
		//alarmMgr = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
		//Intent intent = new Intent(this, AlarmReceiver.class);
		//alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

		// Set the alarm to start at 8:30 a.m.
		calendar = Calendar.getInstance();
		//calendar.set(Calendar.HOUR_OF_DAY, 0);
		//calendar.set(Calendar.MINUTE, 34);

		//alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() , alarmIntent);
		context=getApplicationContext();
		
		addMedication = (Button) findViewById(R.id.medication_button_addmedication);
		removeMedication = (Button) findViewById(R.id.medication_button_removemedication);
		
		addMedication.setOnClickListener(new View.OnClickListener() 
		{
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
					@SuppressLint("NewApi")
					public void onClick(View v) {	
						Time time = new Time();
						
						timePicker.clearFocus();
						time.hour = timePicker.getCurrentHour();
						time.minute = timePicker.getCurrentMinute();
						hour = time.hour;
						minute = time.minute;
					
						if(sunToggle.isChecked())
						{
							idsun=1;
							calendar.setTimeInMillis(System.currentTimeMillis());
							calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
							calendar.set(Calendar.HOUR_OF_DAY, hour);
							calendar.set(Calendar.MINUTE,minute);
							Intent i = new Intent("com.application.healthnow.medication.alarm");
							//pendingIntentsun = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
							pendingIntentsun=PendingIntent.getBroadcast(getApplicationContext(),idsun, i,PendingIntent.FLAG_UPDATE_CURRENT);
							alarmManagersun = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
							alarmManagersun.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),5000, pendingIntentsun);
							//idsun=pendingIntentsun.getCreatorUid();
							
						}
						else
						{
							try
							{
								Intent i = new Intent("com.application.healthnow.medication.alarm");
								//PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
								pISender = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
								AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
			                    am.cancel(pISender);
			                    pISender.cancel();
								//pISender.cancel();
								//displayIntent.cancel();
							}catch(Exception e){
								
							}
						}
						if(monToggle.isChecked())
						{
							idmon=2;
							calendar.setTimeInMillis(System.currentTimeMillis());
							calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
							calendar.set(Calendar.HOUR_OF_DAY, hour);
							calendar.set(Calendar.MINUTE,minute);
							Intent i = new Intent("com.application.healthnow.medication.alarm");
							//pendingIntentmon = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
							pendingIntentmon=PendingIntent.getBroadcast(getApplicationContext(),idmon, i,PendingIntent.FLAG_UPDATE_CURRENT);
							alarmManagermon = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
							alarmManagermon.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),10000, pendingIntentmon);
							
							
						}
						else
						{
							try
							{
								Intent i = new Intent("com.application.healthnow.medication.alarm");
								//PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
								pISender = PendingIntent.getBroadcast(getApplicationContext(), idmon, i, PendingIntent.FLAG_UPDATE_CURRENT);
								AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
			                    am.cancel(pISender);
			                    pISender.cancel();
								//pISender.cancel();
								//displayIntent.cancel();
							}catch(Exception e){
								
							}
						}
						if(tueToggle.isChecked())
						{
							idtue=3;
							calendar.setTimeInMillis(System.currentTimeMillis());
							calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
							calendar.set(Calendar.HOUR_OF_DAY, hour);
							calendar.set(Calendar.MINUTE,minute);
							//Intent i = new Intent("com.application.healthnow.medication.alarm");
							Intent i = new Intent("com.application.healthnow.medication.PlayAlert");
							//pendingIntenttue = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
							pendingIntenttue=PendingIntent.getBroadcast(getApplicationContext(),idtue, i,PendingIntent.FLAG_UPDATE_CURRENT);
							alarmManagertue = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
							alarmManagertue.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),10000, pendingIntenttue);
							//idtue=pendingIntenttue.getCreatorUid();
							
							
						}
						else
						{
							try
							{
								Intent i = new Intent("com.application.healthnow.medication.alarm");
								//PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
								pISender = PendingIntent.getBroadcast(getApplicationContext(), idtue, i, PendingIntent.FLAG_UPDATE_CURRENT);
								AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
			                    am.cancel(pISender);
			                    pISender.cancel();
								//pISender.cancel();
								//displayIntent.cancel();
							}catch(Exception e){
								
							}
						}
						if(wedToggle.isChecked())
						{
							idwed=4;
							calendar.setTimeInMillis(System.currentTimeMillis());
							calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
							calendar.set(Calendar.HOUR_OF_DAY, hour);
							calendar.set(Calendar.MINUTE,minute);
							Intent i = new Intent("com.application.healthnow.medication.alarm");
							//pendingIntentwed = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
							pendingIntentwed=PendingIntent.getBroadcast(getApplicationContext(),idwed, i,PendingIntent.FLAG_UPDATE_CURRENT);
							alarmManagerwed = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
							alarmManagerwed.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),10000, pendingIntentwed);
							//idwed=pendingIntentwed.getCreatorUid();
							
							
						}
						else
						{
							try
							{
								Intent i = new Intent("com.application.healthnow.medication.alarm");
								//PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
								pISender = PendingIntent.getBroadcast(getApplicationContext(), idwed, i, PendingIntent.FLAG_UPDATE_CURRENT);
								AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
			                    am.cancel(pISender);
			                    pISender.cancel();
								//pISender.cancel();
								//displayIntent.cancel();
							}catch(Exception e){
								
							}
						}
						if(thuToggle.isChecked())
						{
							idthu=5;
							calendar.setTimeInMillis(System.currentTimeMillis());
							calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
							calendar.set(Calendar.HOUR_OF_DAY, hour);
							calendar.set(Calendar.MINUTE,minute);
							Intent i = new Intent("com.application.healthnow.medication.alarm");
							//pendingIntentthu = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
							pendingIntentthu=PendingIntent.getBroadcast(getApplicationContext(),idthu, i,PendingIntent.FLAG_UPDATE_CURRENT);
							alarmManagerthu = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
							alarmManagerthu.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),10000, pendingIntentthu);
							//idthu=pendingIntentthu.getCreatorUid();
							
							
						}
						else
						{
							try
							{
								Intent i = new Intent("com.application.healthnow.medication.alarm");
								//PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
								pISender = PendingIntent.getBroadcast(getApplicationContext(), idthu, i, PendingIntent.FLAG_UPDATE_CURRENT);
								AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
			                    am.cancel(pISender);
			                    pISender.cancel();
								//pISender.cancel();
								//displayIntent.cancel();
							}catch(Exception e){
								
							}
						}
						if(friToggle.isChecked())
						{
							idfri=6;
							/*Intent i = new Intent("com.application.healthnow.medication.alarm");
							PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), x, i, PendingIntent.FLAG_NO_CREATE);
							displayIntent.cancel();*/
							calendar.setTimeInMillis(System.currentTimeMillis());
							calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
							calendar.set(Calendar.HOUR_OF_DAY, hour);
							calendar.set(Calendar.MINUTE,minute);
							Intent i = new Intent("com.application.healthnow.medication.alarm");
							//pendingIntentfri = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
							pendingIntentfri=PendingIntent.getBroadcast(getApplicationContext(),idfri, i,PendingIntent.FLAG_UPDATE_CURRENT);
							alarmManagerfri = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
							alarmManagerfri.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),10000, pendingIntentfri);
							//idfri=pendingIntentfri.getCreatorUid();
							
							
							
							
						}	
						else
						{
							try
							{
								Intent i = new Intent("com.application.healthnow.medication.alarm");
								//PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
								pISender = PendingIntent.getBroadcast(getApplicationContext(), idfri, i, PendingIntent.FLAG_UPDATE_CURRENT);
								AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
			                    am.cancel(pISender);
			                    pISender.cancel();
								//pISender.cancel();
								//displayIntent.cancel();
							}catch(Exception e){
								
							}
						}
						if(satToggle.isChecked())
						{
							idsat=7;
							Toast.makeText(context, "sat", Toast.LENGTH_SHORT).show();
							//RegisterAlarmBroadcast(7);
				        	//alarmManagersat.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+5000,5000 , pendingIntentsat); 
							//Intent intent = new Intent(MedicationActivity.this, TestService.class);
							//pendingIntentsat = PendingIntent.getService(MedicationActivity.this, 0, intent, 0);
							//alarmManagersat = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
							//alarmManagersat.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),5*1000, pendingIntentsat);
							calendar.setTimeInMillis(System.currentTimeMillis());
							calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
							calendar.set(Calendar.HOUR_OF_DAY, hour);
							calendar.set(Calendar.MINUTE,minute);
							Intent i = new Intent("com.application.healthnow.medication.alarm");
							//pendingIntentsat = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
							pendingIntentsat=PendingIntent.getBroadcast(getApplicationContext(),idsat, i,PendingIntent.FLAG_UPDATE_CURRENT);
							alarmManagersat = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
							alarmManagersat.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),7*AlarmManager.INTERVAL_DAY, pendingIntentsat);
				        	//idsat=pendingIntentsat.getCreatorUid();
				        	
				        	//7days=1000*60*60*24*7milliseconds
						}
						else
						{
							/*
							if(alarmManagersat!=null)
							{
								alarmManagersat.cancel(pendingIntentsat);
							}*/

								try
								{
									Intent i = new Intent("com.application.healthnow.medication.alarm");
									//PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
									pISender = PendingIntent.getBroadcast(getApplicationContext(), idsat, i, PendingIntent.FLAG_UPDATE_CURRENT);
									AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
				                    am.cancel(pISender);
				                    pISender.cancel();
									//pISender.cancel();
									//displayIntent.cancel();
								}catch(Exception e){
									
								}
							
						}
						
						
						
						
						
						String ntime = hour + " " + minute; 
						
						//Toast.makeText(getApplicationContext(), ntime, Toast.LENGTH_LONG).show();
					}					
				});											
			}
		});
		
		removeMedication.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{	
				
			}
		});
	}
	
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		// Close The Database
		if(DB != null)
		{
			DB.db.close();
		}
	}
	
	
	
	
	/*private void RegisterAlarmBroadcast(int d)
	{   
		switch(d)
		{
			case 7:
			{
				Log.d("pendingintent", "sat");
				//mReceiversat = new BroadcastReceiver()
		    	//{
		       // private static final String TAG = "Alarm Example Receiver";
		       // @Override
		        //	public void onReceive(Context context, Intent intent)
		        	//{
		            	//Toast.makeText(context, "Alarm time has been reached", Toast.LENGTH_SHORT).show();
		            
		        	//}
		    	//};
				mReceiversat=new MyBroadcastReceiver();
		    	registerReceiver(mReceiversat, new IntentFilter("sample") );
	    	    pendingIntentsat = PendingIntent.getBroadcast( context, 0, new Intent("sample"),0 );
	    	    alarmManagersat = (AlarmManager)(context.getSystemService( Context.ALARM_SERVICE ));
			}break;
		}    
	}
	private void UnregisterAlarmBroadcast(int d)
	{
	    
	    switch(d)
	    {
	    	case 7:
	    	{
	    		try
	    		{
	    			alarmManagersat.cancel(pendingIntentsat); 
	    			getBaseContext().unregisterReceiver(mReceiversat);
	    		}catch(Exception e)
	    		{
	    			
	    		}
	    	}break;
	    }
	}*/
	
	
}








