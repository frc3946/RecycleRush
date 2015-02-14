package org.usfirst.frc.team3946.robot.commands.misc;

import edu.wpi.first.wpilibj.command.Command;
import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static org.usfirst.frc.team3946.robot.Robot.*;

/**
 *
 */
public class TurnToAngle extends Command {
	
	double target;
	double gyroReading;
	double offset;
	double error;
	
    public TurnToAngle(double angle) {
    	requires(drivetrain);
    	target = angle;

    }

    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speedCoefficient;
    	gyroReading = drivetrain.gyro.getAngle();	
    	offset = target - gyroReading;
    	offset = error;
    	//Anti-spinning death machine function
    	error -= 360 * floor(0.5 + (error/360));
    	if(abs(error) > 45){
    		speedCoefficient = 1;
    	} else {
    		speedCoefficient = .5;
    	}
    	//distanceToGo = distanceToGo / (abs(distanceToGo));
    	drivetrain.rotate(.5 * speedCoefficient);
    	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(abs(error) <= 2.0){
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
