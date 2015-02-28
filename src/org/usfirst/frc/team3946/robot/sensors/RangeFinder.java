package org.usfirst.frc.team3946.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class RangeFinder extends AnalogInput{
	
	public RangeFinder(int channel) {
		super(channel);
	}
	
	public double[] rvoltages = {
			0.42,           //0
			0.59,           //1
			0.75,           //2
			1.07,           //3
			1.39,           //4 
			1.67,           //5
			1.9,            //6
			2.2             //7
			
//			0.4384178275,   //0
//			0.6007756594,   //1
//			0.7737746387,   //2
//			0.8951031266,   //3
//			1.009484947,    //4
//			1.156945688,    //5
//			1.326952202,    //6
//			1.519505038     //7
	}; 
	
	public int[] distances = {
			10,             //0
			15, 			//1
			20,				//2
			30,				//3
			40,				//4
			50,				//5
			60,				//6
			70				//7
	};
	
	/**
	 * @param volts voltage read from the potentiometer
	 * @return inches distance from object in inches
	 */
	public double getDistance() {
		double rvolts = 1 / getVoltage();
		//return rvolts;
		//int cm = 0;
		//double inches = 0;
		///**
		int firstPoint = 0;
		int secondPoint = 1;
		boolean timeToProcess = false;
		
		if(rvolts < rvoltages[0]){
			return 0;
		}
		
		if(rvolts > rvoltages[rvoltages.length - 1]){
			return -1;
		}
		
		while (secondPoint < (rvoltages.length)){
			if(rvolts >= rvoltages[firstPoint] &&
				rvolts <= rvoltages[secondPoint]){
				timeToProcess = true;
			}
			
			if(timeToProcess == true){
				double slope = rvoltages[secondPoint] - rvoltages[firstPoint];
				slope = slope / (distances[secondPoint] - distances[firstPoint]);
				double intercept = rvoltages[firstPoint] - distances[firstPoint] * slope;
				double distancesCM = (rvolts - intercept) / slope;
				double distancesIN = distancesCM * .3937;
				return distancesIN;
			}
			else {
				firstPoint++;
				secondPoint++;
			}
			
			
		}
		return 0.0;
	}
}