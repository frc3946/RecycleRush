package org.usfirst.frc.team3946.robot.commands.misc;

import static org.usfirst.frc.team3946.robot.Robot.vl;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetVisionLights extends Command {

int selection;
	
    public SetVisionLights(int input) {
     	requires(vl);
    	selection = input;
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (selection) {
    	case 0: 
    		vl.Off();
    		break;
    	case 1: 
    		vl.On();
    		break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	vl.Off();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}