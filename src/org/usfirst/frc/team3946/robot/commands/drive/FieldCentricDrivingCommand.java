package org.usfirst.frc.team3946.robot.commands.drive;

import libraries.XboxController;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that drives in slide drive mode with gyro orientation for
 * field-centric control.
 */
public class FieldCentricDrivingCommand extends Command {

    /**
     * The joystick axis value below which returns zero. 
     * This is to prevent small, accidental movements of the joystick 
     * from affecting precise control.
     */
    public final double DEADBAND = 0.2;
    /**
     * The maximum speed that the robot is allowed to drive at. The joystick
     * value is scaled down to this value.
     */
    public static final double MAX_DRIVE = 0.8;
    /**
     * The maximum speed that the robot is allowed to strafe at. The joystick
     * value is scaled down to this value.
     */
    public static final double MAX_STRAFE = 0.8;
    /**
     * The maximum speed that the robot is allowed to rotate at. The joystick
     * value is scaled down to this value.
     */
    public static final double MAX_ROTATION = 0.5;

    public FieldCentricDrivingCommand() {
        // This command drives, so it requires the drivetrain.
        requires(Robot.drivetrain);
    }

    /**
     * Does nothing.
     */
    protected void initialize() {
    	Robot.drivetrain.getSlideDrive().resetGyro();
    }

    /**
     * Updates the robot movement based on joystick values.
     */
    protected void execute() {
        XboxController xbox = Robot.oi.getDriveController();
        // Store the axis values.
        double x = xbox.getLeftStickX();
        double y = -xbox.getLeftStickY();
        double z = xbox.getRightStickX();
        double angle = Robot.drivetrain.gyro.getAngle();
        
        // Implement a deadband in order to filter out small, accidental 
        // movement of the joysticks.
        double scaledX = x;
        double scaledY = y;
        double scaledZ = z;
        
        if (Math.abs(x) < DEADBAND) {
            scaledX = 0;
        } else {
            // Scale x down so it is never greater than MAX_STRAFE.
            scaledX += x < 0 ? DEADBAND : -DEADBAND;
            scaledX = (scaledX / (1.0 - DEADBAND)) * MAX_STRAFE;
        }
        if (Math.abs(y) < DEADBAND) {
            scaledY = 0;
        } else {
            // Scale y down so it is never greater than MAX_DRIVE.
            scaledY += y < 0 ? DEADBAND : -DEADBAND;
            scaledY = (scaledY / (1.0 - DEADBAND)) * MAX_DRIVE;
        }
        if (Math.abs(z) < DEADBAND) {
            scaledZ = 0;
        } else {
            // Scale z down so it is never greater than MAX_ROTATION.
            scaledZ += z < 0 ? DEADBAND : -DEADBAND;
            scaledZ = (scaledZ / (1.0 - DEADBAND)) * MAX_ROTATION;
        }
        // Send debugging values.
        SmartDashboard.putNumber("Lateral", scaledX);
        SmartDashboard.putNumber("Longitudinal", scaledY);
        SmartDashboard.putNumber("Rotation", scaledZ);
        // Actually drive the robot using the scaled values for x, y, and z. 
        Robot.drivetrain.getSlideDrive().slideDrive_Cartesian(
            scaledX,
            scaledY,
            scaledZ,
            angle
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
        Robot.drivetrain.getSlideDrive().stop();
    }

    /**
     * Stops the drive motors.
     */
    protected void interrupted() {
        end();
    }
}
