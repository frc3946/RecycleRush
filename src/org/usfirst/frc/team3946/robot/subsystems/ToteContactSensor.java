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
	
	ContactSwitch front = new ContactSwitch(RobotMap.frontSensor);
	
	public boolean contact() {
		return front.get();
	}
	
	public void log() {
		SmartDashboard.putData("Carrying Stack?", (ContactSwitch) front);

	}

    public void initDefaultCommand() {
        setDefaultCommand(new GetPress());
    }
}
