package com.application.healthnow.medication;

import javax.mail.Multipart; 
import javax.mail.PasswordAuthentication; 
import javax.mail.Session; 
import javax.mail.Transport; 
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeBodyPart; 
import javax.mail.internet.MimeMessage; 
import javax.mail.internet.MimeMultipart; 

import java.util.ArrayList;
import java.util.Properties;
import java.util.Date; 
import java.util.Properties; 
import javax.activation.CommandMap; 
import javax.activation.DataHandler; 
import javax.activation.DataSource; 
import javax.activation.FileDataSource; 
import javax.activation.MailcapCommandMap; 
import javax.mail.BodyPart; 
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.application.healthnow.GlobalVariables;
import com.application.healthnow.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayAlert extends Activity
{

	MediaPlayer mPlayer;Context context;
	int count=0; long current;String med;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_alert);
		setTitle("Medication Alarm");
		current=System.currentTimeMillis();
		//context.getResources().getColor(android.R.color.background_dark);
		Window window = this.getWindow();
        window.addFlags(LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(LayoutParams.FLAG_TURN_SCREEN_ON);
		Intent i=getIntent();
		med=i.getExtras().getString("medname");
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
				count=1;
				finish();
				
			}
		});
	    Thread thread = new Thread()
	    {
	        @Override
	        public void run() 
	        {

					
							long current=System.currentTimeMillis();
							while(((System.currentTimeMillis()-current)<10000)&&count==0)
							{
	         	    	
							}
							if(count==0)
							{
								Log.d("alert", "10seconds up without pressing dismiss");
								//this is where email must be sent
								Mail m = new Mail("Team3.cse3310.2014@gmail.com", "CSE_3310"); 
								String[] toArr = {"arvind7694@gmail.com"};
								m.setTo(toArr);
								m.setFrom("Team3.cse3310.2014@gmail.com"); 
								m.setSubject("Medication not Taken");
								m.setBody(GlobalVariables.userName+" "+"did not take "+med);
								try { 
							        

							        if(m.send()) 
							        { 
							          //Toast.makeText(MailApp.this, "Email was sent successfully.", Toast.LENGTH_LONG).show(); 
							        	Log.d("mail", "sent");
							        } else 
							        { 
							         // Toast.makeText(MailApp.this, "Email was not sent.", Toast.LENGTH_LONG).show(); 
							        	Log.d("mail", "notsent");
							        } 
							      } catch(Exception e) { 
							        //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show(); 
							        Log.e("MailApp", "Could not send email", e); 
							      } 
								
								SmsManager manager = SmsManager.getDefault();
								manager.sendTextMessage("6784472900",null,GlobalVariables.userName+" "+"did not take "+med,null,null);
								
								if(mPlayer!=null)mPlayer.stop();
								PlayAlert.this.finish();
							}

						
			}

	            	 	
	         	    	
	         	    	
	      };
	    
	    thread.start();
	    //while(thread.isAlive());
	    //if(count==0)Toast.makeText(PlayAlert.this, "email sent", Toast.LENGTH_LONG).show();

	    
	    


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




class Mail extends javax.mail.Authenticator { 
	  private String _user; 
	  private String _pass; 

	  private String[] _to; 
	  private String _from; 

	  private String _port; 
	  private String _sport; 

	  private String _host; 

	  private String _subject; 
	  private String _body; 

	  private boolean _auth; 

	  private boolean _debuggable; 

	  private Multipart _multipart; 


	  public Mail() { 
	    _host = "smtp.gmail.com"; // default smtp server 
	    _port = "465"; // default smtp port 
	    _sport = "465"; // default socketfactory port 

	    _user = ""; // username 
	    _pass = ""; // password 
	    _from = ""; // email sent from 
	    _subject = ""; // email subject 
	    _body = ""; // email body 

	    _debuggable = false; // debug mode on or off - default off 
	    _auth = true; // smtp authentication - default on 

	    _multipart = new MimeMultipart(); 

	    // There is something wrong with MailCap, javamail can not find a handler for the multipart/mixed part, so this bit needs to be added. 
	    MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap(); 
	    mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html"); 
	    mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml"); 
	    mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain"); 
	    mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed"); 
	    mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822"); 
	    CommandMap.setDefaultCommandMap(mc); 
	  } 

	  public void setTo(String[] toArr) 
	  {
		// TODO Auto-generated method stub
		  _to=toArr;
		
	  }
	  public void setFrom(String toArr) 
	  {
		// TODO Auto-generated method stub
		  _from=toArr;
		
	  }
	  public void setSubject(String toArr) 
	  {
		// TODO Auto-generated method stub
		  _subject=toArr;
		
	  }

	public Mail(String user, String pass) { 
	    this(); 

	    _user = user; 
	    _pass = pass; 
	  } 

	  public boolean send() throws Exception { 
	    Properties props = _setProperties(); 

	    if(!_user.equals("") && !_pass.equals("") && _to.length > 0 && !_from.equals("") && !_subject.equals("") && !_body.equals("")) { 
	      Session session = Session.getInstance(props, this); 

	      MimeMessage msg = new MimeMessage(session); 

	      msg.setFrom(new InternetAddress(_from)); 

	      InternetAddress[] addressTo = new InternetAddress[_to.length]; 
	      for (int i = 0; i < _to.length; i++) { 
	        addressTo[i] = new InternetAddress(_to[i]); 
	      } 
	        msg.setRecipients(MimeMessage.RecipientType.TO, addressTo); 

	      msg.setSubject(_subject); 
	      msg.setSentDate(new Date()); 

	      // setup message body 
	      BodyPart messageBodyPart = new MimeBodyPart(); 
	      messageBodyPart.setText(_body); 
	      _multipart.addBodyPart(messageBodyPart); 

	      // Put parts in message 
	      msg.setContent(_multipart); 

	      // send email 
	      Transport.send(msg); 

	      return true; 
	    } else { 
	      return false; 
	    } 
	  } 

	  public void addAttachment(String filename) throws Exception { 
	    BodyPart messageBodyPart = new MimeBodyPart(); 
	    DataSource source = new FileDataSource(filename); 
	    messageBodyPart.setDataHandler(new DataHandler(source)); 
	    messageBodyPart.setFileName(filename); 

	    _multipart.addBodyPart(messageBodyPart); 
	  } 

	  @Override 
	  public PasswordAuthentication getPasswordAuthentication() { 
	    return new PasswordAuthentication(_user, _pass); 
	  } 

	  private Properties _setProperties() { 
	    Properties props = new Properties(); 

	    props.put("mail.smtp.host", _host); 

	    if(_debuggable) { 
	      props.put("mail.debug", "true"); 
	    } 

	    if(_auth) { 
	      props.put("mail.smtp.auth", "true"); 
	    } 

	    props.put("mail.smtp.port", _port); 
	    props.put("mail.smtp.socketFactory.port", _sport); 
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
	    props.put("mail.smtp.socketFactory.fallback", "false"); 

	    return props; 
	  } 

	  // the getters and setters 
	  public String getBody() { 
	    return _body; 
	  } 

	  public void setBody(String _body) { 
	    this._body = _body; 
	  } 

	  // more of the getters and setters ….. 
	} 




