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

/*
 * This class generates an XY axis report of the diet statistics.
 * It is meant to give the user feedback how the calories of each meal they are eating
 * is trending over time. It answers the questions if they are staying steady if it is going up, down, etc.
 */
public class TotalCalorieReportActivity extends Activity {
	private XYPlot plot;
	private static final String PREFS_NAME = "MyPrefsFile";

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@SuppressWarnings("null")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
				WindowManager.LayoutParams.FLAG_SECURE);
		setContentView(R.layout.activity_total_calorie_report);

		/*
		 * Getting the data for each meal from our xml file
		 */
		Integer breakfastCals[] = returnbreakfasthistory();
		if (breakfastCals != null)
			Log.e("br from ex", "" + breakfastCals[0]);
		Integer lunchCals[] = returnlunchhistory();
		if (lunchCals != null)
			Log.e("lunch from ex", "" + lunchCals[0]);
		Integer dinnerCals[] = returndinnerhistory();
		if (dinnerCals != null)
			Log.e("dinner from ex", "" + dinnerCals[0]);

		/*
		 * Setting the view to our plot
		 */
		plot = (XYPlot) findViewById(R.id.totalCalorieReportPlot);

		/*
		 * Setting up our series with the values
		 */
		Number[] breakfastCalories = (Number[]) breakfastCals;
		Number[] lunchCalories = (Number[]) lunchCals;
		Number[] dinnerCalories = (Number[]) dinnerCals;
		Number[] totalCalories = new Number[dinnerCalories.length];
		
		for(int i = 0; i < totalCalories.length; i++) {
			totalCalories[i] = breakfastCalories[i].intValue() + lunchCalories[i].intValue() + dinnerCalories[i].intValue(); 
		}
		
		/*
		 * Creating the XYSeries for each meal time
		 */
		XYSeries seriesTotalCalories = new SimpleXYSeries(
				Arrays.asList(totalCalories),
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Total Calories");

		// Create a formatter to use for drawing a series using
		// LineAndPointRenderer
		// and configure it from xml:

		LineAndPointFormatter totalCaloriesFormat = new LineAndPointFormatter();
		totalCaloriesFormat.setPointLabelFormatter(new PointLabelFormatter());
		totalCaloriesFormat.configure(getApplicationContext(),
				R.xml.total_calorie_point_formatter);

		plot.addSeries(seriesTotalCalories, totalCaloriesFormat);

		// reduce the number of range labels
		plot.setTicksPerRangeLabel(3);
		plot.getGraphWidget().setDomainLabelOrientation(-45);
	}

	/*
	 * This method will return the breakfast history from the preferences file
	 * written in the DietFragment class.
	 */
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

	/*
	 * This method will return the lunch calorie history from the preferences
	 * file
	 */
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

	/*
	 * This method will return the dinner history from the preferences file
	 */
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
