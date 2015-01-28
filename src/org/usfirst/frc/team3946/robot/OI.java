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
		
		Button enableFieldCentricControl;
		Button enableArcadeControl;
		Button resetGyroButton;
		Button strafeLeftButton;
		Button strafeRightButton;
		
	public XboxController liftController = new XboxController(1);;
		
		Button raiseElevatorButton;
		Button lowerElevatorButton;
		Button lockElevatorButton;
		
    public OI() {
   		driveController.setDeadband(0.2);
		liftController.setDeadband(0.2);
    	
    	// Drive Control Buttons
    	enableFieldCentricControl = new JoystickButton(driveController, XboxController.Start);
    		enableFieldCentricControl.whenPressed(new FieldCentricDrivingCommand());
    	enableArcadeControl = new JoystickButton(driveController, XboxController.Back);
    		enableArcadeControl.whenPressed(new ArcadeDrivingCommand());
    	resetGyroButton = new JoystickButton(driveController, XboxController.B);
    		resetGyroButton.whenPressed(new ResetGyro());
    	strafeLeftButton = new JoystickButton(driveController, XboxController.LeftTrigger);
    		strafeLeftButton.whileHeld(new ManualStrafeLeft());
    	strafeRightButton = new JoystickButton(driveController, XboxController.RightTrigger);
    		strafeRightButton.whileHeld(new ManualStrafeRight());
    	
    	// Lift Control Buttons
        raiseElevatorButton = new JoystickButton(liftController, XboxController.RightBumper);
        	raiseElevatorButton.whenPressed(new RaiseElevatorLevel());
        lowerElevatorButton = new JoystickButton(liftController, XboxController.LeftBumper);
        	lowerElevatorButton.whenPressed(new LowerElevatorLevel());
        lockElevatorButton = new JoystickButton(liftController, XboxController.A);
        	lockElevatorButton.whenPressed(new LockElevatorPosition()); 

        // SmartDashboard Buttons
        SmartDashboard.putData("Reset Gyro", new ResetGyro());
        SmartDashboard.putData("Enable Field Control", new FieldCentricDrivingCommand());
        SmartDashboard.putData("Enable Arcade Control", new ArcadeDrivingCommand());
        
        SmartDashboard.putData("Raise Elevator", new RaiseElevatorLevel());
        SmartDashboard.putData("Lower Elevator", new LowerElevatorLevel());
        SmartDashboard.putData("Lock", new LockElevatorPosition());
        SmartDashboard.putData("Floor", new SetElevatorSetpoint(0));
        SmartDashboard.putData("Platform", new SetElevatorSetpoint(1));
        SmartDashboard.putData("Step", new SetElevatorSetpoint(2));
       
    }
    
    public XboxController getDriveController() {
    	return driveController;
    }
    public XboxController getLiftController() {
    	return liftController;
    }
}