package org.usfirst.frc.team3946.robot.commands.lift;

import libraries.XboxController;
import static org.usfirst.frc.team3946.robot.Robot.*;
import edu.wpi.first.wpilibj.command.Command;

public class  ElevateWithTriggers extends Command {

    public ElevateWithTriggers() {
        requires(elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	elevator.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	XboxController xbox = oi.getDriveController();
    	double throttle = xbox.getThrottle();
    	elevator.elevate(throttle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; // Runs until interrupted
    }

    // Called once after isFinished returns true
    protected void end() {
    	elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	elevator.stop();
    }
}
