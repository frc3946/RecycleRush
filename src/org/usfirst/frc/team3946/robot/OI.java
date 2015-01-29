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
		Button raiseElevatorButton;
		Button lowerElevatorButton;
		
    public OI() {
    	
    	//Controller Buttons
    	raiseElevatorButton = new JoystickButton(controller, XboxController.RightBumper);
    	lowerElevatorButton = new JoystickButton(controller, XboxController.LeftBumper);
    	lockButton = new JoystickButton(controller, XboxController.X);
    	
        //Instantiate the Commands
        raiseElevatorButton.whenPressed(new RaiseElevatorLevel());
        lowerElevatorButton.whenPressed(new LowerElevatorLevel());
        lockButton.whenPressed(new LockElevator()); 
        
        //SmartDashboard Buttons
        SmartDashboard.putData("Raise Elevator", new RaiseElevatorLevel());
        SmartDashboard.putData("Lower Elevator", new LowerElevatorLevel());
        SmartDashboard.putData("Floor", new SetElevatorSetpoint(0));
        SmartDashboard.putData("Platform", new SetElevatorSetpoint(1));
        SmartDashboard.putData("Step", new SetElevatorSetpoint(2));
        SmartDashboard.putData("Lock", new LockElevator());
      
    }
    
    public XboxController getXboxController() {
    	return controller;
    }
}

