package com.application.healthnow;

import com.application.healthnow.R;
import com.application.healthnow.database.LoginDataBaseAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity
{
    EditText editTextUserName,editTextPassword,editTextConfirmPassword, editTextEmail, editTextConfirmEmail;
    Button btnCreateAccount;
	LoginDataBaseAdapter DB;

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        // get Instance  of Database Adapter
        DB = new LoginDataBaseAdapter(this);

        // Get Refferences of Views
        editTextUserName=(EditText)findViewById(R.id.editTextUserName);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextConfirmPassword=(EditText)findViewById(R.id.editTextConfirmPassword);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextConfirmEmail = (EditText)findViewById(R.id.editTextEmailConfirm);
        
        btnCreateAccount=(Button)findViewById(R.id.buttonCreateAccount);
        
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {

            String userName=editTextUserName.getText().toString();
            String password=editTextPassword.getText().toString();
            String confirmPassword=editTextConfirmPassword.getText().toString();
            String email = editTextEmail.getText().toString();
            String confirmEmail = editTextConfirmEmail.getText().toString();

            // check if any of the fields are vaccant
            if(userName.equals("")||password.equals("")||confirmPassword.equals("")||email.equals("")||confirmEmail.equals(""))
            {
                    Toast.makeText(getApplicationContext(), "Fields Vaccant", Toast.LENGTH_LONG).show();
                    return;
            }
            // check if both password matches
            if(!password.equals(confirmPassword))
            {
                Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
                return;
            }
            if(!email.equals(confirmEmail))
            {
            	Toast.makeText(getApplicationContext(), "Emails do not match", Toast.LENGTH_LONG).show();
                return;            	
            }
            else
            {
                // Save the Data in Database
            	DB.insertEntry(userName, password, email);
                Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                
                Intent intentStart = new Intent(getApplicationContext(),LoginActivity.class);
                intentStart.putExtra("userName", userName);
				startActivity(intentStart);
            }
        }
    });
}
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(DB.db != null)
            DB.db.close();
    }
}

