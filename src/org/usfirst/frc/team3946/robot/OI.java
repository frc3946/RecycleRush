package org.usfirst.frc.team3946.robot;

import libraries.XboxController;

import org.usfirst.frc.team3946.robot.commands.AlignToStack;
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
		
		Button enableSlideControl;
		Button enableArcadeControl;
		Button resetGyro;
		Button strafeLeft;
		Button strafeRight;
		Button driveToCrate;
		Button alignToStack;
		
	public XboxController liftController = new XboxController(1);
		
		Button lowerLift;
		Button raiseLift;
		Button incLiftSetpoint;
		Button decLiftSetpoint;
		
    public OI() {
   		driveController.setDeadband(0.2);
		liftController.setDeadband(0.2);
    	
    	// Drive Control Buttons
    	enableSlideControl = new JoystickButton(driveController, XboxController.Start);
    	enableArcadeControl = new JoystickButton(driveController, XboxController.Back);
    	resetGyro = new JoystickButton(driveController, XboxController.B);
    	driveToCrate = new JoystickButton(driveController, XboxController.A);
<<<<<<< HEAD
    		enableSlideControl.whenPressed(new SlideDrivingCommand());
			enableArcadeControl.whenPressed(new ArcadeDrivingCommand());
	    	resetGyro.whenPressed(new ResetGyro());
	    	driveToCrate.whenPressed(new DriveToCrate());    	
=======
    	alignToStack = new JoystickButton(driveController, XboxController.Y);
	    	enableSlideControl.whenPressed(new SlideDrivingCommand());
			enableArcadeControl.whenPressed(new ArcadeDrivingCommand());
	    	resetGyro.whenPressed(new ResetGyro());
	    	driveToCrate.whenPressed(new DriveToCrate());
	    	alignToStack.whenPressed(new AlignToStack());
    	
>>>>>>> aeb5db708cfbf86e52b298da7bc08340e5a9851a
//    	strafeLeft = new JoystickButton(driveController, XboxController.LeftTrigger);
//    	strafeRight = new JoystickButton(driveController, XboxController.RightTrigger);
//    		strafeLeft.whileActive(new ManualStrafeLeft());
//    		strafeRight.whileActive(new ManualStrafeRight());
    	
    	// Lift Control Buttons
    	lowerLift = new JoystickButton(driveController, XboxController.LeftTrigger);
    	raiseLift = new JoystickButton(driveController, XboxController.RightTrigger);
		lowerLift.whileActive(new ElevateWithTriggers());
		raiseLift.whileActive(new ElevateWithTriggers());
    	
		incLiftSetpoint = new JoystickButton(driveController, XboxController.RightBumper);
		decLiftSetpoint = new JoystickButton(driveController, XboxController.LeftBumper);
		incLiftSetpoint.whenPressed(new IncLiftSetpoint());
		decLiftSetpoint.whenPressed(new DecLiftSetpoint());

        // SmartDashboard Buttons			
        SmartDashboard.putData("Reset Gyro", new ResetGyro());
        SmartDashboard.putData("Enable Slide", new SlideDrivingCommand());
        SmartDashboard.putData("Enable Arcade", new ArcadeDrivingCommand());
        
        SmartDashboard.putData("Raise Elevator", new IncLiftSetpoint());
        SmartDashboard.putData("Lower Elevator", new DecLiftSetpoint());
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