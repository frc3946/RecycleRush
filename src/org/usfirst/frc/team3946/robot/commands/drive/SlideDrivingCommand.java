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
public class SlideDrivingCommand extends Command {

    /**
     * The maximum speed that the robot is allowed to rotate at. The joystick
     * value is scaled down to this value.
     */
    public static final double MAX_ROTATION = 0.5;
    public static final double MAX_STRAFE = 0.6;

    public SlideDrivingCommand() {
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
        
        double scaledX = x;
        if (abs(x) > MAX_STRAFE) {
        	scaledX += x < 0 ? -MAX_STRAFE : MAX_STRAFE;
        }
        
        double scaledZ = z;
        // Scale z down so it is never greater than MAX_ROTATION.
        if (abs(z) > MAX_ROTATION) {
        	scaledZ += z < 0 ? -MAX_ROTATION : MAX_ROTATION;
        }
        
// 		  Send debugging values.
//        SmartDashboard.putNumber("X", scaledX);
//        SmartDashboard.putNumber("Y", y);
//        SmartDashboard.putNumber("Rotation", scaledZ);
//        SmartDashboard.putNumber("Gyro", angle);
        
        if (SlowMoMode.slowMoMode == true) {
        	x *= 0.5;
        	y *= 0.5;
        	z *= 0.5;
        }
        
        drivetrain.getSlideDrive().drive(scaledX, y, scaledZ, angle);
        
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
    	drivetrain.getSlideDrive().disablePID();
        end();
    }
}
