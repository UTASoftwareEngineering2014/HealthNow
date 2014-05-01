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
import com.application.healthnow.reporting.BreakfastReportActivity;
import com.application.healthnow.reporting.DinnerReportActivity;
import com.application.healthnow.reporting.LunchReportActivity;
import com.application.healthnow.reporting.ReportsModel;
import com.application.healthnow.reporting.TotalCalorieReportActivity;
import com.application.healthnow.reporting.VitalSignsReportActivity;

import java.util.Arrays;
import java.util.SimpleTimeZone;

public class ReportsFragment extends Fragment {

	public ReportsFragment() {
	}

	private XYPlot plot;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_reports, container,
				false);

		ListView reportListView = (ListView) rootView
				.findViewById(R.id.lv_reportList);

		ReportsModel[] reports = { new ReportsModel(1, "Breakfast Report"),
				new ReportsModel(2, "Lunch Report"),
				new ReportsModel(3, "Dinner Report"),
				new ReportsModel(4, "Total Calories Report")};

		ArrayAdapter<ReportsModel> reportsAdapter = new ArrayAdapter<ReportsModel>(
				getActivity(), android.R.layout.simple_list_item_1, reports);

		reportListView.setAdapter(reportsAdapter);

		reportListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				if (position == 0) {
					Intent breakfastReportIntent = new Intent(getActivity(),
							BreakfastReportActivity.class);
					startActivity(breakfastReportIntent);
				} else if (position == 1) {
					Intent lunchReportIntent = new Intent(getActivity(),
							LunchReportActivity.class);
					startActivity(lunchReportIntent);
				} else if (position == 2) {
					Intent dinnerReportIntent = new Intent(getActivity(),
							DinnerReportActivity.class);
					startActivity(dinnerReportIntent);
				} else if(position == 3) {
					Intent totalCalorieIntent = new Intent(getActivity(),
							TotalCalorieReportActivity.class);
					startActivity(totalCalorieIntent);
				}
			}
		});

		return rootView;
	}
}