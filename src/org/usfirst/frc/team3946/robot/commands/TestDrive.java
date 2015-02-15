package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestDrive extends Command {
	double speed = .5;
	int step = 0;
	boolean done = false;
    public TestDrive() {
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(1);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(isTimedOut() == false){
    		return;
    	}
    	step++;
    	setTimeout(5);
    	switch(step){
    	case 1:
    		Robot.drivetrain.setMotor(speed, 0, 0);
    		break;
    	case 2:
    		Robot.drivetrain.setMotor(-speed, 0, 0);
    		break;
    	case 3:
    		Robot.drivetrain.setMotor(0, speed, 0);
    		break;
    	case 4:
    		Robot.drivetrain.setMotor(0, -speed, 0);
    		break;
    	case 5:
    		Robot.drivetrain.setMotor(0, 0, speed);
    		break;
    	case 6:
    		Robot.drivetrain.setMotor(0, 0, -speed);
    		break;
    	case 7:
    		Robot.drivetrain.setMotor(0, 0, 0);
    		done = true;
    		break; 
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    		return done;
        }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
