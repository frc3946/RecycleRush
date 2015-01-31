package org.usfirst.frc.team3946.robot.commands.drive;

import static org.usfirst.frc.team3946.robot.Robot.*;
import org.usfirst.frc.team3946.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command that drives the robot right sideways. 
 * Really only used to supplement arcade drive functionality.
 */
public class ManualStrafeRight extends Command {

    public ManualStrafeRight() {
        // This command drives, so it requires the drivetrain.
        requires(drivetrain);
    }

    /**
     * Does nothing.
     */
    protected void initialize() {
    }

    /**
     * Drives the robot sideways right at {@value Drivetrain#STRAFE_SPEED}
     * power.
     */
    protected void execute() {
        drivetrain.getSlideDrive().strafe(Drivetrain.STRAFE_SPEED);
    }

    /**
     * This command never finished, but can be interrupted.
     *
     * @return false
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Stops the motors.
     */
    protected void end() {
        drivetrain.getSlideDrive().stop();
    }

    /**
     * Stops the motors.
     */
    protected void interrupted() {
        end();
    }
}
