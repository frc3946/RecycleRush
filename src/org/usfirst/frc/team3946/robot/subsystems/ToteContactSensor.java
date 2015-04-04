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
	
	ContactSwitch lCurb = new ContactSwitch(RobotMap.lCurbFeeler);
	ContactSwitch rCurb = new ContactSwitch(RobotMap.rCurbFeeler);
	ContactSwitch front = new ContactSwitch(RobotMap.touchSensor);


	
	public boolean curbPressed() {
		return lCurb.get()
			|| rCurb.get()
			|| front.get(); 
	}
	
	public boolean carryingStack() {
		return front.get();
	}
	
	public void log() {
		SmartDashboard.putData("Left Curb Pressed?", (ContactSwitch) lCurb);
		SmartDashboard.putData("Right Curb Pressed?", (ContactSwitch) rCurb);
		SmartDashboard.putData("Obstruction?", (ContactSwitch) front);

	}

    public void initDefaultCommand() {
        setDefaultCommand(new GetPress());
    }
}
