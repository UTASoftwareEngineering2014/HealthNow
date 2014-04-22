package com.application.healthnow.reporting;

import java.util.Observable;
import java.util.Observer;

public class MedicationDataSource implements Runnable {

	class DataSourceObserver extends Observable {
		@Override 
		public void notifyObservers() {
			setChanged();
			super.notifyObservers();
		}
	}
	
	private static final int MAX_AMP_SEED = 100;
    private static final int MIN_AMP_SEED = 10;
    private static final int AMP_STEP = 5;
    public static final int SINE1 = 0;
    public static final int SINE2 = 1;
    private static final int SAMPLE_SIZE = 30;
    private int phase = 0;
    private int sinAmp = 20;
    private boolean keepRunning = false;
    private DataSourceObserver notifier;
    {
    	notifier = new DataSourceObserver();
    }
    
    public void stopThread() {
    	keepRunning = false;
    }
    
    
	@Override
	public void run() {
		try {
			keepRunning = true;
			boolean isRising = true;
			while(true) {
				Thread.sleep(50);
				phase++;
				if (sinAmp >= MAX_AMP_SEED) {
					isRising = false;
				} else if (sinAmp <= MIN_AMP_SEED) {
					isRising = true;
				}
				
				if (isRising) {
					sinAmp += AMP_STEP;
				} else {
					sinAmp -= AMP_STEP;
				}
				
				notifier.notifyObservers();
				
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getItemCount(int series) {
		return 30;
	}
	
	public Number getX(int series, int index) {
		if (index >= SAMPLE_SIZE) {
			throw new IllegalArgumentException();
		}
		
		return index;
	}
	
	public Number getY(int series, int index) {
		if(index >= SAMPLE_SIZE) {
			throw new IllegalArgumentException();
		}
		
		double amplitude = sinAmp * Math.sin(index + phase + 4);
		switch (series) {
		case SINE1:
			return amplitude;
		case SINE2:
			return -amplitude;
		default:
			throw new IllegalArgumentException();
				
		}
	}		
	
	public void addObserver(Observer observer) {
		notifier.addObserver(observer);
	}
	
	public void removeObserver(Observer observer) {
		notifier.deleteObserver(observer);
	}
}
