package com.application.healthnow;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.androidplot.xy.*;

import java.util.Arrays;
import java.util.SimpleTimeZone;

public class ReportsFragment extends Fragment {
	
	public ReportsFragment() { }
	private XYPlot plot;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
		
		View rootView = inflater.inflate(R.layout.fragment_reports, container, false);
		
		plot = (XYPlot) rootView.findViewById(R.id.mySimpleXYPlot);
		
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
        series1Format.configure(getActivity(),
                R.xml.line_point_formatter_with_plf1);
 
        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);
 
        // same as above:
        LineAndPointFormatter series2Format = new LineAndPointFormatter();
        series2Format.setPointLabelFormatter(new PointLabelFormatter());
        series2Format.configure(getActivity(),
                R.xml.line_point_formatter_with_plf2);
        plot.addSeries(series2, series2Format);
 
        // reduce the number of range labels
        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);	
		
		return rootView;
	}
}