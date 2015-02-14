package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.GetPress;
import org.usfirst.frc.team3946.robot.sensors.TouchSensor;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CurbFeeler extends Subsystem {
	
	TouchSensor touch = new TouchSensor(RobotMap.touch);
	
	public void isPressed() {
		touch.getTouch();
	}
	
	public void log() {
		SmartDashboard.putData("CurbFeeler", (TouchSensor) touch);
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GetPress());
    }
}

