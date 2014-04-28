package com.application.healthnow.medication;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;



public class MyBroadcastReceiver extends BroadcastReceiver 
{
	WakeLock wakeLock;MediaPlayer mPlayer;
 @Override
 public void onReceive(Context context, Intent intent) 
 {
    Toast.makeText(context, "Time is up!!!!.",
   Toast.LENGTH_SHORT).show();
    
    
    
    
    PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
    wakeLock.acquire();
    KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE); 
    KeyguardLock keyguardLock =  keyguardManager.newKeyguardLock("TAG");
    keyguardLock.disableKeyguard();
   // context.startService(new Intent(context,TestService.class));
    
    Intent i=new Intent(context,PlayAlert.class);
    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(i);
    /*Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
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
    }*/
    
    // Vibrate the mobile phone
   /* Vibrator vibrator = (Vibrator) context
  .getSystemService(Context.VIBRATOR_SERVICE);
    vibrator.vibrate(2000);*/
 }
}