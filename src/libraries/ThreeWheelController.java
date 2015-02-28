package libraries;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls a robot that has two wheels, or (in most cases) two sides that each
 * act as a unit, as well as a perpendicular strafe wheel for slide/h drive handling.
 */
public class ThreeWheelController extends DriveController {

    protected WheelController leftWheel;
    protected WheelController rightWheel;
    protected WheelController strafeWheel;

    /**
     * Creates a new {@link ThreeWheelController} that drives the specified
     * {@link WheelController}s.
     *
     * @param leftWheel the left {@link WheelController}
     * @param rightWheel the right {@link WheelController}
     * @param strafeWheel the strafe {@link WheelController}
     */
    public ThreeWheelController(WheelController leftWheel, WheelController rightWheel, WheelController strafeWheel) {
        this.leftWheel = leftWheel;
        this.rightWheel = rightWheel;
        this.strafeWheel = strafeWheel;
    }

    /**
     * Drives the left and right {@link WheelController}s at the specified
     * speeds.
     * @param leftSpeed {@inheritDoc}
     * @param rightSpeed {@inheritDoc}
     */
	public void drive(double leftSpeed, double rightSpeed) {
		drive(leftSpeed, rightSpeed, 0);
	}
    /**
     * Sets the speeds of all three {@link WheelController}s independently.
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
        return "Three Wheel Controller";
    }
}
