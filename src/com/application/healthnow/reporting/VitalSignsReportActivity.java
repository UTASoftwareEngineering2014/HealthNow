package com.application.healthnow.reporting;

import java.util.Arrays;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.application.healthnow.DietFragment;
import com.application.healthnow.GlobalVariables;
import com.application.healthnow.R;
import com.application.healthnow.UtilityFunctions;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

public class VitalSignsReportActivity extends Activity {
	private XYPlot plot;
	private UtilityFunctions utilityFunctions = new UtilityFunctions();
	private static final String PREFS_NAME = "MyPrefsFile";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		DietReportActivity dietReportActivity = new DietReportActivity();
		DietFragment dietFragment = new DietFragment();
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
				WindowManager.LayoutParams.FLAG_SECURE);
		setContentView(R.layout.activity_diet_report);

		try {
			int[] breakfastCalories = dietReportActivity.returnBreakfastHistory();
			Log.d("Breakfast Calories", breakfastCalories.toString());
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
		

		// Number[] dinnerCalories = (Number[])dinnerInt;
		// Log.d("Checking values", dinnerCalories.toString());

//		try {
//			int[] dinnerInt = dietFragment.returnBreakfastHistory();
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		}

		plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

		Number[] timeNumbers = { 1, 8, 5, 2, 7, 4 };
		Number[] calorieNumbers = { 1, 8, 5, 2, 7, 4 };

		XYSeries timeNumbersSeries = new SimpleXYSeries(
				Arrays.asList(timeNumbers),
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series1");

		XYSeries series2 = new SimpleXYSeries(Arrays.asList(calorieNumbers),
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");

		// Create a formatter to use for drawing a series using
		// LineAndPointRenderer
		// and configure it from xml:
		LineAndPointFormatter series1Format = new LineAndPointFormatter();
		series1Format.setPointLabelFormatter(new PointLabelFormatter());
		series1Format.configure(getApplicationContext(),
				R.xml.line_point_formatter_with_plf1);

		// add a new series' to the xyplot:
		plot.addSeries(timeNumbersSeries, series1Format);

		// same as above:
		LineAndPointFormatter series2Format = new LineAndPointFormatter();
		series2Format.setPointLabelFormatter(new PointLabelFormatter());
		series2Format.configure(getApplicationContext(),
				R.xml.line_point_formatter_with_plf2);
		plot.addSeries(series2, series2Format);

		// reduce the number of range labels
		plot.setTicksPerRangeLabel(3);
		plot.getGraphWidget().setDomainLabelOrientation(-45);
		int[] breakfastTest = dietReportActivity.returnBreakfastHistory();
		Log.d("DietFragment Test", breakfastTest.toString());
	}

	public Integer[] returndayhistory() {
		SharedPreferences settings = getBaseContext().getSharedPreferences(
				PREFS_NAME, 0);
		String dayhist = settings.getString("dayhistory"
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

	public int[] returnBreakfastHistory() {
		SharedPreferences settings = getSharedPreferences(
				PREFS_NAME, 0);
		String dayhist = settings.getString("breakfasthistory"
				+ GlobalVariables.userName, "");
		int days[] = null;

		if (!(dayhist.equals(""))) {
			String dayarray[] = dayhist.split(" ");
			int size = dayarray.length;
			days = new int[size];

			for (int i = 0; i < size; i++) {
				days[i] = Integer.parseInt((dayarray[i].toString()));
			}
		}

		return days;
	}

	public int[] returnLunchHistory() {
		SharedPreferences settings = getSharedPreferences(
				PREFS_NAME, 0);
		String dayhist = settings.getString("lunchhistory"
				+ GlobalVariables.userName, "");
		int days[] = null;

		if (!(dayhist.equals(""))) {
			String dayarray[] = dayhist.split(" ");
			int size = dayarray.length;
			days = new int[size];

			for (int i = 0; i < size; i++) {
				days[i] = Integer.parseInt((dayarray[i].toString()));
			}

		}

		return days;
	}

	public Integer[] returnDinnerHistory() {
		SharedPreferences settings = getBaseContext().getSharedPreferences(
				PREFS_NAME, 0);
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

	private Number[] getCalories() {

		int[] dinnerHistory = utilityFunctions.returnDinnerHistory();
		Integer[] dinnerHistoryObj = new Integer[dinnerHistory.length];
		Number[] dinnerHistoryArray = (Number[]) dinnerHistoryObj;

		return dinnerHistoryArray;
	}
}