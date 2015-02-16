package org.usfirst.frc.team3946.robot.commands.lift;

import libraries.XboxController;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class  ElevateWithTriggers extends Command {

    public ElevateWithTriggers() {
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	XboxController j = Robot.oi.getDriveController();
    	double throttle = j.getThrottle();
    	Robot.elevator.elevate(throttle);
    	SmartDashboard.putNumber("Lift Throttle", throttle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; // Runs until interrupted
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevator.stop();
    }
}
