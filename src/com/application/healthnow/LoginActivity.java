package com.application.healthnow;

import com.application.healthnow.database.LoginDataBaseAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	Button btnSignIn;
	View btnSignUp;
	LoginDataBaseAdapter DB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		final EditText editTextUserName = (EditText) findViewById(R.id.editTextUserNameToLogin);
		
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
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Close The Database
		if(DB.db != null)
            DB.db.close();;
	}
}
