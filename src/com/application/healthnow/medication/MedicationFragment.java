package com.application.healthnow.medication;


import com.application.healthnow.GlobalVariables;
import com.application.healthnow.R;
import com.application.healthnow.database.LoginDataBaseAdapter;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MedicationFragment extends Fragment{
	
	Button login;
	LoginDataBaseAdapter DB;
	GlobalVariables globalVariable;
	public MedicationFragment() { }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		final View rootView = inflater.inflate(R.layout.fragment_medication_login, container, false);
		DB = new LoginDataBaseAdapter(getActivity());
		login = (Button) rootView.findViewById(R.id.medication_button_login);
		
		login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {	
				
				EditText EditTextPin = (EditText) rootView.findViewById(R.id.medication_password);
				String ApplicationPin = EditTextPin.getText().toString();
				String uName = GlobalVariables.userName;
				
				String pin = DB.GetPin(uName);
				
				if(ApplicationPin.equals(pin))
				{
					EditTextPin.setText("");
					Intent intent = new Intent(getActivity(), MedicationActivity.class);
					startActivity(intent);
				}		
				else
				{
					Toast.makeText(getActivity(), "Wrong Pin", Toast.LENGTH_SHORT).show();
				}
			}
		});	
		
		return rootView;
	}
}