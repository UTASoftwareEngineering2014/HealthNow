package com.application.healthnow.communication;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.application.healthnow.GlobalVariables;
import com.application.healthnow.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"vd31192@gmail.com"});
				i.putExtra(Intent.EXTRA_SUBJECT, "Your patient" + GlobalVariables.userName + " did not take their medicine.");
				i.putExtra(Intent.EXTRA_TEXT   , "You need to get onto them.");
				try {
				    startActivity(Intent.createChooser(i, "Send mail..."));
				} catch (android.content.ActivityNotFoundException ex) {
				    Toast.makeText(MailSenderActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
				}
			}
//			@Override
//			public void onClick(View v) {
//				Mail mail = new Mail(username, password);
//
//				String[] toArr = { "vd31192@gmail.com", "vd31192@gmail.com" };
//				mail.set_to(toArr);
//				mail.set_from(username);
//				mail.set_subject("This is an email sent using my Mail JavaMail wrapper from an Android device.");
//				mail.setBody("Email body.");
//
//				try {
//					if (mail.send()) {
//						Toast.makeText(MailSenderActivity.this,
//								"Email was sent successfully.",
//								Toast.LENGTH_LONG).show();
//					} else {
//						Toast.makeText(MailSenderActivity.this,
//								"Email was not sent.", Toast.LENGTH_LONG)
//								.show();
//					}
//				} catch (Exception e) {
//					// Toast.makeText(MailApp.this,
//					// "There was a problem sending the email.",
//					// Toast.LENGTH_LONG).show();
//					Log.e("MailApp", "Could not send email", e);
//				}
//			}
		});
	}
}
