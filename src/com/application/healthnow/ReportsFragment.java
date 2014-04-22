package com.application.healthnow;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.androidplot.xy.*;
import com.application.healthnow.reporting.DietReportActivity;
import com.application.healthnow.reporting.MedicationReportActivity;
import com.application.healthnow.reporting.ReportsModel;

import java.util.Arrays;
import java.util.SimpleTimeZone;

public class ReportsFragment extends Fragment {
	
	public ReportsFragment() { }
	private XYPlot plot;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_reports, container, false);
		
		ListView reportListView = (ListView) rootView.findViewById(R.id.lv_reportList);
		
		ReportsModel[] reports = {
				new ReportsModel(1, "Diet Report"),
				new ReportsModel(2, "Exercise Report"),
				new ReportsModel(3, "Medication Report"),
				new ReportsModel(4, "Vital Signs Report"),
		};
		
		ArrayAdapter<ReportsModel> reportsAdapter = new ArrayAdapter<ReportsModel>(getActivity(), android.R.layout.simple_list_item_1, reports);
		
		reportListView.setAdapter(reportsAdapter);
		
		reportListView.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent reportIntent = new Intent(getActivity(), DietReportActivity.class);
				startActivity(reportIntent);
			}
		});
		
		return rootView;
	}
}