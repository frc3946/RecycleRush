package org.usfirst.frc.team3946.robot.sensors;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch extends DigitalInput{
	
	public LimitSwitch(int channel) {
		super(channel);
	}
	
    public boolean get() { 	
    	
    	return !super.get();
    }
}
