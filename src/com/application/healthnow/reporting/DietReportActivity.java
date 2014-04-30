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

public class DietReportActivity extends Activity {
	private XYPlot plot;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
		setContentView(R.layout.activity_diet_report);
		int br[]=returnbreakfasthistory();
		if(br!=null)Log.e("br from ex",""+ br[0]);
		plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
		
		Number[] series1Numbers = {1,8,5,2,7,4};
		Number[] series2Numbers = {4,6,3,8,2,10};
		
		XYSeries series1 = new SimpleXYSeries(Arrays.asList(series1Numbers),
												SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
												"Series1");
		
		XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers),
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
				"Series2");
		
		// Create a formatter to use for drawing a series using LineAndPointRenderer
        // and configure it from xml:
        LineAndPointFormatter series1Format = new LineAndPointFormatter();
        series1Format.setPointLabelFormatter(new PointLabelFormatter());
        series1Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf1);
 
        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);
 
        // same as above:
        LineAndPointFormatter series2Format = new LineAndPointFormatter();
        series2Format.setPointLabelFormatter(new PointLabelFormatter());
        series2Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf2);
        plot.addSeries(series2, series2Format);
 
        // reduce the number of range labels
        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);	
	}
	
	
	public int[] returnbreakfasthistory()
	{
		SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
		String dayhist=settings.getString("breakfasthistory"+GlobalVariables.userName, "");
		int days[]=null;
		if(!(dayhist.equals("")))
		{
			
				
			String dayarray[]=dayhist.split(" ");
			int size=dayarray.length;
			days=new int[size];
			for(int i=0;i<size;i++)
			{
				days[i]=Integer.parseInt((dayarray[i].toString()));
			}
			
		}
		return days;
		
	}
}
