package com.application.healthnow.medication;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.application.healthnow.R;
import com.application.healthnow.database.LoginDataBaseAdapter;
import com.application.healthnow.database.MedicationDataBaseAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
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
	MedicationDataBaseAdapter MED_DB;
	EditText medname;int uid;
	ListView med;ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_medication);
		
		MED_DB = new MedicationDataBaseAdapter(this);
		
		
		
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
		
		med=(ListView)findViewById(R.id.listview_medication);
		adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1,MED_DB.GetAllMedication());
		med.setAdapter(adapter);
		med.setOnItemClickListener(new OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) 
			{
				// TODO Auto-generated method stub
				final String  itemValue    = (String) med.getItemAtPosition(position);
				AlertDialog.Builder builder = new AlertDialog.Builder(context);

			    builder.setTitle("Confirm");
			    builder.setMessage("Are you sure you want to delete "+itemValue+"?");

			    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			        public void onClick(DialogInterface dialog, int which) 
			        {
			            // Do nothing but close the dialog
			        	int uid=MED_DB.GetIntentId(itemValue);
			        	try
						{
							Intent i = new Intent("com.application.healthnow.medication.alarm");
							//PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
							pISender = PendingIntent.getBroadcast(getApplicationContext(), uid, i, PendingIntent.FLAG_UPDATE_CURRENT);
							AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		                    am.cancel(pISender);
		                    pISender.cancel();
							//pISender.cancel();
							//displayIntent.cancel();
						}catch(Exception e)
						{
							
						}
			        	try
						{
							Intent i = new Intent("com.application.healthnow.medication.alarm");
							//PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
							pISender = PendingIntent.getBroadcast(getApplicationContext(), uid+1, i, PendingIntent.FLAG_UPDATE_CURRENT);
							AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		                    am.cancel(pISender);
		                    pISender.cancel();
							//pISender.cancel();
							//displayIntent.cancel();
						}catch(Exception e)
						{
						}
			        	try
						{
							Intent i = new Intent("com.application.healthnow.medication.alarm");
							//PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
							pISender = PendingIntent.getBroadcast(getApplicationContext(), uid+2, i, PendingIntent.FLAG_UPDATE_CURRENT);
							AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		                    am.cancel(pISender);
		                    pISender.cancel();
							//pISender.cancel();
							//displayIntent.cancel();
						}catch(Exception e)
						{
						}
			        	try
						{
							Intent i = new Intent("com.application.healthnow.medication.alarm");
							//PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
							pISender = PendingIntent.getBroadcast(getApplicationContext(), uid+3, i, PendingIntent.FLAG_UPDATE_CURRENT);
							AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		                    am.cancel(pISender);
		                    pISender.cancel();
							//pISender.cancel();
							//displayIntent.cancel();
						}catch(Exception e)
						{
						}
			        	try
						{
							Intent i = new Intent("com.application.healthnow.medication.alarm");
							//PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
							pISender = PendingIntent.getBroadcast(getApplicationContext(), uid+4, i, PendingIntent.FLAG_UPDATE_CURRENT);
							AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		                    am.cancel(pISender);
		                    pISender.cancel();
							//pISender.cancel();
							//displayIntent.cancel();
						}catch(Exception e)
						{
						}
			        	try
						{
							Intent i = new Intent("com.application.healthnow.medication.alarm");
							//PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
							pISender = PendingIntent.getBroadcast(getApplicationContext(), uid+5, i, PendingIntent.FLAG_UPDATE_CURRENT);
							AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		                    am.cancel(pISender);
		                    pISender.cancel();
							//pISender.cancel();
							//displayIntent.cancel();
						}catch(Exception e)
						{
						}
			        	try
						{
							Intent i = new Intent("com.application.healthnow.medication.alarm");
							//PendingIntent displayIntent = PendingIntent.getBroadcast(getApplicationContext(), idsun, i, PendingIntent.FLAG_UPDATE_CURRENT);
							pISender = PendingIntent.getBroadcast(getApplicationContext(), uid+6, i, PendingIntent.FLAG_UPDATE_CURRENT);
							AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		                    am.cancel(pISender);
		                    pISender.cancel();
							//pISender.cancel();
							//displayIntent.cancel();
						}catch(Exception e)
						{
						}
			        	MED_DB.DeleteMedication(itemValue);
			        	med=(ListView)findViewById(R.id.listview_medication);
			    		adapter = new ArrayAdapter<String>(context,
			    	              android.R.layout.simple_list_item_1, android.R.id.text1,MED_DB.GetAllMedication());
			    		med.setAdapter(adapter);
			         
			        
			        }
			    });

			    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			            // Do nothing
			            dialog.dismiss();
			        }
			    });

			    AlertDialog alert = builder.create();
			    alert.show();
				
			}
		});
		
		
		
		addMedication.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{	
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
				medname=(EditText)findViewById(R.id.medication_name);
				String medication=medname.getText().toString();
				uid=hash(medication);
				idsun=uid;idmon=uid+1;idtue=uid+2;idwed=uid+3;idthu=uid+4;idfri=uid+5;idsat=uid+6;
				MED_DB.InsertMedication(medication,uid );
				saveAlarm.setOnClickListener(new View.OnClickListener() 
				{
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
						/*else
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
						}*/
						if(monToggle.isChecked())
						{
							
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
						/*else
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
						}*/
						if(tueToggle.isChecked())
						{
							
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
						/*else
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
						}*/
						if(wedToggle.isChecked())
						{
							
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
						/*else
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
						}*/
						if(thuToggle.isChecked())
						{
							
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
						/*else
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
						}*/
						if(friToggle.isChecked())
						{
							
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
						/*else
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
						}*/
						if(satToggle.isChecked())
						{
							
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
						/*else
						{
//							
//							if(alarmManagersat!=null)
//							{
//								alarmManagersat.cancel(pendingIntentsat);
//							}

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
							
						}*/
						
						
						
						
						
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
	
	
	
	public int hash(String name)
	{
		Random rand = new Random(); 
		int value = rand.nextInt(Integer.MAX_VALUE);
		int sum=0;
		for(int i=0;i<name.length();i++)
		{
			sum=sum+name.charAt(i);
		}
		return (value/sum);
	}
	
	
	
	
}



class StableArrayAdapter extends ArrayAdapter<String> 
{

	HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	public StableArrayAdapter(Context context, int textViewResourceId,
			List<String> objects) {
		super(context, textViewResourceId, objects);
		for (int i = 0; i < objects.size(); ++i) {
			mIdMap.put(objects.get(i), i);
		}
	}
}







