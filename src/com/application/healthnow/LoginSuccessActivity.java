package com.application.healthnow;

import com.application.healthnow.database.LoginDataBaseAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginSuccessActivity extends Activity {

	AlertDialog.Builder builder;
	AlertDialog alertDialog;
	LoginDataBaseAdapter DB;
	Button saveInfo, savePin;
	Context mContext = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_popup);

		final String userName = GlobalVariables.userName;

		// Initilize the database
		DB = new LoginDataBaseAdapter(this);

		// Hide softKeyboard ---- FIX
		InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		keyboard.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

		// Start of Dialog box
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater
				.inflate(R.layout.activity_login_firstlogin, null);

		builder = new AlertDialog.Builder(mContext);
		builder.setView(layout).setTitle("Personal Info");
		builder.setNegativeButton("SKIP",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Intent intentStart = new Intent(
								getApplicationContext(), MainActivity.class);
						startActivity(intentStart);
						finish();
					}
				});

		builder.setPositiveButton("CONTINUE",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						setContentView(R.layout.activity_login_personalinfo);
						saveInfo = (Button) findViewById(R.id.login_success_button_save);

						saveInfo.setOnClickListener(new View.OnClickListener() {

							public void onClick(View v) {

								EditText editTextFirstName = (EditText) findViewById(R.id.login_success_text_firstname);
								EditText editTextLastName = (EditText) findViewById(R.id.login_success_text_lastname);
								EditText editTextWeight = (EditText) findViewById(R.id.login_success_text_weight);
								EditText editTextHeight = (EditText) findViewById(R.id.login_success_text_height);
								String firstName = editTextFirstName.getText()
										.toString();
								String lastName = editTextLastName.getText()
										.toString();
								String weight = editTextWeight.getText()
										.toString();
								String height = editTextHeight.getText()
										.toString();

								if (firstName.equals("")) {
									Toast.makeText(getApplicationContext(),
											"PLEASE FILL FIRST NAME FEILD",
											Toast.LENGTH_LONG).show();
									return;
								}
								if (lastName.equals("")) {
									Toast.makeText(getApplicationContext(),
											"PLEASE FILL LAST NAME FEILD",
											Toast.LENGTH_LONG).show();
									return;
								}
								if (weight.equals("")) {
									Toast.makeText(getApplicationContext(),
											"PLEASE FILL WEIGHT FEILD",
											Toast.LENGTH_LONG).show();
									return;
								}
								if (height.equals("")) {
									Toast.makeText(getApplicationContext(),
											"PLEASE FILL HEIGHT FEILD",
											Toast.LENGTH_LONG).show();
									return;
								} else {
									DB.InsertFirstLogin(userName, firstName,
											lastName, weight, height);

									// Hide softKeyboard ---- FIX
									InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
									keyboard.toggleSoftInput(
											InputMethodManager.HIDE_IMPLICIT_ONLY,
											0);

									setContentView(R.layout.activity_login_popup);
									LayoutInflater inflater = (LayoutInflater) mContext
											.getSystemService(LAYOUT_INFLATER_SERVICE);
									View layout = inflater
											.inflate(
													R.layout.activity_login_firstlogin_pin,
													null);

									builder = new AlertDialog.Builder(mContext);
									builder.setView(layout).setTitle(
											"Create Pin");
									builder.setPositiveButton(
											"Continue",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int id) {

													setContentView(R.layout.activity_login_pin);
													savePin = (Button) findViewById(R.id.login_success_button_pin_save);

													savePin.setOnClickListener(new View.OnClickListener() {

														public void onClick(
																View v) {

															EditText editTextPin = (EditText) findViewById(R.id.login_success_text_pin);
															String pin = editTextPin
																	.getText()
																	.toString();

															if (pin.equals("")) {
																Toast.makeText(
																		getApplicationContext(),
																		"PLEASE FILL FIRST NAME FEILD",
																		Toast.LENGTH_LONG)
																		.show();
																return;
															} else {
																DB.insertPin(
																		pin,
																		userName);

																// Hide
																// softKeyboard
																// ---- FIX
																InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
																keyboard.toggleSoftInput(
																		InputMethodManager.HIDE_IMPLICIT_ONLY,
																		0);

																Intent intentStart = new Intent(
																		getApplicationContext(),
																		MainActivity.class);
																startActivity(intentStart);
																finish();
															}
														}
													});
												}
											});
									alertDialog = builder.create();

									alertDialog.show();
								}
							}
						});

					}
				});

		builder.setNeutralButton("NEVER SHOW AGAIN",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						DB.DismissFirstLogin(userName);
						Intent intentStart = new Intent(
								getApplicationContext(), MainActivity.class);
						startActivity(intentStart);
						finish();

					}
				});

		alertDialog = builder.create();

		alertDialog.show();
	}
}
