package libraries;

import static java.lang.Math.*;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Heart of the slide drive code. All calculations are done here.
 * 
 * A {@link DriveMethod} that uses a {@link ThreeWheelDriveController} (which
 * controls three speed controllers to drive using a gyro to maintain orientation
 * and drive relative to the field.
 *
 * @see ThreeWheelDriveController
 * @see DriveMethod
 */
public class SlideDrive extends DriveMethod {

    /**
     * The {@link SlideDrive} requires a
     * {@link ThreeWheelDriveController} so the normal
     * {@link DriveMethod#controller} is hidden.
     */
    protected final ThreeWheelDriveController controller;
    
    /**
     * The {@link Gyro} that the {@link SlideDrive} uses for
     * field-oriented driving and keeping the correct orientation.
     */
    protected final Gyro gyro;

    /**
     * The rotation speed below which the rotation PID controller is enabled.
     * When the rotation speed increases above this value the controller is
     * disabled to allow the robot to turn.
     */
    public final double ROTATION_DEADBAND = 0.05;
    
    /**
     * The proportional constant of the PID algorithm. This value is multiplied
     * by the error.
     */
    public static final double ROTATION_P = 0.01;
    
    /**
     * The integral constant of the PID algorithm. This value is multiplied by
     * the sum of the error over time. This is used to make the robot turn more
     * if it is taking a long time to reach its target.
     */
    public static final double ROTATION_I = 0.0;
    
    /**
     * The derivative constant of the PID algorithm. This value is multiplied by
     * the rate of change of the error.
     */
    public static final double ROTATION_D = 0.0;
    
    /**
     * The feed-forward constant of the PID algorithm. This value purely feeds 
     * the command signal forward through the control loop, adding to the loop's output 
     * based directly off of a command input without dependence on prior feedback.
     */
    public static final double ROTATION_F = 0.0;
    private double rotationSpeedPID = 0.0;
    private double gyroOffset = 0.0;

    private final PIDController rotationPIDController;

    /**
     * Creates a new {@link SlideDrive} that controls the specified
     * {@link ThreeWheelDriveController}.
     *
     * @param controller the {@link ThreeWheelDriveController} to control
     * @param gyro the {@link Gyro} to use for orientation correction and
     * field-oriented driving
     */
    public SlideDrive(ThreeWheelDriveController controller, Gyro gyro) {
        super(controller);
        
        this.controller = controller;
        this.gyro = gyro;
        rotationPIDController = new PIDController(
                ROTATION_P,
                ROTATION_I,
                ROTATION_D,
                ROTATION_F,
                gyro,
                new PIDOutput() {
                    public void pidWrite(double output) {
                        rotationSpeedPID = output;
                    }
                }
        );
        SmartDashboard.putData("Rotation PID Controller", rotationPIDController);
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
     * @param gyroAngle the current angle reading from the gyro
     */
    public void drive(double x, double y, double rotation, double gyroAngle) {
        rotation = getRotationPID(rotation);
        drive0(x, y, rotation, gyroAngle);
    }

    /**
     * Moves the robot forward and sideways while rotating at the specified
     * speeds. This moves the robot relative to the robot's current orientation.
     *
     * @param x The sideways (strafe) speed (negative = left, positive = right)
     * @param y The forward speed (negative = backward, positive = forward)
     * @param rotation The speed to rotate at while moving (negative =
     * clockwise, positive = counterclockwise)
     */
    public void drive(double x, double y, double rotation) {
        drive(x, y, rotation, gyro.getAngle() - gyroOffset);
        
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

    public void slideDrive_Orientation(double x, double y, double angle) {
        if (!rotationPIDController.isEnable() || rotationPIDController.getSetpoint() != angle) {
            rotationPIDController.setSetpoint(angle);
            rotationPIDController.enable();
        }
        drive0(x, y, rotationSpeedPID, angle);
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
    private void drive0(double x, double y, double rotation, double gyroAngle) {
        // Send debugging values.
        SmartDashboard.putNumber("Slide X", x);
        SmartDashboard.putNumber("Slide Y", y);
        SmartDashboard.putNumber("Slide Rotation", rotation);
        
        // Compenstate for gyro angle.
        double rotated[] = DriveUtils.rotateVector(x, y, gyroAngle);
        x = rotated[0];
        y = rotated[1];

        double wheelSpeeds[] = new double[3];
        wheelSpeeds[0] = x + y + rotation; // Left speed
        wheelSpeeds[1] = x + y - rotation; // Right speed
        wheelSpeeds[2] = x; // Strafe speed

        DriveUtils.normalize(wheelSpeeds);

        controller.drive(wheelSpeeds[0], wheelSpeeds[1], wheelSpeeds[2]);
    }

    /**
     * Drives the robot at the specified speed in the direction specified as an
     * angle in degrees while rotating. This does not take into account the gyro
     * correction because we do not use it.
     *
     * @param magnitude The speed that the robot should drive in a given
     * direction.
     * @param direction the direction the robot should drive in degrees,
     * independent of rotation
     * @param rotation The rate of rotation for the robot that is completely
     * independent of the magnitude or direction. [-1.0..1.0]
     */
    public void drivePolar(double magnitude, double direction, double rotation) {
        // Normalized for full power along the Cartesian axes.
        magnitude = DriveUtils.limit(magnitude) * sqrt(2.0);
        // The rollers are at 90 degree angles.
        double dirInRad = (direction + 90.0) * PI / 180.0;
        double cosD = cos(dirInRad);
        double sinD = sin(dirInRad);

        double wheelSpeeds[] = new double[3];
        wheelSpeeds[0] = (sinD * magnitude + rotation); // Left speed
        wheelSpeeds[1] = (sinD * magnitude - rotation); // Right speed
        wheelSpeeds[2] = (cosD * magnitude); // Strafe speed

        DriveUtils.normalize(wheelSpeeds);

        controller.drive(wheelSpeeds[0], wheelSpeeds[1], wheelSpeeds[2]);
    }

    /**
     * Gets the corrected rotation speed based on the gyro heading and the
     * expected rate of rotation. If the rotation rate is above a threshold, the
     * gyro correction is turned off.
     *
     * @param rotationSpeed
     */
    private double getRotationPID(double rotationSpeed) {
        // If the controller is already enabled, check to see if it should be 
        // disabled  or kept running. Otherwise check to see if it needs to be 
        // enabled.
        if (rotationPIDController.isEnable()) {
            // If the rotation rate is greater than the deadband disable the PID
            // controller. Otherwise, return the latest value from the
            // controller.
            if (abs(rotationSpeed) >= ROTATION_DEADBAND) {
                rotationPIDController.disable();
            } else {
                return rotationSpeedPID;
            }
        } else {
            // If the rotation rate is less than the deadband, turn on the PID
            // controller and set its setpoint to the current angle.
            if (abs(rotationSpeed) < ROTATION_DEADBAND) {
                gyroOffset = gyro.getAngle();
                rotationPIDController.setSetpoint(gyroOffset);
                rotationPIDController.enable();
            }
        }
        // Unless told otherwise, return the rate that was passed in.
        return rotationSpeed;
    }

    /**
     * Resets the robot's gyro value to zero and resets the PID controller
     * error. This is usually called on command, or after the robot has been
     * disabled to get rid of drift.
     */
    public void resetGyro() {
        // Reset the gyro value to zero
        gyro.reset();
        // Reset the integral component to zero (which also disables the 
        // controller). This is very important because the integral value will
        // have gotten really big and will cause the robot to spin in circles
        // unless it is reset.
        rotationPIDController.reset();
        // Since the gyro value is now zero, the robot should also try to point 
        // in that direction.
        rotationPIDController.setSetpoint(0);
        // Re-enable the controller because it was disabled by calling reset().
        rotationPIDController.enable();
    }
}
