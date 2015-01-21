package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *	TODO: Add a deadband to prevent unintended motion.
 */
public class Strafe extends Subsystem {
	private SpeedController strafeMotor;

	public Strafe() {
		super();
		strafeMotor = new Talon(3);
		
		// Show motor in the LiveWindow
    	LiveWindow.addActuator("Drivetrain", "Strafe Motor", (Talon) strafeMotor);
    
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new StrafeWithJoystick());
		
	}
	
	public void log() {
		
	}
	
	public void strafe(double move) {
		strafeMotor.set(move);
	}
	
	public void strafe(XboxController controller){
		strafe(controller.getRightStickX());
	}
	
	public void stop() {
		strafeMotor.set(0);
	}
}    
		
