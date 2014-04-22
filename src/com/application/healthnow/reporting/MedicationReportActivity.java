package com.application.healthnow.reporting;

import java.io.InterruptedIOException;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import com.androidplot.Plot;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.PointLabeler;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYStepMode;
import com.application.healthnow.R;
import com.application.healthnow.R.id;
import com.application.healthnow.R.layout;
import com.application.healthnow.R.menu;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MedicationReportActivity extends Activity {

	private class PlotUpdate implements Observer {
		@SuppressWarnings("rawtypes")
		Plot plot;
		
		@SuppressWarnings("rawtypes")
		public PlotUpdate(Plot plot) {
			this.plot = plot;
		}
		 
		@Override
		public void update(Observable observable, Object data) {
			plot.redraw();	
		}
	}
	
	private XYPlot dynamicPlot;
	private XYPlot staticPlot;
	private PlotUpdate plotUpdater;
	private Thread graphThread;
	MedicationDataSource data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medication_report);

		dynamicPlot = (XYPlot) findViewById(R.id.dynamicPlot);
		
		plotUpdater = new PlotUpdate(dynamicPlot);
		
		dynamicPlot.getGraphWidget().setDomainValueFormat(new DecimalFormat("0"));
		
		MedicationDataSource data = new MedicationDataSource();
		MedicationDynamicSeries sine1Series = new MedicationDynamicSeries(data, 0, "Sine 1");
		MedicationDynamicSeries sine2Series = new MedicationDynamicSeries(data, 1, "Sine 2");
		
		dynamicPlot.addSeries(sine1Series, new LineAndPointFormatter(Color.rgb(0, 0, 200), null, Color.rgb(0, 0, 80), (PointLabelFormatter) null));
		
		LineAndPointFormatter formatter1 = new LineAndPointFormatter(Color.rgb(0, 0, 200), null, Color.rgb(0, 0, 80), (PointLabelFormatter) null);
		formatter1.getFillPaint().setAlpha(220);
		dynamicPlot.addSeries(sine1Series, formatter1);
		dynamicPlot.setGridPadding(5, 0, 5, 0);
		
		// hook up the plotUpdater to the data model:
        data.addObserver(plotUpdater);
 
        dynamicPlot.setDomainStepMode(XYStepMode.SUBDIVIDE);
        dynamicPlot.setDomainStepValue(sine1Series.size());
 
        // thin out domain/range tick labels so they dont overlap each other:
        dynamicPlot.setTicksPerDomainLabel(5);
        dynamicPlot.setTicksPerRangeLabel(3);
 
        // freeze the range boundaries:
        dynamicPlot.setRangeBoundaries(-100, 100, BoundaryMode.FIXED);
 
        // kick off the data generating thread:
        new Thread(data).start();
	}

	@Override
	public void onResume() {
		graphThread = new Thread(data);
		graphThread.start();
		super.onResume();
	}
	
	@Override
	public void onPause() {
		data.stopThread();
		super.onPause();
	}
}
