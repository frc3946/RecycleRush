package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.misc.SetVisionLights;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class VisionLights extends Subsystem {
	private Relay r1 = new Relay(RobotMap.spike1);

    public void initDefaultCommand() {
        setDefaultCommand(new SetVisionLights(0));
    }
    
    public void On() {
    	r1.set(Relay.Value.kForward); //Left right alignment
    }
    
	public void Off() {
		r1.set(Relay.Value.kOff);
	}
}

