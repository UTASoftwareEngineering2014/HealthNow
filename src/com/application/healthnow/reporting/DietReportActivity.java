package com.application.healthnow.reporting;

import java.util.Arrays;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.application.healthnow.GlobalVariables;
import com.application.healthnow.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

public class DietReportActivity extends Activity 
{
	private XYPlot plot;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
				WindowManager.LayoutParams.FLAG_SECURE);
		setContentView(R.layout.activity_diet_report);

		Integer breakfastCals[] = returnbreakfasthistory();
		if (breakfastCals != null)
			Log.e("br from ex", "" + breakfastCals[0]);
		Integer lunchCals[] = returnlunchhistory();
		if (lunchCals != null)
			Log.e("lunch from ex", "" + lunchCals[0]);
		Integer dinnerCals[] = returndinnerhistory();
		if (dinnerCals != null)
			Log.e("dinner from ex", "" + dinnerCals[0]);

		plot = (XYPlot) findViewById(R.id.dietReportPlot);

		Number[] breakfastCalories = (Number[]) breakfastCals;
		Number[] lunchCalories = (Number[]) lunchCals;
		Number[] dinnerCalories = (Number[]) dinnerCals;

		XYSeries seriesBreakfast = new SimpleXYSeries(Arrays.asList(breakfastCalories),
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Breakfast");

		XYSeries seriesLunch = new SimpleXYSeries(Arrays.asList(lunchCalories),
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Lunch");

		XYSeries seriesDinner = new SimpleXYSeries(Arrays.asList(dinnerCalories),
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Dinner");

		// Create a formatter to use for drawing a series using
		// LineAndPointRenderer
		// and configure it from xml:
		LineAndPointFormatter breakfastSeriesFormat = new LineAndPointFormatter();
		breakfastSeriesFormat.setPointLabelFormatter(new PointLabelFormatter());
		breakfastSeriesFormat.configure(getApplicationContext(),
				R.xml.breakfast_point_formatter);

		// add a new series' to the xyplot:
		plot.addSeries(seriesBreakfast, breakfastSeriesFormat);

		// same as above:
		LineAndPointFormatter lunchSeriesFormat = new LineAndPointFormatter();
		lunchSeriesFormat.setPointLabelFormatter(new PointLabelFormatter());
		lunchSeriesFormat.configure(getApplicationContext(),
				R.xml.lunch_point_formatter);
		
		plot.addSeries(seriesLunch, lunchSeriesFormat);
		
		LineAndPointFormatter dinnerSeriesFormat = new LineAndPointFormatter();
		dinnerSeriesFormat.setPointLabelFormatter(new PointLabelFormatter());
		dinnerSeriesFormat.configure(getApplicationContext(),
				R.xml.dinner_point_formatter);
		
		plot.addSeries(seriesDinner, dinnerSeriesFormat);

		// reduce the number of range labels
		plot.setTicksPerRangeLabel(3);
		plot.getGraphWidget().setDomainLabelOrientation(-45);
	}

	public Integer[] returnbreakfasthistory() {
		SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
		String dayhist = settings.getString("breakfasthistory"
				+ GlobalVariables.userName, "");
		Integer days[] = null;

		if (!(dayhist.equals(""))) {
			String dayarray[] = dayhist.split(" ");

			int size = dayarray.length;
			days = new Integer[size];

			for (int i = 0; i < size; i++) {
				days[i] = Integer.parseInt((dayarray[i].toString()));
			}

		}

		return days;
	}

	public Integer[] returnlunchhistory() {
		SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
		String dayhist = settings.getString("lunchhistory"
				+ GlobalVariables.userName, "");
		Integer days[] = null;

		if (!(dayhist.equals(""))) {
			String dayarray[] = dayhist.split(" ");

			int size = dayarray.length;
			days = new Integer[size];

			for (int i = 0; i < size; i++) {
				days[i] = Integer.parseInt((dayarray[i].toString()));
			}

		}

		return days;
	}

	public Integer[] returndinnerhistory() {
		SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
		String dayhist = settings.getString("dinnerhistory"
				+ GlobalVariables.userName, "");
		Integer days[] = null;

		if (!(dayhist.equals(""))) {
			String dayarray[] = dayhist.split(" ");

			int size = dayarray.length;
			days = new Integer[size];

			for (int i = 0; i < size; i++) {
				days[i] = Integer.parseInt((dayarray[i].toString()));
			}

		}

		return days;
	}
}
