package org.usfirst.frc.team3946.robot.commands.drive;

import libraries.XboxController;

import static org.usfirst.frc.team3946.robot.Robot.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static java.lang.Math.abs;

/**
 * Command that drives in slide drive mode with gyro orientation for
 * field-centric control.
 */
public class FieldCentricDrivingCommand extends Command {

    /**
     * The maximum speed that the robot is allowed to rotate at. The joystick
     * value is scaled down to this value.
     */
    public static final double MAX_ROTATION = 0.6;

    public FieldCentricDrivingCommand() {
        // This command drives, so it requires the drivetrain.
        requires(drivetrain);
    }

    /**
     * Does nothing.
     */
    protected void initialize() {
    	drivetrain.getSlideDrive().resetGyro();
    }

    /**
     * Updates the robot movement based on joystick values.
     */
    protected void execute() {
        XboxController xbox = oi.getDriveController();
        // Store the axis values.
        double x = xbox.getLeftStickX();
        double y = -xbox.getLeftStickY();
        double z = xbox.getRightStickX();
        double angle = drivetrain.gyro.getAngle();
        
        double scaledZ = z;
        // Scale z down so it is never greater than MAX_ROTATION.
        if (abs(z) > MAX_ROTATION) scaledZ += z < 0 ? -MAX_ROTATION : MAX_ROTATION;
        
        // Send debugging values.
        SmartDashboard.putNumber("Lateral", x);
        SmartDashboard.putNumber("Longitudinal", y);
        SmartDashboard.putNumber("Rotation", scaledZ);
        
        drivetrain.getSlideDrive().slideDrive_Cartesian(x, y, scaledZ, angle);
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
