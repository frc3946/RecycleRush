package libraries;

/**
 * This class acts as a bridge between a HID (joystick, gamepad) and a
 * {@link DriveController}. It takes input and uses it to control the robot
 * through different algorithms. This class cannot be used directly and must be
 * extended to provide functionality. A drive algorithm does not have to worry
 * about where the wheels are in most cases, because this is taken care of by
 * the {@link DriveController}.
 *
 * @see ArcadeDrive
 * @see SlideDrive
 * 
 * @see DriveController
 */
public abstract class DriveMethod {

    protected DriveController controller;

    /**
     * Creates a new {@link DriveMethod} that controls the specified
     * {@link DriveController}.
     *
     * @param controller the drive controller to use
     */
    public DriveMethod(DriveController controller) {
        this.controller = controller;
    }

    /**
     * Stops the robot. This just calls {@link DriveController#stopMotor()}.
     */
    public void stop() {
        controller.stopMotor();
    }
}
