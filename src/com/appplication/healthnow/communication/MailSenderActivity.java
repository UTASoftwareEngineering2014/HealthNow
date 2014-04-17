package com.appplication.healthnow.communication;

import java.io.UnsupportedEncodingException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.application.healthnow.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MailSenderActivity extends Activity {
	private static final String username = "UTASoftwareEngineering2014@gmail.com";
	private static final String password = "softengineering";
	private EditText emailEdit;
	private EditText subjectEdit;
	private EditText messageEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mail_sender);
		
		emailEdit = (EditText) findViewById(R.id.et_recipient_email);
		subjectEdit = (EditText) findViewById(R.id.et_emailSubject);
		messageEdit = (EditText) findViewById(R.id.et_emailMessage);
		Button btnSendMail = (Button) findViewById(R.id.btn_send_email);
		
		btnSendMail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String email = emailEdit.getText().toString();
				String subject = subjectEdit.getText().toString();
				String message = messageEdit.getText().toString();
				try {
					sendMail(email, subject, message);
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});	
	}

	private void sendMail(String email, String subject, String messageBody) throws AddressException  {
		Session session = createSessionObject();
		
		Message message = null;
		try {
			message = createMessage(email, subject, messageBody, session);
			new SendMailTask().execute(message);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private Message createMessage(String email, String subject,
			String messageBody, Session session) throws UnsupportedEncodingException, MessagingException{
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("UTASoftwareEngineering2014@gmail.com", "Team 3"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, email));
		message.setSubject(subject);
		message.setText(messageBody);
		return null;
	}

	private Session createSessionObject() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class SendMailTask extends AsyncTask<Message, Void, Void> {
		private ProgressDialog progressDialog;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(MailSenderActivity.this, "Please wait.", "Sending Mail...", true, false);
		}
		
		@Override
		protected void onPostExecute(Void aVoid)
		{
			super.onPostExecute(aVoid);
			progressDialog.dismiss();
		}
		
		@Override
		protected Void doInBackground(Message... messages) {
			try
			{
				Transport.send(messages[0]);
			}
			catch(MessagingException e)
			{
				e.printStackTrace();
			}
			
			return null;
		}

	}

}


