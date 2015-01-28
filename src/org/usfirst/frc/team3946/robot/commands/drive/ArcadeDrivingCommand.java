package org.usfirst.frc.team3946.robot.commands.drive;

import libraries.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team3946.robot.Robot.*;

/**
 * Command that drives in arcade drive mode.
 */
public class ArcadeDrivingCommand extends Command {

    public ArcadeDrivingCommand() {
        // This command drives, so it requires the drivetrain.
        requires(drivetrain);
    }

    /**
     * Does nothing.
     */
    protected void initialize() {
    }

    /**
     * Updates the robot speed and rotation based on joystick values.
     */
    protected void execute() {
        XboxController xbox = oi.getDriveController();
        drivetrain.getArcadeDrive().arcadeDrive(
            -xbox.getLeftStickY(),
            -xbox.getLeftStickX()
        );
    }

    /**
     * This command never ends on its own but it could be interrupted.
     * @return false
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Stops the drive motors.
     */
    protected void end() {
        drivetrain.getSlideDrive().stop();
    }

    /**
     * Stops the drive motors.
     */
    protected void interrupted() {
        end();
    }
}
