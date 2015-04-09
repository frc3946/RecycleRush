package org.usfirst.frc.team3946.robot.commands.drive;

import static org.usfirst.frc.team3946.robot.Robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command that resets the gyro value to zero.
 */
public class ResetDistance extends Command {

    public ResetDistance() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivetrain.getSlideDrive().resetEncoder();
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
