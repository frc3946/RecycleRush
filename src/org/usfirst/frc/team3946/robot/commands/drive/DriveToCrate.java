package org.usfirst.frc.team3946.robot.commands.drive;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToCrate extends Command {
	
	
	public DriveToCrate() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.toteContact);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.driveStraight(0.4);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean contact = Robot.toteContact.isPressed();
    	if (contact == true) {
    		Robot.drivetrain.getSlideDrive().stop();
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
