package org.usfirst.frc.team3946.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import static java.lang.Math.abs;
import static org.usfirst.frc.team3946.robot.Robot.*;

/**
 *
 */
public class TurnCommand extends Command {
	
	double desiredAngle;
	double distanceToGo;
	double currentAngle;

    public TurnCommand(double angle) {
    	requires(drivetrain);
    	desiredAngle = angle;

    }

    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = drivetrain.gyro.getAngle();	
    	distanceToGo = desiredAngle - currentAngle;
    	drivetrain.getSlideDrive().slideDrive_Orientation(0, 0, desiredAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(abs(distanceToGo) <= 1.5){
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
