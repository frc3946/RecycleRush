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

	private XboxController controller = new XboxController(1);
		Button lockButton;
		Button setElevatorButton;
		Button fineAdjustButton;

    public OI() {
    	
    	//Controller Buttons
    	setElevatorButton = new JoystickButton(controller, XboxController.TRIGGERS);
    	fineAdjustButton = new JoystickButton(controller, XboxController.RIGHT_STICK);
    	lockButton = new JoystickButton(controller, XboxController.A);
    	
        //Instantiate the Commands
        setElevatorButton.whenPressed(new SetElevatorSetpoint(0));
        fineAdjustButton.whileHeld(new EnableFineAdjustment());
        lockButton.whenPressed(new LockElevator()); 
        
        //SmartDashboard Buttons
        SmartDashboard.putData("Elevator Top", new SetElevatorSetpoint(0));
        SmartDashboard.putData("Enable Fine Adjustment", new EnableFineAdjustment());
        SmartDashboard.putData("Lock", new LockElevator());
      
    }
    
    public XboxController getXboxController() {
    	return controller;
    }
}

