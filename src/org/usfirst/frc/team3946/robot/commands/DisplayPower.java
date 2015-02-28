package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */

public class DisplayPower extends Command {

	double maxCurrent1 = 0;
	double maxCurrent2 = 0;
    public DisplayPower() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.pdp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double elevatorCurrent1 = Robot.pdp.getElevatorCurrent1();
    	double elevatorCurrent2 = Robot.pdp.getElevatorCurrent2();
    	maxCurrent1 = Math.max(maxCurrent1, elevatorCurrent1);
    	maxCurrent2 = Math.max(maxCurrent2, elevatorCurrent2);

    	SmartDashboard.putNumber("Max Current 1 ", maxCurrent1);
    	SmartDashboard.putNumber("Max Current 2 ", maxCurrent2);
    	SmartDashbaord.putNumber("Elevator Current 1 ", elevatorCurrent1);
    	SmartDashbaord.putNumber("Elevator Current 2 ", elevatorCurrent2);


    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
