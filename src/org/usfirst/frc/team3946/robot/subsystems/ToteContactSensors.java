package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.misc.GetPress;
import org.usfirst.frc.team3946.robot.sensors.ContactSwitch;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToteContactSensors extends Subsystem {
	
	ContactSwitch left = new ContactSwitch(RobotMap.lTouch);
	ContactSwitch center = new ContactSwitch(RobotMap.cTouch);
	ContactSwitch right = new ContactSwitch(RobotMap.rTouch);
	
	public boolean isPressed() {
		return left.get() 
			|| center.get() 
			|| right.get();
	}
	
	public void log() {
		SmartDashboard.putData("Left Pressed?", (ContactSwitch) left);
		SmartDashboard.putData("Center Pressed?", (ContactSwitch) center);
		SmartDashboard.putData("Right Pressed?", (ContactSwitch) right);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new GetPress());
    }
}
