package com.application.healthnow;

import com.application.healthnow.ExerciseFragment.buttonlistener;
import com.application.healthnow.heartratemonitor.HeartRateMonitor;

import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class VitalSignsFragment extends Fragment{
	
	int heartRate,bloodPressure,glucoseLevel,cholesterol;
	
	public VitalSignsFragment() { }
	
	private EditText editText;
	private TextView textView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		final View rootView = inflater.inflate(R.layout.fragment_vitalsigns, container, false);
		 
		Button heartRateMonitor = (Button) rootView
				.findViewById(R.id.btn_vital_heartrate_monitor);
		
		Context vitalsignsContext = getActivity().getApplicationContext();
		View vitalsignsView = rootView;
	
		heartRateMonitor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent startHeartRateMnitor = new Intent(getActivity(),
						HeartRateMonitor.class);
				startActivity(startHeartRateMnitor);
				
			}
		});

		
		editText = (EditText)rootView.findViewById(R.id.et_heartrate);
		editText.setText(Integer.toString(heartRate));
		editText = (EditText)rootView.findViewById(R.id.et_bloodpressure);
		editText.setText(Integer.toString(bloodPressure));
		editText = (EditText)rootView.findViewById(R.id.et_glucoselevel);
		editText.setText(Integer.toString(glucoseLevel));
		editText = (EditText)rootView.findViewById(R.id.et_cholesterol);
		editText.setText(Integer.toString(cholesterol));
		
		editText = (EditText) rootView.findViewById(R.id.et_bloodpressure);
		editText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				EditText etBP = (EditText) rootView.findViewById(R.id.et_bloodpressure);
				int blood = Integer.valueOf(etBP.getText().toString());
				if(blood < 120)
				{
					TextView textView = (TextView) rootView.findViewById(R.id.tv_bpstatus);
					String status = "Good";
					textView.setText(String.format("%s",status));
					textView.setTextColor(getResources().getColor(R.color.good));
				}
				if(blood >= 120 && blood < 140)
				{
					TextView textView = (TextView) rootView.findViewById(R.id.tv_bpstatus);
					String status = "Fair";
					textView.setText(String.format("%s",status));
					textView.setTextColor(getResources().getColor(R.color.fair));
				}
				if(blood >= 140)
				{
					TextView textView = (TextView) rootView.findViewById(R.id.tv_bpstatus);
					String status = "Poor";
					textView.setText(String.format("%s",status));
					textView.setTextColor(getResources().getColor(R.color.poor));
				}
				
			}
		});
		
		editText = (EditText) rootView.findViewById(R.id.et_heartrate);
		editText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				EditText etHR = (EditText) rootView.findViewById(R.id.et_heartrate);
				int heart = Integer.valueOf(etHR.getText().toString());
				if(heart < 80)
				{
					TextView textView = (TextView) rootView.findViewById(R.id.tv_hrstatus);
					String status = "good";
					textView.setText(String.format("%s",status));
					textView.setTextColor(getResources().getColor(R.color.good));
				}
				if(heart >= 80 && heart < 100)
				{
					TextView textView = (TextView) rootView.findViewById(R.id.tv_hrstatus);
					String status = "fair";
					textView.setText(String.format("%s",status));
					textView.setTextColor(getResources().getColor(R.color.fair));
				}
				if(heart >= 100)
				{
					TextView textView = (TextView) rootView.findViewById(R.id.tv_hrstatus);
					String status = "poor";
					textView.setText(String.format("%s",status));
					textView.setTextColor(getResources().getColor(R.color.poor));
				}
				
			}
		});
		
		editText = (EditText) rootView.findViewById(R.id.et_glucoselevel);
		editText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				EditText etGL = (EditText) rootView.findViewById(R.id.et_glucoselevel);
				int glucose = Integer.valueOf(etGL.getText().toString());
				if(glucose < 100)
				{
					TextView textView = (TextView) rootView.findViewById(R.id.tv_glstatus);
					String status = "good";
					textView.setText(String.format("%s",status));
					textView.setTextColor(getResources().getColor(R.color.good));
				}
				if(glucose >= 100  && glucose < 140)
				{
					TextView textView = (TextView) rootView.findViewById(R.id.tv_glstatus);
					String status = "fair";
					textView.setText(String.format("%s",status));
					textView.setTextColor(getResources().getColor(R.color.fair));
				}
				if(glucose > 140)
				{
					TextView textView = (TextView) rootView.findViewById(R.id.tv_glstatus);
					String status = "poor";
					textView.setText(String.format("%s",status));
					textView.setTextColor(getResources().getColor(R.color.poor));
				}
				
			}
		});
		
		editText = (EditText) rootView.findViewById(R.id.et_cholesterol);
		editText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				EditText etCH = (EditText) rootView.findViewById(R.id.et_cholesterol);
				int cholesterol = Integer.valueOf(etCH.getText().toString());
				if(cholesterol < 200)
				{
					TextView textView = (TextView) rootView.findViewById(R.id.tv_clstatus);
					String status = "good";
					textView.setText(String.format("%s",status));
					textView.setTextColor(getResources().getColor(R.color.good));
				}
				if(cholesterol >= 200 && cholesterol < 240)
				{
					TextView textView = (TextView) rootView.findViewById(R.id.tv_clstatus);
					String status = "fair";
					textView.setText(String.format("%s",status));
					textView.setTextColor(getResources().getColor(R.color.fair));
				}
				if(cholesterol >= 240)
				{
					TextView textView = (TextView) rootView.findViewById(R.id.tv_clstatus);
					String status = "poor";
					textView.setText(String.format("%s",status));
					textView.setTextColor(getResources().getColor(R.color.poor));
				}
				
			}
		});
		
		return rootView;
		
		
	}
}

//define DB KEVIN
	//VSDataBaseAdapter DB;


//Instantiate database KEVIN
		//DB = new VSDataBaseAdapter(getActivity());
		
		//Call DB method to insert KEVIN
		//DB.InsertVitalSigns(bloodPressure, heartRate, glucose, cholesterol);