package org.usfirst.frc.team3946.robot;

import libraries.XboxController;

import org.usfirst.frc.team3946.robot.commands.drive.*;
import org.usfirst.frc.team3946.robot.commands.lift.*;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public XboxController driveController = new XboxController(0);
		
    public OI() {
   		driveController.setDeadband(0.2);
    	
    	// Drive Control Buttons
    	Button enableSlideControl = new JoystickButton(driveController, XboxController.Start);
    	Button enableArcadeControl = new JoystickButton(driveController, XboxController.Back);
    	Button resetGyro = new JoystickButton(driveController, XboxController.B);
    	//driveToCrate = new JoystickButton(driveController, XboxController.A);
    		enableSlideControl.whenPressed(new SlideDrivingCommand());
			enableArcadeControl.whenPressed(new ArcadeDrivingCommand());
	    	resetGyro.whenPressed(new ResetGyro());
	    	//driveToCrate.whenPressed(new DriveToCrate());    	
    	
//    	strafeLeft = new JoystickButton(driveController, XboxController.LeftTrigger);
//    	strafeRight = new JoystickButton(driveController, XboxController.RightTrigger);
//    		strafeLeft.whileActive(new ManualStrafeLeft());
//    		strafeRight.whileActive(new ManualStrafeRight());
    	
    	// Lift Control Buttons
	    Button lowerLift = new JoystickButton(driveController, XboxController.LeftTrigger);
	    Button raiseLift = new JoystickButton(driveController, XboxController.RightTrigger);
			lowerLift.whileActive(new ElevateWithTriggers());
			raiseLift.whileActive(new ElevateWithTriggers());
    	
		Button incLiftSetpoint = new JoystickButton(driveController, XboxController.RightBumper);
		Button decLiftSetpoint = new JoystickButton(driveController, XboxController.LeftBumper);
			incLiftSetpoint.whenPressed(new IncLiftSetpoint());
			decLiftSetpoint.whenPressed(new DecLiftSetpoint());

		Button switchOverride = new JoystickButton(driveController, XboxController.B);
			switchOverride.whenPressed(new SwitchOverride());
			
        // SmartDashboard Buttons			
        SmartDashboard.putData("Reset Gyro", new ResetGyro());
        SmartDashboard.putData("Enable Slide", new SlideDrivingCommand());
        SmartDashboard.putData("Enable Arcade", new ArcadeDrivingCommand());
       
        SmartDashboard.putData("Switch Override", new SwitchOverride());
//        SmartDashboard.putData("Raise Elevator", new IncLiftSetpoint());
//        SmartDashboard.putData("Lower Elevator", new DecLiftSetpoint());
//        SmartDashboard.putData("Floor", new SetElevatorSetpoint(0));
//        SmartDashboard.putData("Platform", new SetElevatorSetpoint(1));
//        SmartDashboard.putData("Step", new SetElevatorSetpoint(2));
       
    }
    
    public XboxController getDriveController() {
    	return driveController;
    }
}