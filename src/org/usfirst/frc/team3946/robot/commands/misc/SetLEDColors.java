package org.usfirst.frc.team3946.robot.commands.misc;

import static org.usfirst.frc.team3946.robot.Robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetLEDColors extends Command {
	int selection;
	
    public SetLEDColors(int input) {
     	requires(lights);
    	selection = input;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	if(isTimedOut()){
//    	//	Rainbow();
//    		setTimeout(5.0);
//    	}
//    }
    protected void execute(){
    		
    	switch (selection) {
    	case 0: 
    		lights.Off();
    		break;
    	case 1: 
    		lights.White();
    		break;
    	case 2:
    		lights.Red();
    		break;
    	case 3:
    		lights.Blue();
    		break;
    	case 4:
    		lights.Green();
    		break;
    	case 5:
    		lights.Yellow();
    		break;
    	case 6:
    		lights.Cyan();
    		break;
    	case 7:
    		lights.Magenta();
    		break;
    	}
    	System.out.println("Selection = " + selection );
//    	selection++;
//    	if(selection > 8){
//    		selection = 0;
//    	}
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
