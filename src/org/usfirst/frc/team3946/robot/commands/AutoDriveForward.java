package org.usfirst.frc.team3946.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3946.robot.Robot; 

/**
 *
 */
public class AutoDriveForward extends Command {
	
	double currentDistance;
	
	public AutoDriveForward(double timeout) {
	// Use requires() here to declare subsystem dependencies
	requires(Robot.drivetrain);
	setTimeout(timeout);
	}
	
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.drive(1, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
