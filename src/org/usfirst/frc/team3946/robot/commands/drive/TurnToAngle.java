package org.usfirst.frc.team3946.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import static java.lang.Math.abs;
import static org.usfirst.frc.team3946.robot.Robot.*;

/**
 *
 */
public class TurnToAngle extends Command {
	
	double desiredAngle;
	double distanceToGo;
	double currentAngle;

    public TurnToAngle(double angle) {
    	requires(drivetrain);
    	desiredAngle = angle;

    }

    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speedCoefficient;
    	currentAngle = drivetrain.gyro.getAngle();	
    	distanceToGo = desiredAngle - currentAngle;
    	if(abs(distanceToGo) > 45){
    		speedCoefficient = 1;
    	} else {
    		speedCoefficient = .5;
    	}
    	//distanceToGo = distanceToGo / (abs(distanceToGo));
    	drivetrain.rotate(.5 * speedCoefficient);
    	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(abs(distanceToGo) <= 2.0){
    		return false;
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
