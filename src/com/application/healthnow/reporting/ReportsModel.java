package com.application.healthnow.reporting;

public class ReportsModel {
	private String reportName;
	private int id;
	
	public ReportsModel()
	{
		super();
	}
	
	public ReportsModel(int id, String reportName)
	{
		super();
		this.id = id;
		this.reportName = reportName;
	}
	
	
	@Override
	public String toString()
	{
		return this.id + ". " + this.reportName;
	}

}
