package org.usfirst.frc.team3946.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3946.robot.*;

/**
 *
 */
public class Turn extends Command {
	
	double desiredAngle;
	double distanceToGo;
	double currentAngle;

    public Turn(double newDesiredAngle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	desiredAngle = newDesiredAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.drivetrain.getHeading();
    	distanceToGo = desiredAngle - currentAngle;
    	double turnSpeed = distanceToGo / 40;
    	Robot.drivetrain.turn(turnSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(distanceToGo) <= 2){
    		return true;
    	} else {
    		return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
