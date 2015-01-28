package org.usfirst.frc.team3946.robot.commands.drive;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command that resets the gyro value to zero.
 */
public class ResetGyro extends Command {

    public ResetGyro() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.getSlideDrive().resetGyro();
    }

    /**
     * Does nothing.
     */
    protected void execute() {
    }

    /**
     * This command only needs to run once, so this method always returns true.
     *
     * @return true
     */
    protected boolean isFinished() {
        return true;
    }

    /**
     * Does nothing.
     */
    protected void end() {
    }

    /**
     * Does nothing.
     */
    protected void interrupted() {
    }
}
