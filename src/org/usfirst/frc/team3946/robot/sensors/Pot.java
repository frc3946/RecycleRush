package org.usfirst.frc.team3946.robot.sensors;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class Pot extends AnalogPotentiometer{
	
	public Pot(int channel) {
		super(channel);
	}
	
    public double[] potVolts = {
    		.087,
    		.167,
    		.334,
    		.501,
    		.635,
    		.826,
    };
    
    public int[] feet = {0, 1, 2, 3, 4, 5, 6};
	
	public double getHeight() {
    	int firstPoint = 0;
		int secondPoint = 1;
    	double volts = super.get();
    	while(secondPoint < feet.length){
    		if(volts >= potVolts[firstPoint] ||
				(volts <= potVolts[secondPoint])){
    			double slope = potVolts[secondPoint] - potVolts[firstPoint];
    			slope = slope / (feet[secondPoint] - feet[firstPoint]);
    			double intercept = potVolts[firstPoint] - feet[firstPoint] * slope;
    			double distance = (volts - intercept) / slope;
    			return distance;
    		}
    		else{
    			firstPoint++;
    			secondPoint++;
    		}
		 
    		if(secondPoint >= potVolts.length){
    			return 0.0;
    		}
    	}
    	return 0.0;
	}
}
