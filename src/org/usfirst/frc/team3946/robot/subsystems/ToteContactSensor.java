package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.misc.GetPress;
import org.usfirst.frc.team3946.robot.sensors.ContactSwitch;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToteContactSensor extends Subsystem {
	
//	ContactSwitch left = new ContactSwitch(RobotMap.lTouch);
//	ContactSwitch center = new ContactSwitch(RobotMap.cTouch);
//	ContactSwitch right = new ContactSwitch(RobotMap.rTouch);
	ContactSwitch lCurb = new ContactSwitch(RobotMap.lCurbFeeler);
	ContactSwitch rCurb = new ContactSwitch(RobotMap.rCurbFeeler);

	
	public boolean isPressed() {
		return lCurb.get()
			|| rCurb.get();
//			|| left.get() 
//			|| center.get() 
//			|| right.get();
	}
	
	public void log() {
//		SmartDashboard.putData("Left Touch Pressed?", (ContactSwitch) left);
//		SmartDashboard.putData("Center Touch Pressed?", (ContactSwitch) center);
//		SmartDashboard.putData("Right Touch Pressed?", (ContactSwitch) right);
		SmartDashboard.putData("Left Curb Pressed?", (ContactSwitch) lCurb);
		SmartDashboard.putData("Right Curb Pressed?", (ContactSwitch) rCurb);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new GetPress());
    }
}
