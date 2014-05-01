package com.application.healthnow;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.application.healthnow.database.LoginDataBaseAdapter;
import com.application.healthnow.medication.MedicationActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	Button btnSignIn;
	View btnSignUp;
	LoginDataBaseAdapter DB;
	View forgot_pass;Context context;
	ArrayList<String> emailandpass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		final EditText editTextUserName = (EditText) findViewById(R.id.editTextUserNameToLogin);
		context=this;
		//Place username in textfield after successful registration.
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    boolean isNew = extras.getBoolean("userName", false);
		    if (isNew) {
		    		
		    } else {
		        // Do something else
		    	String userName = extras.getString("userName");
		    	editTextUserName.setText(userName);
		    }
		}
			
		// create a instance of SQLite Database
		DB = new LoginDataBaseAdapter(this);
		
		// Get The Reference Of Buttons
		btnSignUp = findViewById(R.id.link_to_register);
		btnSignIn = (Button) findViewById(R.id.buttonSignIn);
		forgot_pass=findViewById(R.id.forgot_pass);
		// Set OnClick Listener on SignUp button
		btnSignUp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {	
				// Create Intent for SignUpActivity and Start The Activity
				Intent intentSignUP = new Intent(getApplicationContext(),RegisterActivity.class);
				startActivity(intentSignUP);
			}
		});
		
		btnSignIn.setOnClickListener(new View.OnClickListener() {
			// get The User name and Password
			
			public void onClick(View v) {

				// get the References of views
				final EditText editTextUserName = (EditText) findViewById(R.id.editTextUserNameToLogin);
				final EditText editTextPassword = (EditText) findViewById(R.id.editTextPasswordToLogin);
				
				String userName = editTextUserName.getText().toString();
				String password = editTextPassword.getText().toString();
				// fetch the Password from database for respective user name
				String storedPassword = DB.GetSinlgeEntry(userName);

				// check if the Stored password matches with Password entered by
				// user
				if (password.equals(storedPassword)) {
					Toast.makeText(LoginActivity.this,
							"Congrats: Login Successfull", Toast.LENGTH_LONG)
							.show();
					
					//Set username to be used globally
					GlobalVariables globalVariable = (GlobalVariables) getApplicationContext();
					globalVariable.SetUserName(userName);
					
					//Is this first time login?
					boolean firstLogin = DB.IsFirstLogin(userName);
					
					//If fist time take to welcome screen
					if(firstLogin == true)
					{
						Intent intentStart = new Intent(getApplicationContext(),LoginSuccessActivity.class);
						startActivity(intentStart);						
					}
					else
					{
						Intent intentStart = new Intent(getApplicationContext(),MainActivity.class);
						startActivity(intentStart);						
					}

				} else {
					Toast.makeText(LoginActivity.this,
							"User Name or Password does not match",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		forgot_pass.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
				builder.setTitle("Forgot Password");
			    builder.setMessage("Enter your User Name and an email will be sent with your password");
			 // Set up the input
			    final EditText input = new EditText(context);
			    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
			    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			    builder.setView(input);

			    builder.setPositiveButton("OK",  new DialogInterface.OnClickListener() 
			    {
					
					@Override
					public void onClick(DialogInterface dialog, int which) 
					{
						// TODO Auto-generated method stub
						String user=input.getText().toString();
						if(user.equalsIgnoreCase(""))
						{
							Toast.makeText(context, "Username was blank", Toast.LENGTH_LONG).show();
						}
						else
						{
							emailandpass=DB.GetForgotPassword(user);
							if(emailandpass==null)
							{
								Toast.makeText(context, "Username does not exist", Toast.LENGTH_LONG).show();
							}
							else
							{
								Thread thread=new Thread()
								{
									
									@Override
									public void run()
									{
													Mail m = new Mail("Team3.cse3310.2014@gmail.com", "CSE_3310"); 
													Log.d("email is",emailandpass.get(0).toString());
													String[] toArr={emailandpass.get(0).toString()};
													m.setTo(toArr);
													m.setFrom("Team3.cse3310.2014@gmail.com"); 
													m.setSubject("Forgot Password");
													m.setBody("your password for HealthNow is:"+emailandpass.get(1).toString());
													try 
													{ 
												        
					
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
									}

									};
									thread.start();
									Toast.makeText(context, "Password sent to "+emailandpass.get(0), Toast.LENGTH_LONG).show();
								
								}
							}
						}
					
				});
			    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
			    builder.show();

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





