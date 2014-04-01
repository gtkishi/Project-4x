package com.shared;


public class Model implements Runnable {
	public MovingUnit unit;
	public boolean isBlack;
	public int timeStep = 200; // Number of milliseconds per simulation step
	
	public Model() {
		unit = new MovingUnit( 0.0, 0.0, 1.0 );
		isBlack = false;
	}
	
	public void run() {
		long lastEndTime = System.currentTimeMillis();
		while(true) {
			long startTime = lastEndTime;
			
			simulateFrame();
			
			//System.out.println(unit.location.x + " " + unit.location.y);
			
			while(System.currentTimeMillis() < startTime + timeStep);
			lastEndTime = lastEndTime + timeStep;
		}
		
	}
	
	public void simulateFrame() {
		unit.simulateTimeStep(timeStep);
	}

	public void setTarget( double x, double y ) {
		unit.setTarget(x, y);
	}
	
	public MovingUnit getUnit() {
		return unit;
	}
	
}
