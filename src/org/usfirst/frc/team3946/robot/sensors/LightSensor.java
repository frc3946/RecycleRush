package org.usfirst.frc.team3946.robot.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class LightSensor extends DigitalInput {
	
	public LightSensor (int channel) {
		super(channel);
	}
	
	public boolean getValue(){
		return super.get();
	}
}
