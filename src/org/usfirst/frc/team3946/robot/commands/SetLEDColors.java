package org.usfirst.frc.team3946.robot.commands;

import static org.usfirst.frc.team3946.robot.Robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetLEDColors extends Command {
	int selection;
	
    public SetLEDColors(int input) {
    	requires(lights);
    	input = selection;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	System.out.println("Selection = " + selection );
    	
      	if (selection == 0) {
    		lights.Off();
    	}
       	if (selection == 1) {
    		lights.White();
    	}
    	if (selection == 2) {
    		lights.Red();
    	}
    	if (selection == 3) {
    		lights.Blue();
      	}
    	if (selection == 4) {
    		lights.Green();
    	}
    	if (selection == 5) {
    		lights.Yellow();
       	}
    	if (selection == 6) {
    		lights.Cyan();
    	}
    	if (selection == 7) {
    		lights.Magenta();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	lights.Off();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
