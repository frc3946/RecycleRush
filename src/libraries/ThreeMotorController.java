package libraries;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls a robot that has two wheels, or (in most cases) two sides that each
 * act as a unit, as well as a perpendicular strafe wheel for slide/h drive handling.
 */
public class ThreeMotorController extends DriveController {

    protected MotorController leftWheel;
    protected MotorController rightWheel;
    protected MotorController strafeWheel;

    /**
     * Creates a new {@link ThreeMotorController} that drives the specified
     * {@link MotorController}s.
     *
     * @param leftWheel the left {@link MotorController}
     * @param rightWheel the right {@link MotorController}
     * @param strafeWheel the strafe {@link MotorController}
     */
    public ThreeMotorController(MotorController leftWheel, MotorController rightWheel, MotorController strafeWheel) {
        this.leftWheel = leftWheel;
        this.rightWheel = rightWheel;
        this.strafeWheel = strafeWheel;
    }

    /**
     * Drives the left and right {@link MotorController}s at the specified
     * speeds.
     * @param leftSpeed {@inheritDoc}
     * @param rightSpeed {@inheritDoc}
     */
	public void drive(double leftSpeed, double rightSpeed) {
		drive(leftSpeed, rightSpeed, 0);
	}
    /**
     * Sets the speeds of all three {@link MotorController}s independently.
     *
     * @param leftSpeed the speed of the left wheels
     * @param rightSpeed the speed of the right wheels
     * @param strafeSpeed the speed of the strafe wheel
     */
    public void drive(double leftSpeed, double rightSpeed, double strafeSpeed) {
        leftWheel.set(leftSpeed);
        SmartDashboard.putNumber("Left Power", leftSpeed);
        rightWheel.set(rightSpeed);
        SmartDashboard.putNumber("Right Power", rightSpeed);
        strafeWheel.set(strafeSpeed);
        SmartDashboard.putNumber("Strafe Power", strafeSpeed);

        // Make sure to feed the watchdog!
        safetyHelper.feed();
    }

    /**
     * Stops the robot.
     */
    public void stopMotor() {
        leftWheel.set(0);
        rightWheel.set(0);
        strafeWheel.set(0);
    }

    public String getDescription() {
        return "Three Motor Controller";
    }
}
