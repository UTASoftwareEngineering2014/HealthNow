package com.application.healthnow.medication;

import com.application.healthnow.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

public class PlayAlert extends Activity
{

	MediaPlayer mPlayer;Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_alert);
		//context.getResources().getColor(android.R.color.background_dark);
		Window window = this.getWindow();
        window.addFlags(LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(LayoutParams.FLAG_TURN_SCREEN_ON);
		Intent i=getIntent();
		String med=i.getExtras().getString("medname");
		TextView med_totake=(TextView)findViewById(R.id.tv_medicationtotake);
		med_totake.setText("Time to take "+med+"!");
		
		
		context=getApplicationContext();
	    Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
	    if(alert == null){
	        // alert is null, using backup
	        alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
	        if(alert == null){  // I can't see this ever being null (as always have a default notification) but just incase
	            // alert backup is null, using 2nd backup
	            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);               
	        }
	    }
	    mPlayer = new MediaPlayer();
	    try {
	        mPlayer.setDataSource(context, alert);
	        mPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
	        mPlayer.setLooping(false);
	        mPlayer.prepare();
	        mPlayer.start();
	    }
	    catch(Exception e) {
	    //TODO : Implement Error Checking
	        e.printStackTrace();
	        Log.e("MediaPlayer", "Error while playing!");
	    }
	    Button stop_alarm=(Button)findViewById(R.id.stop_Alarm);
	    stop_alarm.setOnClickListener(new View.OnClickListener() 
	    {
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				if(mPlayer!=null)mPlayer.stop();
				
			}
		});
	}
	@Override
	protected void onDestroy() 
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		finish();
	}
	@Override
	protected void onPause() 
	{
		// TODO Auto-generated method stub
		super.onPause();
		
	}
	
	
	
}
