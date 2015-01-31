package org.usfirst.frc.team3946.robot.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class TouchSensor extends DigitalInput {
	
	public TouchSensor(int channel) {
		super(channel);
	}
	
	public boolean getTouch() {
		return super.get();
	}
}
