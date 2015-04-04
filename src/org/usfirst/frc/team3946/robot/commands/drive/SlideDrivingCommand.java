package org.usfirst.frc.team3946.robot.commands.drive;

import libraries.XboxController;
import static org.usfirst.frc.team3946.robot.Robot.*;
import edu.wpi.first.wpilibj.command.Command;
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

    protected void initialize() {
    	drivetrain.getSlideDrive().resetEncoder();
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
        double rate = ((drivetrain.leftEncoder.getRate() + drivetrain.rightEncoder.getRate()) / 2); 
        
        double scaledX = x;
        if (abs(x) > MAX_STRAFE) {
        	scaledX += x < 0 ? -MAX_STRAFE : MAX_STRAFE;
        }
        
        double scaledZ = z;
        // Scale z down so it is never greater than MAX_ROTATION.
        if (abs(z) > MAX_ROTATION) {
        	scaledZ += z < 0 ? -MAX_ROTATION : MAX_ROTATION;
        }
        
//        if (toteContact.contact() == true) {
//        	drivetrain.slow = true;
//        } else if (toteContact.contact() == false) {
//        	drivetrain.slow = false;
//        }
        
         if (drivetrain.slow == true) {
        	scaledX *= 0.5;
        	y *= 0.5;
        	scaledZ *= 0.5;
        }
        
        drivetrain.getSlideDrive().drive(scaledX, y, scaledZ);
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
