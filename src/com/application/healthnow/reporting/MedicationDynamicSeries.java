package com.application.healthnow.reporting;

import com.androidplot.xy.XYSeries;

public class MedicationDynamicSeries implements XYSeries {
	private MedicationDataSource medicationDataSource;
	private int seriesIndex;
	private String title;
	
	public MedicationDynamicSeries(MedicationDataSource medicationDataSource,
									int seriesIndex, String title) {
		this.medicationDataSource = medicationDataSource;
		this.seriesIndex = seriesIndex;
		this.title = title;
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Number getX(int index) {
		return medicationDataSource.getX(seriesIndex, index);
	}

	@Override
	public Number getY(int index) {
		return medicationDataSource.getY(seriesIndex, index);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
