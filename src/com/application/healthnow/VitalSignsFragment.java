package com.application.healthnow;

import com.application.healthnow.database.VSDataBaseAdapter;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VitalSignsFragment extends Fragment{
	
	//define DB KEVIN
	//VSDataBaseAdapter DB;
	
	public VitalSignsFragment() { }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_vitalsigns, container, false);
		 
		//Instantiate database KEVIN
		//DB = new VSDataBaseAdapter(getActivity());
		
		//Call DB method to insert KEVIN
		//DB.InsertVitalSigns(bloodPressure, heartRate, glucose, cholesterol);
		
		
		return rootView;
	}
}