package org.usfirst.frc.team3946.robot.commands.misc;

import org.usfirst.frc.team3946.robot.Robot;
import static org.usfirst.frc.team3946.robot.Robot.rangefinders;

import edu.wpi.first.wpilibj.command.Command;abstract


/**
 *
 */
public class Square extends Command {

	double leftInches;
	double rightInches;

    public Square() {
    	requires(Robot.drivetrain);
    	requires(Robot.rangefinders);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftInches = rangefinders.getLeftInches();
    	rightInches = rangefinders.getRightInches();
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
