package org.usfirst.frc.team3946.robot.commands;

import static org.usfirst.frc.team3946.robot.Robot.elevator;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToCrate extends Command {
	
	boolean contact = false;
	
	public DriveToCrate() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.toteContact);
    	requires(Robot.drivetrain);
    	 requires(elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 
    	if (contact == false){
    		contact = Robot.toteContact.contact();
    		elevator.elevate(-.4); 
    		Robot.drivetrain.driveStraight(0.25);
    	}else{
    		elevator.elevate(1);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	contact = false;
    	Robot.drivetrain.getSlideDrive().stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	contact = false;
    	Robot.drivetrain.getSlideDrive().stop();
    }
}
