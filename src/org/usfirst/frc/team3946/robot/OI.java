package org.usfirst.frc.team3946.robot;

import org.usfirst.frc.team3946.robot.commands.*;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private XboxController drive_controller = new XboxController(1);
	private XboxController lift_controller = new XboxController(2);
		Button lockButton;
		Button raiseElevatorButton;
		Button lowerElevatorButton;
		Button fineAdjustButton;

    public OI() {
    	
    	//Controller Buttons
    	raiseElevatorButton = new JoystickButton(lift_controller, XboxController.RIGHT_BUMPER);
    	lowerElevatorButton = new JoystickButton(lift_controller, XboxController.LEFT_BUMPER);
    	fineAdjustButton = new JoystickButton(lift_controller, XboxController.TRIGGERS);
    	lockButton = new JoystickButton(drive_controller, XboxController.A);
    	
        //Instantiate
        raiseElevatorButton.whenPressed(new RaiseElevator(0));
        lowerElevatorButton.whenPressed(new LowerElevator(0));
        fineAdjustButton.whileHeld(new EnableFineAdjustment());
        lockButton.whenPressed(new LockElevation()); 
        
        //SmartDashboard Buttons
        SmartDashboard.putData("Raise Elevator", new RaiseElevator(0));
        SmartDashboard.putData("Lower Elevator", new LowerElevator(0));
        SmartDashboard.putData("Enable Fine Adjustment", new EnableFineAdjustment());
        SmartDashboard.putData("Lock", new LockElevation());
      
    }
    
    public XboxController getXboxController() {
    	return drive_controller;
    }
}

