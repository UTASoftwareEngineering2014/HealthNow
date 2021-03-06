package com.application.healthnow.medication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.application.healthnow.GlobalVariables;
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
	LoginDataBaseAdapter LG_DB;
	EditText medname;int uid;
	ListView med;StableArrayAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_medication);
		
		MED_DB = new MedicationDataBaseAdapter(this);
		LG_DB = new LoginDataBaseAdapter(this);
		
		
		
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
		
		med=(ListView)findViewById(R.id.listview_medication);
		if(MED_DB.GetAllMedication()!=null)
			{
					ArrayList<String> a=new ArrayList<String>();	//populate the list for medication
					ArrayList<String> b=new ArrayList<String>();
					a=MED_DB.GetAllMedication();String ad;
        			for(int i=0;i<a.size();i++)
        			{
        				ad=a.get(i).toString();
        				if(ad.contains(""+LG_DB.GetUserId()))
        				{
        					b.add(ad.replace(""+LG_DB.GetUserId(), ""));
        				}
        			}
					adapter = new StableArrayAdapter(this,
							android.R.layout.simple_list_item_1, b);
					med.setAdapter(adapter);
			}
		med.setOnItemClickListener(new OnItemClickListener() //set listener for deleting
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) 
			{
				// TODO Auto-generated method stub
				final String  itemValue    = (String) med.getItemAtPosition(position);
				AlertDialog.Builder builder = new AlertDialog.Builder(MedicationActivity.this);

			    builder.setTitle("Confirm");
			    builder.setMessage("Are you sure you want to delete "+itemValue+"?");

			    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			        public void onClick(DialogInterface dialog, int which) 
			        {
			        	
			        	
			        	Log.d("fromlist", "1"+itemValue+"1");	//cancel all the alarms by getting their request codes from the database

			        	int uid=MED_DB.GetIntentId(itemValue+LG_DB.GetUserId());
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
			        	MED_DB.DeleteMedication(itemValue+LG_DB.GetUserId());
			        	if(MED_DB.GetAllMedication()!=null)
						{
								/*adapter = new StableArrayAdapter(context,
										android.R.layout.simple_list_item_1, MED_DB.GetAllMedication());
								med.setAdapter(adapter);*/
			        			adapter.clear();
			        			ArrayList<String> a=new ArrayList<String>();
			        			ArrayList<String> b=new ArrayList<String>();
			        			a=MED_DB.GetAllMedication();String ad;
			        			for(int i=0;i<a.size();i++)
			        			{
			        				ad=a.get(i).toString();
			        				if(ad.contains(""+LG_DB.GetUserId()))
			        				{
			        					b.add(ad.replace(""+LG_DB.GetUserId(), ""));
			        				}
			        			}
			        			adapter.addAll(b);
			        			adapter.notifyDataSetChanged();
			        			med.invalidateViews();
			        			med.refreshDrawableState();
						}
			         
			        
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
				medname=(EditText)findViewById(R.id.timer_text);
				/*String medication=medname.getText().toString();
				uid=hash(medication);
				idsun=uid;idmon=uid+1;idtue=uid+2;idwed=uid+3;idthu=uid+4;idfri=uid+5;idsat=uid+6;
				MED_DB.InsertMedication(medication,uid );*/
				saveAlarm.setOnClickListener(new View.OnClickListener() 
				{
					@SuppressLint("NewApi")
					public void onClick(View v) 
					{
						int count=0;
						String medication=medname.getText().toString();
						if(medication.length()==0)   //error check for blank medication name
						{
							Toast.makeText(context, "Medication name cannot be blank", Toast.LENGTH_SHORT).show();
							count=1;
						}
						if(count==0)
						{
										uid=hash(medication);   //perform a hash for the request code for the medication alarm
										idsun=uid;idmon=uid+1;idtue=uid+2;idwed=uid+3;idthu=uid+4;idfri=uid+5;idsat=uid+6;
										MED_DB.InsertMedication(medication+LG_DB.GetUserId(),uid );
										Time time = new Time();
										
										timePicker.clearFocus();
										time.hour = timePicker.getCurrentHour();
										time.minute = timePicker.getCurrentMinute();
										hour = time.hour;
										minute = time.minute;
									//set the alarm for the checked days
										if(sunToggle.isChecked())
										{
											
											calendar.setTimeInMillis(System.currentTimeMillis());
											calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
											calendar.set(Calendar.HOUR_OF_DAY, hour);
											calendar.set(Calendar.MINUTE,minute);
											Intent i = new Intent("com.application.healthnow.medication.alarm");
											i.putExtra("medname", medication);
											//pendingIntentsun = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
											pendingIntentsun=PendingIntent.getBroadcast(getApplicationContext(),idsun, i,PendingIntent.FLAG_UPDATE_CURRENT);
											alarmManagersun = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
											alarmManagersun.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),7*AlarmManager.INTERVAL_DAY, pendingIntentsun);
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
											i.putExtra("medname", medication);
											//pendingIntentmon = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
											pendingIntentmon=PendingIntent.getBroadcast(getApplicationContext(),idmon, i,PendingIntent.FLAG_CANCEL_CURRENT);
											alarmManagermon = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
											alarmManagermon.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),7*AlarmManager.INTERVAL_DAY, pendingIntentmon);
											
											
										}

										if(tueToggle.isChecked())
										{
											
											calendar.setTimeInMillis(System.currentTimeMillis());
											calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
											calendar.set(Calendar.HOUR_OF_DAY, hour);
											calendar.set(Calendar.MINUTE,minute);
											//Intent i = new Intent("com.application.healthnow.medication.alarm");
											Intent i = new Intent("com.application.healthnow.medication.alarm");
											i.putExtra("medname", medication);
											//pendingIntenttue = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
											pendingIntenttue=PendingIntent.getBroadcast(getApplicationContext(),idtue, i,PendingIntent.FLAG_UPDATE_CURRENT);
											alarmManagertue = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
											alarmManagertue.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),7*AlarmManager.INTERVAL_DAY, pendingIntenttue);
											//idtue=pendingIntenttue.getCreatorUid();
											
											
										}

										if(wedToggle.isChecked())
										{
											
											calendar.setTimeInMillis(System.currentTimeMillis());
											calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
											calendar.set(Calendar.HOUR_OF_DAY, hour);
											calendar.set(Calendar.MINUTE,minute);
											Intent i = new Intent("com.application.healthnow.medication.alarm");
											i.putExtra("medname", medication);
											//pendingIntentwed = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
											pendingIntentwed=PendingIntent.getBroadcast(getApplicationContext(),idwed, i,PendingIntent.FLAG_UPDATE_CURRENT);
											alarmManagerwed = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
											alarmManagerwed.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),7*AlarmManager.INTERVAL_DAY, pendingIntentwed);
											//idwed=pendingIntentwed.getCreatorUid();
											
											
										}

										if(thuToggle.isChecked())
										{
											
											calendar.setTimeInMillis(System.currentTimeMillis());
											calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
											calendar.set(Calendar.HOUR_OF_DAY, hour);
											calendar.set(Calendar.MINUTE,minute);
											Intent i = new Intent("com.application.healthnow.medication.alarm");
											i.putExtra("medname", medication);
											//pendingIntentthu = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
											pendingIntentthu=PendingIntent.getBroadcast(getApplicationContext(),idthu, i,PendingIntent.FLAG_UPDATE_CURRENT);
											alarmManagerthu = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
											alarmManagerthu.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),7*AlarmManager.INTERVAL_DAY, pendingIntentthu);
											//idthu=pendingIntentthu.getCreatorUid();
											
											
										}

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
											i.putExtra("medname", medication);
											//pendingIntentfri = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
											pendingIntentfri=PendingIntent.getBroadcast(getApplicationContext(),idfri, i,PendingIntent.FLAG_UPDATE_CURRENT);
											alarmManagerfri = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
											alarmManagerfri.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),7*AlarmManager.INTERVAL_DAY, pendingIntentfri);
											//idfri=pendingIntentfri.getCreatorUid();
											
											
											
											
										}	

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
											i.putExtra("medname", medication);
											//pendingIntentsat = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
											pendingIntentsat=PendingIntent.getBroadcast(getApplicationContext(),idsat, i,PendingIntent.FLAG_UPDATE_CURRENT);
											alarmManagersat = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
											alarmManagersat.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),7*AlarmManager.INTERVAL_DAY, pendingIntentsat);
								        	//idsat=pendingIntentsat.getCreatorUid();
								        	
								        	//7days=1000*60*60*24*7milliseconds
										}

										
										
										
										
										
										
										//Toast.makeText(getApplicationContext(), ntime, Toast.LENGTH_LONG).show();
							}			
					}
				});											
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
	
	
	

	
	
	
	public int hash(String name)  //hash function to generate the request code
	{
		Random rand = new Random(); 
		int value = rand.nextInt(Integer.MAX_VALUE);
		int sum=0;char a=name.charAt(0);
		for(int i=0;i<name.length();i++)
		{
			sum=sum+(int)name.charAt(i);
		}
		if(sum!=0)return (value/sum);
		else return value;
	}
	
	
	
	
}



class StableArrayAdapter extends ArrayAdapter<String>   //custom adapter for the list
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







