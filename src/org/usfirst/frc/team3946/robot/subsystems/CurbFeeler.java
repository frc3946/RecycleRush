package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.misc.GetPress;
import org.usfirst.frc.team3946.robot.sensors.TouchSensor;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CurbFeeler extends Subsystem {
	
	TouchSensor left = new TouchSensor(RobotMap.lTouch);
	TouchSensor center = new TouchSensor(RobotMap.cTouch);
	TouchSensor right = new TouchSensor(RobotMap.rTouch);
	
	public void isPressed() {
		left.get();
		center.get();
		right.get();
	}
	
	public void log() {
		SmartDashboard.putData("Left Pressed?", (TouchSensor) left);
		SmartDashboard.putData("Center Pressed?", (TouchSensor) center);
		SmartDashboard.putData("Right Pressed?", (TouchSensor) right);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new GetPress());
    }
}
