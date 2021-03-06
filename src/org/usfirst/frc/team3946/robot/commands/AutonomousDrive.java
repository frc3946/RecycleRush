package org.usfirst.frc.team3946.robot.commands;

import static org.usfirst.frc.team3946.robot.Robot.drivetrain;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousDrive extends Command {
double v; 
	 public AutonomousDrive(double timeout, double speed) {
	 // Use requires() here to declare subsystem dependencies
    	 requires(drivetrain);
    	 setTimeout(timeout);
    	 v = speed;
	 }
    	 
	 // Called just before this Command runs the first time
	 protected void initialize() {
    		 drivetrain.getGyro().reset();
	 }
	 // Called repeatedly when this Command is scheduled to run
	 protected void execute() {
		 //drivetrain.driveStraight(v);
		 drivetrain.getSlideDrive().drive(0, v, 0);
	 }
	 // Make this return true when this Command no longer needs to run execute()
	 protected boolean isFinished() {
		 return isTimedOut();
	 }
	 // Called once after isFinished returns true
	 protected void end() {
		 //drivetrain.driveStraight(0);
		 drivetrain.getSlideDrive().drive(0, v, 0);
	 }
	 // Called when another command which requires one or more of the same
	 // subsystems is scheduled to run
	 protected void interrupted() {
	 }
}
