package org.usfirst.frc.team3946.robot.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class ContactSwitch extends DigitalInput {
	
	public ContactSwitch(int channel) {
		super(channel);
	}
	
	public boolean get() {
		return !super.get();
	}
}
