package libraries;

import static java.lang.Math.*;
import static org.usfirst.frc.team3946.robot.Robot.drivetrain;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Heart of the slide drive code. All calculations are done here.
 * 
 * A {@link DriveMethod} that uses a {@link ThreeMotorController} (which
 * controls three speed controllers to drive using a gyro to maintain orientation
 * and drive relative to the field.
 *
 * @see ThreeMotorController
 * @see DriveMethod
 */
public class SlideDrive extends DriveMethod {

    /**
     * The {@link SlideDrive} requires a
     * {@link ThreeMotorController} so the normal
     * {@link DriveMethod#controller} is hidden.
     */
    protected final ThreeMotorController controller;

    protected final Encoder leftEncoder;
    protected final Encoder rightEncoder;
    
    public final double SPEED_DEADBAND = 0.9;
    public static final double SPEED_P = 0.007;
    public static final double SPEED_I = 0.0;
    public static final double SPEED_D = 0.0;
    public static final double SPEED_F = 0.0;
    private double speedPID = 0.0;
    private double encoderOffset = 0.0;
    private double leftCurrentMotorSpeed = 0.0;
    private double rightCurrentMotorSpeed = 0.0;

    private final PIDController speedPIDController;

    /**
     * Creates a new {@link SlideDrive} that controls the specified
     * {@link ThreeMotorController}.
     */
	public SlideDrive(ThreeMotorController controller, Encoder leftEncoder, Encoder rightEncoder) {
        super(controller);
        
        this.controller = controller;
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;
        speedPIDController = new PIDController(
        		SPEED_P,
        		SPEED_I,
        		SPEED_D,
        		SPEED_F,
                leftEncoder, 
                new PIDOutput() {
                    public void pidWrite(double output) {
                        speedPID = output;
                    }
                }
        );
        //SmartDashboard.putData("Rotation PID Controller", rotationPIDController);
    }

    /**
     * Moves the robot forward and sideways while rotating at the specified
     * speeds. This method uses the specified angle to implement field oriented
     * controls.
     *
     * @param x The sideways (strafe) speed (negative = left, positive = right)
     * @param y The forward speed (negative = backward, positive = forward)
     * @param rotation The speed to rotate at while moving (negative =
     * clockwise, positive = counterclockwise)
     */
    public void drive(double x, double y, double rotation) {
        //y = getSpeedPID(y);
        drive0(x, y, rotation);    
    }
    
    /**
     * Moves the robot forward and sideways at the specified speeds.
     *
     * @param x The sideways (strafe) speed (negative = left, positive = right)
     * @param y The forward speed (negative = backward, positive = forward)
     *
     */
    public void drive(double x, double y) {
        drive(x, y, 0);
    }

    /**
     * Moves the robot sideways at the specified speed.
     *
     * @param speed The speed and direction to strafe (negative = left, positive =
     * right)
     */
    public void strafe(double speed) {
        drive(speed, 0);
    }
    
    public void driveSetSpeed(double x, double y, double rotation) {
        if (!speedPIDController.isEnable() || speedPIDController.getSetpoint() != y) {
            speedPIDController.setSetpoint(y);
            speedPIDController.enable();
        }
        drive0(x, y, rotation);
    }

    /**
     * Real implementation of slide cartesian driving. This method does not
     * include gyro orientation correction and it is only called from with this
     * class.
     *
     * @param x The sideways (strafe) speed (negative = left, positive = right)
     * @param y The forward speed (negative = backward, positive = forward)
     * @param rotation The speed to rotate at while moving (positive =
     * clockwise, negative = counterclockwise)
     * @param gyroAngle the current angle reading from the gyro
     */
    private void drive0(double x, double y, double rotation) {
        double wheelSpeeds[] = new double[2];
        wheelSpeeds[0] = y + rotation; // Left speed
        wheelSpeeds[1] = y - rotation; // Right speed
                
        double error[] = new double[2];
        error[0] = y + rotation - drivetrain.getLeftEncoder().getRate();
        error[1] = y - rotation - drivetrain.getRightEncoder().getRate();
//		if (y * SPEED_P * error >= y) {
//			controller.drive(0, y, 0);
//		} else {
//			controller.drive(0, y * SPEED_P * error, 0);
//		}
        
        DriveUtils.normalize(wheelSpeeds);

        controller.drive(wheelSpeeds[0], wheelSpeeds[1], x);
    }

    /**
     * Gets the corrected drive speed based on the encoder reading and the
     * expected rate of wheel rotation.
     *
     * @param driveSpeed
     */
    private double getSpeedPID(double driveSpeed) {
        // If the controller is already enabled, check to see if it should be 
        // disabled  or kept running. Otherwise check to see if it needs to be 
        // enabled.
        if (speedPIDController.isEnable()) {
            // If the rotation rate is greater than the deadband disable the PID
            // controller. Otherwise, return the latest value from the
            // controller.
            if (abs(driveSpeed) >= SPEED_DEADBAND) {
                speedPIDController.disable();
            } else {
                return speedPID;
            }
        } else {
            // If the wheel rotation rate is less than the deadband, turn on the PID
            // controller and set its setpoint to the desired speed.
        	encoderOffset = leftEncoder.getRate();
        	encoderOffset -= rightEncoder.getRate();
        	speedPIDController.setSetpoint(encoderOffset);
        	speedPIDController.enable();
        }
        // Unless told otherwise, return the rate that was passed in.
        return driveSpeed;
    }

    /**
     * Resets the robot's gyro value to zero and resets the PID controller
     * error. This is usually called on command, or after the robot has been
     * disabled to get rid of drift.
     */
    public void resetEncoder() {
        // Reset the encoder values to zero.
        leftEncoder.reset();
        rightEncoder.reset();
        // Reset the integral component to zero (which also disables the 
        // controller). This is very important because the integral value will
        // have gotten really big and will cause the robot to spin in circles
        // unless it is reset.
        speedPIDController.reset();
        // Since the encoder values are now zero, the robot should stop.
        speedPIDController.setSetpoint(0);
        // Re-enable the controller because it was disabled by calling reset().
        speedPIDController.enable();
    }
    
    public void disablePID() {
    	speedPIDController.disable();
    }
}
